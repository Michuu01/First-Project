package first.project.service;

public class NoSufficientFoundsException extends RuntimeException {
    public NoSufficientFoundsException(String message) {
        super(message);
    }

    public NoSufficientFoundsException() {
        super("Not enough funds!");

    }
}
