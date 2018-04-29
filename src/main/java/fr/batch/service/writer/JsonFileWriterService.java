/**
 * 
 */
package fr.batch.service.writer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.batch.data.InputParameters;
import fr.batch.data.Report;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thierry
 *
 */
@Slf4j
@Service
public class JsonFileWriterService implements IFileWriterService {

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void write(InputParameters inputParameters, Report traitement) {
		Path outputFile = Paths.get(inputParameters.getOutputPath().toString(), "output.json");
		try {
			mapper.writeValue(outputFile.toFile(), traitement);
		} catch (IOException e) {
			log.error("Le fichier \"{}\" n'a pas été correctement écrit", outputFile.toString());
			System.exit(1);
		}
	}
}
