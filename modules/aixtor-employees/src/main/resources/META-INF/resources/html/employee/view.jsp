<%@ include file="/html/employee/init.jsp"%>

<portlet:renderURL var="filterURL" />

<portlet:renderURL var="getRegistrationForm">
	<portlet:param name="renderPage" value="showRegistrationForm" />
</portlet:renderURL>

<portlet:renderURL var="getDateFormatForm">
	<portlet:param name="renderPage" value="showDateFormatForm" />
</portlet:renderURL>

<portlet:renderURL var="getSearchInputForm">
	<portlet:param name="renderPage" value="getSearchInputForm" />
</portlet:renderURL>

<portlet:actionURL var="deleteEmployeeURL" name="deleteRecordById">
	<portlet:param name="employeeId" value="EMPLOYEE_ID" />
</portlet:actionURL>

<form id="dateForm" action="${getDateFormatForm}" method="post">
	<label for="fromdate">From Date:</label>
	<input type="date" id="fromdate" name="<portlet:namespace />fromdate" value="${fromdate}"></br>
	<span id="fromdateError" style="color: red; display: none;">Please Select a From Date</span>
	
	<label for="todate">To Date:</label>
    <input type="date" id="todate" name="<portlet:namespace />todate" value="${todate}"></br>
    <span id="todateError" style="color: red; display: none;">Please Select a To Date</span></br>
	<button type="submit" style="padding: 8px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 4px;">Filter</button></br>	
    
</form>
<script>
    document.getElementById('dateForm').addEventListener('submit', function(event) {
        var fromDateValue = document.getElementById('fromdate').value.trim();
        var toDateValue = document.getElementById('todate').value.trim();
        
        if (fromDateValue === '') {
            event.preventDefault(); 
            document.getElementById('fromdateError').style.display = 'block';
        } else {
            document.getElementById('fromdateError').style.display = 'none';
        }

        if (toDateValue === '') {
            event.preventDefault(); 
            document.getElementById('todateError').style.display = 'block';
        } else {
            document.getElementById('todateError').style.display = 'none';
        }
    });
</script>

<h1><liferay-ui:success key="successKey" message="Inserted Successfully Record" /></h1>
<form id="searchForm" action="${getSearchInputForm}" method="post">
	<input type="search" name="<portlet:namespace />searchInput"
		id="searchInput" value="${searchInput}"
		placeholder="Enter search keyword">
	<button type="submit">Search</button>
	<span id="errorMessage" style="color: red; display: none;">Please enter a search keyword</span>
</form>

<script>
	document.getElementById('searchForm').addEventListener('submit',
			function(event){
						var searchInputValue = document
								.getElementById('searchInput').value.trim();
						if (searchInputValue === '') {
							event.preventDefault(); // Prevent form submission
							document.getElementById('errorMessage').style.display = 'block';
						} else {
							document.getElementById('errorMessage').style.display = 'none';
						}
					});
</script>

<form action="${filterURL}" method="post">
	</br>
	<!-- <button type="submit" >EmployeeRegistration</button> -->
	<a href='${getRegistrationForm}'>Registration Form</a>
	<div style="color: red;">
		<b>${validaiton}</b>
	</div>
	<div class="container">
		<h2>Employee Table</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Employee ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Mobile Number</th>
					<th>Department Name</th>
					<th>Branch Name</th>
					<th>Designation Name</th>
					<th>Address</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="employee" items="${employeeList}">
					<tr>
						<td>${employee.employeeId}</td>
						<td>${employee.firstName}</td>
						<td>${employee.lastName}</td>
						<td>${employee.email}</td>
						<td>${employee.mobileNumber}</td>
						<td>${employee.departmentName}</td>
						<td>${employee.branchName}</td>
						<td>${employee.designationName}</td>
						<td>${employee.address}</td>
						<td>
							<portlet:renderURL var="updateDataURL">
									<portlet:param name="renderPage" value="ShowEmployeeDataById" />
									<portlet:param name="employeeId" value="${employee.employeeId}" />
							</portlet:renderURL> 
							<a href="${updateDataURL}" class="btn btn-success">Update</a><br>
							<button onclick="deleteEmployee('${employee.employeeId}')"  class="btn btn-danger">Delete</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form>
<script>
    function deleteEmployee(employeeId) {
    	let url = '${deleteEmployeeURL}';
    	url = url.replace('EMPLOYEE_ID', employeeId);
    	let text = "Are You Sure You Want Delete ???";
    	  if (confirm(text) == true) {
            window.location.href = url;
    	  } else {
    	    text = "You canceled!";
    	  }   
    }
</script>