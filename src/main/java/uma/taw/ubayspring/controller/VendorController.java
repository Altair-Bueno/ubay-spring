package uma.taw.ubayspring.controller;

/**
 *
 * @author Francisco Javier Hernández Martín
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.bids.BidsParamsDTO;
import uma.taw.ubayspring.dto.bids.BidsSortingOptions;
import uma.taw.ubayspring.dto.bids.ReceivedBidsDTO;
import uma.taw.ubayspring.dto.products.index.FavOwnedDTO;
import uma.taw.ubayspring.keys.VendorKeys;
import uma.taw.ubayspring.service.BidService;
import uma.taw.ubayspring.types.KindEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static uma.taw.ubayspring.keys.ProductKeys.localizedString;

@Controller
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    BidService bidService;

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

    @GetMapping("/bids")
    public String getIndex(Model model,
                           HttpServletRequest request,
                           @ModelAttribute("bidsModel") BidsParamsDTO bidsModel
    ){
        List<ReceivedBidsDTO> bidList = bidService.getReceivedBids(bidsModel, getSession());
        List<BidsSortingOptions> sortingOptions = Arrays.stream(VendorKeys.ORDER_BY_LIST)
                .map((x) -> new BidsSortingOptions(x, localizedString(request, x)))
                .toList();

        model.addAttribute("sortingOptions", sortingOptions);
        model.addAttribute("bidsByVendor", bidList);
        model.addAttribute("bidsModel", bidsModel);
        return "/vendor/bids/index";
    }
}
