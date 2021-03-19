package br.com.zupacademy.augusto.casadocodigo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.augusto.casadocodigo.model.Autor;

public class AutorForm {
	@NotBlank
	private String nome;
	@NotBlank
	@Email
	private String email;
	@NotBlank @Size(max = 400)
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getDescricao() {
		return descricao;
	}

	public Autor converter() {
		return new Autor(nome, email, descricao);
	}
}
