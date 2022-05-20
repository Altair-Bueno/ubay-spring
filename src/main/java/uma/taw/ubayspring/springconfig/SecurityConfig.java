package uma.taw.ubayspring.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import uma.taw.ubayspring.keys.AuthKeys;

/**
 * Configures the security on the system
 * <p>
 * (by default it's disabled, or else
 * a sign in will always pop-up)
 *
 * @author Francisco Javier Hernández Martín
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/auth/changePassword").authenticated().and()

                .logout()
                .logoutUrl("/auth/signoff")
                .logoutSuccessUrl("/")
                .and()

                .csrf().disable()

                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter(AuthKeys.USERNAME_PARAMETER)
                .passwordParameter(AuthKeys.PASSWORD_PARAMETER)
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/auth/login");
    }
}
