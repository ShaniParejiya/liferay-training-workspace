<%@ include file="/html/branch/init.jsp"%>
<portlet:actionURL var="getBranchDetails" name="branchDetails" />
<portlet:resourceURL var="getState" id="getState" />
<portlet:resourceURL var="getCity" id="getCity" />
<head>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
	<form action="${getBranchDetails}" method="post">
			 <label for="name"> Branch Name:</label>
		        <input type="text" id="name" name="<portlet:namespace />name" required><br> <br> 
		     <%-- <h6>${countries}</h6> --%>
		     <label for="countryId">Select Country Id:</label> 
	            <select id="countryId" name="<portlet:namespace />countryName">
	            	<option name="<portlet:namespace/>country" value="">Select Country</option>
		           <c:forEach var="country" items="${countries}">
			          <option value="${country.countryId}">${country.name}</option>
		           </c:forEach>
	            </select><br>
	             <br>
	              
	         <label for="stateId">State Id:</label> 
	             <select id="<portlet:namespace />state" name="<portlet:namespace />stateName">
		            <option value="">Select State</option>
	             </select>
	             <br>
	             <br>
	             
	         <label for="cityId">City Id:</label> 
				 <select id="<portlet:namespace />city" name="<portlet:namespace />cityName">
		            <option value="">Select City</option>
	             </select>
	             <br>
	             <br>
	             
	         <button type="submit" value="Register">Click Here</button>
	</form>

<script type="text/javascript">	
    $(document).ready(function () {
        $('#countryId').on('change', function () {
            var selectedCountryValue = $(this).val();
            var resourceURL = '${getState}';

            $.ajax({
                url:resourceURL,
                type: 'GET',
                data: {
                	'<portlet:namespace />countryId' : selectedCountryValue,
                	'<portlet:namespace />command' : "searchStateByCountry"
                },
                datatype: 'json',
                success: function (stateData) {                	
                	const resObj = JSON.parse(stateData);
                	const states = resObj.data;
		    		var stateEle = $('#<portlet:namespace />state').empty();
		    		stateEle.append('<option>Select State</option>');
		            $.each(states, function(i,item) {
		            	stateEle.append( '<option value="'
		                                     + item['stateId']
		                                     + '">'
		                                     + item['stateName']
		                                     + '</option>' ); 
		            });
                },
                error: function (xhr, status, error) {
                    console.log("Something wrong !!! when call Country -> State Ajax....");
                }
            });
        });
		$('#<portlet:namespace />state').on('change', function () {
            var selectedStateValue = $(this).val();
            var resourceURL = '${getCity}';
            
            $.ajax({
                url:resourceURL,
                type: 'GET',
                data: {
                	'<portlet:namespace />stateId' : selectedStateValue,
                	'<portlet:namespace />command' : "searchCityByState"
                },
                datatype: 'json',
                success: function (cityData) {
                	const resObj = JSON.parse(cityData);
                	const cities = resObj.data;
		    		var cityEle = $('#<portlet:namespace />city').empty();
		    		cityEle.append('<option>Select City</option>');
		            $.each(cities, function(i,item) {
		            	cityEle.append( '<option value="'
		                                     + item['cityId']
		                                     + '">'
		                                     + item['cityName']
		                                     + '</option>' ); 
		            });
                },
                error: function (xhr, status, error) {
                    console.log("Something wrong !!! when call Country -> State Ajax....");
                }
            });
        });
    });
</script>