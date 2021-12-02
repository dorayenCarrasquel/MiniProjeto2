package br.com.zup.ProximosRicos.evento;

import br.com.zup.ProximosRicos.conta.Conta;
import br.com.zup.ProximosRicos.conta.ContaRepository;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class EventoService {

    private EventoRepository eventoRepository;
    private ContaRepository contaRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository, ContaRepository contaRepository) {
        this.eventoRepository = eventoRepository;
        this.contaRepository = contaRepository;
    }


    public void gerarEvento(TipoEvento tipoEvento, double valorAtual, double valorEvento, Conta conta) {
        Evento evento = new Evento();
        evento.setTipoEvento(tipoEvento);
        evento.setSaldoDisponivel(valorAtual);
        evento.setValorEvento(valorEvento);
        evento.setData(LocalDateTime.now());
        conta.getEventos().add(evento);

        eventoRepository.save(evento);
    }
}

