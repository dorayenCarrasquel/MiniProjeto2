package br.com.zup.ProximosRicos.correntista;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "conrrentistas")
public class Correntista {
    @Id
    @CPF(message = "CPF inválido.")
    private String cpf;
    @Size(min = 3, max = 50, message = "Quantidade de caracteres inválido.")
    @NotNull(message = "Campo obrigatório.")
    @NotBlank(message = "Este campo não pode ficar em branco.")
    private String nome;

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

}
