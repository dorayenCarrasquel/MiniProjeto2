package br.com.zup.ProximosRicos.conta;

public class Conta {
    private String numeroConta;
    private String agencia;
    private Correntista correntista;
    private Tipo tipo;

    public Conta(String numeroConta, String agencia, Correntista correntista, Tipo tipo) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.correntista = correntista;
        this.tipo = tipo;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
