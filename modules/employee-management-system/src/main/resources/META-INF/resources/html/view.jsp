<%@ include file="/html/init.jsp" %>
<p>
	<b><h1>Employee Registration Details</h1></b>
</p> 
 <portlet:renderURL var="addEmployeeRenderURL">
    <portlet:param name="mvcPath" value="registration" />
</portlet:renderURL>
<a href="${addEmployeeRenderURL}">Employee Registration</a>
<p>
	<table class="table">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Country</th>
      <th scope="col">State</th>
    </tr>
  </thead>
  <tbody>
      <c:forEach var="employee" items="${empDataList}">
      	<tr>
       		<td> ${employee.name}</td>
       		<td> ${employee.email}</td>
       		<td> ${employee.country}</td>
       		<td> ${employee.state}</td>
       		</tr>
      </c:forEach>
  </tbody>
</table></br>
</br>



