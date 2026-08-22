package br.com.senai.autoescolas164.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorGlobalErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarNotFount() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosBadRequest>> tratarBadRequest(
            MethodArgumentNotValidException e) {
        List<FieldError> erros = e.getFieldErrors();
        return ResponseEntity
                .badRequest()
                .body(erros
                        .stream()
                        .map(DadosBadRequest::new)
                        .toList()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DadosException> tratarErroGenerico(Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(new DadosException(e.getMessage()));
    }

    private record DadosException(String erro) {
    }

    private record DadosBadRequest(String campo, String mensagem) {
        public DadosBadRequest(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}