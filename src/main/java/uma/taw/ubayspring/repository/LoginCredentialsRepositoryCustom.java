package uma.taw.ubayspring.repository;

/**
 * @author Altair Bueno
 */

import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;

public interface LoginCredentialsRepositoryCustom {
    public LoginCredentialsEntity searchClientLoginByClient(ClientEntity client);
}
