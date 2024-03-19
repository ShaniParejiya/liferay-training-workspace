<%@ include file="/html/department/init.jsp"%>
<head>
<title>Department Details Form</title>
</head>
<h2>Department Details Form</h2>
<portlet:actionURL var="getDepartmentDetails" name="departmentDetails" />
<form action="${getDepartmentDetails}" method="post">
	<label for="name">Department Name:</label> <input type="text" id="name"
		name="<portlet:namespace />name" required><br> <br>
	<label for="departmentHead">Select Department Head:</label> 
	<select	id="departmentHead" name="<portlet:namespace />departmentHead">
		<c:forEach var="user" items="${userNameList}">
			<option  value="${user.userId}">${user.firstName }${user.lastName}</option>
		</c:forEach>
	</select> <br> <br>
	<button type="submit" value="Register">Add Departments Details</button>
</form>