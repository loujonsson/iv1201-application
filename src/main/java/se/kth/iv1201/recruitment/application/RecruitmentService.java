package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.Competence;
import se.kth.iv1201.recruitment.repository.CompetenceRepository;
import se.kth.iv1201.recruitment.domain.IllegalRecruitmentTransactionException;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.PersonDTO;
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

    @Autowired
    private CompetenceRepository competenceRepo;

    /**
     * Gets all information in the competence table
     * @return a list of all the competences (all columns)
     */
    public List<Competence> getAllCompetences(){
        return competenceRepo.findAll();
    }

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
        Person person = new Person(username, password, firstName, lastName, emailAddress, dateOfBirth, roleId, isComplete);
        return personRepo.save(person);
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

    /**
     * Method checks if one of these parameters: username, date of birth or email exists in the database
     * @param username
     * @param dateOfBirth
     * @param email
     * @return User with one of these parameters
     */
    public PersonDTO checkUsernameDateOfBirthOrEmailExists(String username, String dateOfBirth, String email) {
        return personRepo.findPersonByUsernameOrDateOfBirthOrEmailAddress(username, dateOfBirth, email);
    }

    /**
     * This method checks if the Person is complete (all attributes exists in the table)
     * @param givenLoginValue can be email, username or date of birth
     * @return Person if all attribute values exists in database, false if there is an empty string in one of them
     */
    public PersonDTO checkIsCompleteFalse(String givenLoginValue) {
        List<Person> isCompleteFalseList = personRepo.findPersonByIsCompleteFalse();

        Person currentPerson = null;
        for (Person person : isCompleteFalseList) {
            if (person.equals(personRepo.findPersonByUsernameOrDateOfBirthOrEmailAddress(givenLoginValue,givenLoginValue,givenLoginValue))){
                currentPerson = person;
            }
        }
        return currentPerson;
    }

    /**
     * Updates person in database
     *
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param dateOfBirth
     * @param isComplete
     * @return updated person
     */
    public PersonDTO updatePerson(String username, String password, String firstName, String lastName, String emailAddress, String dateOfBirth, boolean isComplete) {
        Person oldPerson = personRepo.findPersonByUsernameOrDateOfBirthOrEmailAddress(username, dateOfBirth, emailAddress);
        oldPerson.setUsername(username);
        oldPerson.setPassword(password);
        oldPerson.setFirstName(firstName);
        oldPerson.setLastName(lastName);
        oldPerson.setEmail(emailAddress);
        oldPerson.setDateOfBirth(dateOfBirth);
        oldPerson.setIsComplete(isComplete);

        return personRepo.save(oldPerson);
     }
}
