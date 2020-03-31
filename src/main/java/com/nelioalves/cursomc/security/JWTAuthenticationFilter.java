package com.nelioalves.cursomc.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelioalves.cursomc.dto.CredenciaisDTO;

/**
 * UsernamePasswordAuthenticationFilter sabe que tem que interceptar a
 * requisição de LOGIN /login
 * 
 * @author Rafael.Franca
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager AuthenticationManager;

	private JWTUtil jWTUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jWTUtil) {
		this.AuthenticationManager = authenticationManager;
		this.jWTUtil = jWTUtil;
	}

	/**
	 * tente autenticar
	 */

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {

			CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getSenha(), new ArrayList<>());
			Authentication authentication = AuthenticationManager.authenticate(authToken);
			return authentication;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * se a autenticação for sucesso
	 * gerando o token e adicionando ao corpo da requisição
	 */

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String userName = ((UserSS) authResult.getPrincipal()).getUsername();
		String token = jWTUtil.generateToken(userName);
		response.addHeader("Authorization", "Bearer " + token);
	}

}
