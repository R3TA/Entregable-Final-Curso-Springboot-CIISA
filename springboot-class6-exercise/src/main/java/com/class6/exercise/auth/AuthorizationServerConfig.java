package com.class6.exercise.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{ //configuración por el lado de oauth2 y jwt

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager; //lo necesitamos para el proceso de login
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken; //inyectamos el componente de información adicional

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()") //para que tengan acceso a cualquier usuario a autenticarse oauth token
		.checkTokenAccess("isAuthenticated()"); //permiso al endpoint para validar el token para endpoints que se está enviando
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception { //configuración de credenciales con oauth2 para ambos lados de la aplicación (tanto de los usuarios del backend como las credenciales del frontend)
		clients.inMemory().withClient("angularapp") //usuario de la app del frontend, con and puede agregar mas apps clientes
		.secret(passwordEncoder.encode("12345")) //password encriptado
		.scopes("read", "write") //tipos de permisos
		.authorizedGrantTypes("password", "refresh_token") //credenciales de usuario del backend con un token de acceso validado sin tener que iniciar sesión nuevamente
		.accessTokenValiditySeconds(3600) //tiempos de expiración o caducidad
		.refreshTokenValiditySeconds(3600); //en cuanto se renueva
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain(); //anida la información del token con la nueva
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter())); //ahora tenemos ambos objetos dentro de la cadena
		
		endpoints.authenticationManager(authenticationManager) //encargado de la autenticación y validación del token
		.tokenStore(tokenStore()) //para registrar una instancia del componente que crea el token  y almacena los datos
		.accessTokenConverter(accessTokenConverter()) //encargado del token jwt, traduce los datos de acceso y lo maneja todo por debajo los datos del usuario (username y roles)
		.tokenEnhancer(tokenEnhancerChain); //registramos la cadena
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA); //el que firma
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA); //el que valida que nuestro token sea autentico
		return jwtAccessTokenConverter;
	}
	

}
