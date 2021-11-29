package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.conta.dto.CadastroEntradaDTO;
import br.com.zup.ProximosRicos.conta.dto.CadastroSaidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/caixa")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper modelMapper;

}
