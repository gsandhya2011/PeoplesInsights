package com.example.peoplesinsights.model.audit;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//To include information about who created or updated a poll in the poll model, 
//and automatically populate this information based on current logged in user.
@MappedSuperclass
@JsonIgnoreProperties( value = {"createdBy", "updatedBy"}, allowGetters=true)
public abstract class UserDateAudit extends DateAudit {

	@CreatedBy
	@Column(updatable = false)
	private Long createdBy;
	
	@LastModifiedBy
	private Long updatedBy;

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
}
