package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.correntista.Correntista;
import br.com.zup.ProximosRicos.enums.TipoConta;
import br.com.zup.ProximosRicos.evento.Evento;
import br.com.zup.ProximosRicos.extrato.Extrato;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "contas")
public class Conta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int numeroConta;
    @OneToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Correntista correntista;
    @Enumerated(EnumType.STRING)
    private TipoConta tipo;
    @ManyToMany (cascade= CascadeType.PERSIST)
    private List<Evento> eventos;

    public Conta() {
    }

    public Extrato getExtrato() {
        return extrato;
    }

    public void setExtrato(Extrato extrato) {
        this.extrato = extrato;
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

