package uma.taw.ubayspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uma.taw.ubayspring.exception.AuthenticationException;
import uma.taw.ubayspring.service.AuthService;
import uma.taw.ubayspring.types.GenderEnum;

import java.sql.Date;


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
    public void getRegister() {
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword, @RequestParam String name, @RequestParam String lastName, @RequestParam String address, @RequestParam String city, @RequestParam GenderEnum gender, @RequestParam Date birthDate) throws AuthenticationException {
        service.register(username, password, repeatPassword, name, lastName, address, city, gender, birthDate);

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
