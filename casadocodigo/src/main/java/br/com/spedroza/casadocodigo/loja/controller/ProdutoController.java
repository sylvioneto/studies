package br.com.spedroza.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spedroza.casadocodigo.loja.dao.ProdutoDAO;
import br.com.spedroza.casadocodigo.loja.model.Produto;
import br.com.spedroza.casadocodigo.loja.model.TipoPreco;

@Controller
public class ProdutoController {

	// this annotation is to spring inject product dao here
	@Autowired
	private ProdutoDAO pdao;
	
	@RequestMapping("/produtos/form")
	public ModelAndView form(){
		System.out.println("Inside ProdutoController.form...");
		System.out.println("Creating a modelAndView for /produtos/form");
		ModelAndView modelAndView = new ModelAndView("/produtos/form");
		System.out.println("Setting tipos object from the TipoPreco enum");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	//metodo mapedo no form.jsp de produtos para inserir produtos
	@RequestMapping("/produtos")
	public String gravar(Produto produto){
		System.out.println(produto);
		pdao.insert(produto);;
		return "produtos/ok";
	}
}
