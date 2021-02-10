package se.kth.iv1201.recruitment.domain;

import java.util.Date;

/**
 * Defines all operation that can be performed on a {@link Person} outside the
 * application and domain layers.
 */
public interface PersonDTO {
    /**
     * Returns the person's first name
     */
    String getFirstName();

    /**
     * Returns the person's last name
     */
    String getLastName();

    /**
     * Returns the person's date of birth
     */
    Date getDateOfBirth();

    /**
     * Returns the person's email
     */
    String getEmail();

}
