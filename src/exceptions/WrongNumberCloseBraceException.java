package exceptions;

public class WrongNumberCloseBraceException extends Exception{
    String message;

    public WrongNumberCloseBraceException() {
    }

    public WrongNumberCloseBraceException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WrongNumberCloseBraceException: " + message;
    }
}
