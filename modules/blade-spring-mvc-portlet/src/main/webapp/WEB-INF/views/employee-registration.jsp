<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<portlet:defineObjects/>
<portlet:actionURL var="submitFormURL" name="handleEmployee"/>
<p>
<h2>Employee Details Form</h2>
<form:form name="employee" action="${submitFormURL.toString()}" method="post" modelAttribute="insertEmployeeDetails">
	<input type="hidden" id="<portlet:namespace />fname" name="<portlet:namespace />employeeId"
		required> <label for="firstName">First Name:</label> <input
		type="text" id="<portlet:namespace />fname" name="<portlet:namespace />firstName" required>
	<br> <br> <label for="lastName">Last Name:</label> <input
		type="text" id="<portlet:namespace />lname" name="<portlet:namespace />lastName" required>
	<br> <br> <label for="email">Email:</label> <input
		type="email" id="<portlet:namespace />email" name="<portlet:namespace />email" required>
	<br> <br> <label for="mobileNumber">Mobile Number:</label> <input
		type="number" id="<portlet:namespace />number" name="<portlet:namespace />mobileNumber"
		required> <br> <br> <label for="departmentId">Department
		Id:</label> 
		<select id="<portlet:namespace />departmentId" name="<portlet:namespace />departmentId">
		<option> -- Select Department Head -- </option>
		<c:forEach var="department" items="${departments}">
		<option value="${department.departmentId}">${department.departmentName}</option>
	</c:forEach>
	</select> <br> <br>
	<label for="branchId">Branch Id:</label>
	 <select id="<portlet:namespace />branchId" name="<portlet:namespace />branchId">
		<option>-- Select Branch --</option>
		<c:forEach var="branch" items="${branch}">
		   <option value="${branch.branchId}">${branch.branchName}</option>
	    </c:forEach>
	</select> <br> <br>
	<label for="designationId">Designation Id:</label>
	<select id="<portlet:namespace />designationId" name="<portlet:namespace />designationId">
		<option>-- Select Designation --</option>
		<c:forEach var="designation" items="${designation}">
		  <option value="${designation.designationId}">${designation.designationName}</option>
	    </c:forEach>
	</select> <br> <br> <label for="address">Address:</label>
	 <input type="text" id="address" name="<portlet:namespace />address" required>
	<br> <br>
	<button type="submit" value="Register">Click Here</button>
</form:form>
</p>