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

import br.com.silva.duarte.ifevento.models.Participante;
import br.com.silva.duarte.ifevento.repository.ParticipanteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Eventos")
@CrossOrigin(origins = "*")
public class ParticipanteController {
	
	
	@Autowired
	private ParticipanteRepository _participanteRepository;
	
	@RequestMapping(value = "/participante", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna lista de Participante")
	public List<Participante> listParticipante(){
		return _participanteRepository.findAll();
	}
	
	@RequestMapping(value = "/participante/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna Participante Unico")
	public ResponseEntity<Participante>ListParticipanteGetById(@PathVariable(value = "id") long id){
		Optional<Participante> participante = Optional.ofNullable(_participanteRepository.findById(id));
		if(participante.isPresent()) {
			return new ResponseEntity<Participante>(participante.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/participante", method = RequestMethod.POST)
	@ApiOperation(value = "Registra um Participante")
	public Participante CadastrarParticipante(@Valid @RequestBody Participante participante ) {
		return _participanteRepository.save(participante);
	}
	
	@RequestMapping(value = "/participante/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Atualiza um Participante")
	public ResponseEntity<Participante> AtualizaParticipante(@PathVariable(value ="id") long id, @Valid @RequestBody Participante participante){
		Optional<Participante> newParicipante = Optional.ofNullable(_participanteRepository.findById(id));
		if(newParicipante.isPresent()) {
			Participante p = newParicipante.get();
			p.setNome(participante.getNome());
			p.setEmail(participante.getEmail());
			p.setDataNascimento(participante.getDataNascimento());
			
			_participanteRepository.save(participante);
			
			return new ResponseEntity<Participante>(participante, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	 @RequestMapping(value = "/participante/{id}", method = RequestMethod.DELETE)
	 @ApiOperation(value = "Deleta um evento")
	 public ResponseEntity<Object> DeletaParticipante(@PathVariable(value = "id") long id){
	        Optional<Participante> participante = Optional.ofNullable(_participanteRepository.findById(id));
	        if(participante.isPresent()){
	        	_participanteRepository.delete(participante.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

}
