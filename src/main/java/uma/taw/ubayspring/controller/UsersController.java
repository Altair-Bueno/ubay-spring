package uma.taw.ubayspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.notifications.BidsDTO;
import uma.taw.ubayspring.dto.products.ProductClientDTO;
import uma.taw.ubayspring.dto.users.ClientDTO;
import uma.taw.ubayspring.dto.users.PasswordChangeDTO;
import uma.taw.ubayspring.dto.users.ProductDTO;
import uma.taw.ubayspring.service.UsersService;
import uma.taw.ubayspring.service.products.ProductService;
import uma.taw.ubayspring.types.GenderEnum;
import uma.taw.ubayspring.types.KindEnum;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    ProductService productService;

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

    @GetMapping("")
    public String client(Model model,
                         @RequestParam(defaultValue = "") String id,
                         @RequestParam(defaultValue = "") String name,
                         @RequestParam(defaultValue = "") String lastName,
                         @RequestParam(defaultValue = "") String address,
                         @RequestParam(defaultValue = "") String city,
                         @RequestParam(defaultValue = "") String gender) {

        List<ClientDTO> clientDTOList = usersService.users(id, name, lastName, address, city, gender);

        model.addAttribute("search-user", clientDTOList);
        return "users/users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        usersService.deleteUser(id);
        return "redirect:";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam String id,
                         @RequestParam String name,
                         @RequestParam String lastName,
                         @RequestParam GenderEnum gender,
                         @RequestParam String address,
                         @RequestParam String city,
                         @RequestParam Date birthDate,
                         @RequestParam (defaultValue = "") String edited){
        usersService.modifyUser(id, name, lastName, gender, address, city, birthDate);

        if (edited.equals("")) {
            return "users/modify";
        } else {
            usersService.modifyUser(id, name, lastName, gender, address, city, birthDate);
            return "redirect:";
        }
    }

    @GetMapping("/addFavourite")
    public String addFavourite(@RequestParam String productID, @RequestParam String clientID){
        usersService.addFavProduct(productID, clientID);
        return "redirect:products";
    }

    @GetMapping("/deleteFavourite")
    public String deleteFavourite(@RequestParam String productID, @RequestParam String clientID){
        usersService.deleteFavProduct(productID, clientID);
        return "redirect:products";
    }

    @GetMapping("/products")
    public String products(Model model){
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<ProductDTO> favouriteProducts = usersService.products(user);


        model.addAttribute("clientID", usersService.getClientID(user));
        model.addAttribute("favourite-products-list", favouriteProducts);

        return "users/products";
    }

    @GetMapping("/passwordChangeLink")
    public String passwordChangeLink(Model model, @RequestParam String id){
        PasswordChangeDTO passwordChangeDTO = usersService.passwordChange(id);

        model.addAttribute("passwordChangeID", passwordChangeDTO.getPasswordChangeID());
        model.addAttribute("username", passwordChangeDTO.getUsername());

        return "users/passwordChange";
    }

    /**
     *
     * @author Francisco Javier Hernández Martín
     *
     */
    @GetMapping("/notifications")
    @PostMapping("/notifications")
    public String processNotifications(Model model
                                       ){

        var client = getSession();

        HashMap<BidsDTO, Boolean> notifications = usersService.getNotifications(client.getId());

        model.addAttribute("notifications", notifications);
        return "users/notifications";
    }


}
