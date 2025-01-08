package com.ta2khu75.quiz.configuration;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.model.RoleDefault;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Permission;
import com.ta2khu75.quiz.model.entity.PermissionGroup;
import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.PermissionGroupRepository;
import com.ta2khu75.quiz.repository.PermissionRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.service.util.EndpointUtil;
import com.ta2khu75.quiz.util.StringUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataInitializer implements CommandLineRunner {
	@NonFinal
	@Value("${app.api-prefix}")
	String apiPrefix;
	AccountRepository accountRepository;
	RoleRepository roleRepository;
	PermissionRepository permissionRepository;
	PermissionGroupRepository permissionGroupRepository;
	PasswordEncoder passwordEncoder;
	ApplicationContext applicationContext;
	EndpointUtil endpointUtil;

	private Role initRole() {
		List<Role> roles = List.of(Role.builder().name(RoleDefault.ANONYMOUS.name()).build(),
				Role.builder().name(RoleDefault.USER.name()).build(),
				Role.builder().name(RoleDefault.ADMIN.name()).build());
		roles = roleRepository.saveAll(roles);
		return roles.stream().filter(role -> RoleDefault.ADMIN.name().equals(role.getName())).findFirst()
				.orElseThrow(() -> new NotFoundException("Not found role name ADMIN"));
	}

	private Set<Permission> initPermission() {
		Set<Permission> permissions = new HashSet<>();
		Set<String> publicPostEndpoint = endpointUtil.getPublicEndpoint(RequestMethod.POST);
		Set<String> publicGetEndpoint = endpointUtil.getPublicEndpoint(RequestMethod.GET);
		Map<String, RequestMappingHandlerMapping> requestMappingMap = applicationContext
				.getBeansOfType(RequestMappingHandlerMapping.class);
		for (RequestMappingHandlerMapping handlerMapping : requestMappingMap.values()) {
			handlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
				Method method = handlerMethod.getMethod();
				if (method.isAnnotationPresent(EndpointMapping.class)) {
					EndpointMapping endpointMapping = method.getAnnotation(EndpointMapping.class);
					RequestMethod httpMethod = requestMappingInfo.getMethodsCondition().getMethods().iterator().next();
					String path = requestMappingInfo.getPathPatternsCondition().getPatternValues().iterator().next();
					String permissionGroupName = StringUtil
							.convertCamelCaseToReadable(method.getDeclaringClass().getSimpleName());
					PermissionGroup permissionGroup = permissionGroupRepository.findByName(permissionGroupName);
					if (permissionGroup == null) {
						permissionGroup = permissionGroupRepository
								.save(PermissionGroup.builder().name(permissionGroupName).build());
					}
					Permission permission = permissionRepository.findByName(endpointMapping.name());
					if (permission != null) {
						permission.setPath(path);
						permission.setHttpMethod(httpMethod);
						permission.setDescription(endpointMapping.description());
						permission.setPermissionGroup(permissionGroup);
					} else {
						permission = Permission.builder().name(endpointMapping.name())
								.description(endpointMapping.description()).path(path).httpMethod(httpMethod)
								.permissionGroup(permissionGroup).build();
					}
					permission = permissionRepository.save(permission);
					switch (httpMethod) {
					case GET: {
						endpointUtil.getPublicEndpoint(httpMethod);
						if (publicGetEndpoint.contains(path))
							permissions.add(permission);
						break;
					}
					case POST: {
						if (publicPostEndpoint.contains(path))
							permissions.add(permission);
						break;
					}
					case PUT: {
						break;
					}
					case PATCH: {
						break;
					}
					case DELETE: {
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + httpMethod);
					}
				}
			});
		}
		return permissions;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		if (accountRepository.count() == 0) {
			Account account = Account.builder().email("admin@g.com").password(passwordEncoder.encode("123"))
					.displayName("admin").firstName("admin").lastName("admin").enabled(true).birthday(LocalDate.now())
					.role(initRole()).build();
			accountRepository.save(account);
		}
		Set<Permission> permissionSet = initPermission();
		Role role = roleRepository.findByName(RoleDefault.ANONYMOUS.name())
				.orElseThrow(() -> new NotFoundException("Could not find role name " + RoleDefault.ANONYMOUS.name()));
		if (role.getPermissions().isEmpty()) {
			role.setPermissions(permissionSet);
		}
	}
}
