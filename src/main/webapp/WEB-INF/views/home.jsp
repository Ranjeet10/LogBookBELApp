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
		<div class="container">
			<div class="row">
				<div></div><a class="myFont-text navbar-brand" href="/DeliveryManagement"
				style="color: blue;">Delivery Management System</a></div>
				<div><h3 style="margin-top: 0px;">Job Detail Information</h3></div>
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
						<td></td>
						<td><input style="margin-top: 10px;" type="submit"
							class="btn btn-xs btn-primary pull-right" id="add_emp_details"
							value="Add Employee"></td>
					</tr>
				</table>
				<span id="msg"></span>
			</div>
		</div>
</body>
</html>
