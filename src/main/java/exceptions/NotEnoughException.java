package exceptions;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

public class NotEnoughException extends Exception {

    public NotEnoughException() {
    }

    public NotEnoughException(String message) {
        super(message);
    }
}
