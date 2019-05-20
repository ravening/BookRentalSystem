package com.booksrental.bookrental.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import com.auth0.jwt.*;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration { // extends WebSecurityConfigurerAdapter {
    @Value(value = "${auth0.apiAudience}")
    private String apiAudience;
    @Value(value = "${auth0.issuer}")
    private String issuer;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        JwtWebSecurityConfigurer
//                .forRS256(apiAudience, issuer)
//                .configure(http)
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/v1/books").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/books").hasAuthority("view:registrations")
//                .antMatchers(HttpMethod.GET, "/api/v1/books/**").hasAuthority("view:registration")
//                .antMatchers(HttpMethod.POST, "/api/v1/rentedbooks").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/rentedbooks").hasAuthority("view:registrations")
//                .antMatchers(HttpMethod.GET, "/api/v1/rentedbooks/**").hasAuthority("view:registration")
//                .antMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/user").hasAuthority("view:registrations")
//                .antMatchers(HttpMethod.GET, "/api/v1/user/**").hasAuthority("view:registration")
//                .anyRequest().authenticated();
//    }
}
