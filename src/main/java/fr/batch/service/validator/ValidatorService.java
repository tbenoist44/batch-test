/**
 * 
 */
package fr.batch.service.validator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import fr.batch.data.InputParameters;
import fr.batch.data.OutputFormat;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thierry
 *
 */
@Slf4j
@Service
public class ValidatorService {
	
	private static final int PARAM_NB = 3;
	private static final int PARAM_INPUT_INDEX = 0;
	private static final int PARAM_FORMAT_INDEX = 1;
	private static final int PARAM_OUTPUT_INDEX = 2;

	public InputParameters validate(String[] args) {
		
		validateNbParam(args);

		Path inputFile = Paths.get(args[PARAM_INPUT_INDEX]);
		validateInputFile(args, inputFile);

		OutputFormat outputFormat = OutputFormat.valueOf(args[PARAM_FORMAT_INDEX]);
		validateOuputFormat(args, outputFormat);

		Path outputPath = Paths.get(args[PARAM_OUTPUT_INDEX]);
		validateOuputPath(args, outputPath);
		
		return new InputParameters(inputFile, outputFormat, outputPath);
	}

	/**
	 * @param args
	 * @param outputPath
	 */
	private void validateOuputPath(String[] args, Path outputPath) {
		if (!Files.exists(outputPath) || !Files.isDirectory(outputPath) || !Files.isWritable(outputPath)) {
			log.error("Le chemin de sortie \"{}\"n'existe pas ou n'a pas les droits d'écritures !!",
					args[PARAM_OUTPUT_INDEX]);
		}
	}

	/**
	 * @param args
	 * @param outputFormat
	 */
	private void validateOuputFormat(String[] args, OutputFormat outputFormat) {
		if (outputFormat == null) {
			log.error("Format \"{}\" non supporté !!", args[PARAM_FORMAT_INDEX]);
		}
	}

	/**
	 * @param args
	 * @param inputFile
	 */
	private void validateInputFile(String[] args, Path inputFile) {
		if (!Files.exists(inputFile) || Files.isDirectory(inputFile) || !Files.isReadable(inputFile)) {
			log.error("Le fichier d'entré \"{}\"n'existe pas ou n'est pas lissible !!", args[PARAM_INPUT_INDEX]);
			System.exit(1);
		}
	}

	/**
	 * @param args
	 */
	private void validateNbParam(String[] args) {
		if (args.length < PARAM_NB) {
			log.error("Paramètre de lancement manquant :");
			log.error("  - Chemin du fichier texte");
			log.error("  - Format de sortie (XML/JSON)");
			log.error("  - Chemin du fichier en sortie");
			System.exit(1);
		}
	}
}
