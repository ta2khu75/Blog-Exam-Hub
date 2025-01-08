package com.ta2khu75.quiz.service.util;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class EndpointUtil {
	@Value("${app.api-prefix}")
	private String apiPrefix;
	private final Set<String> PUBLIC_POST_ENDPOINT = Set.of("/auth/login", "/auth/register");
	private final Set<String> PUBLIC_GET_ENDPOINT = Set.of("/auth/verify", "/auth/logout");
//	private final String[] PUBLIC_GET_ENDPOINT = { "/accounts/*","/auth/refresh-token", "/account/verify", "/actuator/mappings",
//			"/actuator/custommappings", "/auth/logout", "/account/*/details",  "/blog", "/blog/**", "/exam-category","/blog-tag", "/exam", "/exam/*" };

	public Set<String> getPublicEndpoint(RequestMethod requestMethod) {
		switch (requestMethod) {
		case POST: {
			return getPrefixedEndpoints(PUBLIC_POST_ENDPOINT);
		}
		case GET: {
			return getPrefixedEndpoints(PUBLIC_GET_ENDPOINT);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + requestMethod);
		}
	}

	private Set<String> getPrefixedEndpoints(Set<String> endpoints) {
		return endpoints.stream().map(endpoint -> apiPrefix + endpoint).collect(Collectors.toSet());
	}
}
