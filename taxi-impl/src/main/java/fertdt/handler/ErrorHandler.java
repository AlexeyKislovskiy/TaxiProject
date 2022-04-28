package fertdt.handler;

import fertdt.dto.response.ExceptionExtendedResponse;
import fertdt.dto.response.ExceptionResponse;
import fertdt.exception.ApiException;
import io.jsonwebtoken.JwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ExceptionExtendedResponse exceptionResponse = ExceptionExtendedResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Request contains invalid values")
                .errors(errorList)
                .build();
        return handleExceptionInternal(ex, exceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handleApiException(ApiException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ExceptionResponse.builder()
                        .status(e.getStatus().value())
                        .error(e.getStatus().getReasonPhrase())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException e) {
        return buildExceptionResponse(HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException e) {
        return buildExceptionResponse(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(JwtException.class)
    public final ResponseEntity<ExceptionResponse> handleJwtException(JwtException e) {
        return buildExceptionResponse(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAnotherExceptions(Exception e) {
        return buildExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<ExceptionResponse> buildExceptionResponse(HttpStatus status, Exception e) {
        return ResponseEntity
                .status(status)
                .body(ExceptionResponse.builder()
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .message(e.getMessage())
                        .build());
    }


}
