package com.autodealer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/catalog", "/catalog/**", "/request",
                                                                "/register",
                                                                "/css/**", "/js/**", "/images/**")
                                                .permitAll()
                                                // Admin only - managing core data
                                                .requestMatchers("/admin/cars/**", "/admin/suppliers/**",
                                                                "/admin/prices/**", "/admin/employees/**",
                                                                "/admin/credit-offers/**", "/admin/analytics")
                                                .hasRole("ADMIN")
                                                // Admin and Employee - working with customers and requests
                                                .requestMatchers("/admin/**").hasAnyRole("ADMIN", "EMPLOYEE")
                                                .requestMatchers("/customer/**").hasRole("CUSTOMER")
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .successHandler((request, response, authentication) -> {
                                                        String role = authentication.getAuthorities().iterator().next()
                                                                        .getAuthority();
                                                        if (role.equals("ROLE_CUSTOMER")) {
                                                                response.sendRedirect("/customer/dashboard");
                                                        } else {
                                                                response.sendRedirect("/admin/dashboard");
                                                        }
                                                })
                                                .permitAll())
                                .logout(logout -> logout
                                                // logout links in the templates are plain GET links
                                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                                                .logoutSuccessUrl("/")
                                                .permitAll());

                return http.build();
        }
}
