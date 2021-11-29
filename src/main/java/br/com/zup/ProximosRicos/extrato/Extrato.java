package br.com.zup.ProximosRicos.extrato;

import br.com.zup.ProximosRicos.evento.Evento;

import java.util.List;

public class Extrato {
    private List<Evento>eventos;

    public Extrato() {
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
