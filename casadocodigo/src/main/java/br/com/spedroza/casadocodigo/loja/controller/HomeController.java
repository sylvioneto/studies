package br.com.spedroza.casadocodigo.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //anotacao de que a classe eh um controller
public class HomeController {

	@RequestMapping("/") //anotacao de que este metodo toma conta da requisicao barra
	public String home(){
		System.out.println("Entrando na Home da casa do codigo");
		return "home";
	}
}
