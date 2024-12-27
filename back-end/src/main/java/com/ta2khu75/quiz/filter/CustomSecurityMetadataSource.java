package com.ta2khu75.quiz.filter;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.ta2khu75.quiz.exception.UnAuthenticationException;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.service.RoleService;
import com.ta2khu75.quiz.service.util.EndpointUtil;
import com.ta2khu75.quiz.service.util.EndpointUtil.EndpointType;
import com.ta2khu75.quiz.service.util.RedisUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomSecurityMetadataSource  implements FilterInvocationSecurityMetadataSource{
	@NonFinal
	AntPathMatcher pathMatcher = new AntPathMatcher();
	AccountRepository accountRepository;
	EndpointUtil endpointUtil;
	RedisUtil redisUtil;
	RoleService roleService;
	@Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();
        String httpMethod = filterInvocation.getHttpRequest().getMethod();

        // Public endpoint check
        if (isPublicEndpoint(requestUrl, httpMethod)) {
            return null; // null indicates no restriction (public endpoint)
        }

        // Current user check
        String gmail = SecurityUtil.getCurrentUserLogin().orElse(null);
        if (gmail == null || gmail.equals("anonymousUser")) {
            throw new UnAuthenticationException("You must be login");
        }

        Account account = accountRepository.findByEmail(gmail)
                .orElseThrow(() -> new UnAuthenticationException("You must be login website"));

        // Role-based access
        if (isRootUser(account)) {
            return null; // ROOT user has full access
        }

        Role role = redisUtil.read(account.getRole().getId().toString(), Role.class);
        if (role == null) {
            role = roleService.find(account.getRole().getId());
        }

        if (isAllowedEndpoint(role, requestUrl, httpMethod)) {
            return null;
        }

        throw new UnAuthenticationException("Access denied");
    }

    private boolean isRootUser(Account account) {
        return "ROOT".equals(account.getRole().getName());
    }

	private boolean isPublicEndpoint(String bestMatchingPattern, String httpMethod) {
		return Arrays.stream(endpointUtil.getPublicEndpoint(EndpointType.POST)).anyMatch(
				post -> pathMatcher.match(post, bestMatchingPattern) && HttpMethod.POST.name().equals(httpMethod))
				|| Arrays.stream(endpointUtil.getPublicEndpoint(EndpointType.GET)).anyMatch(
						get -> pathMatcher.match(get, bestMatchingPattern) && HttpMethod.GET.name().equals(httpMethod));
	}
    private boolean isAllowedEndpoint(Role role, String requestUrl, String httpMethod) {
        return role.getPermissions().stream()
                .anyMatch(permission -> httpMethod.equals(permission.getMethod().name())
                        && pathMatcher.match(permission.getPath(), requestUrl));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
