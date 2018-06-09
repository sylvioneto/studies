package br.com.spedroza.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Setting configuration classes...");
		return new Class[]{AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// filter to set UTF-8
	@Override
	protected Filter[] getServletFilters() {
		System.out.println("Setting Servlet Filters...");
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		System.out.println("Setting characterset UTF-8...");
		encodingFilter.setEncoding("UTF-8");
		return new Filter[]{encodingFilter};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		System.out.println("Setting customizeRegistration...");
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}
