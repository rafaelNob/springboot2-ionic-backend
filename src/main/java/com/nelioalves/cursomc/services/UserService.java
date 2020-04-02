package com.nelioalves.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nelioalves.cursomc.security.UserSS;

public class UserService {

	/**
	 * Metodo que retorna no usuario logado
	 * @return
	 */
	public static UserSS usuarioAuthenticado() {
			try {
				
				return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				return null;
			}
	}
}
