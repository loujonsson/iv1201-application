package se.kth.iv1201.recruitment.presentation.account;

import javax.validation.constraints.*;
import se.kth.iv1201.recruitment.util.Util;

/**
 * A form bean for the account creation form.
 */
public class CreateAccountForm {

    @NotEmpty(message = "{field.notempty}")
    @Size(min = 2, max = 30, message = "{username.size}")
    @Pattern(regexp = "\\p{Alnum}*", message = "{alphanum.pattern}")
    private String username;

    @NotEmpty(message = "{field.notempty}")
    @Size(min = 2, max = 30, message = "{firstname.size}")
    @Pattern(regexp = "\\p{Alpha}*", message = "{alpha.pattern}")
    private String firstName;

    @NotEmpty(message = "{field.notempty}")
    @Size(min = 2, max = 30, message = "{lastname.size}")
    @Pattern(regexp = "\\p{Alpha}*", message = "{alpha.pattern}")
    private String lastName;

    @NotEmpty(message = "{field.notempty}")
    @Size(min = 10, max = 10, message = "{dateofbirth.size}")
    @Pattern(regexp = "[0-9]+", message = "{regexp.pattern}")
    private String dateOfBirth;

    @NotEmpty(message = "{field.notempty}")
    @Size(min = 2, max = 320, message = "{email.size}")
    @Email(message = "{email.message}")
    private String email;

    @NotEmpty(message = "{field.notempty}")
    @Size(min = 2, max = 30, message = "{password.size}")
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

