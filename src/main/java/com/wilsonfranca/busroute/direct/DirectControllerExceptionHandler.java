package com.wilsonfranca.busroute.direct;

import com.wilsonfranca.busroute.route.RouteNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DirectControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RouteNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException e, WebRequest webRequest) {
        String response = "Direct route not found!";
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}
