package br.com.spedroza.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.spedroza.casadocodigo.loja.dao.ProdutoDAO;
import br.com.spedroza.casadocodigo.loja.model.CarrinhoCompras;
import br.com.spedroza.casadocodigo.loja.model.CarrinhoItem;
import br.com.spedroza.casadocodigo.loja.model.Produto;
import br.com.spedroza.casadocodigo.loja.model.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		System.out.println("inside CarrinhoComprasController.add");
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		return modelAndView;
	}
	
	/*
	@RequestMapping("/remove")
	public ModelAndView remove(Integer produtoId,TipoPreco tipoPreco) {
		System.out.println("inside CarrinhoComprasController.remove");
		carrinho.remover(produtoId, tipoPreco);
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");		
		return modelAndView;
	}
	*/
	
	@RequestMapping("/remove")
	public ModelAndView remove(Integer produtoId, TipoPreco tipoPreco) {
		System.out.println("inside CarrinhoComprasController.remove");
		carrinho.remover(produtoId, tipoPreco);
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView modelAndView = new ModelAndView("carrinho/itens");
		return modelAndView;
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.getById(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);

		return carrinhoItem;
	}
}
