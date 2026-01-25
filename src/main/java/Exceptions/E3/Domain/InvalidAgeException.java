package Exceptions.E3.Domain;

public class InvalidAgeException extends Exception {
    public InvalidAgeException() {
        super("Invalid Age");
    }

    public InvalidAgeException(String s) {
        super(s);
    }
}