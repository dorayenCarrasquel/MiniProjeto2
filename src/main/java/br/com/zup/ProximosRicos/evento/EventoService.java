package br.com.zup.ProximosRicos.evento;

import br.com.zup.ProximosRicos.conta.Conta;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public void aplicarEvento (Conta conta, Evento evento) {

        if (evento.getTipoEvento() == TipoEvento.SAQUE) {
            if (evento.getSaldoDisponivel() < evento.getValorEvento()) {
                throw new RuntimeException("Você entrou no cheque especial, por favor realize um deposito");
            }
            evento.setSaldoDisponivel(evento.getSaldoDisponivel()  - evento.getValorEvento());
            conta.getEventos().add(evento);
        }
        else if (evento.getTipoEvento() == TipoEvento.DEPOSITO){
            evento.setSaldoDisponivel(evento.getSaldoDisponivel()  + evento.getValorEvento());
            conta.getEventos().add(evento);
        }
       // else if(evento.getTipoEvento() == TipoEvento.EXTRATO) {
        //    conta.getEventos();
      //  }

        evento.setData(LocalDateTime.now());
        eventoRepository.save(evento);

    }


}
