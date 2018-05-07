package runtheworld.exception;

/**
 * @author evans 2018/5/6 23:50
 */

public class InvalidDanmuException extends RuntimeException{
	public InvalidDanmuException(String message) {
		super(message);
	}

	public InvalidDanmuException(String message, Throwable cause) {
		super(message, cause);
	}
}
