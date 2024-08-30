package com.ta2khu75.quiz.configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.Role;
import com.ta2khu75.quiz.interceptor.InterceptorAuthorization;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.RoleRepository;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

	@Bean
	CommandLineRunner init(AccountRepository accountRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			if (accountRepository.count() == 0) {
				Account account = new Account();
				account.setEmail("root@g.com");
				account.setPassword(passwordEncoder.encode("123"));
				account.setDisplayName("root");
				account.setFirstName("root");
				account.setLastName("root");
				account.setEnabled(true);
				account.setBirthday(LocalDate.now());
				account.setRole(roleRepository.save(Role.builder().name("ROOT").build()));
				roleRepository.save(Role.builder().name("USER").build());
				roleRepository.save(Role.builder().name("ADMIN").build());
				accountRepository.save(account);
			}
		};
//			else accountRepository.findAll().forEach(account -> {
//					account.setPassword(passwordEncoder().encode(account.getPassword()));
//				accountRepository.save(account);
//			});
	}

	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		registry.addMapping("/api/**").allowedOrigins("http://localhost:5173").allowedMethods("*").allowedHeaders("*")
				.allowCredentials(true);
	}

	@Override
	public void addInterceptors(@NonNull InterceptorRegistry registry) {
		registry.addInterceptor(new InterceptorAuthorization());
	}

	private static final List<String> MEDIA_TYPES = Arrays.asList(MediaType.APPLICATION_JSON_VALUE);

	@Bean
	EndpointMediaTypes endpointMediaTypes() {
		return new EndpointMediaTypes(MEDIA_TYPES, MEDIA_TYPES);
	}
}
