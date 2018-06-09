package br.com.spedroza.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spedroza.casadocodigo.loja.model.CarrinhoCompras;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinhoCompras;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public ModelAndView finalizar(RedirectAttributes model) {
		System.out.println("inside PagamentoController.finalizar");
		System.out.println("Total: "+carrinhoCompras.getTotal());
		model.addFlashAttribute("sucesso", "Pagamento realizado com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}
}
