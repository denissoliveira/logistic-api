package com.logistic.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	
	public CredenciaisDTO() {
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//Padrão builder
	public static class Builder {
		
		//Requerido
		private String email;
		private String senha;
		
		public Builder(String email, String senha) {
			this.email = email;
			this.senha = senha;
		}
		
		public CredenciaisDTO build() {
			return new CredenciaisDTO(this);
		}
	}
	
	private CredenciaisDTO(Builder builder) {
		email = builder.email;
		senha = builder.senha;
	}

	
}
