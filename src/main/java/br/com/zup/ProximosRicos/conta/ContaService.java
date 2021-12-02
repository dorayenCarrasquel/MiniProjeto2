package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.evento.Evento;
import br.com.zup.ProximosRicos.evento.EventoRepository;
import br.com.zup.ProximosRicos.evento.EventoService;
import br.com.zup.ProximosRicos.exceptions.ChequeEspecialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private ContaRepository contaRepository;
    private EventoRepository eventoRepository;
    private EventoService eventoService;

    @Autowired
    public ContaService(ContaRepository contaRepository, EventoRepository eventoRepository, EventoService eventoService) {
        this.contaRepository = contaRepository;
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
    }


    public Conta salvarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta buscarConta(int numeroConta) {
        Optional<Conta> optionalConta = contaRepository.findById(numeroConta);
        if (optionalConta.isEmpty()) {
            throw new RuntimeException("Conta não registrada");
        }
        return optionalConta.get();
    }

    public void aplicarSaque(int numeroConta, double valorEvento) {

        Optional<Conta> contaOptional = contaRepository.findById(numeroConta);
        Conta contaOp = contaOptional.get();
        double valorAtual = contaOp.getSaldo();

        if (contaOp.getSaldo() < valorEvento) {
            throw new ChequeEspecialException("Você entrou no cheque especial, por favor realize um deposito");
        }

        contaOp.setSaldo(valorAtual - valorEvento);

        eventoService.gerarEvento(TipoEvento.SAQUE, valorAtual, valorEvento, contaOp);
    }

    public void aplicarDeposito(int numeroConta, double valorEvento) {

        Optional<Conta> contaOptional = contaRepository.findById(numeroConta);
        Conta conta = contaOptional.get();
        double valorAtual = conta.getSaldo();
        conta.setSaldo(valorAtual + valorEvento);

        eventoService.gerarEvento(TipoEvento.DEPOSITO, valorAtual, valorEvento, conta);
    }

    public void aplicarTransferencia(int numeroContaSaida, int numeroContaEntrada, double valorEvento) {

        Optional<Conta> contaEntradaEncontrada = contaRepository.findById(numeroContaEntrada);
        if (contaEntradaEncontrada.isPresent()) {
            Optional<Conta> contaSaidaEncontrada = contaRepository.findById(numeroContaSaida);
            if (contaSaidaEncontrada.isPresent()) {

                contaEntradaEncontrada.get().setSaldo(contaEntradaEncontrada.get().getSaldo() + valorEvento);
                eventoService.gerarEvento(TipoEvento.TRANSFERENCIA_ENTRADA, contaEntradaEncontrada.get().getSaldo(), valorEvento, contaEntradaEncontrada.get());

                contaSaidaEncontrada.get().setSaldo(contaSaidaEncontrada.get().getSaldo() - valorEvento);
                eventoService.gerarEvento(TipoEvento.TRANSFERENCIA_SAIDA, contaSaidaEncontrada.get().getSaldo(), valorEvento, contaSaidaEncontrada.get());
            }
        }
    }

    public void removerContaPorId(int numeroConta) {
        boolean contaASerRemovida = false;
        Conta contaRemovida = null;
        for (Conta conta : contaRepository.findAll()) {
            if (conta.getNumeroConta() == numeroConta) {
                contaASerRemovida = true;
                contaRemovida = conta;
            }
        }
        if (contaASerRemovida) {
            contaRepository.delete(contaRemovida);
        }
    }
}