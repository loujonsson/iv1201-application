package se.kth.iv1201.recruitment.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * https://spring.io/guides/gs/accessing-data-jpa/
 */
@Entity
//@Table(name = "APPLICANT")
public class Applicant implements ApplicantDTO {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "APPLICANT_USERNAME")
    private String username;


   // @NotNull(message = "person username is missing")
  //  @Column(name = "APPLICANT_PASSWORD")
    private String password;

    // The regex below should permit only characters, but asterisk is
    // unfortunately also valid.
   // @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Invalid characters in first name")
    //@Size(min = 2, max = 30, message = "person first name")
    //@Column(name= "APPLICANT_FNAME")
    private String firstName;

  //  @NotNull(message = "person last name is missing")
   // @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Invalid characters in persons last name")
   // @Size(min = 2, max = 30, message = "person last name length")
    //@Column(name = "APPLICANT_LNAME")
    private String lastName;

//    @NotNull(message = "person email is missing")
   // @Column(name = "EMAIL")
    // @Column(name = "APPLICANT_EMAIL")
    private String emailAddress;

  //  @NotNull(message = "person date of birth is missing")
   // @Column(name = "DATE_OF_BIRTH")
    // @Column(name = "APPLICANT_DATEOFBIRTH")
    private int dateOfBirth;

    /**
     * Exists only for the sake of JPA
     */
    protected Applicant(){}


    /**
     * Creates a new instance with the specified username, password, first and last name, date of birth and email
     * @param username The person's username
     * @param password The person's password
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @param emailAddress The person's email
     * @param dateOfBirth The person's date of birth
     */
    public Applicant(String username, String password, String firstName, String lastName, String emailAddress, int dateOfBirth){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
    }

    public String toString() {
        return String.format(
                "Applicant[username='%s', password='%s', firstName='%s', lastName='%s', email='%s', dateOfBirth='%s']",
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
}
