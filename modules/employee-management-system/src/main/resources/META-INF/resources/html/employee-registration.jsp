
<%@ include file="/html/init.jsp"%>
<portlet:defineObjects />
<portlet:resourceURL var="stateRenderURL" id="getState" />

<portlet:actionURL var="formActionURL" name="submitForm"/>

	<div class="container-fluid">
		<form action="${formActionURL}" method="post">
			<div class="mb-3">
				<label for="Name" class="form-label">Name :</label> <input
					type="text" class="form-control" name="<portlet:namespace />name"
					id="Name" aria-describedby="nameHelp">
			</div>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Email
					address :</label> <input type="email" class="form-control"
					name="<portlet:namespace />email" id="exampleInputEmail1"
					aria-describedby="emailHelp">
				<div id="emailHelp" class="form-text">We'll never share your
					email with anyone else.</div>
			</div>
			<div class="form-group">
				<label class="" for="countryDropdown">Country</label> <select
					aria-label="Label" id="countryDropdown"
					name="<portlet:namespace/>country" class="form-control">
					<option value="">Select Country</option>
					<c:forEach var="country" items="${country}">
						<option name="<portlet:namespace/>country" value="${country}">${country}</option>
					</c:forEach>
				</select></br>
			</div>
			<div class="form-group">
				<label class="" for="state">State</label> <select aria-label="Label"
					id="state" name="<portlet:namespace/>state" class="form-control">
					<option name="<portlet:namespace/>state" value="">Select
						State</option>
				</select>
			</div>
			<button type="submit">Submit Insert</button>
		</form>
	</div>
	<script>
	
	$(document).ready(function () {
	    $('#countryDropdown').on('change', function () {
	        var selectedValue = $(this).val();
	        
	        var resourceURL = '${stateRenderURL}';
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
	</script>