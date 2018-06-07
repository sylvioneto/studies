package br.com.spedroza.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.spedroza.casadocodigo.loja.model.Produto;

public class ProdutoValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// check if the target classe is a Produto
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("Inside "+this.getClass().getName());
		System.out.println("Checking mandatory fields...");
		
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");

        Produto produto = (Produto) target;
        if(produto.getPaginas() <= 0) {
            errors.rejectValue("paginas", "field.required");
        }
		
	}
	
	
}
