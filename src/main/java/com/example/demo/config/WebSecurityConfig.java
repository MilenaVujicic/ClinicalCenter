package com.example.demo.config;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
	// BCrypt po default-u radi 10 rundi hesiranja prosledjene vrednosti.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	
	  @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception { auth.inMemoryAuthentication().withUser("user")
	  .password("password").roles("USER"); }
	 
	// configuration makes sure any request to the application
	// is authenticated with form based login or HTTP basic authentication.
	
	  @Override protected void configure(AuthenticationManagerBuilder auth) throws
	  Exception { 
		  auth.userDetailsService(userDetailsService);
	  
	  }
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	      http
	      	.formLogin()
	        .loginPage("/login.html")
	        .loginProcessingUrl("/perform_login")
	        .defaultSuccessUrl("/index.html",true)
	        .failureUrl("/login.html?error=true");
	      
	      	
	  }
	 
}
