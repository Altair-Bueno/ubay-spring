package uma.taw.ubayspring.springconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uma.taw.ubayspring.service.MinioService;

/**
 * @author Altair Bueno
 */

@Configuration
public class BeanConfig {
    @Bean
    public MinioService minioService(
            @Value("${minio.bucket}") String bucket,
            @Value("${minio.url}") String url,
            @Value("${minio.username}") String username,
            @Value("${minio.password}") String password
    ) {
        return new MinioService(bucket, url, username, password);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
