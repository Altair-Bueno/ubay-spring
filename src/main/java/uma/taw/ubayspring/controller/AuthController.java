package uma.taw.ubayspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.exception.AuthenticationException;
import uma.taw.ubayspring.service.AuthService;
import uma.taw.ubayspring.types.GenderEnum;

import java.sql.Date;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;

    @GetMapping("/login")
    public void getLogin() {
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String username, @RequestParam String password) throws AuthenticationException {
        var loginDTO = service.login(username, password);
        // TODO hacer algo con el logindto
        return "redirect:/";
    }

    @GetMapping("/changePassword")
    public void getChangePassword() {
    }

    @PostMapping("/changePassword")
    public String postChangePassword(@RequestParam String oldPassword, @RequestParam String password, @RequestParam String repeatPassword) throws AuthenticationException {
        LoginDTO loginDTO = null; // TODO Current loginDTO
        service.changePassword(loginDTO, oldPassword, password, repeatPassword);

        return "redirect:/";
    }

    @GetMapping("/register")
    public void getRegister() {
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword, @RequestParam String name, @RequestParam String lastName, @RequestParam String address, @RequestParam String city, @RequestParam GenderEnum gender, @RequestParam Date birthDate) throws AuthenticationException {
        service.register(username, password, repeatPassword, name, lastName, address, city, gender, birthDate);

        return "redirect:/";
    }

    @GetMapping("/resetPassword")
    public void getResetPassword() {

    }

    @PostMapping("/resetPassword")
    public String postResetPassword(@RequestParam String username, @RequestParam String requestID, @RequestParam String newPassword, @RequestParam String repeatPassword) throws AuthenticationException {
        service.resetPassword(username, requestID, newPassword, repeatPassword);
        return "redirect:/";
    }

    @PostMapping("/signoff")
    public String postSignoff(@RequestHeader(defaultValue = "/") String referer) {
        // TODO close session
        return "redirect:" + referer;
    }
}
