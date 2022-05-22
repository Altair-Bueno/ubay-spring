package uma.taw.ubayspring.springconfig;

import com.jlefebure.spring.boot.minio.MinioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uma.taw.ubayspring.wrapper.MinioWrapperService;

/**
 * @author Altair Bueno
 */

@Configuration
public class BeanConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
    @Bean
    public MinioWrapperService wrapperService(MinioService minioService){
        return new MinioWrapperService(minioService);
    }
}
