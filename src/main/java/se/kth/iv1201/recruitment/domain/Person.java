package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Create an entity for Person so the database can create a table from it
 * https://spring.io/guides/gs/accessing-data-jpa/
 */
@Entity
@Table(name = "person")
public class Person implements PersonDTO {
    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "date_of_birth")
    private int dateOfBirth;

   /* @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    */

    @Column(name = "role_id")
    private int roleId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "competence_profile",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private Set<Competence> competences = new HashSet<>();

    /**
     * Exists only for the sake of JPA
     */
    protected Person(){}

    /**
     * Creates a new instance with the specified username, password, first and last name, date of birth and email
     * @param username The person's username
     * @param password The person's password
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @param emailAddress The person's email
     * @param dateOfBirth The person's date of birth
     */
    public Person(String username, String password, String firstName, String lastName, String emailAddress, int dateOfBirth, int roleId){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.roleId = roleId;
    }

    public String toString() {
        return String.format(
                "Person[username='%s', password='%s', firstName='%s', lastName='%s', email='%s', dateOfBirth='%s', role='%s']",
                username, password, firstName, lastName, emailAddress, dateOfBirth, roleId);
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public int getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getRoleId() {
        return roleId;
    }

    /*
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }*/
}
