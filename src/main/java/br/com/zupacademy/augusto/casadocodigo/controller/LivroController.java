package br.com.zupacademy.augusto.casadocodigo.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.augusto.casadocodigo.dto.LivroDto;
import br.com.zupacademy.augusto.casadocodigo.form.LivroForm;
import br.com.zupacademy.augusto.casadocodigo.model.Livro;
import br.com.zupacademy.augusto.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.augusto.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.augusto.casadocodigo.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
// 5
public class LivroController {
	
	// 3
	private LivroRepository livroRepository;
	private AutorRepository autorRepository;
	private CategoriaRepository categoriaRepository;

	public LivroController(LivroRepository livroRepository,
			AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	@Transactional
	// 2
	public LivroDto cadastrar(@RequestBody @Valid LivroForm livroForm) {
		Livro livro = livroForm.converter(autorRepository, categoriaRepository);
		livroRepository.save(livro);
		return new LivroDto(livro);
	}
}
