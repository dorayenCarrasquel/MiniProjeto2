package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.conta.dto.CadastroEntradaDTO;
import br.com.zup.ProximosRicos.conta.dto.CadastroSaidaDTO;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.evento.Evento;
import br.com.zup.ProximosRicos.evento.EventoService;
import br.com.zup.ProximosRicos.evento.dtos.EventoEntrada;
import br.com.zup.ProximosRicos.evento.dtos.EventoSaida;
import br.com.zup.ProximosRicos.evento.dtos.EventoTransfDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/caixa")
@Api(value = "Serviso de Caixa Banco Proximos Ricos")
@CrossOrigin(origins = "*")
public class ContaController {
    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EventoService eventoService;

    @PostMapping
    @ApiOperation(value = "Método para Cadastrar contas, Banco Proximos Ricos")
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroSaidaDTO cadastrarConta (@RequestBody @Valid CadastroEntradaDTO cadastroEntradaDTO){
        Conta conta = modelMapper.map(cadastroEntradaDTO, Conta.class);
        return modelMapper.map(contaService.salvarConta(conta), CadastroSaidaDTO.class);
    }
    @PutMapping("/{numeroConta}")
    @ApiOperation(value = "Método para aplicar eventos na mesma conta, SAQUE - DEPOSITO")
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
    @ApiOperation(value = "Método para deletar uma conta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerContaPorId(@PathVariable int numeroConta){
        contaService.removerContaPorId(numeroConta);
    }
    @GetMapping("/{numeroConta}")
    @ApiOperation(value = "Método para pesquisar por número de conta")
    public EventoSaida mostrarEventoPorIdConta(@PathVariable int numeroConta){
        Conta conta = contaService.buscarConta(numeroConta);
        EventoSaida eventoSaida = modelMapper.map(conta, EventoSaida.class);
        return eventoSaida;
    }

    @PutMapping
    @ApiOperation(value = "Método para aplicar eventos entre diferentes contas, TRANSFERENCIA")
    @ResponseStatus(HttpStatus.OK)
    public EventoSaida aplicarEvento (@RequestBody EventoTransfDTO eventoTransfDTO){
        int contaTransferenciaID = eventoTransfDTO.getContaTranferencia();
        Conta contaTransferencia = contaService.buscarConta(contaTransferenciaID);
        int contaDestinoID = eventoTransfDTO.getContaDestinoTransferencia();
        Conta contaDestino = contaService.buscarConta(contaDestinoID);
        Evento evento = modelMapper.map(eventoTransfDTO, Evento.class);

        if (evento.getTipoEvento()== TipoEvento.TRANSFERENCIA){
            eventoService.aplicarTransferencia(contaTransferencia, evento, contaDestino);
        }
        EventoSaida eventoSaida = modelMapper.map(contaTransferencia, EventoSaida.class);
        return eventoSaida;
    }
}