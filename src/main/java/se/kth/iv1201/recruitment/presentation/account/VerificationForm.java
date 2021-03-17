package se.kth.iv1201.recruitment.presentation.account;

import se.kth.iv1201.recruitment.util.Util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A form bean for the login form.
 */
public class VerificationForm {
    @NotNull(message = "Field can not be left empty!")
    @Size(min = 2, max = 30, message = "Username must be 2-30 characters")
    private String username;

    @NotNull(message = "Field can not be left empty!")
    @Size(min = 2, max = 30, message = "Password must be 2-30 characters")
    private String password;

    /**
     * @return The username of the login
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username of the login
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The password of the login
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password of the login
     */
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return Util.toString(this);
    }
}