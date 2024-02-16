package it.epicode.w6d5.exception;

public class AlreadyAssignedException extends RuntimeException{

    public AlreadyAssignedException(String message) {
        super(message);
    }
}
