package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;

/**
 * Create an entity for Person so the database can create a table from it
 * https://spring.io/guides/gs/accessing-data-jpa/
 */
@Entity
public class Person implements PersonDTO {
    @Id
    private String username;

   // @Column(name = "password")
    private String password;

    private String firstName;

    private String lastName;

    private String emailAddress;


    private int dateOfBirth;

   // @ManyToOne
  //  @JoinColumn(name="roleId")
    private int roleId;

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
    public Person(String username, String password, String firstName, String lastName, String emailAddress, int dateOfBirth){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
       // this.roleId = roleId;
    }

    public String toString() {
        return String.format(
                "Person[username='%s', password='%s', firstName='%s', lastName='%s', email='%s', dateOfBirth='%s']",
                username, password, firstName, lastName, emailAddress, dateOfBirth);
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
}
