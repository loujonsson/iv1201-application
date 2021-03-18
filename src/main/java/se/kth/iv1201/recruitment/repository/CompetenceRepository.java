package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.recruitment.domain.Competence;
import java.util.List;

public interface CompetenceRepository extends CrudRepository<Competence, Long> {

    List<Competence> findAll();

}
