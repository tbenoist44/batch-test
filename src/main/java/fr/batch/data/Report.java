/**
 * 
 */
package fr.batch.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Thierry
 *
 */
@Data
public class Report {
	private String inputFile;
	private List<Reference> references = new ArrayList<Reference>();
	private List<Error> errors = new ArrayList<Error>();
	
}
