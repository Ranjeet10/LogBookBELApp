package com.ranjeet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.apache.log4j.Logger;

import com.ranjeet.model.AdminUser;
import com.ranjeet.model.Employee;
import com.ranjeet.model.IdleCode;
import com.ranjeet.model.JobDetails;
import com.ranjeet.model.Machine;
import com.ranjeet.service.DeliveryManagementService;

@Controller
@SessionAttributes("loggedInUser")
public class DeliveryManagerController {

	private static Logger LOG = Logger
			.getLogger(DeliveryManagerController.class);

	@Autowired
	private DeliveryManagementService deliveryManagementService;

	@RequestMapping("/")
	public String home(Model model) {
	
		return "index";
	}

	@RequestMapping("/addEmployeeDetails")
	public String addEmployeeDetails(Model model, HttpServletRequest request) {
		
		model.addAttribute("user", new AdminUser());
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("User");
		if (null == userName) {
			model.addAttribute("loginMsg", "Please login to continue");
		   System.out.println("Session ended");
		   return "login";
		}
		return "addEmployee";
	}
	
	@RequestMapping("/addIdleCode")
	public String addIdleCode(Model model, HttpServletRequest request) {
		
		model.addAttribute("user", new AdminUser());
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("User");
		if (null == userName) {
			model.addAttribute("loginMsg", "Please login to continue");
		   System.out.println("Session ended");
		   return "login";
		}
		return "addIdleCode";
	}

	@RequestMapping("/addMachineDetails")
	public String addMachineDetails(Model model, HttpServletRequest request) {
		
		model.addAttribute("user", new AdminUser());
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("User");
		if (null == userName) {
			model.addAttribute("loginMsg", "Please login to continue");
		   System.out.println("Session ended");
		   return "login";
		}
		
		return "addMachine";
	}

	@RequestMapping("/showAdminPage")
	public String showAdminPage(Model model, HttpServletRequest request) {
		
		model.addAttribute("user", new AdminUser());
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("User");
		if (null == userName) {
			model.addAttribute("loginMsg", "Please login to continue");
		   System.out.println("Session ended");
		   return "login";
		}
		
		return "adminPage";
	}

	@RequestMapping(value = "/saveEmployeeDetails", method = RequestMethod.POST)
	@ResponseBody
	public String saveEmployeeDetails(@RequestBody String employeeDetails, HttpServletRequest request, Model model) {
		
		
		model.addAttribute("user", new AdminUser());
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("User");
		if (null == userName) {
			model.addAttribute("loginMsg", "Please login to continue");
		   System.out.println("Session ended");
		   return "login";
		}

		Employee employee = null;
		JSONObject json = new JSONObject();
		String firstName = null;
		String lastName = null;
		String empId = null;

		try {
			json = (JSONObject) (new JSONParser().parse(employeeDetails));
			if (json.containsKey("empId")) {
				empId = (String) json.get("empId");
			}
			if (json.containsKey("firstName")) {
				firstName = (String) json.get("firstName");
			}
			if (json.containsKey("lastName")) {
				lastName = (String) json.get("lastName");
			}

			employee = new Employee();
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmployeeId(Integer.parseInt(empId));
			deliveryManagementService.saveEmployeeDetails(employee);

		} catch (Exception e) {
			LOG.error("Not able to get Details", e);
		}
		return "adminPage";
	}

	@RequestMapping(value = "/saveMachineDetails", method = RequestMethod.POST)
	@ResponseBody
	public String saveMachineDetails(@RequestBody String machineDetails, HttpServletRequest request, Model model) {
		
		model.addAttribute("user", new AdminUser());
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("User");
		if (null == userName) {
			model.addAttribute("loginMsg", "Please login to continue");
		   System.out.println("Session ended");
		   return "login";
		}

		Machine machine = null;
		JSONObject json = new JSONObject();
		String machineName = null;

		try {
			json = (JSONObject) (new JSONParser().parse(machineDetails));
			if (json.containsKey("machineName")) {
				machineName = (String) json.get("machineName");
			}

			machine = new Machine();
			machine.setMachineName(machineName);
			// machine.setMachineId(Integer.parseInt(machineId));
			deliveryManagementService.saveMachineDetails(machine);

		} catch (Exception e) {
			LOG.error("Not able to save machine", e);
		}
		return "adminPage";
	}
	
