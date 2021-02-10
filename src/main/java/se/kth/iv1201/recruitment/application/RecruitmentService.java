package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.Applicant;
import se.kth.iv1201.recruitment.domain.ApplicantDTO;
import se.kth.iv1201.recruitment.repository.ApplicantRepository;
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
    private ApplicantRepository applicantRepo;

    /**
     * Creates a new applicant with the specified username, password, first name, last name, email address and date of birth.
     *
     * @param username The applicant's username
     * @param password The applicant's password
     * @param firstName The applicant's first name
     * @param lastName The applicant's last name
     * @param emailAddress The applicant's email
     * @param dateOfBirth The applicant's date of birth
     * @return the newly created applicant
     */
    public ApplicantDTO createApplicant(String username, String password, String firstName, String lastName, String emailAddress, Integer dateOfBirth) {
        if (username == null) {
            throw new IllegalArgumentException("Please enter a valid username");
        }if (password == null) {
            throw new IllegalArgumentException("Please enter a valid password");
        }if (firstName == null) {
            throw new IllegalArgumentException("Please enter a valid first name");
        }if (lastName == null) {
            throw new IllegalArgumentException("Please enter a valid last name");
        }if (emailAddress == null) {
            throw new IllegalArgumentException("Please enter a valid email address");
        }if (dateOfBirth <= 0) {
            throw new IllegalArgumentException("Please enter a valid date of birth");
        }
        return applicantRepo.save(new Applicant(username, password, firstName, lastName, emailAddress, dateOfBirth));
    }

}
