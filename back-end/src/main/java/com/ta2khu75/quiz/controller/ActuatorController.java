package com.ta2khu75.quiz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ta2khu75.quiz.entity.response.EndpointResponse;

import lombok.RequiredArgsConstructor;

@Component
@Endpoint(id = "endpoints")
//@Controller
@RequiredArgsConstructor
//@RequestMapping("${app.api-prefix}/actuator")
public class ActuatorController {
	private final ApplicationContext applicationContext;

	@ReadOperation
//	@GetMapping("/custommappings")
	public ResponseEntity<List<EndpointResponse>> customMappings() {
//		Map<String, Object> mappings = new HashMap<>();
		List<EndpointResponse> endpoints = new ArrayList<>();
		Map<String, RequestMappingHandlerMapping> allRequestMappings = applicationContext
				.getBeansOfType(RequestMappingHandlerMapping.class);
		for (RequestMappingHandlerMapping handlerMapping : allRequestMappings.values()) {
			Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();

			for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
				RequestMappingInfo requestMappingInfo = entry.getKey();
				HandlerMethod handlerMethod = entry.getValue();

				// Extract path from PathPatternsRequestCondition
				PathPatternsRequestCondition pathPatternsCondition = requestMappingInfo.getPathPatternsCondition();
				Set<String> patterns = pathPatternsCondition != null ? pathPatternsCondition.getPatternValues() : null;

				if (patterns != null && !patterns.isEmpty()) {
					if (isExcludedPath(patterns.iterator().next())) {
						continue; // Skip this path
					}
				}
				endpoints.add(new EndpointResponse(patterns.iterator().next(), requestMappingInfo.getMethodsCondition().getMethods().iterator().next().name()));
			}
		}

		return ResponseEntity.ok().body(endpoints);
	}

	private boolean isExcludedPath(String path) {
		// Define paths to exclude
		return path.startsWith("/v3/api-docs") || path.startsWith("v1/api/account") || path.startsWith("/api/v1/auth") || path.startsWith("/error")
				|| path.startsWith("/swagger-ui.html") || path.startsWith("/v3/api-docs.yaml");
		// Add more paths as needed
	}
}