package se.kth.iv1201.recruitment.presentation.account;

import javax.validation.constraints.*;
import se.kth.iv1201.recruitment.util.Util;

/**
 * A form bean for the account creation form.
 */
class CreateAccountForm {

    @NotEmpty(message = "This field cannot be empty.")
    @Size(min = 2, max = 30, message = "The username should be between 2-30 characters")
    @Pattern(regexp = "\\p{Alnum}*", message = "Only alphanumerical characters are allowed.")
    private String username;

    @NotEmpty(message = "This field cannot be empty.")
    @Size(min = 2, max = 30, message = "The first name should be between 2-30 characters")
    @Pattern(regexp = "\\p{Alpha}*", message = "Only alphabetical characters are allowed")
    private String firstName;

    @NotEmpty(message = "This field cannot be empty.")
    @Size(min = 2, max = 30, message = "The last name should be between 2-30 characters")
    @Pattern(regexp = "\\p{Alpha}*", message = "Only alphabetical characters are allowed")
    private String lastName;

    @NotEmpty(message = "This field cannot be empty.")
    @Size(min = 10, max = 10, message = "Date of birth should be 6 characters on the form YYMMDDXXXX.")
    @Pattern(regexp = "[0-9]+", message = "Only the digits 0-9 are allowed.")
    private String dateOfBirth;

    @NotEmpty(message = "This field cannot be empty.")
    @Size(min = 2, max = 30, message = "Only alphabetical characters are allowed")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotEmpty(message = "This field cannot be empty.")
    @Size(min = 2, max = 30, message = "The password has to be between 2-30 characters.")
    private String password;

    private int roleId = 2;

    /**
     * @return The username of the account that will be created.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username of the account that will be
     *                   created.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The name of the first name of the account that will be created.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The name of the firstName of the account that will be
     *                   created.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The name of the last name of the account that will be created.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The name of the lastName of the account that will be
     *                   created.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The date of birth of the account that will be created.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth The dateOfBirth of the account that will be created.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return The name of the email of the account that will be created.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The name of the email of the account that will be
     *                   created.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The password of the account that will be created.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password of the account that will be
     *                   created.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return Util.toString(this);
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(){
        this.roleId = 2;
    }

    public boolean getIsComplete() {
        return true;
    }
}

