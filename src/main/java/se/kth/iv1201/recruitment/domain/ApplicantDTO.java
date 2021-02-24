package se.kth.iv1201.recruitment.domain;

/**
 * Defines all operation that can be performed on a {@link Applicant} outside the
 * application and domain layers.
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
    int getDateOfBirth();

    /**
     * Returns the username of the applicant
     */
    String getUsername();

    /**
     * Returns the password of the applicant
     */
    String getPassword();

    int getRoleId();


}
