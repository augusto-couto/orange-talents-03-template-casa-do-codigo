package br.com.zupacademy.augusto.casadocodigo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.augusto.casadocodigo.model.Estado;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Long>{
	
	Optional<Estado> findByNomeAndPaisNome(String nome, String nomePais);

	Estado findByNomeAndPaisId(String nomeEstado, Long idPais);

	List<Estado> findByPaisNome(String nome);
}
