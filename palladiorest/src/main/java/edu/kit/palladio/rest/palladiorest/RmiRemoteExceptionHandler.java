package edu.kit.palladio.rest.palladiorest;

import java.rmi.RemoteException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RmiRemoteExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value 
      = { RemoteException.class })
    protected ResponseEntity<Object> handleRemoteEception(
        RemoteException ex, WebRequest request) {
        final String bodyOfResponse = "REST server encountered a problem communicating with Eclipse over RMI: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}