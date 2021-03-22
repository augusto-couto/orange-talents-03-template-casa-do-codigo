package br.com.zupacademy.augusto.casadocodigo.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.augusto.casadocodigo.dto.AutorDto;
import br.com.zupacademy.augusto.casadocodigo.form.AutorForm;
import br.com.zupacademy.augusto.casadocodigo.model.Autor;
import br.com.zupacademy.augusto.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.augusto.casadocodigo.validator.EmailDuplicadoValidator;

@RestController
@RequestMapping("/autores")
public class AutorController {
	
	private AutorRepository autorRepository;
	private EmailDuplicadoValidator emailValidator;
	
	public AutorController(AutorRepository autorRepository, EmailDuplicadoValidator emailValidator) {
		this.autorRepository = autorRepository;
		this.emailValidator = emailValidator;
	}
	
	@InitBinder
	public void inicio(WebDataBinder binder) {
		binder.addValidators(emailValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AutorDto> cadastrar(@RequestBody @Valid AutorForm autorForm, UriComponentsBuilder uriBuilder) {
		Autor autor = autorForm.converter();
		autorRepository.save(autor);
		
		URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).body(new AutorDto(autor));
	}
}
