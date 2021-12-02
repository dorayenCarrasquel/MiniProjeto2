package br.com.zup.ProximosRicos.evento.dtos;

import br.com.zup.ProximosRicos.enums.TipoEvento;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventoEntradaDTO {

    @Valid
    @NotBlank(message = "{validacao.tipoEvento.not-blank}")
    private TipoEvento tipoEvento;
    @NotNull(message = "{validacao.valorEvento.not-null}")
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
