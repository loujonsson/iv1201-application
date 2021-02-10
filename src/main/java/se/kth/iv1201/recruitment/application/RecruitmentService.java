package se.kth.iv1201.recruitment.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.Applicant;
import se.kth.iv1201.recruitment.domain.ApplicantDTO;
import se.kth.iv1201.recruitment.repository.ApplicantRepository;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class RecruitmentService {
    @Autowired
    private static ApplicantRepository applicantRepo;

    public ApplicantDTO createApplicant(String firstName, String lastName, Integer dateOfBirth, String email, String password) {
        return applicantRepo.save(new Applicant(firstName, password, firstName, lastName, email, dateOfBirth));
    }

    /*
    public ApplicantDTO createApplicant(String username, String password, String firstName, String lastName, String email, Integer dateOfBirth) {
        return applicantRepo.save(new Applicant(username, password, firstName, lastName, email, dateOfBirth));
    }*/
}
