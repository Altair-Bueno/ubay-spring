package uma.taw.ubayspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;
import uma.taw.ubayspring.repository.LoginCredentialsRepository;

@SpringBootTest
class ApplicationTests {
    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void CRUDOnLoginCredentialsWorksAsExpected() {
        var createdEntity= new LoginCredentialsEntity();
        createdEntity.setClient(null);
        createdEntity.setKind("admin");
        createdEntity.setPassword("foo");
        createdEntity.setUsername("FOooo");
        loginCredentialsRepository.save(createdEntity);

        var retrievedEntity = loginCredentialsRepository.findById(createdEntity.getId()).get();

        Assertions.assertThat(retrievedEntity)
                .isNotNull()
                .isEqualTo(createdEntity);

        loginCredentialsRepository.delete(retrievedEntity);
    }
}
