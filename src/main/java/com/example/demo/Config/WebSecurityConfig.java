package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home","/bookTime", "/bookDetails/{book}", "/bookDetails", "/index", "/#", "#", "/#slides", "stylesheet", "/", "/slide2.png", "/slide1.png", "/slide4.png", "/slide3.png", "/slide5.png", "/slide6.png").permitAll()
                .antMatchers("/photo1.png", "/photo2.png", "/photo3.png", "/photo4.png", "/photo5.png", "/photo6.png", "/photo7.png", "/photo8.png", "/photo9.png", "/photo10.png", "/img/", "/logo.png", "/resource/**","/*.png", "/team.jpeg", "/*.jpeg", "img/logo.png", "/parallax1.jpg", "/parallax2.png", "/style.ccs", "/style2.css" ).permitAll()
                .antMatchers("/service1.jpg", "/service2.jpg", "/service3.jpg", "/logo.png" ).permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}