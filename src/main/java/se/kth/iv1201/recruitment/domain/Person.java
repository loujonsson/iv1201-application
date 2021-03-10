package se.kth.iv1201.recruitment.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Create an entity for Person so the database can create a table from it
 * https://spring.io/guides/gs/accessing-data-jpa/
 */
@Entity
@Table(name = "person")
public class Person implements PersonDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "username")
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
    private String dateOfBirth;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "is_complete")
    private boolean isComplete;
    /**
     * Exists only for the sake of JPA
     */
    public Person(){}

    /**
     * Creates a new instance with the specified username, password, first and last name, date of birth and email
     * @param username The person's username
     * @param password The person's password
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @param emailAddress The person's email
     * @param dateOfBirth The person's date of birth
     */
    public Person(String username, String password, String firstName, String lastName, String emailAddress, String dateOfBirth, int roleId, boolean isComplete){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.roleId = roleId;
        this.isComplete = isComplete;
    }

    public String toString() {
        return String.format(
                "Person[username='%s', password='%s', firstName='%s', lastName='%s', email='%s', dateOfBirth='%s']",
                username, password, firstName, lastName, emailAddress, dateOfBirth);
    }

  @Override
  public Long getPersonId() {
    return personId;
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
    public String getDateOfBirth() {
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

    @Override
    public Boolean getIsComplete() {
        return isComplete;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setDateOfBirth(String dateOfBirth) {

    }

    @Override
    public void setRoleId(int roleId) {

    }

    @Override
    public void setIsComplete(boolean isComplete) {

    }
}
