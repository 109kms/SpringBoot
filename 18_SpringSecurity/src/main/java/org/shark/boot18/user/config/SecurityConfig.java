package org.shark.boot18.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    UserDetails user = User.withUsername("goodee")
        .password("{noop}1234")  // {noop} : 암호화를 하지 않겠다는 뜻
        .authorities("ROLE_USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }
  
}
