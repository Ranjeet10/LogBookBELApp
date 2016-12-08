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
@Table(name="EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="emp_id", nullable=false)
	private int employeeId;
	
	@Column(name="emp_first_name", nullable=false)
	private String firstName;
	
	@Column(name="emp_last_name", nullable=false)
	private String lastName;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="employee")
	private Set<JobDetails> jobDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<JobDetails> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(Set<JobDetails> jobDetails) {
		this.jobDetails = jobDetails;
	}

}
