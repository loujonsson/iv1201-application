package se.kth.iv1201.recruitment.domain;

/**
 * DTO collects data from Account class
 */
public interface ApplicantDTO {
    /**
     * Returns the First name of the applicant
     */
    String getFirstName();

    /**
     * Returns the last name of the applicant
     */
    String getLastName();

    /**
     * Returns the email address of the applicant
     */
    String getEmailAddress();

    /**
     * Returns the date of birth of the applicant
     */
    long getDateOfBirth();

    /**
     * Returns the username of the applicant
     */
    String getUsername();

    /**
     * Returns the password of the applicant
     */
    String getPassword();

}
