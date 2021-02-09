package se.kth.iv1201.recruitment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.recruitment.config.Greeting;
import se.kth.iv1201.recruitment.config.GreetingRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}

	@Bean
	ApplicationRunner applicationRunner(GreetingRepository greetingRepository){
		return args -> {
			greetingRepository.save(new Greeting("Hello"));

			greetingRepository.save(new Greeting("Hi"));
		};
	}

/*
	@Bean
	public DataSource dataSource() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}*/
}

@RestController
class HelloController() {
	private final GreetingRepository greetingRepository;

	@GetMapping("/hello")
	String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/greeting")
	List<Greeting> greetings() {
		return greetingRepository.findAll();
	}

	HelloController(GreetingRepository  greetingRepository){
		this.greetingRepository = greetingRepository;
	}
}

@Entity
class Greetings{
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String message;

	public Greeting(){

	}
	public Greeting(String message){
		this.message = message;
	}
}
