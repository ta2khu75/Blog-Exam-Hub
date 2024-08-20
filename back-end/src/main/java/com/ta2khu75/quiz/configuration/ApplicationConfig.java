package com.ta2khu75.quiz.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.Role;
import com.ta2khu75.quiz.repository.AccountRepository;

@Configuration
public class ApplicationConfig {
	@Bean
	CommandLineRunner init(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (accountRepository.count() == 0)
				accountRepository.save(Account.builder().email("admin").password(passwordEncoder.encode("123"))
						.role(Role.ADMIN).build());
//			else accountRepository.findAll().forEach(account -> {
//				account.setPassword(passwordEncoder().encode(account.getPassword()));
//				accountRepository.save(account);
//			});
		};
	}
	@Bean
	CorsFilter corsFilter() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:5173");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
