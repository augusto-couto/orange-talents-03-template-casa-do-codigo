package br.com.zupacademy.augusto.casadocodigo.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.augusto.casadocodigo.dto.PaisDto;
import br.com.zupacademy.augusto.casadocodigo.form.PaisForm;
import br.com.zupacademy.augusto.casadocodigo.model.Pais;
import br.com.zupacademy.augusto.casadocodigo.repository.PaisRepository;

@RestController
@RequestMapping("/paises")
public class PaisController {
	
	private PaisRepository paisRepository;
	
	public PaisController(PaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}
	
	@PostMapping
	public PaisDto cadastrar(@RequestBody @Valid PaisForm paisForm) {
		Pais pais = paisForm.converter();
		paisRepository.save(pais);
		return new PaisDto(pais);
	}
}
