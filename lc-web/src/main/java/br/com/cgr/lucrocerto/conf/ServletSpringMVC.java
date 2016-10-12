package br.com.cgr.lucrocerto.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { JPAConfig.class, SecurityConfigExt.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
