package se.kth.iv1201.recruitment.domain;

public class IllegalEmailInsertionException extends Throwable {
    public IllegalEmailInsertionException(String msg){
        super(msg);
    }
}
