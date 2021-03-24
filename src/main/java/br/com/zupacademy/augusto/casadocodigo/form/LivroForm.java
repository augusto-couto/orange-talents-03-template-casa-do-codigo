package br.com.zupacademy.augusto.casadocodigo.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.zupacademy.augusto.casadocodigo.model.Autor;
import br.com.zupacademy.augusto.casadocodigo.model.Categoria;
import br.com.zupacademy.augusto.casadocodigo.model.Livro;
import br.com.zupacademy.augusto.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.augusto.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.augusto.casadocodigo.validator.annotation.ValorUnico;

public class LivroForm {
	@ValorUnico(classe = Livro.class, campo = "titulo")
	@NotNull
	private String titulo;
	@Length(max = 500)
	@NotNull
	private String resumo;
	private String sumario;
	@Min(value = 20)
	@NotNull
	private BigDecimal preco;
	@Min(value = 100)
	@NotNull
	private Integer paginas;
	@ValorUnico(classe = Livro.class, campo = "isbn")
	@NotNull
	private String isbn;
	@DateTimeFormat
	@Future
	private LocalDate dataPublicacao;
	@NotNull
	@ManyToOne
	private String categoria;
	@NotNull
	@ManyToOne
	private String autor;
	
	public String getTitulo() {
		return titulo;
	}
	public String getResumo() {
		return resumo;
	}
	public String getSumario() {
		return sumario;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public Integer getPaginas() {
		return paginas;
	}
	public String getIsbn() {
		return isbn;
	}
	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}
	public String getCategoria() {
		return categoria;
	}
	public String getAutor() {
		return autor;
	}
	public Livro converter(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
		Autor autorAtual = autorRepository.findByNome(autor);
		Categoria categoriaAtual = categoriaRepository.findByNome(categoria);
		
		return new Livro(this.titulo, this.resumo, this.sumario, this.preco,
				this.paginas, this.isbn, this.dataPublicacao, categoriaAtual, autorAtual);
	}
}
