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
import uma.taw.ubayspring.types.KindEnum;

/**
 * Configures the security on the system
 * <p>
 * (by default it's disabled, or else
 * a sign in will always pop-up)
 *
 * @author Altair Bueno 90 Francisco Javier Hernández Martín 10
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
        var requiresAuthentication = new String[]{
                "/admin/*",
                "/users/*",
                "/vendor/*",
                "/categories/*",
                "/product/new",
                "/product/update",
                "/product/delete",
                "/auth/changePassword"
        };
        var requiresClientRole = new String[] {
                "/product/new",
                "/users/addFavourite",
                "/users/deleteFavourite",
                "users/notifications",
                "/users/products",
                "/users/bids/*",
                "/categories/addFavourite",
                "/categories/addFavourite",
                "/categories/deleteFavourite",
                "/vendor/bids/*"
        };
        var requiresAdminRole = new String[]{
                "/admin/*",
                "/categories/new",
                "/categories/update",
                "/categories/delete",
                "/users",
                "/users/new",
                "/users/update",
                "/users/modify",
                "/users/delete",
                "/users/passwordChangeLink"
        };

        security
                .csrf().disable()

                .authorizeRequests()
                .antMatchers(requiresAuthentication)
                .authenticated()
                .and()

                .authorizeRequests()
                .antMatchers(requiresClientRole)
                .hasAuthority(KindEnum.client.toString())
                .and()

                .authorizeRequests()
                .antMatchers(requiresAdminRole)
                .hasAuthority(KindEnum.admin.toString())
                .and()

                .logout()
                .logoutUrl("/auth/signoff")
                .logoutSuccessUrl("/")
                .and()

                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter(AuthKeys.USERNAME_PARAMETER)
                .passwordParameter(AuthKeys.PASSWORD_PARAMETER)
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/auth/login");
    }
}
