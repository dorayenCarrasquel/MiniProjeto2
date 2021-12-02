package br.com.zup.ProximosRicos.conta;


import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.evento.Evento;
import br.com.zup.ProximosRicos.evento.EventoRepository;
import br.com.zup.ProximosRicos.evento.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EventoService eventoService;

    public Conta salvarConta (Conta conta){
        return contaRepository.save(conta);
    }
    public Conta buscarConta(int numeroConta){
        Optional<Conta> optionalConta = contaRepository.findById(numeroConta);
        if (optionalConta.isEmpty()){
            throw new RuntimeException("Conta não registrada");
        }
        return optionalConta.get();
    }
    public void aplicarTransferencia(Conta contaTransferencia, Evento evento, Conta contaDestino) {
        eventoService.aplicarSaque(contaTransferencia,evento);
        aplicarDeposito(contaDestino,evento);
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
    public void removerContaPorId (int numeroConta){
        boolean contaASerRemovida = false;
        Conta contaRemovida = null;
        for (Conta conta : contaRepository.findAll()){
            if (conta.getNumeroConta() == numeroConta){
                contaASerRemovida = true;
                contaRemovida = conta;
            }
        }
        if (contaASerRemovida){
            contaRepository.delete(contaRemovida);
        }
    }
}