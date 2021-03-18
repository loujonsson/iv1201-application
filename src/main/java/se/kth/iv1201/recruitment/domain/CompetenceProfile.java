package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;

@Entity
@Table(name = "competence_profile")
public class CompetenceProfile {

    @Id
    @Column(name= "competence_profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long competenceProfileId;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;

    @ManyToOne
    @JoinColumn(name = "competence_id")
    Competence competence;

    @Column(name = "years_of_experience")
    float yearsOfExperience;

    public Long getCompetenceProfileId() {
        return competenceProfileId;
    }

    public Person getPerson() {
        return person;
    }

    public Competence getCompetence() {
        return competence;
    }

    public float getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setCompetenceProfileId(Long competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public void setYearsOfExperience(float yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
