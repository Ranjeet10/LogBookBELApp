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
<title>Delivery Management System</title>
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
				style="color: blue;">Delivery Management
				System</a>
		</div>
	</div> -->

	<div>
		<div id="addEmployeeDiv" class="container">
			<div class="row">
				<div class="pull-right">
					<div>${loggedInUser.username}</div>
					<a style="text-decoration: underline;" href="updatePassword">Update
						Password</a><a style="margin-left: 5px; text-decoration: underline;"
						href="logout">Logout</a>
				</div>
				<!-- <div></div><a class="myFont-text navbar-brand" href="/DeliveryManagement"
				style="color: blue;">Job Log Book</a> -->
				<!-- <div></div><a class="myFont-text navbar-brand" href="/DeliveryManagement"
				style="color: blue;">Job Log Book</a> -->
			</div>
			<div>
				<h4 style="margin: 0;">Add an employee</h4>
			</div>
			<span style="color: red; padding: 5px;" id="msg"></span>
			<table>
				<tr>
					<td>Enter Employee Id:</td>
					<td><input id="add_emp_id" name="add_emp_id" /></td>
				</tr>
				<tr>
					<td>Enter First Name:</td>
					<td><input id="add_emp_first_name" name="add_emp_first_name" /></td>
				</tr>
				<tr>
					<td>Enter Last Name:</td>
					<td><input id="add_emp_last_name" name="add_emp_last_name" /></td>
				</tr>
				<tr>
					<td><input type="submit"
						class="btn btn-xs btn-primary pull-left" id="add_emp_details"
						value="Add Employee"></td>
					<td>
						<button type="button" class="btn btn-xs btn-success btn-block"
							style="width: 77px;"
							onclick="window.location.href='/DeliveryManagement/showAdminPage'">Cancel</button>
					</td>
				</tr>
			</table>
			<span style="color: green; padding: 5px;" id="savingMsg"></span>
		</div>
	</div>
</body>
</html>