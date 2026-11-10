package com.system.piratas.oak.infra;

import com.system.piratas.oak.exceptions.InventarioInexistenteException;
import com.system.piratas.oak.exceptions.ListaDeProdutosVaziaException;
import com.system.piratas.oak.exceptions.ProdutoInexistenteException;
import com.system.piratas.oak.exceptions.ProdutoRepetidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InventarioInexistenteException.class)
    private ResponseEntity<String> inventarioInexistente(InventarioInexistenteException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventario inexistente.");
    }

    @ExceptionHandler(ListaDeProdutosVaziaException.class)
    private ResponseEntity<String> listaDeProdutosVazia(ListaDeProdutosVaziaException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lista de produtos vazia.");
    }

    @ExceptionHandler(ProdutoInexistenteException.class)
    private ResponseEntity<String> produtoInexistente(ProdutoInexistenteException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto inexistente.");
    }

    @ExceptionHandler(ProdutoRepetidoException.class)
    private ResponseEntity<String> produtoRepetido(ProdutoRepetidoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto já cadastrado.");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            org.springframework.http.HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

}
