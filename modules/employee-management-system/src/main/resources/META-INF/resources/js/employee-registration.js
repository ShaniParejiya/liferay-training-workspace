$(document).ready(function () {
    $('#countryDropdown').on('change', function () {
        var selectedValue = $(this).val();
        alert(selectedValue);
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