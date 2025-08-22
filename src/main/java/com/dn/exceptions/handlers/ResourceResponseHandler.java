package com.interact.exceptions.handlers;

import com.interact.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class ResourceResponseHandler  extends ResponseEntityExceptionHandler
{
    @ExceptionHandler( Exception.class )
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request )
    {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                request.getDescription( false )
        );

        return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
    }

}
