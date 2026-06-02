package com.skillverse.notificationservice.exception;

import com.skillverse.notificationservice.dto.response.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorized(
            UnauthorizedException ex) {

        log.warn("Unauthorized access: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ErrorResponseDTO.builder()
                                .status(HttpStatus.FORBIDDEN.value())
                                .message(ex.getMessage())
                                .errorCode("FORBIDDEN")
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}