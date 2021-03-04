package se.kth.iv1201.recruitment.domain;

/**
 * Thrown whenever an attempt is made to insert an attribute
 * that already exists.
 */
public class IllegalAttributeInsertionException extends Throwable {
    /**
     * Creates a new instance with the specified message.
     *
     * @param msg A message explaining why the exception is thrown.
     */
    public IllegalAttributeInsertionException(String msg){
        super(msg);
    }
}
