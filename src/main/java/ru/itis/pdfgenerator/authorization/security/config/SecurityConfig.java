package ru.itis.pdfgenerator.authorization.security.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.*;
import ru.itis.pdfgenerator.authorization.security.jwt.*;
import ru.itis.pdfgenerator.authorization.security.token.*;

/**
 * 13.03.2021
 * 03. Spring Security
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenAuthenticationFilter tokenAuthenticationFilter;

	@Autowired
	private JwtLogoutFilter jwtLogoutFilter;

	@Autowired
	private TokenAuthenticationProvider tokenAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http
			.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterAt(jwtLogoutFilter, LogoutFilter.class)
			.authorizeRequests()
			.antMatchers("/api/**/*").authenticated()
			.antMatchers("/login").permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(tokenAuthenticationProvider);
	}
}

