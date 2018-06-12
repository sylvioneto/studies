package br.com.spedroza.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spedroza.casadocodigo.loja.dao.ProdutoDAO;

@Controller //anotacao de que a classe eh um controller
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@RequestMapping("/") // anotacao de que este metodo toma conta da requisicao barra
	@Cacheable(value="produtosHome") // keep this on cache
	public ModelAndView home(){
		System.out.println("Inside HomeController.home...");
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtoDAO.getAll());
		return modelAndView;
	}
}
