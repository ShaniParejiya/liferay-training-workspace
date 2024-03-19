<%@ include file="/html/getAllDetails/init.jsp"%>
<!-- <portlet:resourceURL var="getsearchDetails" id="getsearchDetails" /> -->
<!-- <portlet:actionURL var="filterURL" name="filterURL" /> -->
<portlet:renderURL var="filterURL" />
<form action="${filterURL}" method="post">
	<label for="fromdate">From Date:</label>
	 <input type="date" id="fromdate" name="<portlet:namespace />fromdate" value="${fromDate}">
     <label for="todate">To Date:</label> 
     <input type="date" id="todate" name="<portlet:namespace />todate" value="${toDate}">

	<button type="submit" onclick="updateDates()">Filter</button>
	</br>
	
	<input type="search" name="<portlet:namespace />searchInput"
		id="searchInput" value="${search}" placeholder="Enter search keyword">
	<button type="submit">Search</button>
	<div style="color: red;"><b>${validaiton}</b></div>
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
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</form>




