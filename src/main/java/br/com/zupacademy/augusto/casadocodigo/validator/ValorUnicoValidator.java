package br.com.zupacademy.augusto.casadocodigo.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zupacademy.augusto.casadocodigo.validator.annotation.ValorUnico;

//3
public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object>{
	
	private String campoAtributo;
	private Class<?> classe;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	//1
	public void initialize(ValorUnico valorUnico) {
		campoAtributo = valorUnico.campo();
		classe = valorUnico.classe();
	}

	@Override
	//2
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = entityManager.createQuery("SELECT 1 FROM " + classe.getName() +
				" WHERE " + campoAtributo + "=:value");
		query.setParameter("value", value);
		
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "Já existe um " + classe.getName() + " com o valor "
				+ value + " no campo " + campoAtributo);
		
		return list.isEmpty();
	}

}
