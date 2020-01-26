/**
 * 
 */
package fr.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.batch.data.InputParameters;
import fr.batch.data.Report;
import fr.batch.service.reader.TxtFileReaderService;
import fr.batch.service.validator.ValidatorService;
import fr.batch.service.writer.FileWriterServiceFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thierry
 *
 */
@Component
@Slf4j
public class BatchCommandLine implements CommandLineRunner {

	@Autowired
	private ValidatorService validationService;

	@Autowired
	private TxtFileReaderService txtFileReaderService;

	@Autowired
	private FileWriterServiceFactory fileWriterServiceFactory;

	/**
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) {
		log.info("Démarrage du batch");

		log.info("Validation des paramètres d'entrées");
		InputParameters inputParameters = null;
		inputParameters = validationService.validate(args);

		log.info("Lecture du fichier d'entrées");
		Report traitement = txtFileReaderService.read(inputParameters);

		log.info("Ecriture du fichier de sortie");
		fileWriterServiceFactory.get(inputParameters.getOutputFormat()).write(inputParameters, traitement);

		log.info("Fin du batch");
	}
}
