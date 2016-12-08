<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log Book</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/signin.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/theme.css"
	rel="stylesheet">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</head>
<body>

	<!-- <div class="container">
		<div class="navbar-header">
			<a class="myFont-text navbar-brand" href="/DeliveryManagement"
				style="color: blue;">Delivery Management System</a>
		</div>
	</div> -->

	<div>
		<div id="addJobDetailsDiv" class="container">
			<div class="row">
				<div></div>
				<a class="myFont-text navbar-brand" href="/DeliveryManagement"
					style="color: blue;">Job Log Book</a>
			</div>
			<div>
				<h4 style="margin: 0;">Add Job Details</h4>
			</div>
			<span style="color: red; padding: 5px;" id="msg"></span>
			<table>
				<tr>
					<td>Date</td>
					<td><input readonly
						style='margin-right: 10px;' type='text'
						class='datepicker' id='job_date' /></td>
				</tr>
				<!-- <tr>
						<td>Enter your Employee Id:</td>
						<td><input id="empId_add_job" name="empId" /></td>
						</tr> -->
				<tr>
					<td>Employee:</td>
					<td><select id="employee_select" style="width: 50%;">
							<option value="-1">--Select Employee--</option>
							<c:forEach items="${employees}" var="employee">
								<option value="${employee.id}">${employee.employeeId}
									(${employee.firstName} ${employee.lastName})</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Machine:</td>
					<td><select id="machine_select" style="width: 50%;">
							<option value="-1">--Select Machine--</option>
							<c:forEach items="${machines}" var="machine">
								<option value="${machine.id}">${machine.machineName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Part Number:</td>
					<td><input id="partNumber" name="partNumber" /></td>
				</tr>
				<tr>
					<td>Details:</td>
					<td><textarea id="details" name="details"></textarea></td>
				</tr>
				<tr>
					<td>Quantity:</td>
					<td><input id="quantity" name="quantity" /></td>
				</tr>
				<tr>
					<td>Production Order Number:</td>
					<td><input id="orderNo" name="orderNo" /></td>
				</tr>
				<tr>
					<td>Shift:</td>
					<td><input style="margin-right: 5px;" type="radio"
						name="shift" value="General Shift" CHECKED /><span
						style="padding-right: 5px;">General Shift</span> <input
						style="margin-right: 5px;" type="radio" name="shift"
						value="Second Shift" /><span style="padding-right: 5px;">Second
							Shift</span> <input style="margin-right: 5px;" type="radio" name="shift"
						value="Night Shift" /><span style="padding-right: 5px;">Night
							Shift</span> <input style="margin-right: 5px;" type="radio" name="shift"
						value="OT" /><span style="padding-right: 5px;">OT</span></td>
				</tr>
				<tr>
					<td>Total Working Hours (Specify in hrs:)</td>
					<td><input id="total_working_hours" type="text"
						name="total_working_hours" /></td>
				</tr>
				<!-- <tr>
						<td>OT out of total Working Hours (Specify in hrs:)</td>
						<td><input id="others_input" type="text" name="shift" /></td>
						</tr> -->
				<tr>
					<td>BreakDown if any(Specify in hrs:)</td>
					<td><input type="text" name="breakDown" id="breakDown" /></td>
				</tr>
				<tr>
					<td>Idle Hours(Specify in hrs:)</td>
					<td><input type="text" name="idleHours" id="idleHours" /></td>
				</tr>
				<tr>
					<td>Idle Code</td>
					<td><select id="idle_select" style="width: 50%;">
							<option value="-1">--Select Idle Code--</option>
							<c:forEach items="${idleCodes}" var="idleCode">
								<option value="${idleCode.id}">${idleCode.idleCodeDetails}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Remarks:</td>
					<td><textarea name="remarks" id="remarks"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit"
						class="btn btn-xs btn-primary pull-left" id="save_details"
						value="Save Details"></td>
					<td>
						<button type="button" class="btn btn-xs btn-success btn-block"
							style="width: 77px;"
							onclick="window.location.href='/DeliveryManagement'">Cancel</button>
					</td>
				</tr>
			</table>
		</div>
		<div
			style="color: green; margin-left: 6%; margin-bottom: 10px; margin-top: 10px;"
			id="successMsg"></div>

	</div>
</body>
</html>
