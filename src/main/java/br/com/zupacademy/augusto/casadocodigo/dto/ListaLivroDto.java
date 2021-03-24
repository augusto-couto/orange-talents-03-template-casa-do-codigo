package br.com.zupacademy.augusto.casadocodigo.dto;

import org.springframework.data.domain.Page;

import br.com.zupacademy.augusto.casadocodigo.model.Livro;

public class ListaLivroDto {
	private Long id;
	private String titulo;
	
	public ListaLivroDto(Livro livro) {
		super();
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
	}

	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}

	public static Page<ListaLivroDto> converter(Page<Livro> livros) {
		return livros.map(ListaLivroDto::new);
	}
}
