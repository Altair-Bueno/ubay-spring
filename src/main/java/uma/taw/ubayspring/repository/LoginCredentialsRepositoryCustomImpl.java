package uma.taw.ubayspring.repository;

import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LoginCredentialsRepositoryCustomImpl implements LoginCredentialsRepositoryCustom{

    @PersistenceContext
    private EntityManager em;
    @Override
    public LoginCredentialsEntity searchClientLoginByClient(ClientEntity client) {
        var query = em.createQuery("SELECT a FROM LoginCredentialsEntity a WHERE a.client = :client");
        query.setParameter("client", client);
        return (LoginCredentialsEntity) query.getSingleResult();
    }
}
