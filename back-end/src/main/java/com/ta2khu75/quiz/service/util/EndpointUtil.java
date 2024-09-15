package com.ta2khu75.quiz.service.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EndpointUtil {
	@Value("${app.api-prefix}")
	private String apiPrefix;
	private final String[] PUBLIC_POST_ENDPOINT = { "/account", "/auth/login" };
	private final String[] PUBLIC_GET_ENDPOINT = { "/auth/refresh-token", "/account/verify", "/actuator/mappings",
			"/actuator/custommappings", "/auth/logout", "/exam", "/exam/*" };

	public String[] getPublicEndpoint(EndpointType endpointType) {
		switch (endpointType) {
		case POST: {
			return getPrefixedEndpoints(PUBLIC_POST_ENDPOINT);
		}
		case GET: {
			return getPrefixedEndpoints(PUBLIC_GET_ENDPOINT);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + endpointType);
		}
	}

	private String[] getPrefixedEndpoints(String[] endpoints) {
		return Arrays.stream(endpoints).map(endpoint -> apiPrefix + endpoint).toArray(String[]::new);
	}

	public enum EndpointType {
		POST, GET
	}
}
