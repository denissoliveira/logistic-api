package com.logistic.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.logistic.contants.IConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private Environment env;

	//Endereços publicos que podem se acessados
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			IConstants.ROOT_URL + IConstants.V1+"clientes/**",

	};
	
	//Endereços GET publicos que podem se acessados
	private static final String[] PUBLIC_MATCHERS_GET = {
			IConstants.ROOT_URL + IConstants.V1+"produtos/**",
			IConstants.ROOT_URL + IConstants.V1+"categorias/**"
	};
	
	/*
	 * Para todo que esta em PUBLIC_MATCHERS, permite
	 * para os outros requests, autenticar 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Para pode acessar o h2-console no teste
		if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            http.headers().frameOptions().disable();
        }
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll()
			.anyRequest().authenticated();
		
		// Não vai criar a sessão do usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//Configuração básica para qualquer origin pode acessar (cors)
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		
		
//		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
//		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
