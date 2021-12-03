package br.com.zup.ProximosRicos.evento.dtos;

import br.com.zup.ProximosRicos.enums.TipoEvento;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventoEntradaDTO {

    @Valid
    @NotNull(message = "{validacao.tipoEvento.not-null}")
    private TipoEvento tipoEvento;
    @NotNull(message = "{validacao.valorEvento.not-null}")
    @DecimalMin(value = "0.01",message = "{validacao.valorEvento.decimal-min}")
    private double valorEvento;

    public EventoEntradaDTO() {

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
