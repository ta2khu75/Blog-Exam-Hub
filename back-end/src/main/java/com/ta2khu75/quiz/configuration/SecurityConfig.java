package com.ta2khu75.quiz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.service.util.EndpointUtil;
import com.ta2khu75.quiz.service.util.EndpointUtil.EndpointType;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	private final EndpointUtil endpointUtil;
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
	SecurityFilterChain securityFilter(HttpSecurity http, RoleRepository roleRepository) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authz -> authz
						.requestMatchers(HttpMethod.POST, endpointUtil.getPublicEndpoint(EndpointType.POST)).permitAll()
						.requestMatchers(HttpMethod.GET, endpointUtil.getPublicEndpoint(EndpointType.GET)).permitAll()
						.requestMatchers("/").permitAll().anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())
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
