package br.com.zupacademy.augusto.casadocodigo.dto;

import br.com.zupacademy.augusto.casadocodigo.model.Estado;
import br.com.zupacademy.augusto.casadocodigo.model.Pais;

public class EstadoDto {
	private Long id;
	private String nome;
	private Pais pais;
	
	public EstadoDto(Estado estado) {
		super();
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.pais = estado.getPais();
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Pais getPais() {
		return pais;
	}
}
