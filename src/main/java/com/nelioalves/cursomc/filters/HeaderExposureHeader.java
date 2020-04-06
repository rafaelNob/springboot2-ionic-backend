package com.nelioalves.cursomc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


/**
 * 
 * @author rafael.franca
 * classe Responsavel para expor o location da requisição
 */

@Component
public class HeaderExposureHeader implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse res = (HttpServletResponse) response;
		
		res.addHeader("access-control-expose-headers", "location");
		chain.doFilter(request, response);
		
	}

}
