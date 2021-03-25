package br.com.zupacademy.augusto.casadocodigo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.augusto.casadocodigo.model.Pais;

@Repository
public interface PaisRepository extends CrudRepository<Pais, Long>{
	Pais findByNome(String nome);
}
