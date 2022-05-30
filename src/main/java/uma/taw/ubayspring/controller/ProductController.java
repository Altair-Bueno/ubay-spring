package uma.taw.ubayspring.controller;

import com.jlefebure.spring.boot.minio.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.bids.NewBidsDTO;
import uma.taw.ubayspring.dto.products.*;
import uma.taw.ubayspring.dto.products.ProductForm.ProductFormParamsDTO;
import uma.taw.ubayspring.dto.products.index.FavOwnedDTO;
import uma.taw.ubayspring.dto.products.index.ListsDTO;
import uma.taw.ubayspring.dto.products.index.ParamsDTO;
import uma.taw.ubayspring.keys.ProductKeys;
import uma.taw.ubayspring.service.AuthService;
import uma.taw.ubayspring.service.products.ProductService;
import uma.taw.ubayspring.types.KindEnum;
import uma.taw.ubayspring.wrapper.MinioWrapperService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    AuthService authService;

    @Autowired
    MinioWrapperService minioWrapperService;

    private String localizedString(HttpServletRequest request, String key){
        Locale.setDefault(Locale.ENGLISH);
        ResourceBundle bundle = ResourceBundle.getBundle("messages", request.getLocale());
        return bundle.getString(key);
    }

    private ProductClientDTO getSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        ProductClientDTO cliente;


        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            boolean isAdmin = false;
            if (user.getAuthorities().contains(KindEnum.admin)) isAdmin = true;
            cliente = productService.loginDTOtoClientDTO(new LoginDTO(user.getUsername(), isAdmin ? KindEnum.admin : KindEnum.client));
        } else {
            cliente = null;
        }

        return cliente;
    }

    @GetMapping
    public String getIndex(Model model, @ModelAttribute("productModel") ParamsDTO productModel, HttpServletRequest request) {
        ListsDTO listas = new ListsDTO();
        listas.setCategoryList(productService.categories());
        listas.setProductList(productService.getProductsList(productModel, getSession()).getProductsList());
        listas.setFavOwnedFilterOptions(List.of(new FavOwnedDTO[]{
                new FavOwnedDTO("favFilter", localizedString(request, "product.index.filter.favourites")),
                new FavOwnedDTO("ownedFilter", localizedString(request, "product.index.filter.owned"))
        }));

        model.addAttribute("client", getSession());
        model.addAttribute("pageLimit", (int) Math.ceil((double) listas.getProductList().size() / ProductKeys.productsPerPageLimit));
        model.addAttribute("listas", listas);
        model.addAttribute("productModel", productModel);
        return "product";
    }

    @GetMapping("/new")
    public String getNew(@ModelAttribute("productModel") ProductFormParamsDTO productModel, Model model) {
        List<ProductCategoryDTO> categoryList = productService.categories();
        ProductClientDTO client = getSession();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("client", client);
        return "product/new";
    }

    @PostMapping(path = "/new", consumes = {"multipart/form-data"})
    public String postNew(@ModelAttribute("productModel") ProductFormParamsDTO productModel,
                          RedirectAttributes redirectAttributes
    ) throws ServletException, IOException {

        Integer id = productService.createProduct(productModel, getSession());

        redirectAttributes.addAttribute("id", id);
        return "redirect:item";
    }

    @GetMapping("/item")
    public String processItem(Model model,
                              @ModelAttribute("productModel") ProductFormParamsDTO productModel,
                              @ModelAttribute("newBidModel") NewBidsDTO newBidModel,
                              @RequestParam Integer id,
                              HttpServletRequest request
    ) {
        ProductClientDTO cliente = getSession();
        ProductDTO product = productService.findByIdProduct(id);
        ProductBidDTO highestBid = productService.getHighestBid(id);

        productModel.setCategory(product.getCategory().getId());
        productModel.setProductId(id);
        productModel.setTitle(product.getTitle());
        productModel.setDescription(product.getDescription());
        productModel.setPrice(product.getOutPrice());
        productModel.setStatus(product.getCloseDate() == null ? localizedString(request, "activeStatus") : localizedString(request, "closedStatus"));
        productModel.setVendor(product.getVendor());

        if (cliente == null) {
            model.addAttribute("isFav", null);
        } else {
            boolean isUserFav = productService.isProductUserFavourite(cliente, id);
            model.addAttribute("isFav", isUserFav);
        }

        model.addAttribute("productModel", productModel);
        model.addAttribute("client", cliente);
        model.addAttribute("highestBid", highestBid);
        model.addAttribute("imgId", product.getImages());

        return "product/item";
    }

    @GetMapping("/update")
    public String getUpdate(Model model,
                            @ModelAttribute("productModel") ProductFormParamsDTO productModel,
                            @RequestParam String id,
                            HttpServletRequest request
    ) {
        ProductDTO product = productService.findByIdProduct(Integer.parseInt(id));

        productModel.setProductId(product.getId());
        productModel.setCategory(product.getCategory().getId());
        productModel.setTitle(product.getTitle());
        productModel.setDescription(product.getDescription());
        productModel.setPrice(product.getOutPrice());
        productModel.setStatus(product.getCloseDate() == null ? localizedString(request, "activeStatus") : localizedString(request, "closedStatus"));

        model.addAttribute("productModel", productModel);
        model.addAttribute("imageId", product.getImages());
        model.addAttribute("categoryList", productService.categories());
        return "product/update";
    }

    @PostMapping(path = "/update", consumes = {"multipart/form-data"})
    public String postUpdate(RedirectAttributes redirectAttributes,
                             @ModelAttribute("productModel") ProductFormParamsDTO productModel
    ) throws ServletException, IOException {

        productService.updateProduct(productModel);

        redirectAttributes.addAttribute("id", productModel.getProductId());
        return "redirect:item";
    }

    @GetMapping("/delete")
    @PostMapping("/delete")
    public String processDelete(@RequestParam String id) throws MinioException {
        int idParam = Integer.parseInt(id);
        productService.deleteProduct(idParam);
        return "redirect:";
    }
}
