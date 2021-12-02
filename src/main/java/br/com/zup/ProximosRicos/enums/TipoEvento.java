package br.com.zup.ProximosRicos.enums;

public enum TipoEvento {
    SAQUE ("Saque"),
    DEPOSITO("Depósito"),
    TRANSFERENCIA("Transferência"),
    TRANSFERENCIA_SAIDA("Transferência Entrada"),
    TRANSFERENCIA_ENTRADA("Transferência Saida");

    private String descricao;
    TipoEvento (String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }

}
