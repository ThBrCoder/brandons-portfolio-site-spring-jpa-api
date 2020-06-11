package com.brandon.portfolio.site.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.brandon.portfolio.site.jwt.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Add a reference to our security data source
	private DataSource securityDataSource;
	private JwtFilter jwtFilter;
	
	@Autowired
	public AuthSecurityConfiguration(DataSource securityDataSource,
			JwtFilter jwtFilter) {
		this.securityDataSource = securityDataSource;
		this.jwtFilter = jwtFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}pass")
			.roles("Role_Administrator");
		*/
		// Use JDBC authentication
		auth.jdbcAuthentication().dataSource(securityDataSource); // No more hardcoding users
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/user").permitAll()
			.antMatchers("/authenticate").permitAll()
			.antMatchers("/usertoken").permitAll()
			// .antMatchers("/api/games").permitAll()
			// .antMatchers("/").permitAll()
			// .antMatchers(HttpMethod.OPTIONS,"/api/games").authenticated()
			// .antMatchers(HttpMethod.OPTIONS,"/api/games/**").authenticated()
			// .antMatchers(HttpMethod.OPTIONS,"/api/games-form/**").authenticated()
			.anyRequest()
			.authenticated()
			.and()
			//.httpBasic();
			.exceptionHandling()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
