package uma.taw.ubayspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.LoginDTO;
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

@Service
public class AuthService {
    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;
    @Autowired
    PasswordResetRepository passwordResetRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    ClientRepository clientRepository;

    private String getHashedPassword(@NonNull String newPassword) {
        return BCrypt.hashpw(newPassword, BCrypt.gensalt(11));
    }

    private boolean checkPasswordHash(@NonNull String oldPassword, @NonNull String oldHash) {
        return BCrypt.checkpw(oldPassword, oldHash);
    }

    public void changePassword(@NonNull LoginDTO loginDTO,
                               @NonNull String oldPassword,
                               @NonNull String newPassword,
                               @NonNull String repeatPassword
    ) throws AuthenticationException {
        if (!repeatPassword.equals(newPassword))
            throw new AuthenticationException("Passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Invalid password format");

        LoginCredentialsEntity loginCredentials = getCredentialsEntity(loginDTO);
        String oldHash = loginCredentials.getPassword();

        if (checkPasswordHash(oldPassword, oldHash)) {
            var newHash = getHashedPassword(newPassword);
            loginCredentials.setPassword(newHash);

            loginCredentialsRepository.save(loginCredentials);
        } else {
            throw new AuthenticationException("Old password doesn't match");
        }
    }

    public LoginDTO login(@NonNull String username, @NonNull String password) throws AuthenticationException {
        if (!username.matches(AuthKeys.USERNAME_REGEX))
            throw new AuthenticationException("Invalid username format");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Invalid password format");

        var loginCredentialsEntity = loginCredentialsRepository.findLoginCredentialsEntityByUsername(username);

        if (loginCredentialsEntity != null && checkPasswordHash(password, loginCredentialsEntity.getPassword())) {
            var id = loginCredentialsEntity.getId();
            username = loginCredentialsEntity.getUsername();
            var kind = loginCredentialsEntity.getKind();
            return new LoginDTO(id,username, kind);
        } else {
            throw new AuthenticationException("Invalid username or password");
        }
    }

    public void register(
            @NonNull String username,
            @NonNull String password,
            @NonNull String repeatPassword,
            @NonNull String name,
            @NonNull String lastName,
            @NonNull String address,
            @NonNull String city,
            @NonNull GenderEnum gender,
            @NonNull Date birthDate
    ) throws AuthenticationException {
        if (!username.matches(AuthKeys.USERNAME_REGEX))
            throw new AuthenticationException("Username invalid format");
        if (!password.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Password invalid format");
        if (!password.equals(repeatPassword))
            throw new AuthenticationException("Passwords don't match");

        var hashedPassword = getHashedPassword(password);
        var client = new ClientEntity(name, lastName, address, city, birthDate, gender);
        var login = new LoginCredentialsEntity(username, hashedPassword, KindEnum.client, client);

        clientRepository.save(client);
        loginCredentialsRepository.save(login);
    }

    public void resetPassword(@NonNull String username,
                              @NonNull String requestID,
                              @NonNull String newPassword,
                              @NonNull String repeatPassword) throws AuthenticationException {
        if (!newPassword.equals(repeatPassword))
            throw new AuthenticationException("Passwords don't match");
        if (!newPassword.matches(AuthKeys.PASSWORD_REGEX))
            throw new AuthenticationException("Invalid password format");

        var loginCredentialsEntity = loginCredentialsRepository.findLoginCredentialsEntityByUsername(username);
        var passwordResetEntityOptional = passwordResetRepository.findById(new PasswordResetEntityPK(loginCredentialsEntity, requestID));

        if (passwordResetEntityOptional.isEmpty()) {
            throw new AuthenticationException("Request not found");
        } else {
            var passwordResetEntity = passwordResetEntityOptional.get();
            var hashedPassword = getHashedPassword(newPassword);
            loginCredentialsEntity.setPassword(hashedPassword);

            passwordResetRepository.delete(passwordResetEntity);
            loginCredentialsRepository.save(loginCredentialsEntity);
        }
    }

    public LoginCredentialsEntity getCredentialsEntity(@NonNull LoginDTO loginDTO) {
        return loginCredentialsRepository.findById(loginDTO.id()).orElse(null);
    }
}
