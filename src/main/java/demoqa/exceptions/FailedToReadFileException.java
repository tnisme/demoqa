package demoqa.exceptions;

public class FailedToReadFileException extends RuntimeException {
    public FailedToReadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
