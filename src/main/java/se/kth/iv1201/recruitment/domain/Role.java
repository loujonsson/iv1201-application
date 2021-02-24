package se.kth.iv1201.recruitment.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    private int roleId;

    private String name;

    public Role(String name) {
        this.name = name;
    }

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
