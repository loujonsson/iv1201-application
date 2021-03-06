package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.kth.iv1201.recruitment.domain.PersonDTO;

import java.util.Collections;

/**
 * Service for authentication and authorization.
 */
public class RecruitmentUserDetailsService implements UserDetailsService {
    @Autowired
    private RecruitmentService service;

    /**
     * Checks for username in database and applies, depending on what role_id the person in the db has, sets the role
     *
     * @param username Username of the person logging in
     * @return User with username, his/her password and his/her role/(s)
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonDTO person = service.checkUsernameExists(username);

        if(person == null){
            throw new UsernameNotFoundException("Username not found in database");
        }

        SimpleGrantedAuthority current = null;
        if(person.getRoleId() == 1) {
            String admin = "ROLE_ADMIN";
            current = new SimpleGrantedAuthority(admin);
        }else if(person.getRoleId() == 2){
            String applicant = "ROLE_APPLICANT";
            current = new SimpleGrantedAuthority(applicant);
        }

        return new org.springframework.security.core.userdetails.User(person.getUsername(), person.getPassword(), Collections.singleton(current));
    }
}