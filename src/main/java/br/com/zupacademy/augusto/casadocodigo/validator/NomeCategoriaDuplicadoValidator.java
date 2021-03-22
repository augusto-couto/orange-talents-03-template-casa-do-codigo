package br.com.zupacademy.augusto.casadocodigo.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.augusto.casadocodigo.form.CategoriaForm;
import br.com.zupacademy.augusto.casadocodigo.model.Categoria;
import br.com.zupacademy.augusto.casadocodigo.repository.CategoriaRepository;

@Component
public class NomeCategoriaDuplicadoValidator implements Validator {
	
	private CategoriaRepository categoriaRepository;
	
	public NomeCategoriaDuplicadoValidator(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		CategoriaForm categoriaForm = (CategoriaForm) target;
		
		Optional<Categoria> categoria = categoriaRepository.findByNome(categoriaForm.getNome());
		
		if (categoria.isPresent()) {
			errors.rejectValue("nome", null,
					"O nome de categoria " + categoriaForm.getNome() + " ja está cadastrado.");
		}
	}

}
