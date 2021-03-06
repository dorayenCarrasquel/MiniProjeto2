package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.enums.TipoConta;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.evento.EventoRepository;
import br.com.zup.ProximosRicos.evento.EventoService;
import br.com.zup.ProximosRicos.exceptions.ChequeEspecialException;
import br.com.zup.ProximosRicos.exceptions.ContaNaoEncontradaException;
import br.com.zup.ProximosRicos.exceptions.TransferenciaMesmaContaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new ContaNaoEncontradaException("Conta não encontrada.");
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

        //Esta parte do código irá navegar através dos repositórios para encontrar as contas corretas para fazer
        //a soma e a subtração dos saldos de suas respectivas contas recebidas através do Json.
        Optional<Conta> contaEntradaEncontrada = contaRepository.findById(numeroContaEntrada);

        if (contaEntradaEncontrada.isPresent()) {
            Optional<Conta> contaSaidaEncontrada = contaRepository.findById(numeroContaSaida);

            if (contaSaidaEncontrada.isPresent()) {

                //Esta variável armazena o valor do evento recebido através do Json com o propósito de
                //ser chamada futuramente caso o tipo de transferência não seja entre duas contas correntes.
                double valorAnterior = valorEvento;

                //Verifica se a transferência esta sendo feita para a mesma conta.
                if (contaSaidaEncontrada.get().getNumeroConta() == numeroContaEntrada) {
                    throw new TransferenciaMesmaContaException("Não é possível fazer transferência para a própria conta.");
                }

                //Verifica se o usuário tem a quantidade necessária de dinheiro para realizar a transferência.
                if (contaSaidaEncontrada.get().getSaldo() <= 0 || contaSaidaEncontrada.get().getSaldo() < valorEvento) {
                    throw new ChequeEspecialException("Você entrou no cheque especial, por favor realize um deposito");
                }

                //Verifica se a conta do usuário é do tipo Conta Corrente. Se sim, aplicará 10% de taxa.
                //Verifica se o usuário tem a quantidade necessária de dinheiro para realizar a transferência.
                if (contaSaidaEncontrada.get().getTipo() == TipoConta.CONTA_CORRENTE) {
                    valorEvento = valorEvento * 1.10;
                    if (contaSaidaEncontrada.get().getSaldo() <= 0 || contaSaidaEncontrada.get().getSaldo() < valorEvento) {
                        throw new ChequeEspecialException("Você entrou no cheque especial, por favor realize um deposito");
                    }
                }

                //Caso ambas as contas sejam encontradas, irá fazer a soma/subtração do saldo atual,
                //e criará um novo evento de transfererência saida/entrada para ambas as contas.
                contaEntradaEncontrada.get().setSaldo(contaEntradaEncontrada.get().getSaldo() + valorAnterior);
                eventoService.gerarEvento(TipoEvento.TRANSFERENCIA_ENTRADA, contaEntradaEncontrada.get().getSaldo(),
                        valorAnterior, contaEntradaEncontrada.get());


                contaSaidaEncontrada.get().setSaldo(contaSaidaEncontrada.get().getSaldo() - valorEvento);
                eventoService.gerarEvento(TipoEvento.TRANSFERENCIA_SAIDA, contaSaidaEncontrada.get().getSaldo(),
                        valorEvento, contaSaidaEncontrada.get());
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