package com.monit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*
EntityScan and EnableJpaRepos both are only required when we declare them outside main package i.e. com.monit
 */

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
@EnableWebSecurity
//@ComponentScan(basePackages = {"com.monit"})
//@EntityScan(basePackages = {"com.monit.model"})
//@EnableJpaRepositories(basePackages = {"com.monit.repository"})
public class EazyBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackendApplication.class, args);
	}

}
