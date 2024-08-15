package com.ta2khu75.quiz.configuration;

import java.io.IOException;

//import org.springframework.http.HttpStatus;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta2khu75.quiz.exception.AdviceException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	private final AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();
	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		delegate.commence(request, response, authException);
		String exceptionString = authException.getMessage();
		AdviceException.ApiResponse apiResponse;
		if (exceptionString.contains("Jwt expired at")) {
			apiResponse = AdviceException.ApiResponse.builder().statusCode(444).success(false)
					.messageError(authException.getMessage()).build();
		} else {
			apiResponse = AdviceException.ApiResponse.builder().statusCode(HttpStatus.SC_UNAUTHORIZED).success(false)
					.messageError(authException.getMessage()).build();
		}
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(444);
		objectMapper.writeValue(response.getWriter(), apiResponse);
	}

}
