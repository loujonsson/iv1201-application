package se.kth.iv1201.recruitment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import se.kth.iv1201.recruitment.application.RecruitmentUserDetailsService;
import se.kth.iv1201.recruitment.domain.RecruitmentSimpleUrlAuthenticationSuccessHandler;

/**
 * Configuration for authentication and authorization, using the framework Spring security.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configure the URLs that are supposed to be access and permitted to all users of the website
     * Configures URL page for login
     *
     * @param http HTTP security of http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/create-account", "/competence-profile", "/error/**").permitAll()
                .antMatchers("/email-verification", "/update-account", "/non-repudiation-email", "/verification", "/update-account-success").hasAnyAuthority("ROLE_APPLICANT", "ROLE_ADMIN")
                .antMatchers("/applicant").hasAuthority("ROLE_APPLICANT")
                .antMatchers("/admin", "/applicant").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error").permitAll()
                .successHandler(myAuthenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    /**
     * A bean of RecruitmentUserDetailsService that handles the user details
     *
     * @return a custom UserDetailsService
     */
    @Bean
    public RecruitmentUserDetailsService userDetailsService() {
        return new RecruitmentUserDetailsService();
    }

    /**
     * Decrypting passwords from db
     *
     * @return A BcryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Handles authorization, depending on what role the user has the user shall be directed and be able to access different views
     *
     * @return Handler for authentication Success
     */
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new RecruitmentSimpleUrlAuthenticationSuccessHandler();
    }
}
