package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.security.service.CustomUserDetailService;




@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    
    private final CustomUserDetailService userDetailService;

    public WebSecurityConfig(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            request -> request
                            .requestMatchers("/register").permitAll()
                            .requestMatchers("/login").permitAll()
                            .anyRequest().authenticated()
        )
        // .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());

        return http.build();
    }



    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails kshitij = User.withUsername("kshitij")
    //                                 .password("{noop}kp")
    //                                 .roles("USER")
    //                                 .build();

    //     UserDetails raj = User.withUsername("raj")
    //                                 .password(passwordEncoder().encode("raj"))
    //                                 .roles("USER")
    //                                 .build();
        
    //     return new InMemoryUserDetailsManager(kshitij,raj);
    // }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(PAss);


        return provider;
    }
}
