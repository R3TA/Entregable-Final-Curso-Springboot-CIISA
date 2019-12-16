package com.class6.exercise.auth;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.class6.exercise.model.Usuario;
import com.class6.exercise.service.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{ //le agregamos mas info a nuestro token de acceso
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
		
		info.put("nombre", usuario.getUsername()); //estamos pasando datos del usuario autenticado
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info); //le pasamos el arreglo de info
		
		return accessToken;
	}

}
