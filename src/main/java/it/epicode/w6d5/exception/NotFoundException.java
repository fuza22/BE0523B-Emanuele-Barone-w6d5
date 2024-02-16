package it.epicode.w6d5.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
