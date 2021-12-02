package br.com.zup.ProximosRicos.exceptions;

public class TransferenciaMesmaContaException extends RuntimeException{
    public TransferenciaMesmaContaException(String message) {
        super(message);
    }
}
