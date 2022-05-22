package uma.taw.ubayspring.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.taw.ubayspring.dto.users.ClientDTO;
import uma.taw.ubayspring.dto.users.PasswordChangeDTO;
import uma.taw.ubayspring.dto.users.ProductDTO;
import uma.taw.ubayspring.service.UsersService;
import uma.taw.ubayspring.types.GenderEnum;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

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


}
