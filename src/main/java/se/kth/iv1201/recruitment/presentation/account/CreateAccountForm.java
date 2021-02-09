package se.kth.iv1201.recruitment.presentation.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import se.kth.iv1201.recruitment.util.Util;

class CreateAccountForm {
    @NotBlank(message = "{create-account.holder-name.missing}")
    // The regex below should permit only characters, but asterisk is
    // unfortunately also valid.
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-account" + ".first-name" + ".invalid-char}")
    @Size(min = 2, max = 30, message = "{create-account.first-name.length}")
    private String firstName;

    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-account" + ".last-name" + ".invalid-char}")
    @Size(min = 2, max = 30, message = "{create-account.last-name.length}")
    private String lastName;

    @NotNull(message = "{create-account.date-of-birth.missing}")
    private Integer dateOfBirth;

    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-account" + ".email" + ".invalid-char}")
    @Size(min = 2, max = 30, message = "{create-account.email.length}")
    private String email;

    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-account" + ".password" + ".invalid-char}")
    @Size(min = 2, max = 30, message = "{create-account.password.length}")
    private String password;

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