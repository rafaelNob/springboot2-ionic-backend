package com.nelioalves.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nelioalves.cursomc.security.JWTAuthenticationFilter;
import com.nelioalves.cursomc.security.JWTAutorizationFilter;
import com.nelioalves.cursomc.security.JWTUtil;

@Configuration
@EnableWebSecurity
/**
 *  anotação para autorizar alguns perfis junto com @PreAuthorize("hasAnyRole('ADMIN')")
 *	nos endPoints
 *
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SegurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jWTUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;

	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	private static final String[] PUBLIC_MATCHERS_GET = { "/produtos/**", "/categorias/**","/estados/**"};
	
	private static final String[] PUBLIC_MATCHERS_POST = {"/clientes","/auth/forgot/**" };
	
	/**
	 * HABILIA OS CAMINHOS PARA O SWAGGER
	 *	 http://localhost:8080/swagger-ui.html
	 */
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	 web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
	"/swagger-ui.html", "/webjars/**");
	}


	/**
	 * Metodo que recebe o http para ver quem pode acessar o methodo
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		 * Configuração especifica para BD h2
		 */
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		// caso n use
		http.cors().and().csrf().disable();

		/**
		 * 
		 */
		http.authorizeRequests()
				// habilita esses perfils somente para METHOD GET
				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST)
				.permitAll()
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
				.permitAll()
				.antMatchers(PUBLIC_MATCHERS)
				.permitAll()
				.anyRequest()
				.authenticated();
		// resgatando da class JWTAuthenticationFilter
		//authenticationManager() ja é o metodo disponivel FILTRO AUTENTICA
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jWTUtil));
		//FILTRO DE AUTORIZAÇÃO TOKEN BEARER 
		http.addFilter(new JWTAutorizationFilter(authenticationManager(), jWTUtil,userDetailsService));
		
		
		// Assegura que o backend não cria sessão de usuario
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	/**
	 * classe que authentica O USUARIO E de DESCRIPTOGRAFA a SENHA dO USUARIO
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	

	/**
	 * Permite Acesso a multiplas fontes
	 * 
	 * @return
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuracaoCors = new CorsConfiguration().applyPermitDefaultValues();
		//libera o cors para delete
		configuracaoCors.setAllowedMethods(Arrays.asList("POST","PUT","DELETE","OPTIONS","GET"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuracaoCors);

		return source;
	}
/**
 * METODO PARA CRIPTOGRAFICA DA SENHA
 * @return
 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}
	

}
