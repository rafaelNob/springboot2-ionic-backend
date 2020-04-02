package com.nelioalves.cursomc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.security.JWTUtil;
import com.nelioalves.cursomc.security.UserSS;
import com.nelioalves.cursomc.services.UserService;


/**
 * Classe para revalidar o token de expiração	
 * @author Rafael.Franca
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil JWTUtil;
	
	@RequestMapping(value = "/refresh_token" , method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS usuaioAutenticado = UserService.usuarioAuthenticado();
		String token = JWTUtil.generateToken(usuaioAutenticado.getUsername());
		response.setHeader("Authorization", "Bearer " + token);		
		return ResponseEntity.noContent().build();
	}

}
