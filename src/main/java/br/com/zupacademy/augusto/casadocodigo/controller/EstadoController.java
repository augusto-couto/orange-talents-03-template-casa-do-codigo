package br.com.zupacademy.augusto.casadocodigo.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.augusto.casadocodigo.dto.EstadoDto;
import br.com.zupacademy.augusto.casadocodigo.form.EstadoForm;
import br.com.zupacademy.augusto.casadocodigo.model.Estado;
import br.com.zupacademy.augusto.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.augusto.casadocodigo.repository.PaisRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	private PaisRepository paisRepository;
	private EstadoRepository estadoRepository;
	
	public EstadoController(PaisRepository paisRepository, EstadoRepository estadoRepository) {
		this.paisRepository = paisRepository;
		this.estadoRepository = estadoRepository;
	}
	
	@PostMapping
	public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm estadoForm, UriComponentsBuilder uriBuilder) {
		Estado estado = estadoForm.converter(paisRepository);
		
		Optional<Estado> existeEstadoNessePais = estadoRepository
				.findByNomeAndPaisNome(estadoForm.getNome(), estadoForm.getNomePais());
		
		if (existeEstadoNessePais.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "O estado " + estado.getNome() + 
					" ja esta cadastrado no país " + estado.getPais().getNome());
		}
		estadoRepository.save(estado);
		
		URI uri = uriBuilder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}
}
