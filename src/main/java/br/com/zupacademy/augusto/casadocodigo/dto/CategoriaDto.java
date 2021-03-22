package br.com.zupacademy.augusto.casadocodigo.dto;

import br.com.zupacademy.augusto.casadocodigo.model.Categoria;

public class CategoriaDto {
	private Long id;
	private String nome;
	
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
