package se.kth.iv1201.recruitment.presentation.account;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import se.kth.iv1201.recruitment.util.Util;

/**
 * A form bean for the account creation form.
 */
class CreateAccountForm {
    @NotEmpty(message = "{create-account.username.missing}")
    @Size(min = 2, max = 30, message = "{create-account.username.length}")
    @Pattern(regexp = "\\p{Alnum}*", message = "{create-account.username.invalid-char}")
    private String username;

    @NotEmpty(message="{create-account.first-name.missing}")
    @Size(min = 2, max = 30, message = "{create-account.first-name.length}")
    @Pattern(regexp = "\\p{Alpha}*", message = "{create-account.first-name.invalid-char}")
    private String firstName;

    @NotEmpty(message="{create-account.last-name.missing}")
    @Size(min = 2, max = 30, message = "{create-account.last-name.length}")
    @Pattern(regexp = "\\p{Alpha}*", message = "{create-account.last-name.invalid-char}")
    private String lastName;

    @NotEmpty(message="{create-account.date-of-birth.missing}")
    @Size(min = 6, max = 6, message = "{create-account.date-of-birth.length}")
    @Pattern(regexp = "[0-9]+", message = "create-account.date-of-birth.invalid-char}")
    private Integer dateOfBirth;

    @NotEmpty(message="{create-account.email.missing}")
    @Size(min = 2, max = 30, message = "{create-account.email.length}")
    private String email;

    @NotEmpty(message="{create-account.password.missing}")
    @Size(min = 2, max = 30, message = "{create-account.password.length}")
    private String password;

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
    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth The dateOfBirth of the account that will be created.
     */
    public void setDateOfBirth(Integer dateOfBirth) {
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
}
