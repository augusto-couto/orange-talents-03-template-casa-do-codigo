package br.com.zupacademy.augusto.casadocodigo.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.augusto.casadocodigo.form.AutorForm;
import br.com.zupacademy.augusto.casadocodigo.model.Autor;
import br.com.zupacademy.augusto.casadocodigo.repository.AutorRepository;

@Component
public class EmailDuplicadoValidator implements Validator{
	
	private AutorRepository autorRepository;
	
	public EmailDuplicadoValidator(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AutorForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		AutorForm autorForm = (AutorForm) target;
		
		Optional<Autor> hasAutor = autorRepository.findByEmail(autorForm.getEmail());
		
		if (hasAutor.isPresent()) {
			errors.rejectValue("email", null,
					"O email " + autorForm.getEmail() + 
					" já foi utilizado por outro(a) autor(a).");
		}
	}
}
