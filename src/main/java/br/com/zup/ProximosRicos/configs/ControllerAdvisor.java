package br.com.zup.ProximosRicos.configs;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErroValidacao> manipularErrosVal(MethodArgumentNotValidException exception) {
        List<ErroValidacao> erros = new ArrayList<>();
        for (FieldError fieldError : exception.getFieldErrors()) {
            ErroValidacao erroValidacao = new ErroValidacao(fieldError.getField(), fieldError.getDefaultMessage());
            erros.add(erroValidacao);
        }
        return erros;
    }
}
