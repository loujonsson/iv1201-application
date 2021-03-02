package se.kth.iv1201.recruitment.domain;

import org.springframework.security.core.Authentication;

import javax.security.sasl.AuthenticationException;

/**
 * Authentication Provider is a Authentication Manager and has only one method. Can do three things in authenticate() method
 * The provider can support multiple different authentication mechanisms
 * - Return an Authentication if it can verify input
 * - Throw exception if input is invalid principal
 * - Return null if it cannot decide
 *
 * https://spring.io/guides/topicals/spring-security-architecture
 */
public interface AuthenticationProvider {
    Authentication authenticate(Authentication authentication)
            throws AuthenticationException;

    boolean supports(Class<?> authentication);
}