	@RequestMapping(value = "/saveIdleCode", method = RequestMethod.POST)
	@ResponseBody
	public String saveIdleCode(@RequestBody String idleCodeDetails, HttpServletRequest request, Model model) {
		
		model.addAttribute("user", new AdminUser());
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("User");
		if (null == userName) {
			model.addAttribute("loginMsg", "Please login to continue");
		   System.out.println("Session ended");
		   return "login";
		}

		IdleCode idleCode = null;
		JSONObject json = new JSONObject();
		String idleCodeDetail = null;

		try {
			json = (JSONObject) (new JSONParser().parse(idleCodeDetails));
			if (json.containsKey("idleCodeDetail")) {
				idleCodeDetail = (String) json.get("idleCodeDetail");
			}

			idleCode = new IdleCode();
			idleCode.setIdleCodeDetails(idleCodeDetail);
			deliveryManagementService.saveIdleCode(idleCode);

		} catch (Exception e) {
			LOG.error("Not able to save idle code", e);
		}
		return "adminPage";
	}

	@RequestMapping("/getDetailsForm")
	public String getJobDetailsForm(Model model) {

		try {
			List<Machine> machines = deliveryManagementService.getAllMachines();
			System.out.println(machines);
			model.addAttribute("machines", machines);

		} catch (Exception e) {
			LOG.error("Not able to fetch cities", e);
		}
		return "hotelBooking";
	}
	
	@RequestMapping(value = "/getAdminUserDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getAdminUserDetails() {

		try {
			AdminUser user = deliveryManagementService.getAdminUser();
			System.out.println(user);

		} catch (Exception e) {
			LOG.error("Not able to fetch cities", e);
		}
		return "updatePassword";
	}

	@RequestMapping("/addJobDetails")
	public String getJobForm(Model model) {
		try {
			List<Employee> employees = deliveryManagementService
					.getAllEmployees();
			List<Machine> machines = deliveryManagementService.getAllMachines();
			List<IdleCode> idleCodes = deliveryManagementService.getAllIdleCodes();
			System.out.println("Details");
			System.out.println(employees);
			System.out.println(machines);

			model.addAttribute("employees", employees);
			model.addAttribute("machines", machines);
			model.addAttribute("idleCodes", idleCodes);
			model.addAttribute("jobForm", new JobDetails());
			// employee = deliveryManagementService.getEmployee(1);
			// System.out.println(employee.getFirstName());
			// employees1 =
			// deliveryManagementService.findEmployeeByEmployeeId(12);
			// System.out.println(employees1);

		} catch (Exception e) {
			LOG.error("Not able to fetch predefined details", e);
		}
		return "addJobDetails";
	}

	/*
	 * @RequestMapping(value = "/saveJobDetails", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public void saveBookingDetails(@RequestBody String
	 * jobDetails){ JobDetails details = new JobDetails(); Employee employee =
	 * new Employee(); try{ employee =
	 * deliveryManagementService.getEmployee("12"); details.setJobId(1);
	 * details.setPartNumber(1234555); details.setEmployee(employee);
	 * 
	 * deliveryManagementService.saveJobDetails(details);
	 * 
	 * }catch(Exception e){ LOG.error("Not able to book Hotel" , e); }
	 * 
	 * }
	 */

