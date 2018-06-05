package br.com.spedroza.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.spedroza.casadocodigo.loja.controller.HomeController;
import br.com.spedroza.casadocodigo.loja.dao.ProdutoDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class,ProdutoDAO.class})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); //onde estao as paginas
		resolver.setSuffix(".jsp"); //sufixo das paginas
		return resolver;
	}
}
