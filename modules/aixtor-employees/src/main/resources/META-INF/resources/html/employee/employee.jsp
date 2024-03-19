<%@ include file="/html/employee/init.jsp"%>
<!-- <portlet:actionURL var="getEmployeeDetails" name="getEmployeeDetails" /> -->

<portlet:actionURL name="getEmployeeDetails" var="getEmployeeDetails">
	<portlet:param name="actionParam"
		value="InsertEmployeeRegistrationForm" />
</portlet:actionURL>

<portlet:actionURL name="updateEmployeeRecords"
	var="updateEmployeeRecords" />


<p>
<h2>Employee Details Form</h2>
<h3>${employeeListById}</h3>
<h4>${departmentId}</h4>
<h4>${designationId}</h4>
<h4>${branchId}</h4>

<c:if test="${not empty employeeListById}">
	<form action="${updateEmployeeRecords}" method="post">
</c:if>
<c:if test="${empty employeeListById }">
	<form action="${getEmployeeDetails}" method="post">
</c:if>

<input type="hidden" id="fname" name="<portlet:namespace />employeeId"
	value="${employeeListById.employeeId}" required>
	
	
<label for="firstName">First Name:</label>
<input type="text" id="fname" name="<portlet:namespace />firstName"
	value="${employeeListById.firstName}" required>
<br>
<br>
<label for="lastName">Last Name:</label>
<input type="text" id="lname" name="<portlet:namespace />lastName"
	value="${employeeListById.lastName}" required>
<br>
<br>
<label for="email">Email:</label>
<input type="email" id="email" name="<portlet:namespace />email"
	value="${employeeListById.email}" required>
<br>
<br>
<label for="mobileNumber">Mobile Number:</label>
<input type="number" id="number"
	name="<portlet:namespace />mobileNumber"
	value="${employeeListById.mobileNumber}" required>
<br>
<br>
<label for="departmentId">Department Id:</label>
<select id="departmentHead" name="<portlet:namespace />departmentHead">
	<option value="${departmentId}">${employeeListById.departmentName}</option>
	<c:forEach var="department" items="${departments}">
		<option value="${department.departmentId}">${department.departmentName}</option>
	</c:forEach>
</select>
<br>
<br>
<label for="branchId">Branch Id:</label>
<select id="branch" name="<portlet:namespace />branch">
	<option value="${branchId}">${employeeListById.branchName}</option>
	<c:forEach var="branch" items="${branch}">
		<option value="${branch.branchId}">${branch.branchName}</option>
	</c:forEach>
</select>
<br>
<br>
<label for="designationId">Designation Id:</label>
<select id="designationId" name="<portlet:namespace />designationName">
	<option value="${designationId}">${employeeListById.designationName}</option>
	<c:forEach var="designation" items="${designation}">
		<option value="${designation.designationId}">${designation.designationName}</option>
	</c:forEach>
</select>
<br>
<br>
<label for="address">Address:</label>
<input type="text" id="address" name="<portlet:namespace />address"
	value="${employeeListById.address}" required>
<br>
<br>
<c:choose>
	<c:when test="${not empty employeeListById}">
		<button type="submit" value="Update">Update Details</button>
	</c:when>
	<c:otherwise>
		<button type="submit" value="Register">Click Here</button>
	</c:otherwise>
</c:choose>

<liferay-ui:success key="successKey" message="true" />
</form>
</form>
</p>