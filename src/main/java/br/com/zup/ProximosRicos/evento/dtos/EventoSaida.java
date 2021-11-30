package br.com.zup.ProximosRicos.evento.dtos;
import br.com.zup.ProximosRicos.conta.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EventoSaida {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
