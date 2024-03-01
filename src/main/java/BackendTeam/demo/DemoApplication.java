package BackendTeam.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication
{
	// Autowired means spring boot will handle this, anything autowired by the same name is shared throughout the application.
	@Autowired
	DemoService demoService;

	@Autowired
	API_Service apiService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
