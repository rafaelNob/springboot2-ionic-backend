package com.nelioalves.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * 
 * @author Rafael.Franca Classe para autorização pegando o token Bearer
 */
public class JWTAutorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jWTUtil;
	private UserDetailsService userDetailsService;

	public JWTAutorizationFilter(AuthenticationManager authenticationManager, JWTUtil jWTUtil,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jWTUtil = jWTUtil;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * vai executar algo antes da requisição continuar
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/**
		 * Pega o Bearer Token
		 */
		String header = request.getHeader("Autorization");
		//PROCEDIMENTO PARA LIBERAR O USUARIO QUE DESEJA ACESSAR O ENDPOINT
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(request, header.substring(7));
			if(auth != null) {
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		}
		chain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
		if(jWTUtil.tokenValid(token)) {
			String username = jWTUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
		}
		return null;
	}
}
