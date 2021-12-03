package br.com.zup.ProximosRicos.configs;

import br.com.zup.ProximosRicos.exceptions.ChequeEspecialException;
import br.com.zup.ProximosRicos.exceptions.ContaNaoEncontradaException;
import br.com.zup.ProximosRicos.exceptions.TransferenciaInvalidaException;
import br.com.zup.ProximosRicos.exceptions.TransferenciaMesmaContaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ExceptionHandler(ChequeEspecialException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public MensagemErro chequeEspecial (ChequeEspecialException exception){
        return new MensagemErro (exception.getMessage());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemErro manipularEnum(HttpMessageNotReadableException exception) {
        return new MensagemErro ("Possuí erros de escrita.");
    }
    @ExceptionHandler(ContaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErro contaNaoEncontrada(ContaNaoEncontradaException exception){
        return new MensagemErro(exception.getMessage());
    }
    @ExceptionHandler(TransferenciaMesmaContaException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public MensagemErro transferenciaMesmaConta(TransferenciaMesmaContaException exception){
        return new MensagemErro(exception.getMessage());
    }
    @ExceptionHandler(TransferenciaInvalidaException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public MensagemErro tranferenciaInvalidaException (TransferenciaInvalidaException excpetion){
        return new MensagemErro(excpetion.getMessage());
    }
}
