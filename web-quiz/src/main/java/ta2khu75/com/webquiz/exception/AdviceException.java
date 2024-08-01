package ta2khu75.com.webquiz.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class AdviceException implements ResponseBodyAdvice<Object> {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getBindingResult().getAllErrors().toString()));
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> handleBindException(BindException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getBindingResult().getAllErrors().toString()));
    }
    @ExceptionHandler(InValidDataException.class)
    public ResponseEntity<ExceptionResponse> handleInValidDataException(InValidDataException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        ex.printStackTrace();
            return ResponseEntity.internalServerError().body(new ExceptionResponse(ex.getMessage())); // ex.getMessage();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getMessage()));
    }
    @ExceptionHandler(NotMatchesException.class)
    public ResponseEntity<ExceptionResponse> handleNotMatchesException(NotMatchesException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getMessage()));
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletResponse servletResponse=((ServletServerHttpResponse) response).getServletResponse();
        int statusCode=servletResponse.getStatus();
        if (selectedContentType.includes(MediaType.APPLICATION_JSON)) {
            if(statusCode<HttpStatus.BAD_REQUEST.value()){
            return ApiResponse.builder().data(body).statusCode(statusCode).success(true).build();
        }else if(body instanceof ExceptionResponse exceptionResponse){
                return ApiResponse.builder().statusCode(statusCode).messageError(exceptionResponse.messageError).success(false).build();
        }}
        return body;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ApiResponse{
        @Builder.Default
        @JsonProperty("status_code")
        int statusCode=200;
        @Builder.Default
        boolean success=true;
        Object data;
        String message;
        @JsonProperty("message_error")
        String messageError;
    }
    public record  ExceptionResponse (String messageError){
    }

}
