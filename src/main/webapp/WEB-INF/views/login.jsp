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
	<div id="loginDiv">
		<form:form method="post" action="user/login" modelAttribute="user">
			<div id="showLoginDiv">

				<h3 class="myFont-text form-signin-heading">Login as Admin to
					manage the details</h3>
				<h5 style="color: red">${param.loginMsg}</h5>
				<div style="margin-left: -11px;">
					<form:label path="username" class="sr-only">Username</form:label>
					<form:input  type="text" path="username" class="form-control"
						placeholder="Username" style="margin-bottom: 10px;" required="true" />
					<form:label path="password" class="sr-only">Password</form:label>
					<form:password showPassword="true" path="password"
						class="form-control" placeholder="Password" required="true" />
				</div>
				<button id="signIn" class="btn btn-xs btn-primary "
					style="margin-top: 10px;" type="submit">Login</button>
			</div>
		</form:form>

	</div>



	<!-- Login as Admin to manage the details <br> <br>
			<div style="margin-bottom: 5px;">
				Username <input id="username" type="text" name="username">
			</div>
			<div>
				Password <input id="password" type="password" name="password">
			</div>
			<input type="submit" value="Login" id="loginBtn"
				class="btn btn-xs btn-primary" style="margin-top: 10px;">
				<button id="signIn" class="btn btn-xs btn-primary " style="padding-top: 10px;" type="submit">Login
	</button> -->
	<!-- <a href="adminLogin" class="btn btn-xs btn-primary" style="margin-top: 10px;">Login</a> -->

	<div id="updatePasswordDiv" class="hide">
		Update Your Password <br> <br>
		<div style="margin-bottom: 5px;">
			Old Password <input id="old_password" style="margin-left: 25px;"
				type="password" name="oldPassword">
		</div>
		<div style="margin-bottom: 5px;">
			New Password <input id="new_password" style="margin-left: 20px;"
				type="password" name="oldPassword">
		</div>
		<div>
			Confirm Password <input id="confirm_password" type="password"
				name="newPassword">
		</div>
		<input type="submit" value="Update Password" id="changePasswordButton"
			class="btn btn-xs btn-primary" style="margin-top: 10px;">
	</div>
</body>


</html>