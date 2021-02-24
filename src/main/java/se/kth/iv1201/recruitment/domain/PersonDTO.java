package se.kth.iv1201.recruitment.domain;

/**
 * Defines all operation that can be performed on a {@link Person} outside the
 * application and domain layers.
 */
public interface PersonDTO {
    /**
     * Returns the First name of the Person
     */
    String getFirstName();

    /**
     * Returns the last name of the Person
     */
    String getLastName();

    /**
     * Returns the email address of the Person
     */
    String getEmailAddress();

    /**
     * Returns the date of birth of the Person
     */
    int getDateOfBirth();

    /**
     * Returns the username of the Person
     */
    String getUsername();

    /**
     * Returns the password of the Person
     */
    String getPassword();

    int getRoleId();


}
