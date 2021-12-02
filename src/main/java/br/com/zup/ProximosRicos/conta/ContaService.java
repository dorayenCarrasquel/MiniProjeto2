package br.com.zup.ProximosRicos.conta;


import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.evento.Evento;
import br.com.zup.ProximosRicos.evento.EventoRepository;
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
