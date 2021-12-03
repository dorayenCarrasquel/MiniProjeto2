package br.com.zup.ProximosRicos.evento.dtos;


import br.com.zup.ProximosRicos.enums.TipoEvento;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class EventoTransfDTO {
    private int contaTranferencia;
    @Valid
    @NotNull(message = "{validacao.tipoEvento.not-null}")
    private TipoEvento tipoEvento;
    @Valid
    @DecimalMin(value = "0.01",message = "{validacao.valorEvento.decimal-min}")
    private double valorTransferencia;
    private int contaDestinoTransferencia;

    public EventoTransfDTO() {
    }

    public int getContaTranferencia() {
        return contaTranferencia;
    }

    public void setContaTranferencia(int contaTranferencia) {
        this.contaTranferencia = contaTranferencia;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public double getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(double valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public int getContaDestinoTransferencia() {
        return contaDestinoTransferencia;
    }

    public void setContaDestinoTransferencia(int contaDestinoTransferencia) {
        this.contaDestinoTransferencia = contaDestinoTransferencia;
    }
}
