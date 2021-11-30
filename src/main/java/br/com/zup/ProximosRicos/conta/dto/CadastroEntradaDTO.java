package br.com.zup.ProximosRicos.conta.dto;

import br.com.zup.ProximosRicos.correntista.Correntista;
import br.com.zup.ProximosRicos.enums.TipoConta;

public class CadastroEntradaDTO {

    private Correntista correntista;
    private TipoConta tipo;


    public CadastroEntradaDTO() {
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
