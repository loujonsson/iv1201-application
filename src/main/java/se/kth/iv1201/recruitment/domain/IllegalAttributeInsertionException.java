package se.kth.iv1201.recruitment.domain;

/**
 * Exception thrown when trying to create a new user where username
 * already exists.
 */
//public class IllegalUsernameInsertion extends Exception {
public class IllegalAttributeInsertionException extends Exception {

    /**
     * Creates new instance with specified message.
     *
     * @param msg A message explaining why exception is thrown
     */
    public IllegalAttributeInsertionException(String msg){
        super(msg);
    }
}