package se.kth.iv1201.recruitment.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import se.kth.iv1201.recruitment.application.RecruitmentUserDetailsService;

@Component
public class RecruitmentAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private RecruitmentUserDetailsService service;

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials()
                .toString();

        UserDetails user = service.loadUserByUsername(username);
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return new UsernamePasswordAuthenticationToken
                    (user.getUsername(), user.getPassword(), user.getAuthorities());
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}