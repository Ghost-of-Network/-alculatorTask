package exceptions;

public class InvalidDoubleException extends Exception{
    String message;

    public InvalidDoubleException() {
    }

    public InvalidDoubleException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InvalidDoubleException: " + message;
    } 
}
