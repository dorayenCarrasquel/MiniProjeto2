package br.com.zup.ProximosRicos.evento.dtos;

import br.com.zup.ProximosRicos.enums.TipoEvento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventoEntrada {
    @NotBlank(message = "O valor debe ser preenchido")
    private TipoEvento tipoEvento;
    @NotNull(message = "O valor debe ser preenchido")
    private double valorEvento;

    public EventoEntrada() {

    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public double getValorEvento() {
        return valorEvento;
    }

    public void setValorEvento(double valorEvento) {
        this.valorEvento = valorEvento;
    }
}
