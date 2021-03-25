package br.com.zupacademy.augusto.casadocodigo.dto;

import br.com.zupacademy.augusto.casadocodigo.model.Pais;

public class PaisDto {
	private Long id;
	private String nome;

	public PaisDto(Pais pais) {
		super();
		this.id = pais.getId();
		this.nome = pais.getNome();
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
