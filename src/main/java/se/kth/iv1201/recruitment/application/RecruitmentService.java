package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.PersonDTO;
import se.kth.iv1201.recruitment.domain.Role;
import se.kth.iv1201.recruitment.repository.PersonRepository;

import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * <p>
 * This is the recruitment application class, which defines tasks that can be performed
 * by the domain layer.
 * </p>
 *
 * <p>
 * Transaction demarcation is defined by methods in this class, a transaction
 * starts when a method is called from the presentation layer, and ends (commit
 * or rollback) when that method returns.
 * </p>
 */

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class RecruitmentService {
    @Autowired
    private PersonRepository personRepo;

    /**
     * Creates a new Person with the specified username, password, first name, last name, email address and date of birth.
     *
     * @param username The Person's username
     * @param password The Person's password
     * @param firstName The Person's first name
     * @param lastName The Person's last name
     * @param emailAddress The Person's email
     * @param dateOfBirth The Person's date of birth
     * @return the newly created Person
     * @throws IllegalRecruitmentTransactionException When attempting to save a person with a missing attribute
     */
    public PersonDTO createPerson(String username, String password, String firstName, String lastName, String emailAddress, String dateOfBirth, int roleId, boolean isComplete) throws IllegalRecruitmentTransactionException {
        if (username == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + username);
        }if (password == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + password);
        }if (firstName == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + firstName);
        }if (lastName == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + lastName);
        }if (emailAddress == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + emailAddress);
        }if (dateOfBirth == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + dateOfBirth);
        }if (roleId == 0) {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + roleId);
        }if(isComplete == FALSE){
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + isComplete);
        }
        return personRepo.save(new Person(username, password, firstName, lastName, emailAddress, dateOfBirth, roleId, isComplete));
    }

    /**
     * Finds Person in database by username and password
     * @param username the Persons username
     * @param password the Persons password
     * @return the Person with the specified username and password
     * @throws IllegalRecruitmentTransactionException when attempting to login without username or password
     */
    public PersonDTO checkLogin(String username, String password) throws IllegalRecruitmentTransactionException {
        System.out.println("In check login");
        if(username == ""){
            throw new IllegalRecruitmentTransactionException("Attempt to login without: " + username);
        }
        if(password == ""){
            throw new IllegalRecruitmentTransactionException("Attempt to login without: " + password);
        }
        /*
        if(checkUsernameExists(username) != null) {
            return personRepo.findPersonByUsernameAndPassword(username, password);
        }if(checkEmailExists(username) != null){
            return personRepo.findPersonByEmailAddressAndPassword(username, password);
        }if(checkDateOfBirthExists(username) != null){
            return personRepo.findPersonByDateOfBirthAndPassword(username, password);
        }*/
        return personRepo.findPersonByUsernameOrDateOfBirthOrEmailAddressAndPassword(username, username, username, password);
    }

    /**
     * This method checks whether a username already exists in the database.
     * @param username
     * @return
     */
    public PersonDTO checkUsernameExists(String username) {
        return personRepo.findPersonByUsername(username);
    }

    /**
     * This method checks whether an email already exists in the database.
     * @param emailAddress
     * @return
     */
    public PersonDTO checkEmailExists(String emailAddress){
        return personRepo.findPersonByEmailAddress(emailAddress);
    }

    /**
     * This method checks whether a date of birth already exists in the database.
     * @param dateOfBirth
     * @return
     */
    public PersonDTO checkDateOfBirthExists(String dateOfBirth){
        return personRepo.findPersonByDateOfBirth(dateOfBirth);
    }

    public PersonDTO checkUsernameDateOfBirthOrEmailExists(String username) {
       // System.out.println("isCompleteFalseAndUsername....: " + checkIsCompleteFalse(username).toString());
        return personRepo.findPersonByUsernameOrDateOfBirthOrEmailAddress(username, username, username);
    }

    /**
     * This method checks if the Person is complete (all attributes exists in the table)
     * @return Person if all attribute values exists in database, false if there is an empty string in one of them
     */
    public PersonDTO checkIsCompleteFalse(String givenLoginValue) {
        System.out.println("in checkIsCompleteFalse");
        List<Person> isCompleteFalseList = personRepo.findPersonByIsCompleteFalse();
        System.out.println("List: " + isCompleteFalseList.toString());

        Person currentPerson = null;
        for (Person person : isCompleteFalseList) {
            System.out.println("in for loop");
            if (person.equals(personRepo.findPersonByUsernameOrDateOfBirthOrEmailAddress(givenLoginValue,givenLoginValue,givenLoginValue))){
                System.out.println("currentPerson: " + person.toString());
                currentPerson = person;
            }
        }
        return currentPerson;
    }
    //TODO: update this method
    public PersonDTO updatePerson(String username, String password, String firstName, String lastName, String email, String dateOfBirth, int roleId, boolean isComplete) throws IllegalRecruitmentTransactionException {
        if (username == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to update a person without adding attribute: " + username);
        }if (password == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to update a person without adding attribute: " + password);
        }if (firstName == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to update a person without adding attribute: " + firstName);
        }if (lastName == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to update a person without adding attribute: " + lastName);
        }if (email == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to update a person without adding attribute: " + email);
        }if (dateOfBirth == "") {
            throw new IllegalRecruitmentTransactionException("Attempt to update a person without adding attribute: " + dateOfBirth);
        }if (roleId == 0) {
            throw new IllegalRecruitmentTransactionException("Attempt to update a person without adding attribute: " + roleId);
        }if(isComplete == FALSE){
            throw new IllegalRecruitmentTransactionException("Attempt to update a person without adding attribute: " + isComplete);
        }
        return personRepo.update(new Person(username, password, firstName, lastName, email, dateOfBirth, roleId, isComplete));
    }
}
