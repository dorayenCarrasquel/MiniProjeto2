package br.com.zup.ProximosRicos.evento.dtos;

import br.com.zup.ProximosRicos.conta.Conta;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EventoSaida {
    private Conta conta;
    private TipoEvento tipoEvento;
    private double valorEvento;
    private double saldoInicial;
    private double saldodisponivel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public EventoSaida() {

    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
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

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldodisponivel() {
        return saldodisponivel;
    }

    public void setSaldodisponivel(double saldodisponivel) {
        this.saldodisponivel = saldodisponivel;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
