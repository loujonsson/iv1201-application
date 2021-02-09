package se.kth.iv1201.recruitment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account implements AccountDTO{
    @Id
    @Column(name = "ACCOUNT_USERNAME")
    private String username;

    @Column(name = "ACCOUNT_PASSWORD")
    private String password;

    @Column(name= "ACCOUNT_FIRSTNAME")
    private String firstName;

    @Column(name = "ACCOUNT_LASTNAME")
    private String lastName;

    @Column(name = "ACCOUNT_EMAIL")
    private String emailAddress;

    @Column(name = "ACCOUNT_DATEOFBIRTH")
    private String dateOfBirth;

    /**
     * Constructor
     */
    public Account()

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public String getEmailAddress() {
        return null;
    }

    @Override
    public long getDateOfBirth() {
        return 0;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
