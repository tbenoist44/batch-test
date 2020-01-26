/**
 * 
 */
package fr.batch.service.validator;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import fr.batch.data.InputParameters;
import fr.batch.data.OutputFormat;
import fr.batch.data.exception.ValidationException;

/**
 * @author Thierry
 *
 */
public class ValidatorServiceTest {

	@Test
	public void testValidate() {
		// Given
		Path inputFile = Paths.get("src/test/resources/input.txt");
		Path outputPath = Paths.get("src/test/resources/");
		String[] args = new String[] { inputFile.toString(), OutputFormat.JSON.toString(), outputPath.toString() };

		// When
		InputParameters inputParameters = new ValidatorService().validate(args);

		// Then
		Assert.assertEquals(inputFile, inputParameters.getInputFile());
		Assert.assertEquals(OutputFormat.JSON, inputParameters.getOutputFormat());
		Assert.assertEquals(outputPath, inputParameters.getOutputPath());
	}

	@Test(expected = ValidationException.class)
	public void testValidateWrongNbParam() {
		// Given
		Path inputFile = Paths.get("src/test/resources/input.txt");
		Path outputPath = Paths.get("src/test/resources/");
		String[] args = new String[] { inputFile.toString(), outputPath.toString() };

		// When
		new ValidatorService().validate(args);

		// Then
		Assert.fail("expected ValidationException");
	}

	@Test(expected = ValidationException.class)
	public void testValidateWrongInputFile() {
		// Given
		Path outputPath = Paths.get("src/test/resources/");
		String[] args = new String[] { "fdsf?%dsljfglds", OutputFormat.JSON.toString(), outputPath.toString() };

		// When
		new ValidatorService().validate(args);

		// Then
		Assert.fail("expected ValidationException");
	}

	@Test(expected = ValidationException.class)
	public void testValidateInputFileNotExist() {
		// Given
		Path inputFile = Paths.get("src/test/resources/inputNotExist.txt");
		Path outputPath = Paths.get("src/test/resources/");
		String[] args = new String[] { inputFile.toString(), OutputFormat.JSON.toString(), outputPath.toString() };

		// When
		new ValidatorService().validate(args);

		// Then
		Assert.fail("expected ValidationException");
	}

	@Test(expected = ValidationException.class)
	public void testValidateInputFileIsDirectory() {
		// Given
		Path inputFile = Paths.get("src/test/resources/");
		Path outputPath = Paths.get("src/test/resources/");
		String[] args = new String[] { inputFile.toString(), OutputFormat.JSON.toString(), outputPath.toString() };

		// When
		new ValidatorService().validate(args);

		// Then
		Assert.fail("expected ValidationException");
	}

	@Test(expected = ValidationException.class)
	public void testValidateWrongOutputFormat() {
		// Given
		Path inputFile = Paths.get("src/test/resources/input.txt");
		Path outputPath = Paths.get("src/test/resources/");
		String[] args = new String[] { inputFile.toString(), "WrongOutputFormat", outputPath.toString() };

		// When
		new ValidatorService().validate(args);

		// Then
		Assert.fail("expected ValidationException");
	}

	@Test(expected = ValidationException.class)
	public void testValidateWrongOuputPath() {
		// Given
		Path inputFile = Paths.get("src/test/resources/input.txt");
		String[] args = new String[] { inputFile.toString(), OutputFormat.JSON.toString(), "fdsf?%dsljfglds" };

		// When
		new ValidatorService().validate(args);

		// Then
		Assert.fail("expected ValidationException");
	}

	@Test(expected = ValidationException.class)
	public void testValidateOutputFileNotExist() {
		// Given
		Path inputFile = Paths.get("src/test/resources/input.txt");
		Path outputPath = Paths.get("src/test/resourcesNotExist/");
		String[] args = new String[] { inputFile.toString(), OutputFormat.JSON.toString(), outputPath.toString() };

		// When
		new ValidatorService().validate(args);

		// Then
		Assert.fail("expected ValidationException");
	}

	@Test(expected = ValidationException.class)
	public void testValidateOutputFileNotDirectory() {
		// Given
		Path inputFile = Paths.get("src/test/resources/input.txt");
		Path outputPath = Paths.get("src/test/resources/input.txt");
		String[] args = new String[] { inputFile.toString(), OutputFormat.JSON.toString(), outputPath.toString() };

		// When
		new ValidatorService().validate(args);

		// Then
		Assert.fail("expected ValidationException");
	}

}
