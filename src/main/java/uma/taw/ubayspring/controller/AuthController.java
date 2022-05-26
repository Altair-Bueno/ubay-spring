package uma.taw.ubayspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uma.taw.ubayspring.dto.auth.ChangePasswordDTO;
import uma.taw.ubayspring.dto.auth.RegisterDTO;
import uma.taw.ubayspring.dto.auth.ResetPasswordDTO;
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
    public ChangePasswordDTO getChangePassword() {
        return new ChangePasswordDTO();
    }

    @PostMapping("/changePassword")
    public String postChangePassword(@ModelAttribute ChangePasswordDTO changePasswordDTO) throws AuthenticationException {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        service.changePassword(user, changePasswordDTO);

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
    public ResetPasswordDTO getResetPassword(@ModelAttribute ResetPasswordDTO passwordDTO) {
        return passwordDTO;
    }

    @PostMapping("/resetPassword")
    public String postResetPassword(@ModelAttribute ResetPasswordDTO resetPasswordDTO) throws AuthenticationException {
        service.resetPassword(resetPasswordDTO);
        return "redirect:/";
    }
}
