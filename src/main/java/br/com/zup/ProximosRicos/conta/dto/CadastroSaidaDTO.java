package br.com.zup.ProximosRicos.conta.dto;

import br.com.zup.ProximosRicos.correntista.Correntista;
import br.com.zup.ProximosRicos.enums.TipoConta;

import javax.validation.Valid;


public class CadastroSaidaDTO {


    private String numeroConta;
    @Valid
    private Correntista correntista;
    @Valid
    private TipoConta tipo;

    public CadastroSaidaDTO() {
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
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
