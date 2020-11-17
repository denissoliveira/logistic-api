package com.logistic.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.logistic.contants.IConstants;
import com.logistic.security.JWTAuthenticationFilter;
import com.logistic.security.JWTAuthorizationFilter;
import com.logistic.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;

	//Endereços publicos que podem se acessados
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			"/actuator/**",
			"/api-docs/**",
			"/swagger-ui/**"
	};
	
	//Endereços GET publicos que podem se acessados
	private static final String[] PUBLIC_MATCHERS_GET = {
			IConstants.ROOT_URL + IConstants.V1+"produtos/**",
			IConstants.ROOT_URL + IConstants.V1+"categorias/**",
			IConstants.ROOT_URL + IConstants.V1+"estados/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			IConstants.ROOT_URL + IConstants.V1+"clientes/**",
			IConstants.ROOT_URL + IConstants.V1+"auth/forgot/**",
			"/login/**",
			"/login**"
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
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	//Configuração básica para qualquer origin pode acessar (cors)
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
