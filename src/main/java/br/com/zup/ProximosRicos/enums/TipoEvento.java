package br.com.zup.ProximosRicos.enums;

public enum TipoEvento {
    SAQUE ("Saque"),
    DEPOSITO("Depósito"),
    EXTRATO("Extrato");

    private String descricao;
    TipoEvento (String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }

}
