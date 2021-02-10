package se.kth.iv1201.recruitment.repository;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.Person;
import java.util.List;
/**
 * Contains all database access concerning persons.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY) // Applies only to methods explicitly declared in this interface,
// not to those inherited from JpaRepository. This is quite dangerous, there
// will be no error if an inherited method is
// called, but default transaction configuration will be used instead of what is
// declared here.
public interface PersonRepository extends JpaRepository<Person, Long> {
    // /**
    // * Searches for all holders with the specified name.
    // *
    // * @param name The name of the holder's to search for.
    // * @return A list containing all holders with the specified name. The list
    // * is empty if there are no such holders.
    // */
    // public List<Holder> findHoldersByName(String name);
    //

    /**
     * Returns the person with the specified person number, or null if there is no
     * such holder.
     *
     * @param personNo The number of the holder to search for.
     * @return The account with the specified person number, or null if there is no
     *         such person.
     * @throws IncorrectResultSizeDataAccessException If more than one person with
     *                                                the specified number was
     *                                                found.
     */
    Person findPersonByPersonNo(long personNo);

    @Override
    Person save(Person person);

    @Override
    List<Person> findAll();
}
