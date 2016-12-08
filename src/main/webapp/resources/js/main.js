$(document)
		.ready(
				function() {

					$(function() {

						$('#filter_action').hide();
						$('input[name=shift]').click(function() {
							if ($(this).is(':checked') && $(this).val() == "") {
								$("#others").addClass("hide");
								$("#others_input").removeClass("hide");
							}
						});
						var d = new Date();
						var curr_date = d.getDate();
						var curr_month = d.getMonth() + 1; // Months are zero
						// based
						var curr_year = d.getFullYear();
						var today_date = (curr_year + "-" + curr_month + "-" + curr_date);

						$("#today_date").html(today_date);

						$(".datepicker").datepicker({});
						$("#format").change(
								function() {
									$("#datepicker").datepicker("option",
											"dateFormat", $(this).val());
								});

						$('input[type=radio][name=shift]').change(
								function() {
									if (this.value == "OT") {
										$("#breakDown").parent().parent()
												.addClass("hide");
									} else {
										$("#breakDown").parent().parent()
												.removeClass("hide");
									}
								});
						$("#myTable").DataTable();
					});

					$(document).on("click", "#get_emp_details", function(e) {

						var employee = {};
						var empId = $('#empId_info').val();
						employee["empId"] = empId;

						if (true) {
							getJobDetailsAsPerEmployee();

							/*
							 * $ .ajax({ type : "POST", url :
							 * "getEmployeeDetails", data : JSON
							 * .stringify(employee), contentType :
							 * "application/json; charset=utf-8", dataType :
							 * "json", success : function( response, textStatus,
							 * jqXHR) { if (response != null) { console
							 * .log(response); $("#empDetails") .html( ""); var
							 * data = "<b>Hello, </b>"; data = data + "<i>" +
							 * response.firstName + " " + response.lastName + "</i>";
							 * $("#empDetails") .append( data);
							 * 
							 * getJobDetailsAsPerEmployee(response.id); } },
							 * error : function(jqXHR, textStatus, erroThrown) {
							 * alert("Snot able to fetch employee details"); }
							 * });
							 */
						}
					});

					function getJobDetailsAsPerEmployee() {

						$("#empDetails").html("");
						var empId = $('#empId_info').val();
						var employee = {};
						var isValid = true;
						$("#msg").html("");
						$("#savingMsg").html("");

						if (empId == "") {
							$("#msg").html("Please provide Employee Id");
							isValid = false;
							return;
						} else if (isNaN(empId)) {
							$("#msg").html("Employee Id should be a number");
							isValid = false;
							return;
						} else if (parseInt(empId) < 0) {
							$("#msg").html("Employee Id cannot be negative");
							isValid = false;
							return;
						}
						if (isValid) {
							employee["partNumber"] = empId;
							$
									.ajax({
										type : "POST",
										url : "getJobDetailsByPartNumber",
										data : JSON.stringify(employee),
										contentType : "application/json; charset=utf-8",
										dataType : "json",
										success : function(response,
												textStatus, jqXHR) {
											if (response != null) {
												console.log(response);
												$('#filter_action').show();
												$("#msg").html("");
												var data = "<div style='padding: 10px 0px 5px 0px;'><b>Job Details</b></div>";
												data = data
														+ "<table class='tablesorter' id='myTable' border='1'><thead><tr><th>Part Number</th><th>Employee</th><th>Machine</th><th>Details</th><th>Quantity</th><th>Order Number</th><th>Shift</th><th>Working Hours</th><th>BreakDown</th><th>Overtime(OT)</th><th>Remarks</th><th>Edit</th></tr></thead><tbody>";
												$
														.each(
																response,
																function(key,
																		value) {

																	var overtime;
																	var totalWorkingHours;

																	if (value.workingShift == "OT") {
																		totalWorkingHours = 0;
																		overtime = value.workingHours;
																	} else {

																		if (value.workingHours > 7.5) {

																			overtime = parseFloat(value.workingHours) - 7.5;
																			totalWorkingHours = parseFloat(value.workingHours)
																					- parseFloat(value.breakdownHours)
																					- overtime;
																		} else {
																			overtime = 0;
																			totalWorkingHours = value.workingHours
																					- value.breakdownHours;
																		}

																	}
																	data = data
																			+ "<tr><td style='text-align: center;padding: 5px;'>"
																			+ value.partNumber
																			+ "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.plannedQuantity
																			// +
																			// "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.launchedQuantity
																			// +
																			// "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.deliveredQuantity
																			// +
																			// "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.employee.firstName
																			+ " "
																			+ value.employee.lastName
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.machine.machineName
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.details
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.qunatity
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.productionOrderNumber
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.workingShift
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ totalWorkingHours
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.breakdownHours
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ overtime
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.remarks
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.savedDate
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ "<button id='job_"
																			+ value.jobId
																			+ "' class='btn btn-info btn-xs edit_job_details' />"
																			+ "</td>"
																			+ "</tr>";
																});
												data = data
														+ "</tbody></table>";
												$("#empDetails").append(data);
												$('#myTable').DataTable();
											}
										},
										error : function(jqXHR, textStatus,
												erroThrown) {
											alert("Not able to fetch job details");
										}
									});
						}

					}

					$(document)
							.on(
									"click",
									"#add_emp_details",
									function(e) {
										var empId = $('#add_emp_id').val();
										var firstName = $('#add_emp_first_name')
												.val();
										var lastName = $('#add_emp_last_name')
												.val();
										var employeeDetails = {};
										var isValid = true;
										$("#msg").html("");
										$("#savingMsg").html("");

										if (empId == "") {
											$("#msg")
													.html(
															"Please provide employee Id");
											isValid = false;
											return;
										} else if (isNaN(empId)) {
											$("#msg")
													.html(
															"Employee Id should be a number");
											isValid = false;
											return;
										} else if (parseInt(empId) < 0) {
											$("#msg")
													.html(
															"Employee Id cannot be negative");
											isValid = false;
											return;
										}
										if (firstName == "") {
											$("#msg")
													.html(
															"Please provide First Name");
											isValid = false;
											return;
										} else if (!isNaN(firstName)) {
											$("#msg")
													.html(
															"First Name should be a string");
											isValid = false;
											return;
										}
										if (lastName == "") {
											$("#msg").html(
													"Please provide Last Name");
											isValid = false;
											return;
										} else if (!isNaN(lastName)) {
											$("#msg")
													.html(
															"Last Name should be a string");
											isValid = false;
											return;
										}

										if (isValid) {
											employeeDetails["empId"] = empId;
											employeeDetails["firstName"] = firstName;
											employeeDetails["lastName"] = lastName;

											$
													.ajax({
														type : "POST",
														url : "saveEmployeeDetails",
														data : JSON
																.stringify(employeeDetails),
														contentType : "application/json; charset=utf-8",
														success : function(
																data,
																textStatus,
																jqXHR) {
															if (data == null
																	|| data == "") {
																var savingMsg = "Error saving employee details";
																$("#savingMsg")
																		.html(
																				savingMsg);
															} else {
																var savingMsg = "Employee Details saved successfully ";
																$("#msg").html(
																		"");
																$("#savingMsg")
																		.html(
																				savingMsg);
															}

														},
														error : function(jqXHR,
																textStatus,
																erroThrown) {
															alert("Something went wrong, not able to save employee");
														}
													});
										}
									});

					$(document)
							.on(
									"click",
									"#save_details",
									function(e) {
										// var empId = $("#loggedInUser").val();
										// var empId =
										// $('#empId_add_job').val();
										$("#msg").html("");
										$("#savingMsg").html("");
										var partNumber = $('#partNumber').val();
										/*
										 * var plannedQauntity = $(
										 * '#plannedQauntity').val(); var
										 * launchedQuantity = $(
										 * '#launchedQuantity').val(); var
										 * deliveredQuantity = $(
										 * '#deliveredQuantity').val();
										 */
										var details = $('#details').val();
										var quantity = $('#quantity').val();
										var orderNo = $('#orderNo').val();
										var workingShift = $(
												"input[name='shift']:checked")
												.val();
										var workingHours = $(
												"#total_working_hours").val();
										if (workingHours == "") {
											workingHours = $("#others_input")
													.val();
										}
										var breakDown = $('#breakDown').val();
										var idleHours = $('#idleHours').val();
										var remarks = $('#remarks').val();
										var empId = $(
												"#employee_select option:selected")
												.val();
										var idleCodeId = $(
												"#idle_select option:selected")
												.val();
										var machineId = $(
												"#machine_select option:selected")
												.val();
										// var savedDate =
										// $("#today_date").html();
										var savedDate = $("#job_date").val();
										// var partNumber = "213141431";
										var jobDetails = {};

										var isValid = true;

										if (savedDate == "") {
											$("#msg").html(
													"Please provie job date");
											isValid = false;
											return;
										}

										if (empId == -1) {
											$("#msg")
													.html(
															"Please select an employee");
											isValid = false;
											return;
										}
										if (machineId == -1) {
											$("#msg").html(
													"Please select a machine");
											isValid = false;
											return;
										}

										// if (checkInDate == "") {
										// $("#checkIn_Date_err").html("Value
										// provided for check in date is
										// incomplete.");
										// $("#checkIn_Date_err").css("display","block");
										// isValid = false;
										// }

										//
										if (partNumber == "") {
											$("#msg")
													.html(
															"Please provide part Number");
											isValid = false;
											return;
										} else if (isNaN(partNumber)) {
											$("#msg")
													.html(
															"Part Number should be a number");
											isValid = false;
											return;
										} else if (parseInt(partNumber) < 0) {
											$("#msg")
													.html(
															"Part Number cannot be negative");
											isValid = false;
											return;
										}

										if (details == "") {
											details = "";
										}

										if (quantity == "") {
											$("#msg").html(
													"Please provide quantity");
											isValid = false;
											return;
										} else if (isNaN(quantity)) {
											$("#msg")
													.html(
															"Quantity should be a number");
											isValid = false;
											return;
										} else if (parseInt(quantity) < 0) {
											$("#msg")
													.html(
															"Quantity cannot be negative");
											isValid = false;
											return;
										}

										if (workingHours == "") {
											$("#msg")
													.html(
															"Please provide Working hours");
											isValid = false;
											return;
										} else if (isNaN(workingHours)) {
											$("#msg")
													.html(
															"Working hours should be a number");
											isValid = false;
											return;
										} else if (parseFloat(workingHours) < 0) {
											$("#msg")
													.html(
															"Working hours should be greater than 0");
											isValid = false;
											return;
										}

										if (orderNo == "") {
											$("#msg").html(
													"Please provide order No.");
											isValid = false;
											return;
										} else if (isNaN(orderNo)) {
											$("#msg")
													.html(
															"Order No. should be a number");
											isValid = false;
											return;
										} else if (parseInt(orderNo) < 0) {
											$("#msg")
													.html(
															"Order number cannot be negative");
											isValid = false;
											return;
										}

										if (breakDown == "") {
											breakDown = "0";
										}
										if (isNaN(breakDown)) {
											$("#msg")
													.html(
															"Break down hours should be a number");
											isValid = false;
											return;
										} else if (parseFloat(breakDown) < 0) {
											$("#msg")
													.html(
															"Break down hours should be greater than 0");
											isValid = false;
											return;
										} else if (parseFloat(breakDown) > 7.5) {
											$("#msg")
													.html(
															"Break down cannot be greater than 7.5 hrs");
											isValid = false;
											return;
										}

										if (isNaN(idleHours)) {
											$("#msg")
													.html(
															"Idle hours should be a number");
											isValid = false;
											return;
										} else if (parseFloat(idleHours) < 0) {
											$("#msg")
													.html(
															"Idle hours should be greater than 0");
											isValid = false;
											return;
										} else if (parseFloat(idleHours) > 7.5) {
											$("#msg")
													.html(
															"Idle hours cannot be greater than 7.5 hrs");
											isValid = false;
											return;
										}

										if (remarks == "") {
											remarks = "";
										}

										if (parseFloat(breakDown) > parseFloat(workingHours)) {
											$("#msg")
													.html(
															"Break down cannot be greater than working hours");
											isValid = false;
											return;
										}

										if (isValid) {
											/*
											 * if
											 * ($('input[name=shift]:checked')
											 * .val() == "") {
											 * $("#others").addClass("hide");
											 * $("#others_input").removeClass(
											 * "hide"); }
											 */

											jobDetails["empId"] = empId;
											jobDetails["partNumber"] = partNumber;
											jobDetails["savedDate"] = savedDate;
											/*
											 * jobDetails["plannedQauntity"] =
											 * plannedQauntity;
											 * jobDetails["launchedQuantity"] =
											 * launchedQuantity;
											 * jobDetails["deliveredQuantity"] =
											 * deliveredQuantity;
											 */
											jobDetails["details"] = details;
											jobDetails["quantity"] = quantity;
											jobDetails["orderNo"] = orderNo;
											jobDetails["workingHours"] = workingHours;
											jobDetails["workingShift"] = workingShift;
											jobDetails["breakDown"] = breakDown;
											jobDetails["idleHours"] = idleHours;
											jobDetails["idleCodeId"] = idleCodeId;
											jobDetails["remarks"] = remarks;
											jobDetails["machineId"] = machineId;
											// jobDetails["cityName"] =
											// cityName;
											// jobDetails["hotelId"] =
											// hotelId;
											// jobDetails["checkInDate"] =
											// checkInDate;
											// bookingDetails["checkOutDate"]
											// =
											// checkOutDate;
											// bookingDetails["numberOfRooms"]
											// =
											// numberOfRooms;

											$
													.ajax({
														type : "POST",
														url : "saveJobDetails",
														data : JSON
																.stringify(jobDetails),
														contentType : "application/json; charset=utf-8",
														success : function(
																data,
																textStatus,
																jqXHR) {
															var bookingMsg = "Details saved successfully ";
															bookingMsg = bookingMsg
																	+ "<div style='margin-top:10px;'><a style='margin-left 10px;' class='btn btn-primary btn-xs' href='addJobDetails'>Add More</a></div>";
															$("#msg").html("");
															$("#successMsg")
																	.html(
																			bookingMsg);
															$('#partNumber')
																	.val("");
															$('#details').val(
																	"");
															$('#quantity').val(
																	"");
															$('#orderNo').val(
																	"");
															$(
																	"input[name='shift']")
																	.val(
																			"General Shift");
															$(
																	"#total_working_hours")
																	.val("");
															$('#breakDown')
																	.val("");
															$('#remarks').val(
																	"");
															$(
																	"#employee_select")
																	.val("-1");
															$("#machine_select")
																	.val("-1");
															// $("#today_date")
															// .html();
														},
														error : function(jqXHR,
																textStatus,
																erroThrown) {
															alert("Something went wrong, not able to save details");
														}
													});
										}
									});

					$(document).on("click", "#filterJobDetails", function(e) {
						getFilteredJobDetailsByDate();
					});

					function getFilteredJobDetailsByDate() {

						$("#empDetails").html("");
						var isValid = true;
						var filteringDetails = {};
						// var empId = $('#empId_info').val();
						var machineId = $(
								"#machine_get_details option:selected").val();

						var fromDate = $('#from_date').val();
						var toDate = $('#to_date').val();
						$("#msg").html("");
						$("#savingMsg").html("");

						if (machineId == -1) {
							$("#msg").html("Please select a machine");
							isValid = false;
							return;
						}

						/*
						 * if (empId == "") { $("#msg").html("Please provide
						 * Employee Id"); isValid = false; return; } else if
						 * (isNaN(empId)) { $("#msg").html("Employee Id should
						 * be a number"); isValid = false; return; } else if
						 * (parseInt(empId) < 0) { $("#msg").html("Employee Id
						 * cannot be negative"); isValid = false; return; }
						 */

						if (fromDate == "") {
							$("#msg").html("Please provide from date");
							isValid = false;
							return;
						}
						if (toDate == "") {
							$("#msg").html("Please provide To date");
							isValid = false;
							return;
						}

						if (isValid) {

							// filteringDetails["id"] = String(empId);
							filteringDetails["machineId"] = String(machineId);
							filteringDetails["fromDate"] = String(fromDate);
							filteringDetails["toDate"] = String(toDate);

							$
									.ajax({
										type : "POST",
										// url : "getFilteredJobDetails",
										url : "getFilteredJobDetailsByMachine",
										data : JSON.stringify(filteringDetails),
										contentType : "application/json; charset=utf-8",
										dataType : "json",
										success : function(response,
												textStatus, jqXHR) {
											if (response != null) {
												console.log(response);
												$('#filter_action').show();
												$("#empDetails").html("");
												var data = "<div style='padding: 10px 0px 5px 0px;'><b>Job Details</b></div>";
												data = data
														+ "<table class='tablesorter' id='myTable' border='1'><thead><tr><th>Part Number</th><th>Employee</th><th>Machine</th><th>Details</th><th>Quantity</th><th>Order Number</th><th>Shift</th><th>Working Hours</th><th>BreakDown</th><th>Overtime(OT)</th><th>Remarks</th><th>Date</th></tr></thead><tbody>";
												$
														.each(
																response,
																function(key,
																		value) {

																	var overtime;
																	var totalWorkingHours;

																	if (value.workingShift == "OT") {
																		totalWorkingHours = 0;
																		overtime = value.workingHours;
																	} else {

																		if (value.workingHours > 7.5) {

																			overtime = parseFloat(value.workingHours) - 7.5;
																			totalWorkingHours = parseFloat(value.workingHours)
																					- parseFloat(value.breakdownHours)
																					- overtime;
																		} else {
																			overtime = 0;
																			totalWorkingHours = value.workingHours
																					- value.breakdownHours;
																		}

																	}

																	data = data
																			+ "<tr><td style='text-align: center;padding: 5px;'>"
																			+ value.partNumber
																			+ "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.plannedQuantity
																			// +
																			// "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.launchedQuantity
																			// +
																			// "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.deliveredQuantity
																			// +
																			// "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.employee.firstName
																			+ " "
																			+ value.employee.lastName
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.machine.machineName
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.details
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.qunatity
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.productionOrderNumber
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.workingShift
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ totalWorkingHours
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.breakdownHours
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ overtime
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.remarks
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.savedDate
																			+ "</td>"
																			+ "</tr>";
																});
												data = data
														+ "</tbody></table>";
												$("#empDetails").append(data);
												$("#myTable").DataTable();
											}
										},
										error : function(jqXHR, textStatus,
												erroThrown) {
											alert("Not able to fetch job details");
										}
									});
						}

					}

					$(document)
							.on(
									"click",
									"#add_machine_details",
									function(e) {
										/*
										 * var machineId = $('#add_machine_id')
										 * .val();
										 */
										var machineName = $('#add_machine_name')
												.val();
										var machineDetails = {};
										var isValid = true;
										$("#msg").html("");
										$("#savingMsg").html("");

										if (machineName == "") {
											$("#msg")
													.html(
															"Please provide Machine Name");
											isValid = false;
											return;
										} else if (!isNaN(machineName)) {
											$("#msg")
													.html(
															"Machine Name should be a string");
											isValid = false;
											return;
										}
										if (isValid) {
											// machineDetails["machineId"] =
											// machineId;
											machineDetails["machineName"] = machineName;

											$
													.ajax({
														type : "POST",
														url : "saveMachineDetails",
														data : JSON
																.stringify(machineDetails),
														contentType : "application/json; charset=utf-8",
														success : function(
																data,
																textStatus,
																jqXHR) {
															if (data == null
																	|| data == "") {
																var savingMsg = "Error saving mahine details";
																$("#savingMsg")
																		.html(
																				savingMsg);
															} else {
																var savingMsg = "Machine details saved successfully";
																$("#savingMsg")
																		.html(
																				savingMsg);
															}

														},
														error : function(jqXHR,
																textStatus,
																erroThrown) {
															alert("Something went wrong, not able to book rooms");
														}
													});
										}
									});

					$(document)
							.on(
									"click",
									"#add_idle_code_details",
									function(e) {
										var idleCodeDetail = $('#add_idle_code')
												.val();
										var idleCodeDetails = {};
										var isValid = true;
										$("#msg").html("");
										$("#savingMsg").html("");

										if (idleCodeDetail == "") {
											$("#msg").html(
													"Please provide Idle Code");
											isValid = false;
											return;
										} else if (!isNaN(idleCodeDetail)) {
											$("#msg")
													.html(
															"Idle Code should be a string");
											isValid = false;
											return;
										}
										if (isValid) {
											idleCodeDetails["idleCodeDetail"] = idleCodeDetail;

											$
													.ajax({
														type : "POST",
														url : "saveIdleCode",
														data : JSON
																.stringify(idleCodeDetails),
														contentType : "application/json; charset=utf-8",
														success : function(
																data,
																textStatus,
																jqXHR) {
															if (data == null
																	|| data == "") {
																var savingMsg = "Error saving idle code";
																$("#savingMsg")
																		.html(
																				savingMsg);
															} else {
																var savingMsg = "Idle code saved successfully";
																$("#savingMsg")
																		.html(
																				savingMsg);
															}

														},
														error : function(jqXHR,
																textStatus,
																erroThrown) {
															alert("Something went wrong, not able to book rooms");
														}
													});
										}
									});

					$(document).on("click", "#getJobDetailsByMachine",
							function(e) {
								getAllJobDetailsByMachine();
							});

					function getAllJobDetailsByMachine() {

						$("#empDetails").html("");
						// var machineName = $('#machine_name').val();
						var machineId = $(
								"#machine_get_details option:selected").val();
						var machine = {};
						var isValid = true;
						$("#msg").html("");
						$("#savingMsg").html("");
						if (machineId == -1) {
							$("#msg").html("Please select a machine");
							isValid = false;
							return;
						}

						// if (machineName == "") {
						// $("#msg").html("Please provide Machine Name");
						// isValid = false;
						// return;
						// } else if (!isNaN(machineName)) {
						// $("#msg").html("Machine Name cannot be a number");
						// isValid = false;
						// return;
						// }
						if (isValid) {
							machine["machineId"] = machineId;
							$
									.ajax({
										type : "POST",
										url : "getJobDetailsByMachineId",
										data : JSON.stringify(machine),
										contentType : "application/json; charset=utf-8",
										dataType : "json",
										success : function(response,
												textStatus, jqXHR) {
											if (response != null) {
												console.log(response);
												$('#filter_action').show();
												$("#msg").html("");
												var data = "<div style='padding: 10px 0px 5px 0px;'><b>Job Details</b></div>";
												data = data
														+ "<table class='tablesorter' id='myTable' border='1'><thead><tr><th>Part Number</th><th>Employee</th><th>Machine</th><th>Details</th><th>Quantity</th><th>Order Number</th><th>Shift</th><th>Working Hours</th><th>BreakDown</th><th>Overtime(OT)</th><th>Remarks</th><th>Date</th></tr></thead><tbody>";
												$
														.each(
																response,
																function(key,
																		value) {

																	var overtime;
																	var totalWorkingHours;

																	if (value.workingShift == "OT") {
																		totalWorkingHours = 0;
																		overtime = value.workingHours;
																	} else {

																		if (value.workingHours > 7.5) {

																			overtime = parseFloat(value.workingHours) - 7.5;
																			totalWorkingHours = parseFloat(value.workingHours)
																					- parseFloat(value.breakdownHours)
																					- overtime;
																		} else {
																			overtime = 0;
																			totalWorkingHours = value.workingHours
																					- value.breakdownHours;
																		}

																	}

																	data = data
																			+ "<tr><td style='text-align: center;padding: 5px;'>"
																			+ value.partNumber
																			+ "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.plannedQuantity
																			// +
																			// "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.launchedQuantity
																			// +
																			// "</td>"
																			// +
																			// "<td
																			// style='text-align:
																			// center;padding:
																			// 5px;'>"
																			// +
																			// value.deliveredQuantity
																			// +
																			// "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.employee.firstName
																			+ " "
																			+ value.employee.lastName
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.machine.machineName
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.details
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.qunatity
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.productionOrderNumber
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.workingShift
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ totalWorkingHours
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.breakdownHours
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ overtime
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.remarks
																			+ "</td>"
																			+ "<td style='text-align: center;padding: 5px;'>"
																			+ value.savedDate
																			+ "</td>"
																			+ "</tr>";
																});
												data = data
														+ "</tbody></table>";
												$("#empDetails").append(data);
												$('#myTable').DataTable();
											}
										},
										error : function(jqXHR, textStatus,
												erroThrown) {
											alert("Not able to fetch job details");
										}
									});
						}

					}

					$('#btnFilterByPartNumber')
							.click(
									function() {
										var totalQuantity = 0;
										var totalWorkingHours = 0;
										$("#details").html("");
										var partNumber = $(
												'#filterByPartNumber').val();
										$('tr').show();
										$('tr td.partNumber')
												.each(
														function() {
															if ($(this).text() != partNumber) {
																$(this)
																		.parent()
																		.hide();
															}
															if ($(this).text() == partNumber) {

																// var x =
																// parseInt(empId)
																var x = $(this)
																		.parent()
																		.find(
																				".qunatity")
																		.text();
																totalQuantity = totalQuantity
																		+ parseInt(x);

																var y = $(this)
																		.parent()
																		.find(
																				".workingHours")
																		.text();

																totalWorkingHours = totalWorkingHours
																		+ parseFloat(y);

															}

														});
										$("#details")
												.append(
														"<div>Total quantity: "
																+ totalQuantity
																+ "</div><div> Total Working Hours: "
																+ totalWorkingHours
																+ " hrs </div>");
									});

					$('#btnFilterByMachineName').click(function() {
						var machineName = $('#filterByMachineName').val();
						$('tr').show();
						$('tr td.machineName').each(function() {
							if ($(this).text() != machineName) {
								$(this).parent().hide();
							}
						});

					});

					// $('#updatePassword').click(function() {
					// $("#updatePasswordDiv").removeClass("hide");
					// $("#showLoginDiv").addClass("hide");
					// $("#updatePassword").addClass("hide");
					// $("#showLoginLink").removeClass("hide");
					//
					// });
					//
					// $('#showLoginLink').click(function() {
					// $("#updatePasswordDiv").addClass("hide");
					// $("#showLoginDiv").removeClass("hide");
					// $("#updatePassword").removeClass("hide");
					// $("#showLoginLink").addClass("hide");
					// });

					/*
					 * $('#loginBtn') .click( function() {
					 * 
					 * var isValid = true; var userDetails = {}; var username =
					 * $('#username').val(); var password =
					 * $('#password').val();
					 * 
					 * if (isValid) {
					 * 
					 * userDetails["username"] = String(username);
					 * userDetails["password"] = String(password); $ .ajax({
					 * type : "POST", // url : // "getFilteredJobDetails", url :
					 * "adminLogin", data : JSON .stringify(userDetails),
					 * contentType : "application/json; charset=utf-8", dataType :
					 * "json", success : function( response, textStatus, jqXHR) {
					 * if (response != null) { } }, error : function(jqXHR,
					 * textStatus, erroThrown) { alert("Not able to login"); }
					 * }); }
					 * 
					 * });
					 */

					// $('#changePasswordButton').click(function() {
					// $("#updatePasswordDiv").addClass("hide");
					// $("#showLoginDiv").removeClass("hide");
					// $("#updatePassword").removeClass("hide");
					// $("#showLoginLink").addClass("hide");
					// });
					$("#updatePassword").click(function() {
						fetchAdminDetails();
					});

					function fetchAdminDetails() {

						$.ajax({
							type : "GET",
							url : "getAdminUserDetails",
							contentType : "application/json; charset=utf-8",
							success : function(response, textStatus, jqXHR) {
								console.log(response);
								if (response != null) {
									console.log(response);

									// $.each(response, function(key, value) {
									//
									// });
								}
							},
							error : function(jqXHR, textStatus, erroThrown) {
								alert("Not able to fetch job details");
							}
						});

					}

					$(".edit_job_details").click(function() {
						window.location.href = "editDetails";
					});

				});
