package se.kth.iv1201.recruitment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.iv1201.recruitment.domain.Applicant;
import se.kth.iv1201.recruitment.repository.ApplicantRepository;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		SpringApplication.run(Main.class);
	}

	/**
	 * Hard coded demo test for the business logic through all the layers.
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(ApplicantRepository repository){
		return (args) -> {
			repository.save(new Applicant("loujons", "123", "Lou", "Jönsson", "loujons@kth.se", 990717));
			repository.save(new Applicant("agnesal", "234", "Agnes", "Altin", "agnesal@kth.se", 990101));
			repository.save(new Applicant("person", "234", "Person", "Personsson", "person@kth.se", 990101));
			repository.save(new Applicant("vernic", "345", "Veronika", "Nicolaou", "vernic@kth.se", 940101));

			log.info("Applicants found with findAll():");
			log.info("--------------------------------");
			for(Applicant applicant : repository.findAll()){
				log.info(applicant.toString());
			}
			log.info("");

			Applicant applicant = repository.findApplicantByUsername("loujons");
			log.info("Applicant found with findByApplicantByUsername('loujons')");
			log.info("---------------------------------------------------------");
			log.info(applicant.toString());
			log.info("");

			log.info("Applicant found with findByDateOfBirth(990101)");
			log.info("----------------------------------------------");
	    	repository.findByDateOfBirth(990101).forEach(birth -> {
				log.info(birth.toString());
			});
			log.info("");
		};
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
