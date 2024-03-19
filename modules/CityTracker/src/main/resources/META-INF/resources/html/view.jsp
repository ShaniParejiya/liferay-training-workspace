<%@ include file="/html/init.jsp"%>

 
<portlet:renderURL var="addEmployeeRenderURL">
    <portlet:param name="mvcPath" value="registration" />
</portlet:renderURL>
<portlet:resourceURL var="cityRenderURL" id="getCity" />

<p>
<a href="${addEmployeeRenderURL}">
Register here please
</a>
	<table class="table">
  <thead>
    <tr>
    <th scope="col">Name is </th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Country</th>
      <th scope="col">State</th>
    </tr>
  </thead>
  <tbody>
    
      <c:forEach var="emplist" items="${empDataList}">
      	<tr>
       		<td> ${emplist.name}</td>
       		<td> ${emplist.email}</td>
       		<td> ${emplist.country}</td>
       		<td> ${emplist.state}</td>
       		</tr>
      </c:forEach>
    
  </tbody>
</table></br>
</br>

<%--  <center><p>Register <a href="/registration.jsp">here</a>.</p></center>
 --%>

<%-- <center>
	<div class="container-fluid">
		<form>
		  <div class="mb-3">
            <label for="Name" class="form-label">Name</label>
            <input type="text" class="form-control" id="Name" aria-describedby="nameHelp">
          </div>
	      <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
          </div>
		  <div class="form-group">
		    <label class="" for="countryDropdown">Country</label>
		     <select aria-label="Label" id="countryDropdown" name="<portlet:namespace/>state" class="form-control">
				<option value="">Select Country</option>
			    	<c:forEach var="country" items="${country}">
						<option value="${country}">${country}</option>
					</c:forEach>
		   	 </select></br>
          </div>
		  <div class="form-group">
				<label class="" for="state">State</label> 
				  <select aria-label="Label" id="state" name="<portlet:namespace/>state" class="form-control">
						<option value="">Select State</option>
			   	</select>
		  </div>
				<button type="submit" onclick="submitSelection()">Submit</button>
		</form>
	</div>

</center>
<script>
$(document).ready(function () {
    $('#countryDropdown').on('change', function () {
        var selectedValue = $(this).val();
        var resourceURL = '<%=cityRenderURL%>';
			$.ajax({
				url : resourceURL,
				type : 'POST',
				dataType : 'json',
				data : {
					'<portlet:namespace />countryDropdown' : selectedValue
				},
				success : function(response) {
					var items = response.items;

					console.log(items);
					$("#state").empty();
					// Populate dropdown with received items
					$.each(response.items, function(index, item) {
						$("#state").append($("<option>", {
							value : item,
							text : item
						}));
					});
				},
				error : function(xhr, status, error) {
					console.log("Failed");
				}
			});
		});
	});
</script> --%>
</p>