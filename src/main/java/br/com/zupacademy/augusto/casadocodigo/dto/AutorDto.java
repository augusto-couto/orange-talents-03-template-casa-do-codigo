package br.com.zupacademy.augusto.casadocodigo.dto;

import java.time.LocalDateTime;

import br.com.zupacademy.augusto.casadocodigo.model.Autor;

public class AutorDto {
	private Long id;
	private String nome;
	private String email;
	private String descricao;
	private LocalDateTime registeredAt;
	
	public AutorDto(Autor autor) {
		this.id = autor.getId();
		this.nome = autor.getNome();
		this.email = autor.getEmail();
		this.descricao = autor.getDescricao();
		this.registeredAt = autor.getRegisteredAt();
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}
}
