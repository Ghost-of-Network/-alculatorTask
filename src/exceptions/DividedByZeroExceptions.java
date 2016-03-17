package exceptions;

public class DividedByZeroExceptions extends Exception{
    String message;

    public DividedByZeroExceptions() {
    }

    public DividedByZeroExceptions(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DividedByZeroExceptions: " + message;
    }
    
}
