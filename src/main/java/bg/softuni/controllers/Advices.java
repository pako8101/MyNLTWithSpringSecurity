package bg.softuni.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Advices {
    @ExceptionHandler
    public ResponseEntity<Object> handlerEntityNotFound(EntityNotFoundException ex){
        System.out.println(ex.getMessage());

        return ResponseEntity
                .notFound()
                .build();
    }
}
