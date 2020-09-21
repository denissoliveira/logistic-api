package com.logistic.domain;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@MappedSuperclass
public abstract class BaseAudit extends BaseEntity {

	private static final long serialVersionUID = -3465965549823384527L;

	// @Schema isso é usado para o swagger use o DTO ou se esta entidade só enviada direto para o "frontend"
	@Schema(description = "A data de criação do registro.", example = "2020-02-16 21:22:27")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date createdAt;
	
	@Schema(description = "O login de quem criou o registro.", example = "admin")
	private String creator;
	
	@Schema(description = "A data de atualização do registro.", example = "2020-02-16 21:22:27")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date updatedAt;
	
	@Schema(description = "O login de quem atualizou o registro.", example = "admin")
	private String updater;
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = new Date();
		// FIX-ME: Até ser craido um login
		this.updater = this.updater == null ? "system" : this.updater;
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		this.createdAt = atual;
		this.updatedAt = atual;
		// FIX-ME: Até ser craido um login
		this.updater = this.updater == null ? "system" : this.updater;
		this.creator = this.creator == null ? "system" : this.creator;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
}
