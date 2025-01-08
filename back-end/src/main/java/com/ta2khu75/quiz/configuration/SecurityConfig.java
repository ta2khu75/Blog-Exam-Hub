package com.ta2khu75.quiz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import com.ta2khu75.quiz.repository.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	private final AccessDeniedHandler accessDeniedHandler;
	private final AuthorizationManager<HttpServletRequest> authorizationManager;
	private final AuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// config get auth on jwt
	@Bean
	JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

	@Bean
	SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new AuthorizationFilter(authorizationManager), AuthorizationFilter.class)
				.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler))
				.authorizeHttpRequests(authz ->
//				authz.requestMatchers("/ws").permitAll()
//						.requestMatchers(HttpMethod.POST, endpointUtil.getPublicEndpoint(EndpointType.POST)).permitAll()
//						.requestMatchers(HttpMethod.GET, endpointUtil.getPublicEndpoint(EndpointType.GET)).permitAll()
//						.requestMatchers("/").permitAll().anyRequest().authenticated()
				authz.anyRequest().permitAll()).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())
						.authenticationEntryPoint(authenticationEntryPoint))
				.formLogin(login -> login.disable());
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService(AccountRepository accountRepository) {
		return email -> {
			return accountRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		};
	}

}
