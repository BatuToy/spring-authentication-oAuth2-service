package com.dev.batu.authentication_module.config.exception.handler;

import com.dev.batu.authentication_module.config.exception.dto.ErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @ResponseBody 's importance= Helping to return a message to client!
     */



    @ResponseBody
    @ExceptionHandler(exception = {AccessDeniedException.class})
    @ResponseStatus(code = FORBIDDEN)
    public ResponseEntity<ErrorDto> handle(AccessDeniedException accessDeniedException) {
        final String excMsg = accessDeniedException.getMessage();
        log.error(excMsg, accessDeniedException);
        return ResponseEntity.status(FORBIDDEN.value()).body(
                ErrorDto.builder()
                        .code(FORBIDDEN.getReasonPhrase())
                        .message(excMsg)
                        .build()
        );
    }

    // Todo: In general handling for the InternalServerError gives the reasonable statements except defined ones!@
    @ResponseBody
    @ExceptionHandler(exception = {Exception.class})
    @ResponseStatus(code = INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> handle(Exception exception) {
        // todo: getMessage() and getLocalizedMessage() is the same thing eventually! USAGE IMPORTANCE!
        final String excMsg = exception.getMessage();
        log.error(excMsg, exception);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR.value())
                .body(ErrorDto.builder()
                        .code(INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message(excMsg)
                        .build()
                );
    }

    @ResponseBody
    @ExceptionHandler(exception = {ValidationException.class})
    @ResponseStatus(code = BAD_REQUEST)
    public ErrorDto handle(ValidationException exc) {
        if (exc instanceof ConstraintViolationException) {
            String violations = extractViolation((ConstraintViolationException) exc);
            final String excMsg = exc.getMessage();
            log.error(excMsg, exc);
            return ErrorDto.builder()
                    .message(violations.toLowerCase())
                    .code(BAD_REQUEST.getReasonPhrase())
                    .build();
        } else {
            final String excMsg = exc.getMessage();
            log.error(excMsg, exc);
            return ErrorDto.builder()
                    .code(BAD_REQUEST.getReasonPhrase())
                    .message(excMsg)
                    .build();
        }
    }

    private String extractViolation(ConstraintViolationException exc) {
        return exc.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }


}
