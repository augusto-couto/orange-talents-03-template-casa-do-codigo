package br.com.zupacademy.augusto.casadocodigo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.augusto.casadocodigo.model.Cliente;
import br.com.zupacademy.augusto.casadocodigo.model.Pais;
import br.com.zupacademy.augusto.casadocodigo.repository.PaisRepository;
import br.com.zupacademy.augusto.casadocodigo.validator.annotation.ValorUnico;

public class ClienteForm {

	@Email
	@NotBlank
	@ValorUnico(classe = Cliente.class, campo = "email")
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@ValorUnico(classe = Cliente.class, campo = "documento")
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@NotNull
	private String nomePais;
	private String nomeEstado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	
	public String getEmail() {
		return email;
	}
	public String getNome() {
		return nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public String getDocumento() {
		return documento;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public String getNomePais() {
		return nomePais;
	}
	public String getNomeEstado() {
		return nomeEstado;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getCep() {
		return cep;
	}
	
	public Cliente converte(PaisRepository paisRepository) {
		Pais pais = paisRepository.findByNome(nomePais);
		
		return new Cliente(this.email, this.nome, this.sobrenome, this.documento, this.endereco,
				this.complemento, this.cidade, pais, this.telefone, this.cep);
	}
}
