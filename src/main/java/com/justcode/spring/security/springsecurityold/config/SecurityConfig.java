package com.justcode.spring.security.springsecurityold.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("arun")
                .password(encoder().encode("arun"))
                .roles("ADMIN")
                .and()
                .withUser("kiran")
                .password(encoder().encode("kiran"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests()
                .antMatchers("/**")
              //  .hasAnyRole()
                .hasRole("ADMIN") .and()
                .formLogin();*/



        http.authorizeHttpRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/","static/css","static/js").permitAll()
                 .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder encoder() {
       return new BCryptPasswordEncoder();
      // return BCryptPasswordEncoder.class.newInstance();
     //   return NoOpPasswordEncoder.getInstance();
    }
}
