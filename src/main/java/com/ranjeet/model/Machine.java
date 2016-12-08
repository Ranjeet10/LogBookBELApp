package com.ranjeet.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="machine")
public class Machine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	//@Column(name="machine_id", nullable=false)
	//private int machineId;
	
	@Column(name="machine_name", nullable=false)
	private String machineName;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="machine")
	private Set<JobDetails> jobDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//public int getMachineId() {
	//	return machineId;
	//}

	//public void setMachineId(int machineId) {
	//	this.machineId = machineId;
	//}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public Set<JobDetails> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(Set<JobDetails> jobDetails) {
		this.jobDetails = jobDetails;
	}
	

}
