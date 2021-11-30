package br.com.zup.ProximosRicos.evento;

import br.com.zup.ProximosRicos.conta.Conta;
import br.com.zup.ProximosRicos.conta.ContaRepository;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ContaRepository contaRepository;



    public void aplicarSaque(Conta conta, Evento evento) {
        if (evento.getSaldoDisponivel() < evento.getValorEvento()) {
            throw new RuntimeException("Você entrou no cheque especial, por favor realize um deposito");
        }
        evento.setSaldoDisponivel(evento.getSaldoDisponivel() - evento.getValorEvento());
        eventoRepository.save(evento);

    }
    public void aplicarDeposito(Conta conta, Evento evento) {
        evento.setSaldoDisponivel(evento.getSaldoDisponivel() + evento.getValorEvento());
        conta.getEventos().add(evento);

        evento.setData(LocalDateTime.now());
        eventoRepository.save(evento);

    }

    public void aplicarExtrato(Conta conta, Evento evento ){

    }


}
