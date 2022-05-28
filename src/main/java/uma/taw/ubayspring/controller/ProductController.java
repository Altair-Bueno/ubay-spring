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
import uma.taw.ubayspring.dto.products.*;
import uma.taw.ubayspring.dto.products.ProductForm.ProductFormParamsDTO;
import uma.taw.ubayspring.dto.products.index.FavOwnedDTO;
import uma.taw.ubayspring.dto.products.index.ListsDTO;
import uma.taw.ubayspring.dto.products.index.ParamsDTO;
import uma.taw.ubayspring.dto.products.index.ProductIndexDTO;
import uma.taw.ubayspring.keys.ProductKeys;
import uma.taw.ubayspring.service.AuthService;
import uma.taw.ubayspring.service.products.ProductService;
import uma.taw.ubayspring.types.KindEnum;
import uma.taw.ubayspring.wrapper.MinioWrapperService;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
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
    public String getIndex(Model model, @ModelAttribute("productModel") ParamsDTO productModel) {
        ListsDTO listas = new ListsDTO();
        listas.setCategoryList(productService.categories());
        listas.setProductList(productService.getProductsList(productModel, getSession()).getProductsList());
        listas.setFavOwnedFilterOptions(List.of(new FavOwnedDTO[]{
                new FavOwnedDTO("favFilter", "Favoritos"),
                new FavOwnedDTO("ownedFilter", "Mis productos")
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
    public String postNew(@ModelAttribute("productModel") ProductFormParamsDTO productModel, RedirectAttributes redirectAttributes) throws ServletException, IOException {

        Integer id = productService.createProduct(productModel, getSession());

        redirectAttributes.addAttribute("id", id);
        return "redirect:item";
    }

    @GetMapping("/item")
    public String processItem(Model model,
                              @RequestParam Integer id
    ) {
        ProductClientDTO cliente = getSession();

        ProductDTO productDTO = productService.findByIdProduct(id);
        ProductBidDTO highestBid = productService.getHighestBid(id);

        if (cliente == null) {
            model.addAttribute("isFav", null);
        } else {
            boolean isUserFav = productService.isProductUserFavourite(cliente, id);
            model.addAttribute("isFav", isUserFav);
        }

        model.addAttribute("user", cliente);
        model.addAttribute("product", productDTO);
        model.addAttribute("highestBid", highestBid);
        model.addAttribute("isAdmin", cliente != null && cliente.getKind().equals(KindEnum.admin));

        return "product/item";
    }

    @GetMapping("/update")
    public String getUpdate(Model model,
                            @RequestParam String id
    ) {
        model.addAttribute("product", productService.findByIdProduct(Integer.parseInt(id)));
        model.addAttribute("cats", productService.categories());
        return "product/update";
    }

    @PostMapping("/update")
    public String postUpdate(Model model,
                             RedirectAttributes redirectAttributes,
                             @RequestParam String productId,
                             @RequestParam String category,
                             @RequestParam String title,
                             @RequestParam Optional<String> description,
                             @RequestParam Optional<String> status,
                             @RequestParam String price,
                             @RequestPart Optional<Part> img
    ) throws ServletException, IOException {

        int categoria = Integer.parseInt(category);
        int idParam = Integer.parseInt(productId);

        String estado = status.isEmpty() ? null : status.get();
        String desc = description.isEmpty() ? "" : description.get();
        String titulo = title;
        Double precio = Double.parseDouble(price);
        Part file = img.isEmpty() ? null : img.get();

        productService.updateProduct(idParam, categoria, estado, desc, titulo, precio, file);

        redirectAttributes.addAttribute("id", idParam);

        return "redirect:item";
    }

    @GetMapping("/delete")
    @PostMapping("/delete")
    public String processDelete(Model model,
                                @RequestParam String id
    ) throws MinioException {
        int idParam = Integer.parseInt(id);
        productService.deleteProduct(idParam);
        return "redirect:";
    }
}
