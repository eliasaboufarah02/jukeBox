package jukeBox.JukeBoxBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring Boot Application
 * @author Elias
 *
 */
@RestController
@SpringBootApplication
public class JukeBoxBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JukeBoxBackendApplication.class, args);
	}
	@RequestMapping("/")
	public String greeting(){
		return "Hello world!";
	}
}
