package se.kth.iv1201.recruitment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.domain.Competence;
import se.kth.iv1201.recruitment.domain.Person;

import java.util.List;

public interface CompetenceRepository extends CrudRepository<Competence, Long> {
    List<Competence> findAllById(int competenceId);

    //List<Competence> findAllByNameSe();
}
