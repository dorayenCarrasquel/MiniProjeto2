package br.com.zup.ProximosRicos.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public Conta salvarConta (Conta conta){
        return contaRepository.save(conta);
    }
    public Conta buscarConta(Integer numeroConta){
        Optional<Conta> optionalConta = contaRepository.findById(numeroConta);
        if (optionalConta.isEmpty()){
            throw new RuntimeException("Conta não registrada");
        }
        return optionalConta.get();
    }

}
