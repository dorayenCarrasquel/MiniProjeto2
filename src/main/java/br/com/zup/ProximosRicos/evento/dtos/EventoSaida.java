package br.com.zup.ProximosRicos.evento.dtos;

import br.com.zup.ProximosRicos.conta.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.Valid;

public class EventoSaida {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Valid
    private Conta conta;

    public EventoSaida() {

    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
