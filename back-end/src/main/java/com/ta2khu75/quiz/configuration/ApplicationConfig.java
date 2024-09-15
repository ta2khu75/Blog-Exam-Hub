package com.ta2khu75.quiz.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ta2khu75.quiz.interceptor.InterceptorAuthorization;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig implements WebMvcConfigurer {
	private final InterceptorAuthorization interceptorAuthorization;
	private final List<String> MEDIA_TYPES = Arrays.asList(MediaType.APPLICATION_JSON_VALUE);

	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		registry.addMapping("/api/**").allowedOrigins("http://localhost:5173").allowedMethods("*").allowedHeaders("*")
				.allowCredentials(true);
	}

	@Override
	public void addInterceptors(@NonNull InterceptorRegistry registry) {
		registry.addInterceptor(interceptorAuthorization);
	}

	@Bean
	EndpointMediaTypes endpointMediaTypes() {
		return new EndpointMediaTypes(MEDIA_TYPES, MEDIA_TYPES);
	}
}
