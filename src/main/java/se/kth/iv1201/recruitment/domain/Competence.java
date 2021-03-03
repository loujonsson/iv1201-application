package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;

@Entity
public class Competence {
    @Id
    @Column(name = "competence_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
