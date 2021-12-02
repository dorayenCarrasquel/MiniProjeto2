package br.com.zup.ProximosRicos.evento;

import br.com.zup.ProximosRicos.conta.Conta;
import br.com.zup.ProximosRicos.conta.ContaRepository;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.exceptions.ChequeEspecialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
    public List<Evento> exibirTodosOsCadastros(Integer numeroConta) {
        if (numeroConta != null) {
            return eventoRepository.findAllById(numeroConta);
        }
        Iterable<Evento> eventos = eventoRepository.findAllById(numeroConta);
        return (List<Evento>) eventos;
    }
    public void gerarEvento(TipoEvento tipoEvento, double valorAtual, double valorEvento, Conta conta){
        Evento evento = new Evento();
        evento.setTipoEvento(tipoEvento);
        evento.setSaldoDisponivel(valorAtual);
        evento.setValorEvento(valorEvento);
        evento.setData(LocalDateTime.now());
        conta.getEventos().add(evento);

        eventoRepository.save(evento);
    }
}

