package br.com.spedroza.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.spedroza.casadocodigo.loja.dao.ProdutoDAO;
import br.com.spedroza.casadocodigo.loja.model.Produto;

@Controller
public class ProdutoController {

	// this annotation is to spring inject product dao here
	@Autowired
	private ProdutoDAO pdao;
	
	@RequestMapping("/produtos/form")
	public String form(){
		return "produtos/form";
	}
	
	//metodo mapedo no form.jsp de produtos para inserir produtos
	@RequestMapping("/produtos")
	public String gravar(Produto produto){
		System.out.println(produto);
		pdao.insert(produto);;
		return "produtos/ok";
	}
}
