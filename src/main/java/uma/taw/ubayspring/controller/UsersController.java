package uma.taw.ubayspring.controller;

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
import uma.taw.ubayspring.dto.bids.BidsSortingOptions;
import uma.taw.ubayspring.dto.bids.NewBidsDTO;
import uma.taw.ubayspring.dto.notifications.BidsDTO;
import uma.taw.ubayspring.dto.products.ProductClientDTO;
import uma.taw.ubayspring.dto.users.ClientDTO;
import uma.taw.ubayspring.dto.users.FilterUsersDTO;
import uma.taw.ubayspring.dto.users.PasswordChangeDTO;
import uma.taw.ubayspring.dto.users.ProductDTO;
import uma.taw.ubayspring.keys.UsersKeys;
import uma.taw.ubayspring.service.BidService;
import uma.taw.ubayspring.service.UsersService;
import uma.taw.ubayspring.service.products.ProductService;
import uma.taw.ubayspring.types.KindEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static uma.taw.ubayspring.keys.ProductKeys.localizedString;

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

    /**
     * @author: José Luis Bueno Pachón
     */

    @GetMapping
    public String getUsers(Model model, @ModelAttribute FilterUsersDTO filterUsersDTO) {
        List<ClientDTO> clientDTOList = usersService.users(filterUsersDTO);
        model.addAttribute("search-user", clientDTOList);
        model.addAttribute("filterUsersDTO", filterUsersDTO);
        return "users/users";
    }

    /**
     * @author: José Luis Bueno Pachón
     */


    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        usersService.deleteUser(id);
        return "redirect:";
    }

    /**
     * @author: José Luis Bueno Pachón
     */


    @GetMapping("/modify")
    public ClientDTO getModify(@ModelAttribute ClientDTO clientDTO){
        return clientDTO;
    }


    /**
     * @author: José Luis Bueno Pachón
     */

    @PostMapping("/modify")
    public String postModify(@ModelAttribute ClientDTO clientDTO){
        usersService.modifyUser(clientDTO);
        return "redirect:";
    }

    /**
     * @author: José Luis Bueno Pachón
     */

    @GetMapping("/addFavourite")
    public String addFavourite(@RequestParam String productID, @RequestParam String clientID){
        usersService.addFavProduct(productID, clientID);
        return "redirect:products";
    }

    /**
     * @author: José Luis Bueno Pachón
     */

    @GetMapping("/deleteFavourite")
    public String deleteFavourite(@RequestParam String productID, @RequestParam String clientID){
        usersService.deleteFavProduct(productID, clientID);
        return "redirect:products";
    }

    /**
     * @author: José Luis Bueno Pachón
     */

    @GetMapping("/products")
    public String products(Model model){
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<ProductDTO> favouriteProducts = usersService.products(user);


        model.addAttribute("clientID", usersService.getClientID(user));
        model.addAttribute("favourite-products-list", favouriteProducts);

        return "users/products";
    }

    /**
     * @author: José Luis Bueno Pachón
     */

    @GetMapping("/passwordChangeLink")
    public String passwordChangeLink(Model model, @RequestParam String id){

        PasswordChangeDTO passwordChangeDTO = usersService.passwordChange(id);

        model.addAttribute("loginID", passwordChangeDTO.getLoginID());
        model.addAttribute("requestID", passwordChangeDTO.getRequestID());

        return "users/passwordChange";
    }

    /**
     *
     * @author Francisco Javier Hernández Martín
     *
     */
    @GetMapping("/notifications")
    public String processNotifications(Model model){
        var client = getProductSession();

        HashMap<BidsDTO, Boolean> notifications = usersService.getNotifications(client.getId());

        model.addAttribute("notifications", notifications);
        return "users/notifications";
    }

    @GetMapping("/bids")
    public String bidsIndex(Model model,
                            HttpServletRequest request,
                            @ModelAttribute("sentBidsModel") BidsParamsDTO sentBidsModel) {
        var bidList = bidService.getSentBids(sentBidsModel, getSession());
        List<BidsSortingOptions> sortingOptions = Arrays.stream(UsersKeys.ORDER_BY_LIST)
                .map((x) -> new BidsSortingOptions(x, localizedString(request, x)))
                .toList();

        model.addAttribute("sortingOptions", sortingOptions);
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
