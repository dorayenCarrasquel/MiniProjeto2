package br.com.zup.ProximosRicos.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BancoService {
    @Autowired
    private BancoRepository bancoRepository;

}
