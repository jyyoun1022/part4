package part4.part4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Part4Application {

	public static void main(String[] args) {
		SpringApplication.run(Part4Application.class, args);
	}

}
