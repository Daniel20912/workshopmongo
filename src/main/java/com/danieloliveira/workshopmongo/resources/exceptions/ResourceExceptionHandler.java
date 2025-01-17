package com.danieloliveira.workshopmongo.resources.exceptions;

import com.danieloliveira.workshopmongo.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // indica que a classe é responsável por tratar possíveis erro na requisições
public class ResourceExceptionHandler extends RuntimeException {

    // quando ocorrer uma exceção do tipo ObjectNotFoundException, vai gerar e retornar um objeto StandardError com as informações dessa excessão para ser exibida no postman
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
