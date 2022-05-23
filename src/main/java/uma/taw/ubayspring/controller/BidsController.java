package uma.taw.ubayspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.bids.ReceivedBidsDTO;
import uma.taw.ubayspring.service.BidService;
import uma.taw.ubayspring.types.KindEnum;

import java.util.List;

@Controller
@RequestMapping("/vendor/bids")
public class BidsController {
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

    @GetMapping("")
    @PostMapping("")
    public String getIndex(Model model,
                           @RequestParam String startDate,
                           @RequestParam String endDate,
                           @RequestParam String productTitle,
                           @RequestParam String clientName,
                           @RequestParam String page,
                           @RequestParam String orderBy,
                           @RequestParam String asc
                           ){

        List<ReceivedBidsDTO> bidList = bidService.getReceivedBids(
                getSession(),
                startDate,
                endDate,
                productTitle,
                clientName,
                page,
                orderBy,
                asc);

        model.addAttribute("bidsByVendor", bidList);

        return "/vendor/bids/index";
    }
}
