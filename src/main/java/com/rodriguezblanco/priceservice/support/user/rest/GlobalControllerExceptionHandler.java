package com.rodriguezblanco.priceservice.support.user.rest;

import com.rodriguezblanco.priceservice.prices.domain.exception.PriceNotFoundException;
import com.rodriguezblanco.priceservice.prices.user.rest.request.ProductKey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ProblemDetail handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {
        Class<?> type = exception.getRequiredType();
        assert type != null;
        Exception exceptionProblem = exception;
        boolean isDateType = type.equals(LocalDateTime.class);
        if (type.equals(ProductKey.class) || isDateType) {
            Throwable cause = exception.getCause();
            exceptionProblem = (Exception) cause.getCause();
        }

        ProblemDetail problemDetail = createProblemDetail(request, exceptionProblem, HttpStatus.BAD_REQUEST);
        if (isDateType) {
            problemDetail.setDetail("Invalid format for date. Should be '2020-06-14T10:00:00Z'");
        }

        return problemDetail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(PathVariableException.class)
    ProblemDetail handleBadRequest(HttpServletRequest request, Exception exception) {
        return createProblemDetail(request, exception, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(PriceNotFoundException.class)
    ProblemDetail handleNotFound(HttpServletRequest request, Exception exception) {
        return createProblemDetail(request, exception, HttpStatus.NOT_FOUND);
    }

    private ProblemDetail createProblemDetail(HttpServletRequest request, Exception exception, HttpStatus status) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, exception.getLocalizedMessage());
        problem.setInstance(URI.create(request.getRequestURL().toString()));
        return problem;
    }
}
