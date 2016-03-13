package exceptions;

public class IncorrectSymbolException extends Exception{
    String message;

    public IncorrectSymbolException() {
    }

    public IncorrectSymbolException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Exception IncorrectSymbolException: " + message;
    }
}
