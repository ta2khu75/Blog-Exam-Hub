package ta2khu75.com.webquiz.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
import ta2khu75.com.webquiz.exception.InValidDataException;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidationAspect {

    private final Validator validator;

    @Before("execution(* ta2khu75.com.webquiz.service.impl.*.*(.., @jakarta.validation.Valid (*), ..))")
    public void validateMethodArgument(JoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg != null) {
                BindingResult bindingResult = new BeanPropertyBindingResult(arg, arg.getClass().getName());
                validator.validate(arg, bindingResult);
                if (bindingResult.hasErrors()) {
                    StringBuilder errors = new StringBuilder();
                    // Collect field errors
                    for (FieldError fieldError : bindingResult.getFieldErrors()) {
                        errors.append(fieldError.getField()+": ").append(fieldError.getDefaultMessage())
                                .append("\n");
                    }
                    throw new InValidDataException(errors.toString());
                }
            }
        }
    }
}
