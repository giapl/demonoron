package com.example.demonorn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity {

  private CustomUser customUser;

  @Autowired
  public ConfigSecurity(CustomUser customUser) {
    this.customUser = customUser;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf(csrf -> csrf.disable())
          .authorizeRequests(requests -> requests
              .requestMatchers(HttpMethod.POST, "/register").hasRole("ADMIN")
              .anyRequest().denyAll())
          .userDetailsService(customUser)
          .httpBasic(Customizer.withDefaults());
      return http.build();
    }

  @Bean
  public InMemoryUserDetailsManager userDetailService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    PasswordEncoder encoder = passwordEncoder();

    UserDetails user = User.builder()
        .username("user")
        .password(encoder.encode("123"))
        .roles("USER")
        .build();

    UserDetails admin = User.builder()
        .username("admin")
        .password(encoder.encode("123"))
        .roles("ADMIN")
        .build();

    manager.createUser(user);
    manager.createUser(admin);
    return manager;
  }
}
