package com.ranjeet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "job_details")
public class JobDetails {
	
	@Id
	@Column(name="job_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int jobId;
	
	
	@ManyToOne
	@JoinColumn(name="emp_id", nullable=false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="machine_id", nullable=false)
	private Machine machine;
	
	@Column(name="part_number")
	private int partNumber;
	
	@Column(name="details")
	private String details;
	
	@Column(name="qunatity")
	private int qunatity;
	
	@Column(name="production_order_number")
	private int productionOrderNumber;
	
	@Column(name="working_shift")
	private String workingShift;
	
	public String getWorkingShift() {
		return workingShift;
	}


	public void setWorkingShift(String workingShift) {
		this.workingShift = workingShift;
	}


	@Column(name="working_hours")
	private float workingHours;
	
	@Column(name="breakdown_hours")
	private float breakdownHours;
	
	@Column(name="idle_hours")
	private float idleHours;
	
	@ManyToOne
	@JoinColumn(name="idle_code", nullable=false)
	private IdleCode idleCode;
	
	public void setIdleCode(IdleCode idleCode) {
		this.idleCode = idleCode;
	}


	public float getIdleHours() {
		return idleHours;
	}


	public void setIdleHours(float idleHours) {
		this.idleHours = idleHours;
	}


	@Column(name="remarks")
	private String remarks;
	
	//@Column(name="launched_quantity")
	//private String launchedQuantity;
	
	@Column(name="saved_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date savedDate;
	
	/*public String getLaunchedQuantity() {
		return launchedQuantity;
	}


	public void setLaunchedQuantity(String launchedQuantity) {
		this.launchedQuantity = launchedQuantity;
	}*/


	/*public String getPlannedQuantity() {
		return plannedQuantity;
	}


	public void setPlannedQuantity(String plannedQuantity) {
		this.plannedQuantity = plannedQuantity;
	}


	public String getDeliveredQuantity() {
		return deliveredQuantity;
	}


	public void setDeliveredQuantity(String deliveredQuantity) {
		this.deliveredQuantity = deliveredQuantity;
	}*/


	/*@Column(name="planned_quantity")
	private String plannedQuantity;
	
	@Column(name="delivered_quantity")
	private String deliveredQuantity;
	*/


	public int getJobId() {
		return jobId;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public int getQunatity() {
		return qunatity;
	}


	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}


	public int getProductionOrderNumber() {
		return productionOrderNumber;
	}


	public void setProductionOrderNumber(int productionOrderNumber) {
		this.productionOrderNumber = productionOrderNumber;
	}


	public float getWorkingHours() {
		return workingHours;
	}


	public void setWorkingHours(float workingHours) {
		this.workingHours = workingHours;
	}


	public float getBreakdownHours() {
		return breakdownHours;
	}


	public void setBreakdownHours(float breakdownHours) {
		this.breakdownHours = breakdownHours;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public void setJobId(int jobId) {
		this.jobId = jobId;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public int getPartNumber() {
		return partNumber;
	}


	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}


	public Date getSavedDate() {
		return savedDate;
	}


	public void setSavedDate(Date savedDate) {
		this.savedDate = savedDate;
	}


	public Machine getMachine() {
		return machine;
	}


	public void setMachine(Machine machine) {
		this.machine = machine;
	}
		
}
