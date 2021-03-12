package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;

/**
 * Entity for Role table in database
 */
@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "name", nullable = false)
    private String name;

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
