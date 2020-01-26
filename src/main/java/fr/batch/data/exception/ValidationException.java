/**
 * 
 */
package fr.batch.data.exception;

import org.springframework.boot.ExitCodeGenerator;

/**
 * @author Thierry
 *
 */
public class ValidationException extends RuntimeException implements ExitCodeGenerator {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1125888106042897721L;

	@Override
	public int getExitCode() {
		return 1;
	}

}
