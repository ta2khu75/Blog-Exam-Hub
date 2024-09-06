//package com.ta2khu75.quiz.aop;
//
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.*;
//
//import com.ta2khu75.quiz.exception.InValidDataException;
//
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class ValidationAspect {
//
//	private final Validator validator;
//
//	@SuppressWarnings("null")
//	@Before("execution(* ta2khu75.com.webquiz.service.impl.*.*(.., @jakarta.validation.Valid (*), ..))")
//	public void validateMethodArgument(JoinPoint joinPoint) {
//		for (Object arg : joinPoint.getArgs()) {
//			if (arg != null) {
//				BindingResult bindingResult = new BeanPropertyBindingResult(arg, arg.getClass().getName());
//				validator.validate(arg, bindingResult);
//				if (bindingResult.hasErrors()) {
//					throw new InValidDataException(bindingResult.getFieldError().getDefaultMessage());
//				}
//			}
//		}
//	}
//}
