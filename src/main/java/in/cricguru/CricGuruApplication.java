package in.cricguru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "in.cricguru.*")
@EnableCaching
public class CricGuruApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricGuruApplication.class, args);
	}

}
