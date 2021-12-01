package br.com.zup.ProximosRicos.evento.dtos;


import br.com.zup.ProximosRicos.enums.TipoEvento;

public class EventoTransfDTO {
    private int contaTranferencia;
    private TipoEvento tipoEvento;
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
