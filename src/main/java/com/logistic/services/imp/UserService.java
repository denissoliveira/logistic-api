package com.logistic.services.imp;

import org.springframework.security.core.context.SecurityContextHolder;

import com.logistic.security.UserSpringSecurity;

public class UserService {

	//Retorna usuário logado
	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
