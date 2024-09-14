package com.ta2khu75.quiz.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.UnAuthorizedException;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InterceptorAuthorization implements HandlerInterceptor {
	AccountRepository accountRepository;

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
		if (account.getRole().getName().equals("ROOT")) {
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
		boolean isAllowed = account.getRole().getPermissions().stream().anyMatch(
				permission -> request.getMethod().equals(permission.getMethod().name()) && permission.getPath()
						.equals(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString()));
		if (isAllowed) {
			log.info("Allowed request: " + request.getMethod() + " " + request.getRequestURI());
			return HandlerInterceptor.super.preHandle(request, response, handler);
		} else {
			throw new UnAuthorizedException("UnAuthorized request");
		}
	}
}
