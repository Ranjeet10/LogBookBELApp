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

	<div>
		<div id="addMachineDiv" class="container">
			<div class="row">
				<div class="pull-right"><div>${loggedInUser.username}</div><a style="text-decoration:underline;" href="updatePassword">Update Password</a><a style="margin-left:5px; text-decoration:underline;" href="logout">Logout</a></div>
			</div>
				<div><h4 style="margin: 0;">Add Idle Code</h4></div>
				<span style="color:red;padding:5px;" id="msg"></span>
				<table>
					<!-- <tr>
						<td>Enter Machine Id:</td>
						<td><input id="add_machine_id" name="add_machine_id" /></td>
					</tr> -->
					<tr>
						<td>Enter Idle Code:</td>
						<td><input id="add_idle_code" name="add_idle_code" /></td>
					</tr>
					<tr>
						<td><input type="submit"
							class="btn btn-xs btn-primary pull-left" id="add_idle_code_details"
							value="Add Idle Code"></td>
							<td>
							<button type="button" class="btn btn-xs btn-success btn-block" style="width: 77px;" onclick="window.location.href='/DeliveryManagement/showAdminPage'">Cancel</button></td>
					</tr>
				</table>
				<span style="color:green;padding:5px;" id="savingMsg"></span>
			</div>
		</div>
</body>
</html>
