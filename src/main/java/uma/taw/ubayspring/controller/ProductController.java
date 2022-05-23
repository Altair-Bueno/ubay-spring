package uma.taw.ubayspring.controller;

import org.checkerframework.checker.nullness.Opt;
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
import uma.taw.ubayspring.service.AuthService;
import uma.taw.ubayspring.service.products.ProductService;
import uma.taw.ubayspring.types.KindEnum;
import uma.taw.ubayspring.wrapper.MinioWrapperService;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    AuthService authService;

    @Autowired
    MinioWrapperService minioWrapperService;
    
    private ProductClientDTO getSession(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        ProductClientDTO cliente;


        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            boolean isAdmin = false;
            if(user.getAuthorities().contains(KindEnum.admin)) isAdmin = true;
            cliente = productService.loginDTOtoClientDTO(new LoginDTO(user.getUsername(), isAdmin ? KindEnum.admin : KindEnum.client));
        } else {
            cliente = null;
        }
        
        return cliente;
    }

    @GetMapping("")
    public String getIndex(Model model,
                           @RequestParam Optional<String> page,
                           @RequestParam Optional<String> category,
                           @RequestParam Optional<String> name,
                           @RequestParam Optional<String> favOwnedFilter){
        
        ProductClientDTO cliente = getSession();

        String productName = name.isPresent() ? name.get() : "";
        String categoryParam = (category.isEmpty() || (category.isPresent() && category.get().equals("--"))) ? "0" : category.get();
        String pageParam = page.isPresent() ? page.get() : "1";
        String favOwnedFilterParam = favOwnedFilter.isPresent() ? favOwnedFilter.get() : "";

        ProductsDTO productDTOS = productService.getProductsList(cliente, productName, categoryParam, favOwnedFilterParam, pageParam);
        
        model.addAttribute("category-list", productService.categories());
        model.addAttribute("categoryFilter", Integer.parseInt(categoryParam));
        model.addAttribute("nameFilter", productName);
        model.addAttribute("product-tam", productDTOS.getSize());
        model.addAttribute("product-list", productDTOS.getProductsList());
        model.addAttribute("user", cliente != null);
        model.addAttribute("page", pageParam);

        return "product/index";
    }

    @GetMapping("/new")
    public String getNew(Model model){
        List<ProductCategoryDTO> cats = productService.categories();
        ProductClientDTO cliente = getSession();

        model.addAttribute("cats", cats);
        model.addAttribute("user", cliente);
        return "product/new";
    }

    @PostMapping("/new")
    public String postNew(Model model,
                          RedirectAttributes redirectAttributes,
                          @RequestParam Optional<Integer> category,
                          @RequestParam Optional<String> vendor,
                          @RequestParam Optional<String> title,
                          @RequestParam Optional<String> description,
                          @RequestParam Optional<String> price,
                          @RequestPart Optional<Part> img) throws IOException, ServletException {

        int categoryId = category.get();
        if (vendor.isEmpty()) throw new RuntimeException("ERROR: Intentelo de nuevo.");
        int vendorId = Integer.parseInt(vendor.get());
        String descriptionParam = description.get();
        String titleParam = title.get();
        Double outprice = Double.parseDouble(price.get());
        Part file = img.get();
        String imgName = "";

        // IMAGEN
        if (!file.getSubmittedFileName().equals("")) {
            InputStream inputStream = file.getInputStream();


            try {
                imgName = minioWrapperService.uploadObject(inputStream);
            } catch (Exception e) {
                throw new ServletException(e.getStackTrace().toString());
            }
        }

        ProductDTO p = productService.createProduct(
                titleParam,
                descriptionParam,
                outprice,
                imgName,
                new Timestamp(new Date().getTime()),
                vendorId,
                categoryId
        );

        redirectAttributes.addAttribute("id", p.getId());
        //referrer to item created
        return "redirect:item";
    }

    @GetMapping("/item")
    public String getItem(Model model,
                          @RequestParam Optional<String> id
    ){
        ProductClientDTO cliente = getSession();
        
        Integer idParam = Integer.parseInt(id.get());
        ProductDTO productDTO = productService.findByIdProduct(idParam);
        ProductBidDTO highestBid = productService.getHighestBid(idParam);

        if (cliente == null) {
            model.addAttribute("isFav", null);
        } else {
            boolean isUserFav = productService.isProductUserFavourite(cliente, idParam);
            model.addAttribute("isFav", isUserFav);
        }

        model.addAttribute("user", cliente);
        model.addAttribute("product", productDTO);
        model.addAttribute("highestBid", highestBid);
        model.addAttribute("isAdmin", cliente != null && cliente.getKind().equals(KindEnum.admin));

        return "product/item";

    }
}
