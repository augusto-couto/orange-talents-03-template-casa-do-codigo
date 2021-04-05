package br.com.zupacademy.augusto.casadocodigo.dto;

import br.com.zupacademy.augusto.casadocodigo.model.Cliente;
import br.com.zupacademy.augusto.casadocodigo.model.Estado;
import br.com.zupacademy.augusto.casadocodigo.model.Pais;

public class ClienteDto {

	private Long id;
	private String email;
	private String nome;
	private String sobrenome;
	private String documento;
	private String endereco;
	private String complemento;
	private String cidade;
	private Pais pais;
	private Estado estado;
	private String telefone;
	private String cep;
	
	public ClienteDto(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.email = cliente.getEmail();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.documento = cliente.getDocumento();
		this.endereco = cliente.getEndereco();
		this.complemento = cliente.getComplemento();
		this.cidade = cliente.getCidade();
		this.pais = cliente.getPais();
		this.estado = cliente.getEstado();
		this.telefone = cliente.getTelefone();
		this.cep = cliente.getCep();
	}

	public Long getId() {
		return id;
	}

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

	public Pais getPais() {
		return pais;
	}

	public Estado getEstado() {
		return estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}
}
