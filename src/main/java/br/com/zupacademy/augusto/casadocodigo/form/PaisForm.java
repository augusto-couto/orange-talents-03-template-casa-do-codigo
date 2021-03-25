package br.com.zupacademy.augusto.casadocodigo.form;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.augusto.casadocodigo.model.Pais;
import br.com.zupacademy.augusto.casadocodigo.validator.annotation.ValorUnico;

public class PaisForm {
	@NotBlank
	@ValorUnico(classe = Pais.class, campo = "nome")
	private String nome;

	public String getNome() {
		return nome;
	}
	
	public Pais converter() {
		return new Pais(this.nome);
	}
}
