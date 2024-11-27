package com.musicApi.infrastructure.entry_points.web.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import theCatApi.domain.model.common.ResponseDTO;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> generalException(Exception e){
        return ResponseEntity.internalServerError().body(ResponseDTO
                .builder()
                .ok(false)
                .message(e.getMessage())
                .currentPage(0)
                .totalPages(1)
                .build());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseDTO> generalException(Throwable e){
        return ResponseEntity.internalServerError().body(ResponseDTO
                .builder()
                .ok(false)
                .message(e.getMessage())
                .currentPage(0)
                .totalPages(1)
                .build());
    }
}