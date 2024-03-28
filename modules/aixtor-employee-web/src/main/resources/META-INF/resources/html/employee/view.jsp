<%@ include file="/html/employee/init.jsp"%>
<%@ page import="javax.portlet.RenderResponse" %>

<%@ page import="com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalServiceUtil,
				com.aixtor.training.liferay.employee.service.builder.model.Employee,
				com.liferay.portal.kernel.util.ListUtil,
				java.util.List,
				javax.portlet.PortletURL"
 %>
 
<portlet:renderURL var="filterURL" />

<portlet:renderURL var="getRegistrationForm">
	<portlet:param name="mvcRenderCommandName" value="/register" />
</portlet:renderURL>

<portlet:renderURL var="getDateFormatForm">
	<portlet:param name="mvcRenderCommandName" value="/showDateFormatForm" />
</portlet:renderURL>

<portlet:renderURL var="getSearchInputForm">
	<portlet:param name="mvcRenderCommandName" value="/getSearchInputForm" />
</portlet:renderURL>


<portlet:actionURL var="deleteEmployeeURL" name="/deleteRecordById">
	<portlet:param name="employeeId" value="EMPLOYEE_ID" />
</portlet:actionURL>

<div class="container" >
 <form id="dateForm" action="${getDateFormatForm}" method="post">
	<label for="fromdate">From Date:</label> <input type="date"
		id="fromdate" name="<portlet:namespace />fromdate" value="${fromdate}"></br>
	<span id="fromdateError" style="color: red; display: none;">Please
		Select a From Date</span> <label for="todate">To Date:</label> <input
		type="date" id="todate" name="<portlet:namespace />todate"
		value="${todate}"></br> <span id="todateError"
		style="color: red; display: none;">Please Select a To Date</span></br>
	<button type="submit"
		style="padding: 8px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 4px;">Filter</button>
	</br>
</form>
<h1>
	<liferay-ui:success key="successKey" message="Inserted Successfully Record" />
</h1>
<form id="searchForm" action="${getSearchInputForm}" method="post" style="margin-bottom: 20px; text-align: center;">
    <div style="display: flex; justify-content: center;">
        <input type="search" name="<portlet:namespace />searchInput" id="<portlet:namespace />searchInput" value="${searchInput}" placeholder="Enter search keyword" style="padding: 6px; border: 1px solid #ccc; border-radius: 4px; width: 200px; margin-right: 10px;">
        <button type="submit" style="padding: 6px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 4px; cursor: pointer;">Search</button>
		                
    </div>
    <span id="errorMessage" style="color: red; display: none; margin-top: 5px;">Please enter a search keyword</span>
</form> 
	<button id="pdfButton" style="padding: 6px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 4px; cursor: pointer;" onclick="getExcelServeResources()" class="<c:if test="${empty employeeList}"><c:out value="btn btn-secondary disabled"></c:out></c:if>">Export To Excel</button>
    <button id="exportToExcelButton" style="padding: 6px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 4px; cursor: pointer;"  onclick="getPdfServeResources()" class="<c:if test="${empty employeeList}"><c:out value="btn btn-secondary disabled"></c:out></c:if>" >PDF</button>
    
    
<script>

function getExcelServeResources(){
	var searchInput = $("#<portlet:namespace />searchInput").val();
	
	$.ajax({
        url: '<portlet:resourceURL id="/exportTOExcel"/>',
        type: 'POST',
        data: {
        	'<portlet:namespace />searchInput' : searchInput
        },
		xhrFields:{
			responseType: 'blob'
		},
        success: function (response, xhrStatus, request) {
	       	console.log(response);
	       	console.log(xhrStatus);
	       	console.log(request);
       		var x = request.getResponseHeader('Content-Disposition');
       		console.log(x);
       	      var fileName = x.split('=')[1];
	       	  var url = window.URL || window.webkitURL;
	          link = url.createObjectURL(response);
	          var a = $("<a />");
	          a.attr("download", fileName);
	          a.attr("href", link);
	          $("body").append(a);
	          a[0].click();
	          $("body").remove(a);
        	        	
        },
        error: function (xhr, status, error) {
            console.log("Something wrong !!! ");
        }
    });
	
}


// for pdf
function getPdfServeResources(){
	var searchInput = $("#<portlet:namespace />searchInput").val();
	$.ajax({
        url: '<portlet:resourceURL id="/exportTOPdf"/>',
        type: 'POST',
        data: {
        	'<portlet:namespace />searchInput' : searchInput
        },
		xhrFields:{
			responseType: 'blob'
		},
        success: function (response, xhrStatus, request) {
	       console.log(response);
	       	console.log(xhrStatus);
	       	console.log(request);
       		 var x = request.getResponseHeader('Content-Disposition');
       		console.log(x); 
       	       var fileName = x.split('=')[1];
	       	  var url = window.URL || window.webkitURL;
	          link = url.createObjectURL(response);
	          var a = $("<a />");
	          a.attr("download", fileName);
	          a.attr("href", link);
	          $("body").append(a);
	          a[0].click();
	          $("body").remove(a);  
        },
        error: function (xhr, status, error) {
            console.log("Something wrong !!!");
        }
    });	
}
</script>

<form action="${filterURL}" method="post">
	</br>
	<a href='${getRegistrationForm}'>Registration Form</a>
	<div style="color: red;">
		<b>${validaiton}</b>
	</div>
	<div class="container">
		<h2 style="text-align: center;">Employee Table</h2>
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
				<c:choose>
				    <c:when test="${empty employeeList}">
				        <tr>
				            <td colspan="10" style="text-align: center; color: red;" >${NoDataFound}</td>
				        </tr>
				    </c:when>
				    <c:otherwise>
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
				                        <portlet:param name="mvcRenderCommandName" value="/ShowEmployeeDataById" />
				                        <portlet:param name="employeeId" value="${employee.employeeId}" />
				                    </portlet:renderURL>
				                    <a href="${updateDataURL}" class="btn btn-success">Update</a><br>
				                    <button onclick="deleteEmployee('${employee.employeeId}')" class="btn btn-danger">Delete</button>
				                </td>
				            </tr>
				        </c:forEach>
				    </c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</form> 



<%-- <c:catch>
<liferay-ui:search-container delta="1" emptyResultsMessage="no entries were found" total="${totalEmployees}">
	<liferay-ui:search-container-results results="${employeeAllDetailsList}" />
		<liferay-ui:search-container-row
			className="com.aixtor.training.liferay.employee.service.builder.model.Employee"
			keyProperty="employeeId" modelVar="employee" escapedModel="<%= true %>">
			<liferay-ui:search-container-column-text property="firstName" />
			<liferay-ui:search-container-column-text property="lastName" />
			<liferay-ui:search-container-column-text property="email" />
			<liferay-ui:search-container-column-text property="mobileNumber" />
			<liferay-ui:search-container-column-text property="departmentId" />
			<liferay-ui:search-container-column-text property="branchId" />
			<liferay-ui:search-container-column-text property="designationId" />
			<liferay-ui:search-container-column-text property="address" />
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
</liferay-ui:search-container>

</c:catch> --%>
</div>