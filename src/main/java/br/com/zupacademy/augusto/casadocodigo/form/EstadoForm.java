package br.com.zupacademy.augusto.casadocodigo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.augusto.casadocodigo.model.Estado;
import br.com.zupacademy.augusto.casadocodigo.model.Pais;
import br.com.zupacademy.augusto.casadocodigo.repository.PaisRepository;

public class EstadoForm {
	@NotBlank
	private String nome;
	@NotNull
	private String nomePais;
	
	public String getNome() {
		return nome;
	}
	public String getNomePais() {
		return nomePais;
	}
	public Estado converter(PaisRepository paisRepository) {
		Pais paisAtual = paisRepository.findByNome(nomePais);
		return new Estado(this.nome, paisAtual);
	}
}
