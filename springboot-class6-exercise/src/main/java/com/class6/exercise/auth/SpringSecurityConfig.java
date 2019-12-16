package com.class6.exercise.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter { //configuración por el lado de spring security

	@Autowired
	private UserDetailsService usuarioService;
	
	@Bean //el objeto que retorna este método lo va a registrar
	public BCryptPasswordEncoder passwordEncoder() { //método para la encriptación de contraseña
		return new BCryptPasswordEncoder();
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder()); //registramos el usuario con la contraseña encriptada
	}

	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception { //por el lado de spring
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable() //deshabilito la protección cruzada ya que separamos el frontend
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //deshabilito el manejo de sesiones, ya que ahora lo manejamos con tokens, no guardamos la información del usuario en la sesión, sino en el token, por el lado del cliente
	}

}
