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
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</head>
<body>

	<div>
		<div class="container">
			<button type="button" class="btn btn-xs btn-info btn-block" style="width: 77px;" onclick="window.location.href='/DeliveryManagement/showAdminPage'">Back</button>
			<div class="row">
			<div class="pull-right"><div>${loggedInUser.username}</div><a style="text-decoration:underline;" href="updatePassword">Update Password</a><a style="margin-left:5px; text-decoration:underline;" href="logout">Logout</a></div>
				<!-- <div></div><a class="myFont-text navbar-brand" href="/DeliveryManagement"
				style="color: blue;">Job Log Book</a> -->
				<!-- <a class="myFont-text navbar-brand" href="/DeliveryManagement"
					style="color: blue;">Job Log Book</a> -->
			</div>
			<div>
				<h4 style="margin: 0;">Complete Job Details Information</h4>
			</div>
			<!-- <div style='padding: 10px 0px 5px 0px;'>
				<b>Job Details</b>
			</div> -->
			<div style="margin-bottom: 10px; float: right;">
				<span>
					<input type='text' id='filterByPartNumber' />
					<button class="btn btn-xs btn-primary" id='btnFilterByPartNumber'>Filter By Part Number</button>
				</span>
				<%-- <span>
					<input type='text' id='filterByMachineName' />
					<button class="btn btn-xs btn-primary" id='btnFilterByMachineName'>Filter By Machine Name</button>
				</span> --%>
			</div>
			<table id="myTable" class="tablesorter" border='1'>
				<thead>
					<tr>
						<th>Part Number</th>
						<th>Employee</th>
						<th>Machine</th>
						<th>Details</th>
						<th>Quantity</th>
						<th>Order Number</th>
						<th>Shift</th>
						<th>Working Hours</th>
						<th>BreakDown</th>
						<th>Overtime(OT)</th>
						<th>Remarks</th>
						<th>Date</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jobDetails}" var="jobDetail">
						<c:set var="totalWorkingHours" scope="session" value="0" />
						<c:set var="overtime" scope="session" value="0" />
						<c:choose>
							<c:when test="${jobDetail.workingShift=='OT'}">
								<c:set var="totalWorkingHours" scope="session" value="0" />
								<c:set var="overtime" scope="session"
									value="${jobDetail.workingHours}" />
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${jobDetail.workingHours gt 7.5}">
										<c:set var="overtime" scope="session"
											value="${jobDetail.workingHours - 7.5}" />
										<c:set var="totalWorkingHours" scope="session"
											value="${jobDetail.workingHours - jobDetail.breakdownHours - overtime}" />
									</c:when>
									<c:otherwise>
										<c:set var="overtime" scope="session" value="0" />
										<c:set var="totalWorkingHours" scope="session"
											value="${jobDetail.workingHours - jobDetail.breakdownHours}" />
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<tr>
							<td class="partNumber" style='text-align: center; padding: 5px;'>${jobDetail.partNumber}</td>
							<td class="lastName" style='text-align: center; padding: 5px;'>
								${jobDetail.employee.firstName} ${jobDetail.employee.lastName}</td>
							<td class="machineName" style='text-align: center; padding: 5px;'>${jobDetail.machine.machineName}</td>
							<td class="details" style='text-align: center; padding: 5px;'>${jobDetail.details}</td>
							<td class="qunatity" style='text-align: center; padding: 5px;'>${jobDetail.qunatity}
							</td>
							<td class="productionOrderNumber"
								style='text-align: center; padding: 5px;'>${jobDetail.productionOrderNumber}</td>
							<td class="productionOrderNumber"
								style='text-align: center; padding: 5px;'>${jobDetail.workingShift}</td>
							<td class="workingHours"
								style='text-align: center; padding: 5px;'>${totalWorkingHours}</td>
							<td class="breakdownHours"
								style='text-align: center; padding: 5px;'>${jobDetail.breakdownHours}</td>
							<td class="breakdownHours"
								style='text-align: center; padding: 5px;'>${overtime}</td>
							<td class="remarks" style='text-align: center; padding: 5px;'>${jobDetail.remarks}</td>
							<td class="savedDate" style='text-align: center; padding: 5px;'>${jobDetail.savedDate}</td>
							<td class='edit' style='text-align: center; padding: 5px;'><button class="edit_job_details btn btn-xs btn-info" id='${jobDetail.jobId}'>Edit</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div style="font-weight: bold;color: #599933;padding: 10px;" id="details"></div>
		</div>
	</div>
</body>
</html>
