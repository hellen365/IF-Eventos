package br.com.silva.duarte.ifevento.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.silva.duarte.ifevento.models.Evento;
import br.com.silva.duarte.ifevento.repository.EventoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Eventos")
@CrossOrigin(origins = "*")
public class EventoController {
	
	@Autowired
	private EventoRepository _eventoRepository;
	
	@RequestMapping(value = "/evento", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna lista de Eventos")
	public List<Evento> listEvento(){
		return _eventoRepository.findAll();
	}
	
	@RequestMapping(value = "/evento/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna Evento Unico")
	public ResponseEntity<Evento>ListEventoGetById(@PathVariable(value = "id") long id){
		Optional<Evento> evento = Optional.ofNullable(_eventoRepository.findById(id));
		if(evento.isPresent()) {
			return new ResponseEntity<Evento>(evento.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/evento", method = RequestMethod.POST)
	@ApiOperation(value = "Registra um evento")
	public Evento CadastrarEvento(@Valid @RequestBody Evento evento ) {
		return _eventoRepository.save(evento);
	}
	
	@RequestMapping(value = "/evento/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Atualiza um evento")
	public ResponseEntity<Evento> AtualizaEvento(@PathVariable(value ="id") long id, @Valid @RequestBody Evento evento){
		Optional<Evento> newEvento = Optional.ofNullable(_eventoRepository.findById(id));
		if(newEvento.isPresent()) {
			Evento e = newEvento.get();
			e.setNome(evento.getNome());
			e.setDescricao(evento.getDescricao());
			e.setDataInicio(evento.getDataInicio());
			e.setDataFim(evento.getDataFim());
			
			_eventoRepository.save(evento);
			
			return new ResponseEntity<Evento>(evento, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	 @RequestMapping(value = "/evento/{id}", method = RequestMethod.DELETE)
	 @ApiOperation(value = "Deleta um evento")
	 public ResponseEntity<Object> DeletaEvento(@PathVariable(value = "id") long id){
	        Optional<Evento> evento = Optional.ofNullable(_eventoRepository.findById(id));
	        if(evento.isPresent()){
	        	_eventoRepository.delete(evento.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

}
