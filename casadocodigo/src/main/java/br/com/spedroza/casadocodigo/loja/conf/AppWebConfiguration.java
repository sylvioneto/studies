package br.com.spedroza.casadocodigo.loja.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
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

import com.google.common.cache.CacheBuilder;

import br.com.spedroza.casadocodigo.loja.controller.HomeController;
import br.com.spedroza.casadocodigo.loja.dao.ProdutoDAO;
import br.com.spedroza.casadocodigo.loja.infra.FileSaver;
import br.com.spedroza.casadocodigo.loja.model.CarrinhoCompras;

@EnableWebMvc
@EnableCaching //enable cache
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDAO.class , FileSaver.class, CarrinhoCompras.class})
public class AppWebConfiguration {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		System.out.println("Inside AppWebConfiguration.internalResourceViewResolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); // onde estao as paginas
		resolver.setSuffix(".jsp"); // sufixo das paginas
		resolver.setExposedContextBeanNames("carrinhoCompras");
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		System.out.println("Inside AppWebConfiguration.messageSource");
		System.out.println("Setting messages property file...");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
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
		System.out.println("Inside AppWebConfiguration.multipartResolver");
		return new StandardServletMultipartResolver();
	}

	@Bean
	public CacheManager cacheManager() {
		System.out.println("Inside AppWebConfiguration.cacheManager");
		//return new ConcurrentMapCacheManager(); //basic cache only for tests
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(cacheBuilder);
		return null;
		
	}
}
