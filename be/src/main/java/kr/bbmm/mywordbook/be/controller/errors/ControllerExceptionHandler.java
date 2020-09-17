package kr.bbmm.mywordbook.be.controller.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@ControllerAdvice
public class ControllerExceptionHandler implements ProblemHandling, SecurityAdviceTrait {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    private static final String applicationName = "mywordbook";

    @ExceptionHandler
    public ResponseEntity<Problem> handleEmailAlreadyUsedException(kr.bbmm.mywordbook.be.service.EmailAlreadyUsedException ex, NativeWebRequest request) {
        EmailAlreadyUsedException problem = new EmailAlreadyUsedException();
        return create(problem, request, createFailureAlert(applicationName,  true, problem.getEntityName(), problem.getErrorKey(), problem.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleUsernameAlreadyUsedException(kr.bbmm.mywordbook.be.service.UsernameAlreadyUsedException ex, NativeWebRequest request) {
        LoginAlreadyUsedException problem = new LoginAlreadyUsedException();
        return create(problem, request, createFailureAlert(applicationName,  true, problem.getEntityName(), problem.getErrorKey(), problem.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleInvalidPasswordException(kr.bbmm.mywordbook.be.service.InvalidPasswordException ex, NativeWebRequest request) {
        return create(new InvalidPasswordException(), request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {
        return create(ex, request, createFailureAlert(applicationName, true, ex.getEntityName(), ex.getErrorKey(), ex.getMessage()));
    }

    private static HttpHeaders createFailureAlert(String applicationName, boolean enableTranslation, String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        String message = enableTranslation ? "error." + errorKey : defaultMessage;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-error", message);
        headers.add("X-" + applicationName + "-params", entityName);
        return headers;
    }
}
