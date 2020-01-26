/**
 * 
 */
package fr.batch.service.validator;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import fr.batch.data.InputParameters;
import fr.batch.data.OutputFormat;
import fr.batch.data.exception.ValidationException;
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

		Path inputFile = validateInputFile(args[PARAM_INPUT_INDEX]);

		OutputFormat outputFormat = validateOuputFormat(args[PARAM_FORMAT_INDEX]);

		Path outputPath = validateOuputPath(args[PARAM_OUTPUT_INDEX]);

		return new InputParameters(inputFile, outputFormat, outputPath);
	}

	/**
	 * @param outputPath
	 */
	private Path validateOuputPath(String param) {
		Path outputPath = null;
		try {
			outputPath = Paths.get(param);
		} catch (InvalidPathException e) {
			log.error("Le chemin de sortie \"{}\" n'est pas valide !!", param);
			throw new ValidationException();
		}
		if (!Files.exists(outputPath) || !Files.isDirectory(outputPath) || !Files.isWritable(outputPath)) {
			log.error("Le chemin de sortie \"{}\" n'existe pas ou n'a pas les droits d'écritures !!", param);
			throw new ValidationException();
		}
		return outputPath;
	}

	/**
	 * @param param
	 */
	private OutputFormat validateOuputFormat(String param) {
		try {
			return OutputFormat.valueOf(param);
		} catch (IllegalArgumentException e) {
			log.error("Format \"{}\" non supporté !!", param);
			throw new ValidationException();
		}
	}

	/**
	 * @param inputFile
	 */
	private Path validateInputFile(String param) {

		Path inputFile = null;
		try {
			inputFile = Paths.get(param);
		} catch (InvalidPathException e) {
			log.error("Le chemin du fichier d'entré \"{}\" n'est pas valide !!", param);
			throw new ValidationException();
		}
		if (!Files.exists(inputFile) || Files.isDirectory(inputFile) || !Files.isReadable(inputFile)) {
			log.error("Le fichier d'entré \"{}\" n'existe pas ou n'est pas lissible !!", param);
			throw new ValidationException();
		}
		return inputFile;
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
			throw new ValidationException();
		}
	}
}
