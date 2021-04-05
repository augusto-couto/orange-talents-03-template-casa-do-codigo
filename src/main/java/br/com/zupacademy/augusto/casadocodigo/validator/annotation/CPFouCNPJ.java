package br.com.zupacademy.augusto.casadocodigo.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import br.com.zupacademy.augusto.casadocodigo.validator.CPFouCNPJValidator;

@ReportAsSingleViolation
@Documented
@Constraint(validatedBy = {CPFouCNPJValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface CPFouCNPJ {
	String message() default "CPF ou CNPJ inválidos!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
