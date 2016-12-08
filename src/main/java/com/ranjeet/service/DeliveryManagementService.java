package com.ranjeet.service;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ranjeet.dao.JobDetailDao;
import com.ranjeet.model.AdminUser;
import com.ranjeet.model.Employee;
import com.ranjeet.model.IdleCode;
import com.ranjeet.model.JobDetails;
import com.ranjeet.model.Machine;

@Service
public class DeliveryManagementService {

	private static Logger LOG = Logger
			.getLogger(DeliveryManagementService.class);

	@Autowired
	private JobDetailDao jobDetailsDao;

	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> employees = null;
		try {
			employees = jobDetailsDao.getAllEmployees();
		} catch (Exception e) {
			LOG.error("Error in fetching employees" + e);
			throw new Exception(e);
		}
		return employees;
	}

	public void saveJobDetails(JobDetails jobDetails) throws Exception {
		try {
			// employee =
			// jobDetailsDao.getEmployeeDetails(jobDetails.getEmployee().getEmployeeId());
			// jobDetails.setEmployee(employee);
			jobDetailsDao.saveJobDetails(jobDetails);

		} catch (Exception e) {
			LOG.error("Error in fetching job" + e);
			throw new Exception(e);
		}
	}

	public Employee getEmployee(int employeeId) throws Exception {

		Employee employee = new Employee();

		employee = jobDetailsDao.getEmployeeDetails(employeeId);

		return employee;

	}

	public List<Employee> getEmployeeDetailsFromActualEmployeeId(int employeeId)
			throws Exception {

		List<Employee> employees = null;

		employees = jobDetailsDao
				.getEmployeeDetailsFromActualEmployeeId(employeeId);

		return employees;

	}

	public Employee findEmployeeByEmployeeId(int employeeId) {

		Employee employee = null;

		employee = jobDetailsDao.findEmployeeByEmployeeId(employeeId);

		return employee;

	}

	public List<JobDetails> findJobDetailsById(int empId) throws Exception {

		List<JobDetails> fetchedJobDetails = null;

		fetchedJobDetails = jobDetailsDao.findJobDetailsById(empId);

		return fetchedJobDetails;
	}

	public void saveEmployeeDetails(Employee employee) throws Exception {

		try {

			jobDetailsDao.saveEmployeeDetails(employee);

		} catch (Exception e) {
			LOG.error("Error in fetching hotels" + e);
			throw new Exception(e);
		}

	}

	public List<JobDetails> filterJobDetailsByDate(Employee employee,
			Date fromDate, Date toDate) throws Exception {

		List<JobDetails> filteredJobDetails = null;

		filteredJobDetails = jobDetailsDao.findJobDetailsById(employee,
				fromDate, toDate);

		return filteredJobDetails;
	}

	public void saveMachineDetails(Machine machine) throws Exception {
		try {

			jobDetailsDao.saveMachineDetails(machine);

		} catch (Exception e) {
			LOG.error("Error in saving machine" + e);
			throw new Exception(e);
		}
	}

	public List<Machine> getAllMachines() throws Exception {

		List<Machine> machines = null;
		try {
			machines = jobDetailsDao.getAllMachines();
		} catch (Exception e) {
			LOG.error("Error in fetching machines" + e);
			throw new Exception(e);
		}
		return machines;
	}

	public Machine getMachine(int machineId) throws Exception {

		Machine machine = new Machine();

		machine = jobDetailsDao.getMachineDetails(machineId);

		return machine;

	}

	public List<JobDetails> findJobDetailsByPartNumber(int partNumber)
			throws Exception {

		List<JobDetails> fetchedJobDetailsByPartNumber = null;

		fetchedJobDetailsByPartNumber = jobDetailsDao
				.findJobDetailsByPartNumber(partNumber);

		return fetchedJobDetailsByPartNumber;
	}

	public List<JobDetails> getAllJobDetails() throws Exception {

		List<JobDetails> jobDetails = null;
		try {
			jobDetails = jobDetailsDao.getAllJobDetails();
		} catch (Exception e) {
			LOG.error("Error in fetching job details" + e);
			throw new Exception(e);
		}
		
		DeliveryManagementService.this.writeCountryListToFile("/Users/ranjeetsah/JobDetails.xls", jobDetails);
		
		return jobDetails;

	}
	
	public List<JobDetails> findJobDetailsByMachineName(int machineId)
			throws Exception {

		List<JobDetails> fetchedJobDetailsByPartNumber = null;

		fetchedJobDetailsByPartNumber = jobDetailsDao
				.findJobDetailsByMachineName(machineId);

		return fetchedJobDetailsByPartNumber;
	}

	
	public List<JobDetails> filterJobDetailsByMachineAndDate(Machine machine,
			Date fromDate, Date toDate) throws Exception {

		List<JobDetails> filteredJobDetails = null;

		filteredJobDetails = jobDetailsDao.filterJobDetailsByMachineAndDate(machine,
				fromDate, toDate);

		return filteredJobDetails;
	}

	public AdminUser getAdminUser() throws Exception {
		AdminUser adminUser = null;
		try {
			adminUser = jobDetailsDao.getAdminUser();
		} catch (Exception e) {
			LOG.error("Error in fetching admin user" + e);
			throw new Exception(e);
		}
		return adminUser;
	}

	public void saveAdminUser() {
		// TODO Auto-generated method stub
		
	}
	
	public void saveAdminUser(AdminUser user) throws Exception {
		try {

			jobDetailsDao.saveAdminUser(user);

		} catch (Exception e) {
			LOG.error("Error in saving user" + e);
			throw new Exception(e);
		}
	}
	
	
	public void writeCountryListToFile(String fileName, List<JobDetails> jobDetailsList) throws Exception{
		Workbook workbook = null;
		
		if(fileName.endsWith("xlsx")){
			workbook = new XSSFWorkbook();
		}else if(fileName.endsWith("xls")){
			workbook = new HSSFWorkbook();
		}else{
			throw new Exception("invalid file name, should be xls or xlsx");
		}
		
		Sheet sheet = workbook.createSheet("JobDetails");
		
		Iterator<JobDetails> iterator = jobDetailsList.iterator();
		
		int rowIndex = 0;
		Row row = sheet.createRow(rowIndex++);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("S.N");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("PartNumber");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("Employee");
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("Machine");
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("Details");
		Cell cell5 = row.createCell(5);
		cell5.setCellValue("Quantity");
		Cell cell6 = row.createCell(6);
		cell6.setCellValue("Order Number");
		Cell cell7 = row.createCell(7);
		cell7.setCellValue("Shift");
		Cell cell8 = row.createCell(8);
		cell8.setCellValue("Working Hour");
		Cell cell9 = row.createCell(9);
		cell9.setCellValue("BreakDown");
		Cell cell10 = row.createCell(10);
		cell10.setCellValue("Overtime(OT)");
		Cell cell11 = row.createCell(11);
		cell11.setCellValue("Remarks");
		Cell cell12 = row.createCell(12);
		cell12.setCellValue("Date");
		
		while(iterator.hasNext()){
			JobDetails jobDetails = iterator.next();
			row = sheet.createRow(rowIndex++);
			cell0 = row.createCell(0);
			cell0.setCellValue(jobDetails.getJobId());
			cell1 = row.createCell(1);
			cell1.setCellValue(jobDetails.getPartNumber());
			cell2 = row.createCell(2);
			cell2.setCellValue(jobDetails.getEmployee().getEmployeeId());
			cell3 = row.createCell(3);
			cell3.setCellValue(jobDetails.getMachine().getMachineName());
			cell4 = row.createCell(4);
			cell4.setCellValue(jobDetails.getDetails());
			cell5 = row.createCell(5);
			cell5.setCellValue(jobDetails.getQunatity());
			cell6 = row.createCell(6);
			cell6.setCellValue(jobDetails.getProductionOrderNumber());
			cell7 = row.createCell(7);
			cell7.setCellValue(jobDetails.getWorkingShift());
			cell8 = row.createCell(8);
			cell8.setCellValue(jobDetails.getWorkingHours());
			cell9 = row.createCell(9);
			cell9.setCellValue(jobDetails.getBreakdownHours());
			cell10 = row.createCell(10);
			cell10.setCellValue(jobDetails.getWorkingHours());
			cell11 = row.createCell(11);
			cell11.setCellValue(jobDetails.getRemarks());
			cell12 = row.createCell(12);
			cell12.setCellValue(jobDetails.getSavedDate());
			
		}
		
		//lets write the excel data to file now
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
		System.out.println(fileName + " written successfully");
	}

	public JobDetails fetchJobDetailsByJobId(int id) throws Exception{
		
		JobDetails jobDetails = null;
		
		try {

			jobDetails = jobDetailsDao.fetchJobDetailsByJobId(id);

		} catch (Exception e) {
			LOG.error("Error in saving user" + e);
			throw new Exception(e);
		}
		
		return jobDetails;
		
		
	}

	public IdleCode getIdleCode(int idleCodeId) throws Exception {
	
		IdleCode idleCode = new IdleCode();

		idleCode = jobDetailsDao.getIdleCode(idleCodeId);

		return idleCode;
		
	}
	
	
	public void saveIdleCode(IdleCode idleCode) throws Exception {
		try {

			jobDetailsDao.saveIdleCode(idleCode);

		} catch (Exception e) {
			LOG.error("Error in fetching idle code" + e);
			throw new Exception(e);
		}
	}

	public List<IdleCode> getAllIdleCodes() throws Exception {
		
		List<IdleCode> idleCodes = null;
		try {
			idleCodes = jobDetailsDao.getAllIdleCodes();
		} catch (Exception e) {
			LOG.error("Error in fetching machines" + e);
			throw new Exception(e);
		}
		return idleCodes;
		
	}
	


}
