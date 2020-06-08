package com.brandon.portfolio.site.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}pass")
			.roles("Role_Administrator");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			// .antMatchers("/").permitAll()
			//.antMatchers(HttpMethod.OPTIONS,"/api/games").authenticated()
			//.antMatchers(HttpMethod.OPTIONS,"/api/games/**").authenticated()
			//.antMatchers(HttpMethod.OPTIONS,"/api/games-form/**").authenticated()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
}
