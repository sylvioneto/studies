package br.com.spedroza.bookstore.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.spedroza.bookstore.controller.HomeController;
import br.com.spedroza.bookstore.dao.ProductDAO;
import br.com.spedroza.bookstore.infra.FileSaver;
import br.com.spedroza.bookstore.model.Cart;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProductDAO.class , FileSaver.class, Cart.class})
public class AppWebConfiguration {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		System.out.println("Inside AppWebConfiguration.internalResourceViewResolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); // views path
		resolver.setSuffix(".jsp"); // pages sufix
		// cart object will be exposed
		resolver.setExposedContextBeanNames("cart");
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		System.out.println("Inside AppWebConfiguration.messageSource");
		System.out.println("Setting messages property file...");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages"); // messages file
		messageSource.setDefaultEncoding("UTF-8"); // encoding
		messageSource.setCacheSeconds(1);
		return messageSource;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		System.out.println("Inside AppWebConfiguration.mvcConversionService");
	    DefaultFormattingConversionService conversionService = 
	        new DefaultFormattingConversionService();
	    DateFormatterRegistrar registrar = new DateFormatterRegistrar();
	    System.out.println("Setting date format...");
	    registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
	    registrar.registerFormatters(conversionService);
	    return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
}
