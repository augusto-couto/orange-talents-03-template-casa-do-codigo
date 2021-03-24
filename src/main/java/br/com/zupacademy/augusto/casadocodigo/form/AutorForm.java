package br.com.zupacademy.augusto.casadocodigo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.augusto.casadocodigo.model.Autor;
import br.com.zupacademy.augusto.casadocodigo.validator.annotation.ValorUnico;

public class AutorForm {
	@NotBlank
	private String nome;
	@NotBlank
	@Email
	@ValorUnico(classe = Autor.class, campo = "email")
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
		return new Autor(this.nome, this.email, this.descricao);
	}
}
