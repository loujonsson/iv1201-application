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
/*
    public ApplicantDTO createApplicant(String username, String password, String firstName, String lastName, String email, int dateOfBirth) {
        return applicantRepo.save(new Applicant(username, password, firstName, lastName, email, dateOfBirth));
    } */
}
