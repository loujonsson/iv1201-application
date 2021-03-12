package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;

/**
 * Entity for Competence table in database
 */
@Entity
public class Competence {
    @Id
    @Column(name = "competence_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_se")
    private String nameSe;

    @Column(name = "name_en")
    private String nameEn;

    public String getNameSe() {
        return nameSe;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameSe(String nameSe) {
        this.nameSe = nameSe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

