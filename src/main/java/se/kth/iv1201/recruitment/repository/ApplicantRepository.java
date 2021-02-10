package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.domain.Applicant;

import java.util.List;

public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
    /**
     * Returns the person with the specified username, or null if there is no
     * such applicant.
     * @param username The applicants username
     * @return The account with the specified username, or null if there is no
     *         such person.
     */
    Applicant findApplicantByUsername(String username);

    /**
     * Searches for all applicants with the specified date of birth
     * @param dateOfBirth The date of birth of the applicant
     * @return A list containing all applicants with the specified date of birth. The list
     *        is empty if there are no such applicants.
     */
    List<Applicant> findByDateOfBirth(int dateOfBirth);

    @Override
    Applicant save(Applicant applicant);

    @Override
    List<Applicant> findAll();
}