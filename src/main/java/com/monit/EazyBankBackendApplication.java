package com.monit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
EntityScan and EnableJpaRepos both are only required when we declare them outside main package i.e. com.monit
 */

@SpringBootApplication
//@EnableWebSecurity
//@ComponentScan(basePackages = {"com.monit"})
//@EntityScan(basePackages = {"com.monit.model"})
//@EnableJpaRepositories(basePackages = {"com.monit.repository"})
public class EazyBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackendApplication.class, args);
	}

}
