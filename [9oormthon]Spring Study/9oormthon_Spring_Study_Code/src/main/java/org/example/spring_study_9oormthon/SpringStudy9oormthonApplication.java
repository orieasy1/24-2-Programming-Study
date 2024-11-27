package org.example.spring_study_9oormthon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringStudy9oormthonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudy9oormthonApplication.class, args);
	}

}
