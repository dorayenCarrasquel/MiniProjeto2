package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.correntista.Correntista;
import br.com.zup.ProximosRicos.enums.TipoConta;
import br.com.zup.ProximosRicos.evento.Evento;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int numeroConta;
    @OneToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @Valid
    private Correntista correntista;
    @Enumerated(EnumType.STRING)
    @Valid
    @NotNull(message = "{validacao.tipo.not-null}")
    private TipoConta tipo;
    @Valid
    @ManyToMany (cascade= CascadeType.PERSIST)
    private List<Evento> eventos;
    private double saldo;

    public Conta() {
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }
}

