package br.com.zup.ProximosRicos.conta;

import br.com.zup.ProximosRicos.conta.dto.CadastroEntradaDTO;
import br.com.zup.ProximosRicos.conta.dto.CadastroSaidaDTO;
import br.com.zup.ProximosRicos.enums.TipoEvento;
import br.com.zup.ProximosRicos.evento.Evento;
import br.com.zup.ProximosRicos.evento.EventoService;
import br.com.zup.ProximosRicos.evento.dtos.EventoEntradaDTO;
import br.com.zup.ProximosRicos.evento.dtos.EventoSaidaDTO;
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
    public EventoSaidaDTO aplicarEventoSaqueDeposito (@PathVariable int numeroConta, @RequestBody EventoEntradaDTO eventoEntradaDTO){

        double valorEvento = eventoEntradaDTO.getValorEvento();

        if (eventoEntradaDTO.getTipoEvento()== TipoEvento.SAQUE){
            contaService.aplicarSaque(numeroConta, valorEvento);
        }
        if (eventoEntradaDTO.getTipoEvento()== TipoEvento.DEPOSITO){
            contaService.aplicarDeposito(numeroConta, valorEvento);
        }
        EventoSaidaDTO eventoSaidaDTO = modelMapper.map(eventoEntradaDTO, EventoSaidaDTO.class);
        return eventoSaidaDTO;
    }
    @DeleteMapping("/{numeroConta}")
    @ApiOperation(value = "Método para deletar uma conta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerContaPorId(@PathVariable int numeroConta){
        contaService.removerContaPorId(numeroConta);
    }
    @GetMapping("/{numeroConta}")
    @ApiOperation(value = "Método para pesquisar os eventos de uma conta")
    public List<Evento> mostrarEventoPorIdConta(@PathVariable int numeroConta,
                                                @RequestParam(required = false) TipoEvento tipoEvento){

        //Descubir a conta.
        Conta conta = contaService.buscarConta(numeroConta);

        //Criar uma lista de eventos
        List<Evento> eventos =  new ArrayList<>();

        //Caso TipoEvento(@RequestParam) seja preenchido, este if fara filtragen
        // e adiscionará a lista de eventos só os filtrados
        if(tipoEvento != null){
            for (Evento evento: conta.getEventos()){
                if (evento.getTipoEvento().equals(tipoEvento)){
                    eventos.add(evento);
                }
            }
        }//Caso TipoEvento(@RequestParam) não tenha sido prenchido
        // este foreach adicionara todos os eventos a lista de evento
        else {
            for (Evento evento: conta.getEventos()){
                eventos.add(evento);
            }
        }

        return eventos;
    }
    //   @PutMapping
    //    @ApiOperation(value = "Método para aplicar eventos entre diferentes contas, TRANSFERENCIA")
    //    @ResponseStatus(HttpStatus.OK)
    //    public EventoSaidaDTO aplicarEvento (@RequestBody EventoTransfDTO eventoTransfDTO){
    //        int contaTransferenciaID = eventoTransfDTO.getContaTranferencia();
    //        Conta contaTransferencia = contaService.buscarConta(contaTransferenciaID);
    //        int contaDestinoID = eventoTransfDTO.getContaDestinoTransferencia();
    //        Conta contaDestino = contaService.buscarConta(contaDestinoID);
    //        Evento evento = modelMapper.map(eventoTransfDTO, Evento.class);
    //
    //        if (evento.getTipoEvento()== TipoEvento.TRANSFERENCIA){
    //            contaService.aplicarTransferencia(contaTransferencia, evento, contaDestino);
    //        }
    //        EventoSaidaDTO eventoSaidaDTO = modelMapper.map(contaTransferencia, EventoSaidaDTO.class);
    //        return eventoSaidaDTO;
    //    }
}