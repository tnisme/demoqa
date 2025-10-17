package demoqa.exceptions;

public class CanNotCreatePageException extends RuntimeException {
    public CanNotCreatePageException(String message) {
        super(message);
    }
}
