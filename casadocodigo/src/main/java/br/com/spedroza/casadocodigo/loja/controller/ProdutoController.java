package br.com.spedroza.casadocodigo.loja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spedroza.casadocodigo.loja.dao.ProdutoDAO;
import br.com.spedroza.casadocodigo.loja.infra.FileSaver;
import br.com.spedroza.casadocodigo.loja.model.Produto;
import br.com.spedroza.casadocodigo.loja.model.TipoPreco;
import br.com.spedroza.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	// this annotation is to spring inject product dao here
	@Autowired
	private ProdutoDAO pdao;
	
	@Autowired
	private FileSaver fileSaver;
	
	// this method links the validator with the product
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.addValidators(new ProdutoValidation());
	}
	
	//this method is mapped to /produtos/form and send the user to form.jsp
	@RequestMapping("/form")
	public ModelAndView form(Produto produto){
		System.out.println("Inside ProdutoController.form");
		System.out.println("Creating a modelAndView for /produtos/form");
		ModelAndView modelAndView = new ModelAndView("/produtos/form");
		System.out.println("Setting TipoPreco enum");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes){
		// Valid annotation send the Product object to validation
		// MultipartFile is used to received the file		
		System.out.println("Inside ProdutoController.gravar");
		System.out.println(produto);
		
		System.out.println("Filename: "+sumario.getOriginalFilename());
		if(sumario.getOriginalFilename()!=null && !sumario.getOriginalFilename().isEmpty()) {
			String path = fileSaver.write("upload-dir", sumario);
			produto.setSumarioPath(path);	
		}
		
		
		// check problems
		  if (result.hasErrors()) {
		        return form(produto);
		    }
		
		// insert the product  
		pdao.insert(produto);
		
		// set success message
		System.out.println("Setting success message...");
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		// send user to product list page using redirect after posting
		System.out.println("Redirect after posting...");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getProdutos(){
		System.out.println("Inside "+this.getClass().getName());
		ModelAndView modelAndView = new ModelAndView("/produtos/list");
		List<Produto> produtos = pdao.getAll();
		System.out.println("Setting produtos list...");
		modelAndView.addObject("produtos",produtos);
		return modelAndView;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") int id){
		ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
		Produto produto = pdao.getById(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
}
