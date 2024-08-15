package com.ta2khu75.quiz.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
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

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
	@Value("${app.api-prefix}")
	private String apiPrefix;
	private final String[] PUBLIC_POST_ENDPOINT = { "/account", "/auth/login" };
	private final String[] PUBLIC_GET_ENDPOINT = { "/auth/refresh-token", "/auth/logout", "/exam", "/exam/*", "/brand", "/brand/image/*", "/product",
			"/product/*", "/product/image/*" };
	private final AuthenticationEntryPoint authenticationEntryPoint;
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//config get auth on jwt
	@Bean
	JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtAuthenticationConverter;

		
//		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
//		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("auth");
//		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//		return jwtAuthenticationConverter;
	}

	@Bean
	SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						authz -> authz
								.requestMatchers(HttpMethod.POST,
										Arrays.stream(PUBLIC_POST_ENDPOINT).map(t -> apiPrefix + t)
												.toArray(String[]::new))
								.permitAll().requestMatchers("/").permitAll()
								.requestMatchers(HttpMethod.GET,
										Arrays.stream(PUBLIC_GET_ENDPOINT).map(t -> apiPrefix + t)
												.toArray(String[]::new))
								.permitAll().anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()).authenticationEntryPoint(authenticationEntryPoint))///.authenticationEntryPoint(authenticationEntryPoint))
//				.exceptionHandling(exception -> exception.authenticationEntryPoint(null).accessDeniedHandler(null))
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
