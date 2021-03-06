package uma.taw.ubayspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.auth.ChangePasswordDTO;
import uma.taw.ubayspring.dto.auth.RegisterDTO;
import uma.taw.ubayspring.dto.auth.ResetPasswordDTO;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;
import uma.taw.ubayspring.entity.PasswordResetEntityPK;
import uma.taw.ubayspring.exception.AuthenticationException;
import uma.taw.ubayspring.keys.AuthKeys;
import uma.taw.ubayspring.repository.ClientRepository;
import uma.taw.ubayspring.repository.LoginCredentialsRepository;
import uma.taw.ubayspring.repository.PasswordResetRepository;
import uma.taw.ubayspring.repository.SessionRepository;
import uma.taw.ubayspring.types.GenderEnum;
import uma.taw.ubayspring.types.KindEnum;

import java.sql.Date;
import java.util.List;

/**
 * @author Altair Bueno
 */

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;
    @Autowired
    PasswordResetRepository passwordResetRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void changePassword(@NonNull User user,
                               @NonNull ChangePasswordDTO changePasswordDTO
    ) throws AuthenticationException {
        String password = changePasswordDTO.getPassword();
        String repeatPassword = changePasswordDTO.getRepeatPassword();
        String oldPassword = changePasswordDTO.getOldPassword();

        if (!repeatPassword.equals(password))
            throw new AuthenticationException("Passwords don't match");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Invalid password format");

        LoginCredentialsEntity loginCredentials = getCredentialsEntity(user);
        String oldHash = loginCredentials.getPassword();

        if (passwordEncoder.matches(oldPassword, oldHash)) {
            var newHash = passwordEncoder.encode(password);
            loginCredentials.setPassword(newHash);

            loginCredentialsRepository.save(loginCredentials);
        } else {
            throw new AuthenticationException("Old password doesn't match");
        }
    }

    public void register(@NonNull RegisterDTO registerDTO) throws AuthenticationException {
        var username = registerDTO.getUsername();
        var password = registerDTO.getPassword();
        var repeatPassword = registerDTO.getRepeatPassword();
        GenderEnum gender = registerDTO.getGender();
        String address = registerDTO.getAddress();
        String name = registerDTO.getName();
        String lastName = registerDTO.getLastName();
        String city = registerDTO.getCity();
        Date birthDate = registerDTO.getBirthDate();

        if (!username.matches(AuthKeys.USERNAME_REGEX))
            throw new AuthenticationException("Username invalid format");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Password invalid format");
        if (!password.equals(repeatPassword))
            throw new AuthenticationException("Passwords don't match");

        var hashedPassword = passwordEncoder.encode(password);
        var client = new ClientEntity(name, lastName, address, city, birthDate, gender);
        var login = new LoginCredentialsEntity(username, hashedPassword, KindEnum.client, client);

        clientRepository.save(client);
        loginCredentialsRepository.save(login);
    }

    public void resetPassword(@NonNull ResetPasswordDTO resetPasswordDTO) throws AuthenticationException {
        String newPassword = resetPasswordDTO.getNewPassword();
        String repeatPassword = resetPasswordDTO.getRepeatPassword();
        Integer loginID = resetPasswordDTO.getLoginID();
        String requestID = resetPasswordDTO.getRequestID();

        if (!newPassword.equals(repeatPassword))
            throw new AuthenticationException("Passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Invalid password format");

        var optionalLoginCredentialsEntity = loginCredentialsRepository.findById(loginID);
        var passwordResetEntityOptional = passwordResetRepository.findById(new PasswordResetEntityPK(loginID, requestID));

        if (passwordResetEntityOptional.isEmpty()) {
            throw new AuthenticationException("Request not found");
        } else {
            var passwordResetEntity = passwordResetEntityOptional.get();
            var hashedPassword = passwordEncoder.encode(newPassword);
            var loginCredentialsEntity = optionalLoginCredentialsEntity.orElse(null);
            loginCredentialsEntity.setPassword(hashedPassword);

            passwordResetRepository.delete(passwordResetEntity);
            loginCredentialsRepository.save(loginCredentialsEntity);
        }
    }

    public LoginCredentialsEntity getCredentialsEntity(@NonNull User user) {
        return loginCredentialsRepository.findLoginCredentialsEntityByUsername(user.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var loginCredentialsEntity = loginCredentialsRepository.findLoginCredentialsEntityByUsername(username);

        if (loginCredentialsEntity != null) {
            var password = loginCredentialsEntity.getPassword();
            var kind = loginCredentialsEntity.getKind();
            var authorities = List.of(kind);

            return new User(username, password, authorities);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
