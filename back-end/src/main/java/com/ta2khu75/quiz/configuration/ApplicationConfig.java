package com.ta2khu75.quiz.configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.interceptor.InterceptorAuthorization;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.HTTPMethod;
import com.ta2khu75.quiz.model.entity.Permission;
import com.ta2khu75.quiz.model.entity.PermissionGroup;
import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.PermissionGroupRepository;
import com.ta2khu75.quiz.repository.PermissionRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.util.StringUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig implements WebMvcConfigurer {
	@Value("${app.api-prefix}")
	private String apiPrefix;
	private final InterceptorAuthorization interceptorAuthorization;

	@Bean
	CommandLineRunner init(AccountRepository accountRepository, RoleRepository roleRepository,
			PermissionRepository permissionRepository, PermissionGroupRepository permissionGroupRepository,
			PasswordEncoder passwordEncoder, ApplicationContext applicationContext) {
		return args -> {
			if (accountRepository.count() == 0) {
				initPermission(permissionRepository, permissionGroupRepository, applicationContext);
				Account account = Account.builder().email("root@g.com").password(passwordEncoder.encode("123"))
						.displayName("root").firstName("root").lastName("root").enabled(true).birthday(LocalDate.now())
						.role(initRole(roleRepository)).build();
				accountRepository.save(account);
			}
		};
	}

	private Role initRole(RoleRepository roleRepository) {
		List<Role> roles = List.of(Role.builder().name("USER").build(), Role.builder().name("ADMIN").build(),
				Role.builder().name("ROOT").build());
		roles = roleRepository.saveAll(roles);
		return roles.stream().filter(role -> "ROOT".equals(role.getName())).findFirst()
				.orElseThrow(() -> new NotFoundException("Not found role name ROOT"));
	}

	@SuppressWarnings({ "null" })
	private void initPermission(PermissionRepository permissionRepository,
			PermissionGroupRepository permissionGroupRepository, ApplicationContext applicationContext) {
		Map<String, RequestMappingHandlerMapping> allRequestMappings = applicationContext
				.getBeansOfType(RequestMappingHandlerMapping.class);
		for (RequestMappingHandlerMapping handlerMapping : allRequestMappings.values()) {
			Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
			for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
				RequestMappingInfo requestMappingInfo = entry.getKey();
				HandlerMethod handlerMethod = entry.getValue();

				// Extract path from PathPatternsRequestCondition
				PathPatternsRequestCondition pathPatternsCondition = requestMappingInfo.getPathPatternsCondition();
				String path = pathPatternsCondition.getPatternValues().iterator().next();// : null;
				if (isExcludedPath(path)) {
					continue; // Skip this path
				}
				Class<?> beanType = handlerMethod.getBeanType();
				String permissionGroup = StringUtil.convertCamelCaseToReadable(beanType.getSimpleName());
				PermissionGroup group = permissionGroupRepository.findByName(permissionGroup);
				if (group == null) {
					group = permissionGroupRepository.save(PermissionGroup.builder().name(permissionGroup).build());
				}
				@SuppressWarnings("null")
				Permission permission = Permission.builder()
						.name(StringUtil.convertCamelCaseToReadable(handlerMethod.getMethod().getName())).path(path)
						.method(HTTPMethod.valueOf(
								requestMappingInfo.getMethodsCondition().getMethods().iterator().next().name()))
						.permissionGroup(group).build();
				permissionRepository.save(permission);
			}
		}
	}

	private boolean isExcludedPath(String path) {
		return path.startsWith("/v3/api-docs") || path.startsWith("%s/account".formatted(apiPrefix))
				|| path.startsWith("%s/auth".formatted(apiPrefix)) || path.startsWith("/error")
				|| path.startsWith("/swagger-ui.html") || path.startsWith("/v3/api-docs.yaml");
	}

	private static final List<String> MEDIA_TYPES = Arrays.asList(MediaType.APPLICATION_JSON_VALUE);

	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		registry.addMapping("/api/**").allowedOrigins("http://localhost:5173").allowedMethods("*").allowedHeaders("*")
				.allowCredentials(true);
	}

	@Override
	public void addInterceptors(@NonNull InterceptorRegistry registry) {
		registry.addInterceptor(interceptorAuthorization);
	}

	@Bean
	EndpointMediaTypes endpointMediaTypes() {
		return new EndpointMediaTypes(MEDIA_TYPES, MEDIA_TYPES);
	}
}
