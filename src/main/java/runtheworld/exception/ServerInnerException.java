package runtheworld.exception;

/**
 * @author evans 2018/5/6 23:21
 */

public class ServerInnerException extends RuntimeException {
	public ServerInnerException(String message) {
		super(message);
	}

	public ServerInnerException(String message, Throwable cause) {
		super(message, cause);
	}
}
