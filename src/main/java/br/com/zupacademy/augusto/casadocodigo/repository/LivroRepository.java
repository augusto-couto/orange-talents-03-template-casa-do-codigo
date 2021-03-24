package br.com.zupacademy.augusto.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.augusto.casadocodigo.model.Livro;

@Repository
public interface LivroRepository extends PagingAndSortingRepository<Livro, Long>{

}
