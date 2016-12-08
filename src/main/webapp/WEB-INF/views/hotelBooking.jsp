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
	<link href="${pageContext.request.contextPath}/resources/css/dataTables.css"
	rel="stylesheet">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.min.js"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</head>
<body>

	<!-- <div class="container">
		<div class="navbar-header">
			<a class="myFont-text navbar-brand" href="/DeliveryManagement"
				style="color: blue;">Delivery Management
				System</a>
		</div>
	</div> -->

	<div>
		<div class="container">
			<div class="row">
				<div></div>
				<a class="myFont-text navbar-brand" href="/DeliveryManagement"
					style="color: blue;">Job Log Book</a>
			</div>
			<div>
				<h4 style="margin: 0;">Job Detail Information By Machine</h4>
			</div>
			<span style="color: red; padding: 5px;" id="msg"></span>
			<table>
				<%-- <tr>
							<th style="padding: 12px;">Employee</th>
							<td ><select id="user_select" style="width:100%;">
									<option value="-1">--Select Employee--</option>
									<c:forEach items="${employees}" var="employee">
										<option value="${employee.id}">${employee.firstName}</option>
									</c:forEach>
							</select></td>
							<td><span id="city_err" style="color:red;margin-left: 10px;"></span></td>
						</tr> --%>
				<tr>
					<!-- <td><input id="empId_info" placeholder='Part Number' name="empId" /></td> -->
					<!-- <td><input style="margin-left: 10px;" type="submit"
							class="btn btn-xs btn-primary" id="get_emp_details"
							value="Get info"></td> -->
					<!-- <td><input id="machine_name" placeholder='Machine Name'
						name="machineName" /></td>
					<td><input style="margin-left: 10px;" type="submit"
						class="btn btn-xs btn-primary" id="getJobDetailsByMachine"
						value="Get info"></td> -->
				</tr>
				<tr>
					<td>Machine:</td>
					<td><select id="machine_get_details" style="width: 100%;">
							<option value="-1">--Select Machine--</option>
							<c:forEach items="${machines}" var="machine">
								<option value="${machine.id}">${machine.machineName}</option>
							</c:forEach>
					</select></td>
					<td><input style="margin-left: 10px;" type="submit"
						class="btn btn-xs btn-primary" id="getJobDetailsByMachine"
						value="Get Details"></td>
				</tr>
				<!-- <tr>
					<td><a href="getAllJobDetails" id="getAllJobDetails">Get
							All Job Details</a></td>
				</tr> -->
				<tr>
					<td><table style="margin-top: 10px;" id="empDetailsTable"></table></td>
				</tr>
				<!-- <tr>
						<th style="padding: 12px;">From:</th>
						<td><input type="text" class="datepicker" id="from_date" /></td>

					</tr>
					<tr>
						<th style="padding: 12px;">To:</th>
						<td><input type="text" class="datepicker" id="to_date" /></td>
					</tr> -->
			</table>
			<div id="filter_action" style='margin-left: 11%;float: right;'>
				<b>Filter by date: </b> <span><input readonly
					style='margin-right: 10px;' type='text' placeholder='From'
					class='datepicker' id='from_date' /></span> <span><input readonly
					type='text' placeholder='To' class='datepicker' id='to_date' /></span> <span><input
					style="margin-left: 10px;" type="submit"
					class="btn btn-xs btn-primary" id="filterJobDetails" value="Filter"></span>
			</div>
			<div id="empDetails"></div>
		</div>
		<span id="savingMsg"></span>

	</div>
</body>
</html>
