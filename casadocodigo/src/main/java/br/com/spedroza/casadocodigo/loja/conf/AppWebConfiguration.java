package br.com.spedroza.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.spedroza.casadocodigo.loja.controller.HomeController;
import br.com.spedroza.casadocodigo.loja.dao.ProdutoDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDAO.class })
public class AppWebConfiguration {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		System.out.println("Setting spring InternalResourceViewResolver...");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); // onde estao as paginas
		resolver.setSuffix(".jsp"); // sufixo das paginas
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		System.out.println("Setting messages property file...");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}
}
