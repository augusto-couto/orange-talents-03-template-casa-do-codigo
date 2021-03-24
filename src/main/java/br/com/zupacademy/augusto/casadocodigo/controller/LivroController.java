package br.com.zupacademy.augusto.casadocodigo.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.augusto.casadocodigo.dto.ListaLivroDto;
import br.com.zupacademy.augusto.casadocodigo.dto.LivroDto;
import br.com.zupacademy.augusto.casadocodigo.form.LivroForm;
import br.com.zupacademy.augusto.casadocodigo.model.Livro;
import br.com.zupacademy.augusto.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.augusto.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.augusto.casadocodigo.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
// 7
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
	
	@GetMapping
	@Cacheable(value = "listaLivros")
	// 1
	public Page<ListaLivroDto> listar(@PageableDefault(
			sort = "titulo", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		Page<Livro> livros = livroRepository.findAll(pageable);
		return ListaLivroDto.converter(livros);
	}
	
	@GetMapping("/{id}")
	//1
	public ResponseEntity<LivroDto> detalhar(@PathVariable Long id) {
		Optional<Livro> livro = livroRepository.findById(id);
		if (livro.isPresent()) {
			return ResponseEntity.ok(new LivroDto(livro.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaLivros", allEntries = true)
	// 2
	public LivroDto cadastrar(@RequestBody @Valid LivroForm livroForm) {
		Livro livro = livroForm.converter(autorRepository, categoriaRepository);
		livroRepository.save(livro);
		return new LivroDto(livro);
	}
}
