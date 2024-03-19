<%@ include file="/html/employee/init.jsp"%>

<portlet:actionURL name="/addEmployeeDetails" var="getEmployeeDetails" />
<portlet:actionURL name="/updateEmployeeRecords" var="updateEmployeeRecords" />
<div class="container">
    <h2>Employee Details Form</h2>
    <form action="${not empty employeeListById ? updateEmployeeRecords : getEmployeeDetails}" method="post">
        <input type="hidden" id="fname" name="<portlet:namespace />employeeId" value="${employeeListById.employeeId}" required>
        <label for="firstName">First Name:</label>
        <input type="text" id="fname" name="<portlet:namespace />firstName" value="${employeeListById.firstName}" required>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lname" name="<portlet:namespace />lastName" value="${employeeListById.lastName}" required>
        <label for="email">Email:</label>
        <input type="email" id="email" name="<portlet:namespace />email" value="${employeeListById.email}" required>
        <label for="mobileNumber">Mobile Number:</label>
        <input type="number" id="number" name="<portlet:namespace />mobileNumber" value="${employeeListById.mobileNumber}" required>
        <label for="departmentId">Department Id:</label>
        <select id="departmentHead" name="<portlet:namespace />departmentHead">
            <option> -- Select Department Name -- </option>
            <c:forEach var="department" items="${departments}">
                <option class="form-control" value="${department.departmentId}" ${ department.departmentId == employeeListById.departmentId ? 'selected' : ''}>${department.getDepartmentName()}</option>
            </c:forEach>
        </select>
        <label for="branchId">Branch Id:</label>
        <select id="branch" name="<portlet:namespace />branch">
            <option> -- Select Branch Name --</option>
            <c:forEach var="branch" items="${branch}">
                <option class="form-control" value="${branch.branchId}" ${ branch.branchId == employeeListById.branchId ? 'selected' : ''}>${branch.getBranchName()}</option>
            </c:forEach>
        </select>
        <label for="designationId">Designation Id:</label>
        <select id="designationId" name="<portlet:namespace />designationName">
            <option> -- Select Designation Name  -- </option>
            <c:forEach var="designation" items="${designation}">
                <option class="form-control" value="${designation.designationId}" ${ designation.designationId == employeeListById.designationId ? 'selected' : ''}>${designation.getDesignationName()}</option>
            </c:forEach>
        </select>
        <label for="address">Address:</label>
        <input type="text" id="address" name="<portlet:namespace />address" value="${employeeListById.address}" required></br>
        <button type="submit" value="${not empty employeeListById ? 'Update' : 'Register'}">${not empty employeeListById ? 'Update Details' : 'Click Here'}</button>
        <p class="error-message">${errorMessage}</p>
        <liferay-ui:success key="successKey" message="true" />
    </form>
</div>
