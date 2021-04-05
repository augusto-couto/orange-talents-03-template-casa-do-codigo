package br.com.zupacademy.augusto.casadocodigo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotBlank
	private String nome;
	@Column(nullable = false, unique = true)
	@Email
	private String email;
	@Column(nullable = false, length = 400)
	private String descricao;
	private LocalDateTime registeredAt = LocalDateTime.now();
	
	public Autor(String nome, @Email String email, String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	@Deprecated
	public Autor() {
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
