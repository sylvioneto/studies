package br.com.spedroza.casadocodigo.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.spedroza.casadocodigo.loja.model.Produto;

@Controller
public class ProdutoController {

	@RequestMapping("/produtos/form")
	public String form(){
		return "produtos/form";
	}
	
	//metodo mapedo no form.jsp de produtos para inserir produtos
	@RequestMapping("/produtos")
	public String gravar(Produto produto){
		System.out.println(produto);
		return "produtos/ok";
	}
}
