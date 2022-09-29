package com.htphuoc.bookstore.config;

import com.htphuoc.bookstore.security.CustomAccessDeniedHandler;
import com.htphuoc.bookstore.security.JwtAuthenticationEntryPoint;
import com.htphuoc.bookstore.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// login api
		http.authorizeRequests().antMatchers("/api/login").permitAll();

		//ROLE
		http.authorizeRequests().antMatchers("/api/roles*").hasRole("ADMIN");

		//USER
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users*")
									.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.POST, "/api/users*")
									.hasAnyRole("ADMIN", "USER")
								.antMatchers(HttpMethod.PUT, "/api/users/*/*")
									.hasAnyRole("ADMIN", "MANAGER", "USER")
								.antMatchers(HttpMethod.DELETE, "/api/users/*")
									.hasAnyRole("ADMIN", "MANAGER");

		//CATEGORY
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/categories*")
									.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.POST, "/api/categories*")
									.hasAnyRole("ADMIN", "MANAGER")
								.antMatchers(HttpMethod.PUT, "/api/categories*")
									.hasAnyRole("ADMIN", "MANAGER")
								.antMatchers(HttpMethod.DELETE, "/api/categories*")
									.hasAnyRole("ADMIN", "MANAGER");

		//AUTHOR
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/authors*")
									.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.POST, "/api/authors*")
									.hasAnyRole("ADMIN", "MANAGER")
								.antMatchers(HttpMethod.PUT, "/api/authors*")
									.hasAnyRole("ADMIN", "MANAGER")
								.antMatchers(HttpMethod.DELETE, "/api/authors*")
									.hasAnyRole("ADMIN", "MANAGER");


		//PUBLISHING COMPANY
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/publishing-companies*")
									.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.POST, "/api/publishing-companies*")
									.hasAnyRole("ADMIN", "MANAGER")
								.antMatchers(HttpMethod.PUT, "/api/publishing-companies*")
									.hasAnyRole("ADMIN", "MANAGER")
								.antMatchers(HttpMethod.DELETE, "/api/publishing-companies*")
									.hasAnyRole("ADMIN", "MANAGER");

		//ORDER
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/orders*")
									.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.POST, "/api/orders*")
									.hasAnyRole("EMPLOYEE", "USER")
								.antMatchers(HttpMethod.PUT, "/api/orders*")
									.hasAnyRole("EMPLOYEE", "USER")
								.antMatchers(HttpMethod.DELETE, "/api/orders*")
									.hasAnyRole("EMPLOYEE", "USER");

		//ORDER DETAIL
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/order-details*")
									.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.POST, "/api/order-details*")
									.hasAnyRole("EMPLOYEE", "USER")
								.antMatchers(HttpMethod.PUT, "/api/order-details*")
									.hasAnyRole("EMPLOYEE", "USER")
								.antMatchers(HttpMethod.DELETE, "/api/order-details*")
									.hasAnyRole("EMPLOYEE", "USER");

		//ORDER STATE
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/order-states*")
									.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.POST, "/api/order-states*")
									.hasAnyRole("MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.PUT, "/api/order-states*")
									.hasAnyRole("MANAGER", "EMPLOYEE")
								.antMatchers(HttpMethod.DELETE, "/api/order-states*")
									.hasAnyRole("MANAGER", "EMPLOYEE");

		http.authorizeRequests().anyRequest().authenticated();

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(authenticationEntryPoint);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
