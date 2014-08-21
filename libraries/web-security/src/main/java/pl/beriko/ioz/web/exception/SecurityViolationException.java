package pl.beriko.ioz.web.exception;

/**
 * User: iru Date: Feb 11, 2010 Time: 4:15:26 PM
 */
public class SecurityViolationException extends ServiceException {

	private static final long serialVersionUID = 6122550150016690855L;

	public SecurityViolationException() {
	}

	public SecurityViolationException(String message) {
		super(message);
	}

	public SecurityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityViolationException(Throwable cause) {
		super(cause);
	}
}
