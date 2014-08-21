package pl.beriko.ioz.web.exception;

/**
 * User: iru Date: Feb 10, 2010 Time: 5:31:37 PM
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -2268432561214611951L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
