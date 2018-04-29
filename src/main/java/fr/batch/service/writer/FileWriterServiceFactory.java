/**
 * 
 */
package fr.batch.service.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.batch.data.OutputFormat;

/**
 * @author Thierry
 *
 */
@Service
public class FileWriterServiceFactory {

	@Autowired
	private JsonFileWriterService jsonFileWriterService;

	@Autowired
	private XmlFileWriterService xmlFileWriterService;

	public IFileWriterService get(OutputFormat outputFormat) {

		if (OutputFormat.XML.equals(outputFormat)) {
			return xmlFileWriterService;
		} else {
			return jsonFileWriterService;
		}
	}
}
