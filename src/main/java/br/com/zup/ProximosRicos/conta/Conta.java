package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.correntista.Correntista;
import br.com.zup.ProximosRicos.enums.TipoConta;

public class Conta {
    private String numeroConta;
    private String agencia;
    private Correntista correntista;
    private TipoConta tipo;

    public Conta() {
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
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

