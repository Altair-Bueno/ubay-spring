package uma.taw.ubayspring.springconfig;

import lombok.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class Localization {
    private String messagesBasename;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setBasenames("file:" + messagesBasename);
        return messageSource;

    }
}
