package uma.taw.ubayspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uma.taw.ubayspring.dto.auth.RegisterDTO;
import uma.taw.ubayspring.exception.AuthenticationException;
import uma.taw.ubayspring.service.AuthService;


/**
 * @author Altair Bueno
 */

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;

    @GetMapping("/login")
    public void getLogin() {
    }

    @GetMapping("/changePassword")
    public void getChangePassword() {
    }

    @PostMapping("/changePassword")
    public String postChangePassword(@RequestParam String oldPassword, @RequestParam String password, @RequestParam String repeatPassword) throws AuthenticationException {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        service.changePassword(user, oldPassword, password, repeatPassword);

        return "redirect:/";
    }

    @GetMapping("/register")
    public RegisterDTO getRegister() {
        return new RegisterDTO();
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute RegisterDTO registerDTO) throws AuthenticationException {
        service.register(registerDTO);

        return "redirect:/auth/login";
    }

    @GetMapping("/resetPassword")
    public void getResetPassword() {

    }

    @PostMapping("/resetPassword")
    public String postResetPassword(@RequestParam String username, @RequestParam String requestID, @RequestParam String newPassword, @RequestParam String repeatPassword) throws AuthenticationException {
        service.resetPassword(username, requestID, newPassword, repeatPassword);
        return "redirect:/";
    }
}
