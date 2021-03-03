package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "name", nullable = false)
    private String name;

    //The mapping is done by role so hibernate doesn't need to create another table for us
    @OneToMany(mappedBy = "role")
    private List<Person> persons;

    public Role() {
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}
