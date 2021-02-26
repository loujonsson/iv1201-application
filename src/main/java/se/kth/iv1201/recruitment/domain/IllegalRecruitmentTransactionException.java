package se.kth.iv1201.recruitment.domain;
/**
 * Thrown whenever an attempt is made to perform a transaction that is not
 * allowed by the recruitment application's business rules.
 */
public class IllegalRecruitmentTransactionException extends Exception {

    /**
     * Creates a new instance with the specified message.
     *
     * @param msg A message explaining why the exception is thrown.
     */
    public IllegalRecruitmentTransactionException(String msg) {
        super(msg);
    }
}