	@RequestMapping(value = "/saveJobDetails", method = RequestMethod.POST)
	@ResponseBody
	public void saveJobDetails(@RequestBody String jobDetails) {
		JobDetails details = null;
		JSONObject json = new JSONObject();
		String empId = null;
		String partNumber = null;
		/*
		 * String plannedQuantity = null; String launchedQuantity = null; String
		 * deliveredQuantity = null;
		 */
		String savedDate = null;
		String machineId = null;
		String detailsOfJob = null;
		String quantity = null;
		String orderNo = null;
		String workingHours = null;
		String remarks = null;
		String breakDown = null;
		String workingShift = null;
		String idleHours = null;
		String idleCodeId = null;

		try {

			json = (JSONObject) (new JSONParser().parse(jobDetails));
			if (json.containsKey("empId")) {
				empId = (String) json.get("empId");
			}
			// if(json.containsKey("empName")){
			// empName = (String) json.get("empName");
			// }
			if (json.containsKey("partNumber")) {
				partNumber = (String) json.get("partNumber");
			}
			/*
			 * if (json.containsKey("plannedQuantity")) { plannedQuantity =
			 * (String) json.get("plannedQuantity"); } if
			 * (json.containsKey("launchedQuantity")) { launchedQuantity =
			 * (String) json.get("launchedQuantity"); } if
			 * (json.containsKey("deliveredQuantity")) { deliveredQuantity =
			 * (String) json.get("deliveredQuantity"); }
			 */
			if (json.containsKey("savedDate")) {
				savedDate = (String) json.get("savedDate");
			}
			if (json.containsKey("machineId")) {
				machineId = (String) json.get("machineId");
			}
			if (json.containsKey("details")) {
				detailsOfJob = (String) json.get("details");
			}
			if (json.containsKey("quantity")) {
				quantity = (String) json.get("quantity");
			}
			if (json.containsKey("orderNo")) {
				orderNo = (String) json.get("orderNo");
			}
			if (json.containsKey("workingHours")) {
				workingHours = (String) json.get("workingHours");
			}
			if (json.containsKey("workingShift")) {
				workingShift = (String) json.get("workingShift");
			}
			if (json.containsKey("breakDown")) {
				breakDown = (String) json.get("breakDown");
			}
			if (json.containsKey("remarks")) {
				remarks = (String) json.get("remarks");
			}
			if (json.containsKey("idleHours")) {
				idleHours = (String) json.get("idleHours");
			}
			if (json.containsKey("idleCodeId")) {
				idleCodeId = (String) json.get("idleCodeId");
			}
			
			
			System.out.println(savedDate);

			Date savedDateAsDate = new SimpleDateFormat("MM/dd/yyyy").parse(savedDate);

			String savedDateAsString = new SimpleDateFormat("yyyy-MM-dd")
					.format(savedDateAsDate);

			System.out.println(savedDateAsString);

			details = new JobDetails();
			Employee fetchedemployee = new Employee();
			fetchedemployee = deliveryManagementService.getEmployee(Integer
					.parseInt(empId));

			System.out.println(fetchedemployee);

			Machine fetchedmachine = new Machine();
			fetchedmachine = deliveryManagementService.getMachine(Integer
					.parseInt(machineId));
			
			IdleCode fetchedIdleCode = new IdleCode();
			fetchedIdleCode = deliveryManagementService.getIdleCode(Integer
					.parseInt(idleCodeId));
			
			System.out.println(fetchedmachine);
			System.out.println("Break down");
			System.out.println(breakDown);
			// System.out.println(Integer.parseInt(breakDown));
			System.out.println("working hours");
			System.out.println(workingHours);
			// System.out.println(Integer.parseInt(workingHours));

			details.setEmployee(fetchedemployee);
			details.setPartNumber(Integer.parseInt(partNumber));
			/*
			 * details.setPlannedQuantity(plannedQuantity);
			 * details.setLaunchedQuantity(launchedQuantity);
			 * details.setDeliveredQuantity(deliveredQuantity);
			 */
			details.setDetails(detailsOfJob);
			details.setQunatity(Integer.parseInt(quantity));
			details.setProductionOrderNumber(Integer.parseInt(orderNo));
			details.setWorkingHours(Float.parseFloat(workingHours));
			details.setWorkingShift(workingShift);
			details.setBreakdownHours(Float.parseFloat(breakDown));
			details.setIdleHours(Float.parseFloat(idleHours));
			details.setIdleCode(fetchedIdleCode);
			details.setRemarks(remarks);
			details.setMachine(fetchedmachine);
			System.out.println(savedDate);
//			java.sql.Date sqlDate = new java.sql.Date((new SimpleDateFormat(
//					"YYYY-MM-DD").parse(savedDate)).getTime());
//			System.out.println(sqlDate);
			details.setSavedDate(java.sql.Date.valueOf(savedDateAsString));
			deliveryManagementService.saveJobDetails(details);

		} catch (Exception e) {
			LOG.error("Not able to save job details", e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getEmployeeDetails", method = RequestMethod.POST)
	public Employee getEmployeeDetails(@RequestBody String employee)
			throws Exception {

		Employee fetchedEmployee = null;
		JSONObject json = new JSONObject();
		String empId = null;

		try {
			json = (JSONObject) (new JSONParser().parse(employee));
			if (json.containsKey("empId")) {
				empId = (String) json.get("empId");
			}
			fetchedEmployee = deliveryManagementService
					.findEmployeeByEmployeeId(Integer.parseInt(empId));
			System.out.println(fetchedEmployee.getId());

		} catch (Exception e) {
			LOG.error("Not able to get Details", e);
		}

		return fetchedEmployee;
	}

	@ResponseBody
	@RequestMapping(value = "/getJobDetails", method = RequestMethod.POST)
	public List<JobDetails> getJobDetails(@RequestBody String employee)
			throws Exception {

		List<JobDetails> fetchedJobDetails = null;
		JSONObject json = new JSONObject();
		String empId = null;

		try {
			json = (JSONObject) (new JSONParser().parse(employee));
			if (json.containsKey("id")) {
				empId = (String) json.get("id");
			}
			fetchedJobDetails = deliveryManagementService
					.findJobDetailsById(Integer.parseInt(empId));
			System.out.println(fetchedJobDetails.get(0).getPartNumber());

		} catch (Exception e) {
			LOG.error("Not able to get Details", e);
		}

		return fetchedJobDetails;
	}

	@ResponseBody
	@RequestMapping(value = "/getFilteredJobDetails", method = RequestMethod.POST)
	public List<JobDetails> getFilteredJobDetailsByDate(
			@RequestBody String filteringDetails) throws Exception {

		List<JobDetails> filteredJobDetails = null;
		JSONObject json = new JSONObject();
		String empId = null;
		String fromDate = null;
		String toDate = null;
		Date fromDateAsDate = null;
		Date toDateAsDate = null;

		try {
			json = (JSONObject) (new JSONParser().parse(filteringDetails));
			if (json.containsKey("id")) {
				empId = (String) json.get("id");
			}
			if (json.containsKey("fromDate")) {
				fromDate = (String) json.get("fromDate");
			}
			if (json.containsKey("toDate")) {
				toDate = (String) json.get("toDate");
			}

			Employee fetchedemployee = new Employee();
			// emp.setId(Integer.parseInt(empId));
			fetchedemployee = deliveryManagementService
					.findEmployeeByEmployeeId(Integer.parseInt(empId));
			// emp.setFirstName(empName);
			// emp.setLastName(empName);
			// emp.setEmployeeId(12);
			System.out.println(fetchedemployee);

			System.out.println(fromDate);
			System.out.println(toDate);

			fromDateAsDate = new SimpleDateFormat("MM/dd/yyyy").parse(fromDate);
			toDateAsDate = new SimpleDateFormat("MM/dd/yyyy").parse(toDate);

			String fromDateAsString = new SimpleDateFormat("yyyy-MM-dd")
					.format(fromDateAsDate);
			String toDateAsString = new SimpleDateFormat("yyyy-MM-dd")
					.format(toDateAsDate);

			System.out.println(fromDateAsString);
			System.out.println(toDateAsString);

			filteredJobDetails = deliveryManagementService
					.filterJobDetailsByDate(fetchedemployee,
							java.sql.Date.valueOf(fromDateAsString),
							java.sql.Date.valueOf(toDateAsString));

		} catch (Exception e) {
			LOG.error("Not able to get Details", e);
		}

		return filteredJobDetails;
	}

	@ResponseBody
	@RequestMapping(value = "/getJobDetailsByPartNumber", method = RequestMethod.POST)
	public List<JobDetails> getJobDetailsByPartNumber(
			@RequestBody String employee) throws Exception {

		List<JobDetails> fetchedJobDetails = null;
		JSONObject json = new JSONObject();
		String partNumber = null;

		try {
			json = (JSONObject) (new JSONParser().parse(employee));
			if (json.containsKey("partNumber")) {
				partNumber = (String) json.get("partNumber");
			}
			System.out.println(partNumber);
			fetchedJobDetails = deliveryManagementService
					.findJobDetailsByPartNumber(Integer.parseInt(partNumber));

		} catch (Exception e) {
			LOG.error("Not able to get Details", e);
		}

		return fetchedJobDetails;
	}

	@ResponseBody
	@RequestMapping(value = "/getJobDetailsByMachineId", method = RequestMethod.POST)
	public List<JobDetails> getJobDetailsByMachineId(@RequestBody String machine)
			throws Exception {

		List<JobDetails> fetchedJobDetails = null;
		JSONObject json = new JSONObject();
		String machineId = null;

		try {
			json = (JSONObject) (new JSONParser().parse(machine));
			if (json.containsKey("machineId")) {
				machineId = (String) json.get("machineId");
			}
			fetchedJobDetails = deliveryManagementService
					.findJobDetailsByMachineName(Integer.parseInt(machineId));

		} catch (Exception e) {
			LOG.error("Not able to get Details", e);
		}

		return fetchedJobDetails;
	}

	@RequestMapping("/getAllJobDetails")
	public String getAllJobDetails(Model model) {

		try {
			List<JobDetails> jobDetails = deliveryManagementService
					.getAllJobDetails();
			model.addAttribute("jobDetails", jobDetails);
		} catch (Exception e) {
			LOG.error("Unable to fetch job details", e);
		}

		return "showAllJobDetails";
	}

	@ResponseBody
	@RequestMapping(value = "/getFilteredJobDetailsByMachine", method = RequestMethod.POST)
	public List<JobDetails> getFilteredJobDetailsByMachine(
			@RequestBody String filteringDetails) throws Exception {

		List<JobDetails> filteredJobDetails = null;
		JSONObject json = new JSONObject();
		String machineId = null;
		String fromDate = null;
		String toDate = null;
		Date fromDateAsDate = null;
		Date toDateAsDate = null;

		try {
			json = (JSONObject) (new JSONParser().parse(filteringDetails));
			if (json.containsKey("machineId")) {
				machineId = (String) json.get("machineId");
			}
			if (json.containsKey("fromDate")) {
				fromDate = (String) json.get("fromDate");
			}
			if (json.containsKey("toDate")) {
				toDate = (String) json.get("toDate");
			}

			Machine fetchedmachine = new Machine();
			// emp.setId(Integer.parseInt(empId));
			fetchedmachine = deliveryManagementService.getMachine(Integer
					.parseInt(machineId));
			// emp.setFirstName(empName);
			// emp.setLastName(empName);
			// emp.setEmployeeId(12);
			System.out.println(fetchedmachine);

			System.out.println(fromDate);
			System.out.println(toDate);

			fromDateAsDate = new SimpleDateFormat("MM/dd/yyyy").parse(fromDate);
			toDateAsDate = new SimpleDateFormat("MM/dd/yyyy").parse(toDate);

			String fromDateAsString = new SimpleDateFormat("yyyy-MM-dd")
					.format(fromDateAsDate);
			String toDateAsString = new SimpleDateFormat("yyyy-MM-dd")
					.format(toDateAsDate);

			System.out.println(fromDateAsString);
			System.out.println(toDateAsString);

			filteredJobDetails = deliveryManagementService
					.filterJobDetailsByMachineAndDate(fetchedmachine,
							java.sql.Date.valueOf(fromDateAsString),
							java.sql.Date.valueOf(toDateAsString));

		} catch (Exception e) {
			LOG.error("Not able to get Details", e);
		}

		return filteredJobDetails;
	}

	@RequestMapping(value = "/showLoginPage")
	public String showLoginPage(Model model) {
		try{
			AdminUser user = deliveryManagementService.getAdminUser();
			model.addAttribute("user", user);
		}catch (Exception e){
			LOG.error("Not able to get user", e);
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/adminLogin")
	//public String adminLogin(@RequestBody String userDetails) {
	public String adminLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

//		JSONObject json = new JSONObject();
//		String username = null;
//		String password = null;

		try {
//			json = (JSONObject) (new JSONParser().parse(userDetails));
//			if (json.containsKey("username")) {
//				username = (String) json.get("username");
//			}
//			if (json.containsKey("password")) {
//				password = (String) json.get("password");
//			}
			
			if (username == deliveryManagementService.getAdminUser().getUsername() && password == deliveryManagementService.getAdminUser().getPassword()){
				return "adminPage";
			}

		} catch (Exception e) {
			LOG.error("Not able to get Details", e);
		}
		return "login";
	}
	
	@RequestMapping(path = "login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new AdminUser());
		return "login";
	}
	
	@RequestMapping("/updatePassword")
	public String updatePassword(Model model, HttpServletRequest request) {
		model.addAttribute("user", new AdminUser());
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("User");
		if (null == userName) {
		   request.setAttribute("Error", "Session has ended.  Please login.");
		   System.out.println("Session ended");
		   return "login";
		}
		
		return "updatePassword";
	}

	@RequestMapping(path = "logout", method = RequestMethod.GET)
	public String logout(Model model, SessionStatus status,HttpServletRequest request) {
		status.setComplete();
		HttpSession session = request.getSession();
		session.removeAttribute("User");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/editDetails")
	public String fetchJobDetailsByJobId(Model model) {
		
		int id = 1;
		try{
			JobDetails jobDetails = deliveryManagementService.fetchJobDetailsByJobId(id);
			model.addAttribute("jobDetails", jobDetails);
			List<Employee> employees = deliveryManagementService
					.getAllEmployees();
			List<Machine> machines = deliveryManagementService.getAllMachines();
			model.addAttribute("employees", employees);
			model.addAttribute("machines", machines);
		}catch(Exception e){
			LOG.error("Not able to get Details", e);
		}
		
		return "editJobDetails";
	}

}
