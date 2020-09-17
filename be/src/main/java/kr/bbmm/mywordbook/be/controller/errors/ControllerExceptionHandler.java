package kr.bbmm.mywordbook.be.controller.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@ControllerAdvice
public class ControllerExceptionHandler implements ProblemHandling, SecurityAdviceTrait {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

}
