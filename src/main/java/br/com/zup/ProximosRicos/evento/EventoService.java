package br.com.zup.ProximosRicos.evento;

import br.com.zup.ProximosRicos.conta.Conta;
import br.com.zup.ProximosRicos.conta.ContaRepository;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.exceptions.ChequeEspecialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ContaRepository contaRepository;



    public void aplicarSaque(Conta conta, Evento evento) {
        evento.setSaldoDisponivel(conta.getSaldo());
        if (evento.getSaldoDisponivel() < evento.getValorEvento()) {
            throw new ChequeEspecialException("Você entrou no cheque especial, por favor realize um deposito");
        }
        double saque = (evento.getSaldoDisponivel() - evento.getValorEvento());
        conta.setSaldo(saque);
        conta.getEventos().add(evento);
        evento.setData(LocalDateTime.now());

        eventoRepository.save(evento);

    }
    public void aplicarDeposito(Conta conta, Evento evento) {
        evento.setSaldoDisponivel(conta.getSaldo());
        double deposito = (evento.getSaldoDisponivel() + evento.getValorEvento());
        evento.setData(LocalDateTime.now());
        conta.getEventos().add(evento);
        conta.setSaldo(deposito);
        eventoRepository.save(evento);

    }
    public List<Evento> exibirTodosOsCadastros(Integer numeroConta) {
        if (numeroConta != null) {
            return eventoRepository.findAllById(numeroConta);
        }
        Iterable<Evento> eventos = eventoRepository.findAllById(numeroConta);
        return (List<Evento>) eventos;
    }
}
