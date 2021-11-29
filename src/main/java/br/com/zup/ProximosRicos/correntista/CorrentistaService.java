package br.com.zup.ProximosRicos.correntista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository correntistaRepository;
}
