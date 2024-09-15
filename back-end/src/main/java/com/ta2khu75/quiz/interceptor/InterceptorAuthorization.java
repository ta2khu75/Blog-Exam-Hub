package com.ta2khu75.quiz.interceptor;

import java.util.Arrays;

import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.UnAuthorizedException;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.service.util.EndpointUtil;
import com.ta2khu75.quiz.service.util.EndpointUtil.EndpointType;
import com.ta2khu75.quiz.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InterceptorAuthorization implements HandlerInterceptor {
	AccountRepository accountRepository;
	@NonFinal
	AntPathMatcher pathMatcher = new AntPathMatcher();
	EndpointUtil endpointUtil;

	@Override
	@Transactional
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull Object handler) throws Exception {
		String gmail = SecurityUtil.getCurrentUserLogin().orElse(null);
		if (gmail == null || gmail.equals("anonymousUser")) {
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
		Account account = accountRepository.findByEmail(gmail)
				.orElseThrow(() -> new NotFoundException("Account not found with gmail " + gmail));
		if (this.isRootUser(account)) {
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
		String bestMatchingPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		String httpMethod = request.getMethod();
		if (this.isPublicEndpoint(bestMatchingPattern, httpMethod)) {
			log.info("Public endpoint: " + request.getMethod() + " " + request.getRequestURI());
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
		if (this.isAllowedEndpoint(account, bestMatchingPattern, httpMethod)) {
			log.info("Allowed request: " + request.getMethod() + " " + request.getRequestURI());
			return HandlerInterceptor.super.preHandle(request, response, handler);
		} else {
			throw new UnAuthorizedException("UnAuthorized request");
		}
	}

	private boolean isRootUser(Account account) {
		return account.getRole().getName().equals("ROOT");
	}

	private boolean isPublicEndpoint(String bestMatchingPattern, String httpMethod) {
		return Arrays.stream(endpointUtil.getPublicEndpoint(EndpointType.POST)).anyMatch(
				post -> pathMatcher.match(post, bestMatchingPattern) && HttpMethod.POST.name().equals(httpMethod))
				|| Arrays.stream(endpointUtil.getPublicEndpoint(EndpointType.GET)).anyMatch(
						get -> pathMatcher.match(get, bestMatchingPattern) && HttpMethod.GET.name().equals(httpMethod));
	}

	private boolean isAllowedEndpoint(Account account, String bestMatchingPattern, String httpMethod) {
		return account.getRole().getPermissions().stream()
				.anyMatch(permission -> httpMethod.equals(permission.getMethod().name())
						&& pathMatcher.match(permission.getPath(), bestMatchingPattern));
	}
}
