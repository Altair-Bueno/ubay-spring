package uma.taw.ubayspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.bids.BidsParamsDTO;
import uma.taw.ubayspring.dto.bids.ReceivedBidsDTO;
import uma.taw.ubayspring.service.BidService;
import uma.taw.ubayspring.types.KindEnum;

import java.util.List;

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
                           @ModelAttribute("bidsModel") BidsParamsDTO bidsModel
    ){
        List<ReceivedBidsDTO> bidList = bidService.getReceivedBids(bidsModel, getSession());

        model.addAttribute("bidsByVendor", bidList);
        model.addAttribute("bidsModel", bidsModel);
        return "/vendor/bids/index";
    }
}
