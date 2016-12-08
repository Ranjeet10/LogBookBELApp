package com.ranjeet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="idleCode")
public class IdleCode {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="idle_code_details", nullable=false)
	private String idleCodeDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdleCodeDetails() {
		return idleCodeDetails;
	}

	public void setIdleCodeDetails(String idleCodeDetails) {
		this.idleCodeDetails = idleCodeDetails;
	}
	
}
