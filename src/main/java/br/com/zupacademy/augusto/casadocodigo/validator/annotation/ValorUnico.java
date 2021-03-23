package br.com.zupacademy.augusto.casadocodigo.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.augusto.casadocodigo.validator.ValorUnicoValidator;

@Constraint(validatedBy = ValorUnicoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ValorUnico {
	
	String message() default "{br.com.zupacademy.augusto.casadocodigo.validator.annotation.valorunico}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
        
    String campo();
    
    Class<?> classe();
}
