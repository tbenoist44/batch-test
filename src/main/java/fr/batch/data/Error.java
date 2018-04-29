/**
 * 
 */
package fr.batch.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Thierry
 *
 */
@Data
@AllArgsConstructor
public class Error {
	private int line;
	private String message;
	private String value;
	
}
