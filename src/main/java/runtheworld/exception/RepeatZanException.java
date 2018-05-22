package runtheworld.exception;

/**
 * @author evans 2018/5/22 14:54
 */

public class RepeatZanException extends RuntimeException {
    public RepeatZanException(String message) {
        super(message);
    }

    public RepeatZanException(String message, Throwable cause) {
        super(message, cause);
    }
}
