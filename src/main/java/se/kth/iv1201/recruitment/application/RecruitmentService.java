package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.PersonDTO;
import se.kth.iv1201.recruitment.domain.Role;
import se.kth.iv1201.recruitment.repository.PersonRepository;
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
    public PersonDTO createPerson(String username, String password, String firstName, String lastName, String emailAddress, int dateOfBirth, int roleId) throws IllegalRecruitmentTransactionException {
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
        }if (dateOfBirth == 0) {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + dateOfBirth);
        }if (roleId == 0) {
            throw new IllegalRecruitmentTransactionException("Attempt to create a person missing attribute: " + roleId);
        }
        return personRepo.save(new Person(username, password, firstName, lastName, emailAddress, dateOfBirth, roleId));
    }

    /**
     * Finds Person in database by username and password
     * @param username the Persons username
     * @param password the Persons password
     * @return the Person with the specified username and password
     * @throws IllegalRecruitmentTransactionException when attempting to login without username or password
     */
    public PersonDTO checkLogin(String username, String password) throws IllegalRecruitmentTransactionException {
        if(username == ""){
            throw new IllegalRecruitmentTransactionException("Attempt to login without: " + username);
        }
        if(password == ""){
            throw new IllegalRecruitmentTransactionException("Attempt to login without: " + password);
        }
        return personRepo.findPersonByUsernameAndPassword(username, password);
    }

    public PersonDTO checkUsernameExists(String username){
        return personRepo.findPersonByUsername(username);
    }

}
