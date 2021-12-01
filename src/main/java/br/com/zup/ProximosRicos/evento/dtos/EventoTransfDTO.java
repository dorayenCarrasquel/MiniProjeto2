package br.com.zup.ProximosRicos.evento.dtos;

import br.com.zup.ProximosRicos.conta.Conta;
import br.com.zup.ProximosRicos.enums.TipoEvento;

public class EventoTransfDTO {
    private Conta contaTranferencia;
    private TipoEvento tipoEvento;
    private double valorTransferencia;
    private Conta contaDestinoTransferencia;

    public EventoTransfDTO() {
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Conta getContaTranferencia() {
        return contaTranferencia;
    }

    public void setContaTranferencia(Conta contaTranferencia) {
        this.contaTranferencia = contaTranferencia;
    }

    public double getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(double valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public Conta getContaDestinoTransferencia() {
        return contaDestinoTransferencia;
    }

    public void setContaDestinoTransferencia(Conta contaDestinoTransferencia) {
        this.contaDestinoTransferencia = contaDestinoTransferencia;
    }
}
