package ta2khu75.com.webquiz.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
public class InValidDataException extends RuntimeException {
    public InValidDataException(String message) {
        super(message);
    }
}
