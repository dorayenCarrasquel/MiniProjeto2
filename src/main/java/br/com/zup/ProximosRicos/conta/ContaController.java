package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.conta.dto.CadastroEntradaDTO;
import br.com.zup.ProximosRicos.conta.dto.CadastroSaidaDTO;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.evento.Evento;
import br.com.zup.ProximosRicos.evento.EventoService;
import br.com.zup.ProximosRicos.evento.dtos.EventoEntrada;
import br.com.zup.ProximosRicos.evento.dtos.EventoSaida;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/caixa")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EventoService eventoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroSaidaDTO cadastrarConta (@RequestBody @Valid CadastroEntradaDTO cadastroEntradaDTO){
        Conta conta = modelMapper.map(cadastroEntradaDTO, Conta.class);
        return modelMapper.map(contaService.salvarConta(conta), CadastroSaidaDTO.class);
    }
    @PutMapping("/{numeroConta}")
    @ResponseStatus(HttpStatus.OK)
    public EventoSaida aplicarEvento (@PathVariable int numeroConta, @RequestBody EventoEntrada eventoEntrada){
        Conta conta = contaService.buscarConta(numeroConta);
        Evento evento = modelMapper.map(eventoEntrada, Evento.class);
        if (evento.getTipoEvento()== TipoEvento.SAQUE){
            eventoService.aplicarSaque(conta, evento);
        }
        if (evento.getTipoEvento()==TipoEvento.DEPOSITO){
            eventoService.aplicarDeposito(conta, evento);
        }
        EventoSaida eventoSaida = modelMapper.map(conta, EventoSaida.class);
        return eventoSaida;
    }
    @DeleteMapping("/{numeroConta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerContaPorId(@PathVariable int numeroConta){
        contaService.removerContaPorId(numeroConta);
    }
    @GetMapping("/{numeroConta}")
    public List<CadastroSaidaDTO> mostrarEventoPorIdConta(@PathVariable int numeroConta){
        List<CadastroSaidaDTO> listaEventos = new ArrayList<>();
        for (Evento evento : eventoService.exibirTodosOsCadastros(numeroConta)){
            CadastroSaidaDTO cadastroSaidaDTO = modelMapper.map(evento, CadastroSaidaDTO.class);
            listaEventos.add(cadastroSaidaDTO);
        }
        return listaEventos;
    }

}
