package br.com.spedroza.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spedroza.bookstore.model.Cart;

@RequestMapping("/payment")
@Controller
public class PaymentController {

	@Autowired
	private Cart cart;
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public ModelAndView checkout(RedirectAttributes model) {
		System.out.println("inside PaymentController.checkout");
		System.out.println("Total: "+cart.getTotal());
		model.addFlashAttribute("success", "Payment has been processed successfully!!!");
		return new ModelAndView("redirect:/product");
	}
}
