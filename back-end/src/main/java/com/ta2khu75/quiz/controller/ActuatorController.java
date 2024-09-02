package com.ta2khu75.quiz.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Endpoint(id = "endpoints")
@Slf4j
@RequiredArgsConstructor
public class ActuatorController {
	private final ApplicationContext applicationContext;

	@ReadOperation
	public Map<String, Object> customMappings() {
		Map<String, Object> mappings = new HashMap<>();
		Map<String, RequestMappingHandlerMapping> allRequestMappings = applicationContext
				.getBeansOfType(RequestMappingHandlerMapping.class);
		for (Entry<String, RequestMappingHandlerMapping> entryI : allRequestMappings.entrySet()) {
			Map<RequestMappingInfo, HandlerMethod> handlerMethods = entryI.getValue().getHandlerMethods();
			log.info(entryI.getKey());
			for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
				RequestMappingInfo requestMappingInfo = entry.getKey();
				HandlerMethod handlerMethod = entry.getValue();
//                log.info(handlerMethod.toString());
				// Extract path from PathPatternsRequestCondition
				PathPatternsRequestCondition pathPatternsCondition = requestMappingInfo.getPathPatternsCondition();
				Set<String> patterns = pathPatternsCondition != null ? pathPatternsCondition.getPatternValues() : null;

				if (patterns != null && !patterns.isEmpty()) {
					if (isExcludedPath(patterns.iterator().next())) {
						continue; // Skip this path
					}
				}
				Class<?> beanType = handlerMethod.getBeanType();
                String className = beanType.getSimpleName(); // Get the class name, e.g., "AccountController"
                
                
                // You can also log it
                log.info("Controller Class Name: " + className);

				Map<String, Object> endpointInfo = new HashMap<>();
				endpointInfo.put("path", patterns.iterator().next());
				endpointInfo.put("methods",
						requestMappingInfo.getMethodsCondition() != null
								? requestMappingInfo.getMethodsCondition().getMethods()
								: "unknown");
				endpointInfo.put("params",
						requestMappingInfo.getParamsCondition() != null
								? requestMappingInfo.getParamsCondition().getExpressions()
								: "unknown");
				endpointInfo.put("description", handlerMethod.getMethod().getName());

				mappings.put(handlerMethod.getMethod().getName(), endpointInfo);
			}
		}

		return mappings;
	}
//	@ReadOperation
//	public ResponseEntity<List<EndpointResponse>> customMappings() {
//		List<EndpointResponse> endpoints = new ArrayList<>();
//		Map<String, RequestMappingHandlerMapping> allRequestMappings = applicationContext
//				.getBeansOfType(RequestMappingHandlerMapping.class);
//		for (RequestMappingHandlerMapping handlerMapping : allRequestMappings.values()) {
//			Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
//
//			for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
//				RequestMappingInfo requestMappingInfo = entry.getKey();
//				HandlerMethod handlerMethod = entry.getValue();
//
//				PathPatternsRequestCondition pathPatternsCondition = requestMappingInfo.getPathPatternsCondition();
//				Set<String> patterns = pathPatternsCondition != null ? pathPatternsCondition.getPatternValues() : null;
//
//				if (patterns != null && !patterns.isEmpty()) {
//					if (isExcludedPath(patterns.iterator().next())) {
//						continue;
//					}
//				}
//				endpoints.add(new EndpointResponse(patterns.iterator().next(),
//						requestMappingInfo.getMethodsCondition().getMethods().iterator().next().name()));
//			}
//		}
//
//		return ResponseEntity.ok().body(endpoints);
//	}

	private boolean isExcludedPath(String path) {
		return path.startsWith("/v3/api-docs") || path.startsWith("/api/v1/account") || path.startsWith("/api/v1/auth")
				|| path.startsWith("/error") || path.startsWith("/swagger-ui.html")
				|| path.startsWith("/v3/api-docs.yaml");
	}
}