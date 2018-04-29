/**
 * 
 */
package fr.batch.data;

import java.nio.file.Path;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Thierry
 *
 */
@Data
@AllArgsConstructor
public class InputParameters {

	private Path inputFile;
	
	private OutputFormat outputFormat;
	
	private Path outputPath;
}
