package com.monit.config;

import com.monit.exceptionHandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /*
        Below two lines are two ends either allow all request to pass through or deny All request either
        authenticated or not. Now we need sweet spot for our use case.
               http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
               http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
         */
        http.requiresChannel(rcc->rcc.anyRequest().requiresInsecure())    //only http are allowed
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance","/myCards","/myLoans")
                .authenticated()
                .requestMatchers("/notices","/contact","/error","/register").permitAll()
        );
        http.formLogin(withDefaults());
        http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        return http.build();
    }

// Commenting as we have created our own customUserDetailsService implementation
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
        /*
        UserDetails user = User.withUsername("user").password("{noop}bcrypt@12345").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$g./CFwfixaPjcrXuN3nRDOCNmhbyDGfHLGzfgjzEc6F4z.Aa8CLam").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
         */
//        now converting with mysql
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();  //Internally uses bcrypt passwordEncoder
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
