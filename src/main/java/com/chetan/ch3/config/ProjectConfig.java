package com.chetan.ch3.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

	/*
	 * UserDetailsService is implemented by UserDetailsManager intref which
	 * provides: InMemoryUserDetailsManager JdbcUserDetailsManager (our user in DB
	 * so we need this)
	 */

	@Bean
	public JdbcUserDetailsManager userDetailsService() {

		return new JdbcUserDetailsManager(datasource());
	}

	@Bean
	public DataSource datasource() {
		var dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost/spring");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.httpBasic();
		http.csrf().disable();
		http.authorizeRequests().mvcMatchers("/user").permitAll()
		.anyRequest().authenticated();
		return http.build();
	}

}
