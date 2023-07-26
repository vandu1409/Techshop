package com.vandu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


import com.vandu.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
//	@Autowired
//	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}
	
	@Autowired
	private CustomSuccessHandler customSuccessHandler;
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests()

				.requestMatchers("/admin/**","dang-ky").permitAll().
//				requestMatchers("/dtdd/**").permitAll().
				requestMatchers("/gio-hang","/profile","danh-sach-yeu-thich").authenticated().requestMatchers("thanh-toan").authenticated()
				.anyRequest()
				.permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/do-login").failureHandler(new LoginFalidHanlder())
				.successHandler(new CustomSuccessHandler()).permitAll().and().logout().logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler()).and().oauth2Login().loginPage("/login")
				.successHandler(authenticationSuccessHandler).permitAll().and().rememberMe().key("uniqueAndSecret")
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(86400)
				.and()

				.build();
	}

}
