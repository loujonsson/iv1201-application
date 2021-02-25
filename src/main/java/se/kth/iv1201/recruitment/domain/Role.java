package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany()//mappedBy = "role_id"
    @JoinColumn(name = "role_id")
    private List<Person> persons = new ArrayList<Person>();

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
}
