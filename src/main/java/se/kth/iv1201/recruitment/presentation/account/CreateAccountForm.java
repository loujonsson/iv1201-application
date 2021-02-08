package se.kth.iv1201.recruitment.presentation.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import se.kth.iv1201.recruitment.util.Util;

class CreateAccountForm {
    @NotBlank(message = "{create-acct.holder-name.missing}")
    // The regex below should permit only characters, but asterisk is
    // unfortunately also valid.
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-acct" + ".first-name" + ".invalid-char}")
    @Size(min = 2, max = 30, message = "{create-acct.first-name.length}")
    private String firstName;

    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-acct" + ".last-name" + ".invalid-char}")
    @Size(min = 2, max = 30, message = "{create-acct.last-name.length}")
    private String lastName;

    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-acct" + ".email" + ".invalid-char}")
    @Size(min = 2, max = 30, message = "{create-acct.email.length}")
    private String email;

    @NotNull(message = "{create-acct.date-of-birth.missing}")
    private Integer dateOfBirth;

    /**
     * @return The initial balance of the account that will be created.
     */
    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth The initial balance of the account that will be created.
     */
    public void setDateOfBirth(Integer dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return The name of the first name of the account that will be created.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The name of the holderName of the account that will be
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
     * @param lastName The name of the holderName of the account that will be
     *                   created.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The name of the email of the account that will be created.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The name of the holderName of the account that will be
     *                   created.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return Util.toString(this);
    }
}
