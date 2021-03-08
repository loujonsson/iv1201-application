package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.kth.iv1201.recruitment.domain.PersonDTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class RecruitmentUserDetailsService implements UserDetailsService {
    @Autowired
    private RecruitmentService service;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        PersonDTO person = service.checkUsernameDateOfBirthOrEmailExists(username);
        System.out.println("person: " + person.toString());
        if(person == null){
            throw new UsernameNotFoundException("No username, date of birth or email was found");
        }
       /*if(person.equals(service.checkIsCompleteFalse(username))) {
            System.out.println("person2: " + person.toString());
        }*/

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
