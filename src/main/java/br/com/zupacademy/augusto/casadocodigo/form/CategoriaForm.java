package br.com.zupacademy.augusto.casadocodigo.form;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.augusto.casadocodigo.model.Categoria;

public class CategoriaForm {
	@NotBlank
	private String nome;

	public CategoriaForm(@NotBlank String nome) {
		super();
		this.nome = nome;
	}
	
	@Deprecated
	public CategoriaForm() {
		super();
	}

	public String getNome() {
		return nome;
	}
	
	public Categoria converter() {
		return new Categoria(this.nome);
	}
}
