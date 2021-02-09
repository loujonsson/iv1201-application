package se.kth.iv1201.recruitment.domain;

/**
 * DTO collects data from Account class
 */
public interface AccountDTO {
    /**
     * Returns the First name of user for the account
     */
    String getFirstName();

    /**
     * Returns the last name of the user for the account
     */
    String getLastName();

    /**
     * Returns the email address of the user for the account
     */
    String getEmailAddress();

    /**
     * Returns the date of birth of the user for the account
     */
    long getDateOfBirth();

    /**
     * Returns the username of the user for the account
     */
    String getUsername();

    /**
     * Returns the password of the user for the account
     */
    String getPassword();

}
