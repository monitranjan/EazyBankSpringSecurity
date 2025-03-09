package com.monit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /*
        Below two lines are two ends either allow all request to pass through or deny All request either
        authenticated or not. Now we need sweet spot for our use case.
               http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
               http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
         */
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance","/myCards","/myLoans")
                .authenticated()
                .requestMatchers("/notices","/contact","/error").permitAll()
        );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        /*
        UserDetails user = User.withUsername("user").password("{noop}bcrypt@12345").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$g./CFwfixaPjcrXuN3nRDOCNmhbyDGfHLGzfgjzEc6F4z.Aa8CLam").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
         */
//        now converting with mysql
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();  //Internally uses bcrypt passwordEncoder
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
