/**
 * 
 */
package fr.batch.data;

/**
 * @author Thierry
 *
 */
public class ReferenceException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5157268313874430344L;

	/**
	 * 
	 */
	public ReferenceException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ReferenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ReferenceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ReferenceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ReferenceException(Throwable cause) {
		super(cause);
	}

}
