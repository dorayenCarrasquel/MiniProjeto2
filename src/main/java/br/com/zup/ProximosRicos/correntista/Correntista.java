package br.com.zup.ProximosRicos.correntista;

import br.com.zup.ProximosRicos.conta.Conta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conrrentistas")
public class Correntista {
    @Id
    private String cpf;
    private String nome;
    @Column(unique = true)
    @OneToOne
    private Conta conta;

    public Correntista() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
