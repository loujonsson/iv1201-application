package se.kth.iv1201.recruitment.domain;

public class IllegalUsernameInsertionException extends Throwable {
    public IllegalUsernameInsertionException(String msg){
        super(msg);
    }
}
