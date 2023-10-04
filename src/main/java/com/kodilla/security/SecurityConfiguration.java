package com.kodilla.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/v1/bean")
                        .hasAnyRole("BASIC", "ADVANCED", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/v1/calculator/**")
                        .hasAnyRole("BASIC", "ADVANCED", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/students")
                        .hasAnyRole("ADMIN")
                        .anyRequest().fullyAuthenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user").password("user").roles("BASIC").build();
        UserDetails advanced = User.withDefaultPasswordEncoder()
                .username("advanced").password("advanced").roles("ADVANCED").build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, advanced, admin);
    }
}