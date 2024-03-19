<%@ include file="/html/designation/init.jsp"%>
<portlet:actionURL var="getDesignationDetails" name="designationForm" />

<p>
<form action="${getDesignationDetails}" method="post">
	<div class="form-group">
		<label for="designation">Designation:</label>
		   <input type="text" name="<portlet:namespace />name"  class="form-control" id="designation" name="designation" placeholder="Enter Designation">
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
</form>
</p>