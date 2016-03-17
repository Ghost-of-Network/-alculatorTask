package exceptions;

public class WrongNumberOpenBraceException extends Exception{
    String message;

    public WrongNumberOpenBraceException() {
    }

    public WrongNumberOpenBraceException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WrongNumberOpenBraceException: " + message;
    }
}
