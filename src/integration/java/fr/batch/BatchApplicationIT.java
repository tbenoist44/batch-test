package fr.batch;

import org.junit.Test;
import org.springframework.boot.SpringApplication;

public class BatchApplicationIT {

	@Test
	public void contextLoads() throws Exception {
		SpringApplication.run(BatchApplication.class, "src/test/resources/input.txt", "JSON", "target/");
	}
}
