package br.com.zupacademy.augusto.casadocodigo.validator;

import java.util.InputMismatchException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.augusto.casadocodigo.validator.annotation.CPFouCNPJ;

public class CPFouCNPJValidator implements ConstraintValidator<CPFouCNPJ, String> {
	
	@Override
	public void initialize(CPFouCNPJ constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.isEmpty() || isCpf(value) || isCnpj(value);
	}

	/**
	   * Realiza a validação do CPF.
	   * 
	   * @param cpf número de CPF a ser validado pode ser passado no formado 999.999.999-99 ou
	   *        99999999999
	   * @return true se o CPF é válido e false se não é válido
	   */
	private boolean isCpf(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) {
			return false;
		}
		
		int d1, d2;
		d1 = d2 = 0;
		int digito1, digito2, resto;
		digito1 = digito2 = 0;
		int digitoCpf;
		String digitoResultado;
		
		for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
			digitoCpf = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();
			
			d1 = d1 + (11 - nCount) * digitoCpf;
			
			d2 = d2 + (12 - nCount) * digitoCpf;
		}
		
		resto = d1 % 11;
		
		if (resto < 2) {
			digito1 = 0;
		} else {
			digito1 = 11 - resto;
		}
		
		d2 += 2 * digito1;
		
		resto = (d2 % 11);
		
		if (resto < 2) {
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}
		
		String digitoVerificador = cpf.substring(cpf.length() - 2, cpf.length());
		
		digitoResultado = String.valueOf(digito1) + String.valueOf(digito2);
		
		return digitoVerificador.equals(digitoResultado);
	}
	
	/**
	   * Realiza a validação de um cnpj
	   * 
	   * @param cnpj String - o CNPJ pode ser passado no formato 99.999.999/9999-99 ou 99999999999999
	   * @return boolean
	   */
	private boolean isCnpj(String cnpj) {
	    cnpj = cnpj.replace(".", "");
	    cnpj = cnpj.replace("-", "");
	    cnpj = cnpj.replace("/", "");
	    
	    try{
	      Long.parseLong(cnpj);
	    } catch(NumberFormatException e){
	      return false;
	    }
	    
	    // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
	    if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") 
	        || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
	        || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
	        || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
	        || cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || (cnpj.length() != 14)) {
	    	return (false);
	    }
	      
	    char dig13, dig14;
	    int sm, i, r, num, peso;
	                             
	    try {
	      
	      sm = 0;
	      peso = 2;
	      for (i = 11; i >= 0; i--) {
	        
	        num = (int) (cnpj.charAt(i) - 48);
	        sm = sm + (num * peso);
	        peso = peso + 1;
	        if (peso == 10)
	          peso = 2;
	      }

	      r = sm % 11;
	      if ((r == 0) || (r == 1))
	        dig13 = '0';
	      else
	        dig13 = (char) ((11 - r) + 48);

	      
	      sm = 0;
	      peso = 2;
	      for (i = 12; i >= 0; i--) {
	        num = (int) (cnpj.charAt(i) - 48);
	        sm = sm + (num * peso);
	        peso = peso + 1;
	        if (peso == 10)
	          peso = 2;
	      }
	      r = sm % 11;
	      if ((r == 0) || (r == 1))
	        dig14 = '0';
	      else
	        dig14 = (char) ((11 - r) + 48);
	      
	      if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
	        return (true);
	      else
	        return (false);
	    } catch (InputMismatchException erro) {
	      return (false);
	    }
	  }

}
