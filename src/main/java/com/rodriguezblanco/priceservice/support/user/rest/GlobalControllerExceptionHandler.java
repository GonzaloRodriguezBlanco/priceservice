package com.rodriguezblanco.priceservice.support.user.rest;

import com.rodriguezblanco.priceservice.prices.domain.exception.PriceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(PriceNotFoundException.class)
    ProblemDetail handleNotFound(HttpServletRequest request, Exception exception) {
        return createProblemDetail(request, exception, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler({PathVariableException.class, MethodArgumentTypeMismatchException.class})
    ProblemDetail handleBadRequest(HttpServletRequest request, Exception exception) {
        return createProblemDetail(request, exception, HttpStatus.BAD_REQUEST);
    }

    private ProblemDetail createProblemDetail(HttpServletRequest request, Exception exception, HttpStatus status) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, exception.getLocalizedMessage());
        problem.setInstance(URI.create(request.getRequestURL().toString()));
        return problem;
    }
}
