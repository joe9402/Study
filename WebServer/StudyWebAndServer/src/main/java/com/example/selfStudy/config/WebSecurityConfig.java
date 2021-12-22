package com.example.selfStudy.config;

import com.example.selfStudy.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;


@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().httpBasic().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/","/auth/**").permitAll()
                .anyRequest().authenticated();
        // Check book wrong
        //책에는 CorsFilter.class 뒤에 진행하도록 하지만
        //현재 스프링부트에서 그렇게 하면 에러가 발생해서 임의의 다른 filter 뒤로 옮겼다.
        http.addFilterAfter(
                jwtAuthenticationFilter,
                SecurityContextPersistenceFilter.class
        );
    }
}
