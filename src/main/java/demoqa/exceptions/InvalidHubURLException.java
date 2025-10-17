package demoqa.exceptions;

public class InvalidHubURLException extends RuntimeException {
    public InvalidHubURLException(String message, Throwable cause) {
        super(message, cause);
    }
}
