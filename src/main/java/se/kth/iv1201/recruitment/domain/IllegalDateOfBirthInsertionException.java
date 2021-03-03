package se.kth.iv1201.recruitment.domain;

public class IllegalDateOfBirthInsertionException extends Throwable {
    public IllegalDateOfBirthInsertionException(String msg){
        super(msg);
    }
}
