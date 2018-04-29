/**
 * 
 */
package fr.batch.service.writer;

import fr.batch.data.InputParameters;
import fr.batch.data.Report;

/**
 * @author Thierry
 *
 */
public interface IFileWriterService {

	public void write(InputParameters inputParameters, Report traitement);
}
