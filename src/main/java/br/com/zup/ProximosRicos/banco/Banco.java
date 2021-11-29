package br.com.zup.ProximosRicos.banco;

import br.com.zup.ProximosRicos.conta.Conta;

import java.util.List;

public class Banco {
    private int id;
    private List<Conta>contas;
    private String cpf;

    public Banco() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
