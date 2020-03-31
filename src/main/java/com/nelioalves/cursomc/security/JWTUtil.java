package com.nelioalves.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Não esquecer de ver apllication.properties
 * 
 * @author Rafael.Franca
 *
 */

/*
 * jwt.secret= sequencia #um minuto jwt.expiration =60000
 */
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secretKey;
	@Value("${jwt.expiration}")
	private Long expiration;
	/**
	 * metodo que gera o token
	 * @param uername
	 * @return
	 */
	public String generateToken(String uername) {
		
		return Jwts.builder()
		//usuario
		.setSubject(uername)
		.setExpiration(new Date(System.currentTimeMillis() + expiration))
		//como vou assinar o token
		.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
		.compact();
		
	}

	

}
