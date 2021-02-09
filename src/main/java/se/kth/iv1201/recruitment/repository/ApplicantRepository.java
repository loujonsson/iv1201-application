package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.domain.Applicant;

import java.util.List;

public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
    Applicant findApplicantByUsername(String username);

    List<Applicant> findByDateOfBirth(long dateOfBirth);
}
