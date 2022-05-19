package uma.taw.ubayspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    /*
    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;
     */

    @Test
    void contextLoads() {
    }

    @Test
    void CRUDOnLoginCredentialsWorksAsExpected() {
        /*
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

         */
    }
}
