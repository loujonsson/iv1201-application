package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
     */
    public PersonDTO createPerson(String username, String password, String firstName, String lastName, String emailAddress, int dateOfBirth, int roleId) {
        return personRepo.save(new Person(username, password, firstName, lastName, emailAddress, dateOfBirth, roleId));
    }

    /**
     * Finds Person in database by username and password
     * @param username the Persons username
     * @param password the Persons password
     * @return the Person with the specified username and password
     */
    public PersonDTO checkLogin(String username, String password){
        return personRepo.findPersonByUsernameAndPassword(username, password);
    }

}
