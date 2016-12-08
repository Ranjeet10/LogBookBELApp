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
	<div>
	<button type="button" class="btn btn-xs btn-info btn-block" style="width: 77px;margin-left: 35%;margin-bottom: 10px;" onclick="window.location.href='/DeliveryManagement/showAdminPage'">Back</button>
	</div>
	<div id="loginDiv">
		<div id="updatePassword">
		
		<h3  class="myFont-text form-signin-heading">Update Your Password </h3>
			<div style="margin-bottom: 5px;">
				<label>Old Password</label>
				<input id="old_password" type="text" class="form-control"
					placeholder="Password" />
			</div>
			<div style="margin-bottom: 5px;">
				<label>New Password</label>
				<input id="new_password" type="text" class="form-control"
					placeholder="New Password" />
			</div>
			<div>
				<label>Confirm Password</label>
				<input id="confirm_password" type="text" class="form-control"
					placeholder="Confirm Password" />
			</div>
			<button style="margin-top: 10px;" id="updatePassword" class="btn btn-xs btn-primary " style="padding-top: 10px;" type="submit">Update Password</button>
	</div>
	
	</div>
		
	<!-- <a style="text-decoration:underline;float:left;margin-left: -5%;" href="/DeliveryManagement">Go back</a> -->
</body>
</html>