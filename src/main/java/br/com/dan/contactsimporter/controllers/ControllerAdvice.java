package br.com.dan.contactsimporter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.dan.contactsimporter.dtos.ResponseData;
import br.com.dan.contactsimporter.exceptions.DocumentNotFound;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    
    @ExceptionHandler(DocumentNotFound.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseData> handle(DocumentNotFound ex) {
        log.error(ex.getMessage());
        ResponseData responseData = ResponseData.builder().error(ex.getMessage()).status(HttpStatus.NOT_FOUND.value()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
    }

}
