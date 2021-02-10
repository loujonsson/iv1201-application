package se.kth.iv1201.recruitment.domain;

import se.kth.iv1201.recruitment.util.Util;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "PERSON")
public class Person implements PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_ID")
    private Long id;

    @NotNull(message = "person first name is missing")
    // The regex below should permit only characters, but asterisk is
    // unfortunately also valid.
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Invalid characters in persons first name")
    @Size(min = 2, max = 30, message = "person first name length")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull(message = "person last name is missing")
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Invalid characters in persons last name")
    @Size(min = 2, max = 30, message = "person last name length")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull(message = "person date of birth is missing")
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @NotNull(message = "person email is missing")
    @Column(name = "EMAIL")
    private String email;

    @NotNull(message = "person ssn is missing")
    @Column(name = "SSN")
    private Integer ssn;

    /**
     * Creates a new instance with the specified first and last name, date of birth, email and ssn.
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @param dateOfBirth The person's date of birth
     * @param email The person's email
     * @param ssn The person's ssn
     */
    public Person(String firstName, String lastName, Date dateOfBirth, String email, int ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    /**
     * Required by JPA, should not be used.
     */
    protected Person() {
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public Date getDateOfBirth() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String toString() {
        return Util.toString(this);
    }

}
