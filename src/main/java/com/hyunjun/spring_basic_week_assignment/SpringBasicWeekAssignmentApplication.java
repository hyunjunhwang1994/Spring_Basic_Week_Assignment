package com.hyunjun.spring_basic_week_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class SpringBasicWeekAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicWeekAssignmentApplication.class, args);
	}

}
