package com.ta2khu75.quiz.configuration;

import java.util.Arrays;
import java.util.function.Supplier;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.ta2khu75.quiz.model.HTTPMethod;
import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.service.RoleService;
import com.ta2khu75.quiz.service.util.EndpointUtil;
import com.ta2khu75.quiz.service.util.EndpointUtil.EndpointType;
import jakarta.servlet.http.HttpServletRequest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DynamicallySecured implements AuthorizationManager<HttpServletRequest>{
	@NonFinal
	AntPathMatcher pathMatcher = new AntPathMatcher();
	EndpointUtil endpointUtil;
	RoleService roleService;

	private boolean isRootUser(String roleName) {
		return "ROOT".equals(roleName);
	}

	private boolean isPublicEndpoint(String requestUrl, String httpMethod) {
		return Arrays.stream(endpointUtil.getPublicEndpoint(EndpointType.POST))
				.anyMatch(post -> pathMatcher.match(post, requestUrl) && HTTPMethod.POST.name().equals(httpMethod))
				|| Arrays.stream(endpointUtil.getPublicEndpoint(EndpointType.GET)).anyMatch(
						get -> pathMatcher.match(get, requestUrl) && HTTPMethod.GET.name().equals(httpMethod));
	}

	private boolean isAllowedEndpoint(Role role, String requestUrl, String httpMethod) {
		return role.getPermissions().stream().anyMatch(permission -> httpMethod.equals(permission.getMethod().name())
				&& pathMatcher.match(permission.getPath(), requestUrl));
	}

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, HttpServletRequest object) {
		String requestUrl = object.getRequestURI();
		String httpMethod = object.getMethod();
		if (isPublicEndpoint(requestUrl, httpMethod)) {
			return new AuthorizationDecision(true);
		}
		String roleName = authentication.get().getAuthorities().stream().map(t -> t.getAuthority()).toList()
				.getFirst();
		if (roleName.equals("ROLE_ANONYMOUS")) {
			return new AuthorizationDecision(false);
		}
		roleName = roleName.replace("ROLE_", "");
		if (isRootUser(roleName)) {
			return new AuthorizationDecision(true);
		}
		Role role = roleService.readByName(roleName);
		if (isAllowedEndpoint(role, requestUrl, httpMethod)) {
			return new AuthorizationDecision(true);
		}
		return new AuthorizationDecision(false);
	}
}
