package com.cschoolproject.InvestmentPanel2.Security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource userRepo;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth
//		.jdbcAuthentication()
//			.dataSource(userRepo)
//			.withUser("admin").password("{noop}Admin1").roles("USER");
//	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		return passwordEncoder;
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return  new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/userPanel", "/raport").authenticated()
			.antMatchers("/", "/showAll", "/index", "/registration","/propertyForm").permitAll()
//			.antMatchers("kontakt").permitAll()
//			.antMatchers("index").permitAll()
//			.antMatchers("registration").permitAll()
//			.anyRequest().authenticated()
			
//			.anyRequest().permitAll()

		.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
//		.and()
//			.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logoutSuccess"))
//			.logoutSuccessUrl("/logoutSuccess")
//			.invalidateHttpSession(true)
//			.deleteCookies("JSESSIONID")
		.and()
			.httpBasic();
	}
	

}
