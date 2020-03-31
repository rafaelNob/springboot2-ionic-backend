package com.nelioalves.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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
	 * 
	 * @param uername
	 * @return
	 */
	public String generateToken(String uername) {

		return Jwts.builder()
				// usuario
				.setSubject(uername).setExpiration(new Date(System.currentTimeMillis() + expiration))
				// como vou assinar o token
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

	}

	public boolean tokenValid(String token) {

		/**
		 * Claims = armazena as reivindigaçções do token no caso Usuario e tempode
		 * expiração
		 */
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date dataDeExpiração = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());

			/**
			 * obs: now.before(dataDeExpiração) compara a data de expiração com a data Atual
			 */

			if (username != null && dataDeExpiração != null && now.before(dataDeExpiração)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {

			return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();

		}
		return null;
	}

}
