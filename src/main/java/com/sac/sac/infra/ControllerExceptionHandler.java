package com.sac.sac.infra;

import com.sac.sac.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatClienteNotfound(EntityNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(),"500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

}
