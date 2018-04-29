/**
 * 
 */
package fr.batch.service.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.batch.data.Error;
import fr.batch.data.InputParameters;
import fr.batch.data.Reference;
import fr.batch.data.ReferenceException;
import fr.batch.data.Report;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thierry
 *
 */
@Slf4j
@Service
public class TxtFileReaderService {

	public Report read(InputParameters inputParameters) {
		Report traitement = new Report();
		traitement.setInputFile(inputParameters.getInputFile().getFileName().toString());
		try (Stream<String> stream = Files.lines(inputParameters.getInputFile())) {
			AtomicInteger lineIndex = new AtomicInteger(1);
			stream.forEach(line -> {
				try {
					traitement.getReferences().add(Reference.fromString(line));
				} catch (ReferenceException e) {
					traitement.getErrors().add(new Error(lineIndex.get(), e.getMessage(), line));
				}
				lineIndex.incrementAndGet();
			});
		} catch (IOException e) {
			log.error("Le fichier \"{}\" n'a pas été lu correctement", inputParameters.getInputFile().toString());
			System.exit(1);
		}

		return traitement;
	}
}
