package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.PersonDTO;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    /**
     * Returns the person with the specified username, or null if there is no
     * such Person.
     *
     * @param username The Persons username
     * @return The account with the specified username, or null if there is no
     *         such person.
     */
    Person findPersonByUsername(String username);

    /**
     * Searches for the person with the specified date of birth
     *
     * @param dateOfBirth The date of birth of the Person
     * @return The account with the specified date of birth, or null if there is no
     *         such person.
     */
    Person findPersonByDateOfBirth(String dateOfBirth);

    /**
     * Searches for all Persons with the specified email
     *
     * @param emailAddress The email of the Person
     * @return The account with the specified email, or null if there is no
     *         such person.
     */
    Person findPersonByEmailAddress(String emailAddress);

    /**
     * Searches for all Person in the database with the specified username and password
     *
     * @param username username of the user
     * @param password password of the user
     * @return returns user that has the matching username and password from the db
     */
    Person findPersonByUsernameAndPassword(String username, String password);

    Person findPersonByDateOfBirthAndPassword(String dateOfBirth, String password);

    Person findPersonByEmailAddressAndPassword(String email, String password);

    Person findPersonByUsernameOrDateOfBirthOrEmailAddressAndPassword(String username, String dateOfBirth, String email, String password);

    Person findPersonByIsCompleteFalseAndUsernameOrDateOfBirthOrEmailAddress(String username, String dateOfBirth, String email);

    Person findPersonByIsCompleteFalseAndUsername(String username);

    Person findPersonByIsCompleteFalseAndDateOfBirth(String dateOfBirth);

    Person findPersonByIsCompleteFalseAndEmailAddress(String emailAddress);

    List<Person> findPersonByIsCompleteFalse();


    @Override
    Person save(Person person);

    @Override
    List<Person> findAll();

    Person findPersonByUsernameOrDateOfBirthOrEmailAddress(String username, String dateOfBirth, String email);

    Person update(Person person);
}
