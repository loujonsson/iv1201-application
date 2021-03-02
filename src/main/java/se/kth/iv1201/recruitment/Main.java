package se.kth.iv1201.recruitment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication(/*exclude={SecurityAutoConfiguration.class}*/)
public class Main {
	//private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		SpringApplication.run(Main.class,args);
	}

	/**
	 * Hard coded demo test for the business logic through all the layers.
	 * @param
	 * @return
	 */
	/*@Bean
	public CommandLineRunner demo(PersonRepository repository){
		return (args) -> {
			repository.save(new Person("loujons", "123", "Lou", "Jönsson", "loujons@kth.se", 990717));
			repository.save(new Person("agnesal", "234", "Agnes", "Altin", "agnesal@kth.se", 990101));
			repository.save(new Person("person", "234", "Person", "Personsson", "person@kth.se", 990101));
			repository.save(new Person("vernic", "345", "Veronika", "Nicolaou", "vernic@kth.se", 940101));

			log.info("Persons found with findAll():");
			log.info("--------------------------------");
			for(Person Person : repository.findAll()){
				log.info(Person.toString());
			}
			log.info("");

			Person Person = repository.findPersonByUsername("loujons");
			log.info("Person found with findByPersonByUsername('loujons')");
			log.info("---------------------------------------------------------");
			log.info(Person.toString());
			log.info("");

			log.info("Person found with findByDateOfBirth(990101)");
			log.info("----------------------------------------------");
	    	repository.findByDateOfBirth(990101).forEach(birth -> {
				log.info(birth.toString());
			});
			log.info("");
		};
	}*/
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}


}
