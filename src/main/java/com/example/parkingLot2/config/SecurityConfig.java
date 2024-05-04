//package com.example.parkingLot2.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfig {
//
//  @Bean
//  public AccessDeniedHandler customAccessDeniedHandler() {
//    return new CustomAccessDeniedHandler();
//  }
//
//  @Bean
//  public SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter() {
//    return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
//      @Override
//      public void configure(HttpSecurity http) throws Exception {
//        http
//            .exceptionHandling()
//            .accessDeniedHandler(customAccessDeniedHandler())
//            .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
//            .and()
//            .authorizeRequests()
//            .antMatchers("/api/authentication/register").permitAll()
//            .antMatchers("/api/private/**").authenticated()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic()
//            .and()
//            .csrf().disable(); // Disable CSRF for simplicity, consider enabling in production
//      }
//    };
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails user = User.withUsername("user")
//        .password(passwordEncoder().encode("password"))
//        .roles("USER")
//        .build();
//
//    UserDetails admin = User.withUsername("admin")
//        .password(passwordEncoder().encode("admin"))
//        .roles("ADMIN")
//        .build();
//
//    return new InMemoryUserDetailsManager(user, admin);
//  }
//}
//
