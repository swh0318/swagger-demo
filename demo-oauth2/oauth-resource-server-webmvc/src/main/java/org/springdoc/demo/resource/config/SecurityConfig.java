package org.springdoc.demo.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authz -> authz
				.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
				.requestMatchers(HttpMethod.GET, "/user/info", "/api/foos/**")
				.hasAuthority("SCOPE_springdoc.read")
				.requestMatchers(HttpMethod.POST, "/api/foos")
				.hasAuthority("SCOPE_springdoc.write")
				.anyRequest()
				.authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
		return http.build();
	}

}