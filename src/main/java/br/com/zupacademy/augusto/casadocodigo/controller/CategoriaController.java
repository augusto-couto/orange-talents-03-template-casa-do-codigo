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

import br.com.zupacademy.augusto.casadocodigo.dto.CategoriaDto;
import br.com.zupacademy.augusto.casadocodigo.form.CategoriaForm;
import br.com.zupacademy.augusto.casadocodigo.model.Categoria;
import br.com.zupacademy.augusto.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.augusto.casadocodigo.validator.NomeCategoriaDuplicadoValidator;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private CategoriaRepository categoriaRepository;
	private NomeCategoriaDuplicadoValidator nomeValidator;
	
	public CategoriaController(CategoriaRepository categoriaRepository,
			NomeCategoriaDuplicadoValidator nomeValidator) {
		this.categoriaRepository = categoriaRepository;
		this.nomeValidator = nomeValidator;
	}
	
	@InitBinder
	public void inicio(WebDataBinder binder) {
		binder.addValidators(nomeValidator);
	}
	
	@PostMapping
	@Transactional
	private ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm categoriaForm,
			UriComponentsBuilder componentsBuilder) {
		
		Categoria categoria = categoriaForm.converter();
		categoriaRepository.save(categoria);
		
		URI uri = componentsBuilder.path("/autores/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));		
	}
}
