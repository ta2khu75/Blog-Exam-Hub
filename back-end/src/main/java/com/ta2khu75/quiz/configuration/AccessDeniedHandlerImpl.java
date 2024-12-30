package com.ta2khu75.quiz.configuration;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta2khu75.quiz.exception.AdviceException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	private final ObjectMapper objectMapper;
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		AdviceException.ApiResponse apiResponse=AdviceException.ApiResponse.builder().statusCode(403).success(false).messageError(accessDeniedException.getMessage()).build();
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(403);
		objectMapper.writeValue(response.getWriter(), apiResponse);
	}

}
