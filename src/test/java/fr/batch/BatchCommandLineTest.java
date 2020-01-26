package fr.batch;

import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import fr.batch.data.InputParameters;
import fr.batch.data.OutputFormat;
import fr.batch.data.Report;
import fr.batch.data.exception.ValidationException;
import fr.batch.service.reader.TxtFileReaderService;
import fr.batch.service.validator.ValidatorService;
import fr.batch.service.writer.FileWriterServiceFactory;
import fr.batch.service.writer.IFileWriterService;

/**
 * @author Thierry
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BatchCommandLineTest {

	@Mock
	private ValidatorService validationService;

	@Mock
	private TxtFileReaderService txtFileReaderService;

	@Mock
	private FileWriterServiceFactory fileWriterServiceFactory;

	@Mock
	private IFileWriterService fileWriterService;

	@InjectMocks
	private BatchCommandLine batchCommandLine;

	@Test
	public void testRun() {

		// Given
		Path inputFile = Paths.get("src/test/resources/input.txt");
		Path outputPath = Paths.get("src/test/resources/");
		String[] args = new String[] { inputFile.toString(), OutputFormat.JSON.toString(), outputPath.toString() };
		InputParameters inputParameters = new InputParameters(inputFile, OutputFormat.JSON, outputPath);
		Report traitement = new Report();

		when(validationService.validate(args)).thenReturn(inputParameters);
		when(txtFileReaderService.read(inputParameters)).thenReturn(traitement);
		when(fileWriterServiceFactory.get(OutputFormat.JSON)).thenReturn(fileWriterService);

		// When
		batchCommandLine.run(args);

		// Then
		Mockito.verify(validationService).validate(args);
		Mockito.verify(txtFileReaderService).read(inputParameters);
		Mockito.verify(fileWriterServiceFactory).get(OutputFormat.JSON);
		Mockito.verify(fileWriterService).write(inputParameters, traitement);
	}

	@Test(expected = ValidationException.class)
	public void testRunValidationException() {

		// Given
		Path inputFile = Paths.get("src/test/resources/input.txt");
		Path outputPath = Paths.get("src/test/resources/");
		String[] args = new String[] { inputFile.toString(), OutputFormat.JSON.toString(), outputPath.toString() };

		when(validationService.validate(args)).thenThrow(new ValidationException());

		// When
		batchCommandLine.run(args);

		// Then
		Assert.fail("expected ValidationException");
	}
}
