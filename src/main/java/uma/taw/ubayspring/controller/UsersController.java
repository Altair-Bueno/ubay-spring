package uma.taw.ubayspring.controller;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.bids.BidsParamsDTO;
import uma.taw.ubayspring.dto.bids.NewBidsDTO;
import uma.taw.ubayspring.dto.notifications.BidsDTO;
import uma.taw.ubayspring.dto.products.ProductClientDTO;
import uma.taw.ubayspring.dto.users.ClientDTO;
import uma.taw.ubayspring.dto.users.FilterUsersDTO;
import uma.taw.ubayspring.dto.users.PasswordChangeDTO;
import uma.taw.ubayspring.dto.users.ProductDTO;
import uma.taw.ubayspring.service.BidService;
import uma.taw.ubayspring.service.UsersService;
import uma.taw.ubayspring.service.products.ProductService;
import uma.taw.ubayspring.types.GenderEnum;
import uma.taw.ubayspring.types.KindEnum;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    ProductService productService;

    @Autowired
    BidService bidService;

    private ProductClientDTO getProductSession() {
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

    private LoginDTO getSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();


        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            boolean isAdmin = false;
            if (user.getAuthorities().contains(KindEnum.admin)) isAdmin = true;
            return new LoginDTO(user.getUsername(), isAdmin ? KindEnum.admin : KindEnum.client);
        } else {
            return null;
        }
    }
    @GetMapping
    public String getUsers(Model model, @ModelAttribute FilterUsersDTO filterUsersDTO) {
        List<ClientDTO> clientDTOList = usersService.users(filterUsersDTO);
        model.addAttribute("search-user", clientDTOList);
        model.addAttribute("filterUsersDTO", filterUsersDTO);
        return "users/users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        usersService.deleteUser(id);
        return "redirect:";
    }

    @GetMapping("/modify")
    public ClientDTO getModify(@ModelAttribute ClientDTO clientDTO){
        return clientDTO;
    }

    @PostMapping("/modify")
    public String postModify(@ModelAttribute ClientDTO clientDTO){
        usersService.modifyUser(clientDTO);
        return "redirect:";
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
    public String processNotifications(Model model){
        var client = getProductSession();

        HashMap<BidsDTO, Boolean> notifications = usersService.getNotifications(client.getId());

        model.addAttribute("notifications", notifications);
        return "users/notifications";
    }

    @GetMapping("/bids")
    public String bidsIndex(Model model, @ModelAttribute("sentBidsModel") BidsParamsDTO sentBidsModel) {
        var bidList = bidService.getSentBids(sentBidsModel, getSession());

        model.addAttribute("sentBidsModel", sentBidsModel);
        model.addAttribute("bidsList", bidList);
        return "/users/bids";
    }

    @PostMapping("/bids/new")
    public String newBid(HttpServletRequest request, @ModelAttribute("newBidModel") NewBidsDTO newBidModel){
        bidService.createBid(newBidModel, getSession());
        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }

}
