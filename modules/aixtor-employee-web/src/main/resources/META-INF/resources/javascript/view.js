

document.getElementById('searchForm').addEventListener('submit',
				function(event) {
					var searchInputValue = document
							.getElementById('searchInput').value.trim();
					if (searchInputValue === '') {
						event.preventDefault(); // Prevent form submission
						document.getElementById('errorMessage').style.display = 'block';
					} else {
						document.getElementById('errorMessage').style.display = 'none';
					}
				});



function deleteEmployee(employeeId) {
	let url = '${deleteEmployeeURL}';
	url = url.replace('EMPLOYEE_ID', employeeId);
	let text = "Are You Sure You Want Delete ??";
	if (confirm(text) == true) {
		window.location.href = url;
	} else {
		text = "You canceled!";
	}
}


document
.getElementById('dateForm')
.addEventListener(
		'submit',
		function(event) {
			var fromDateValue = document.getElementById('fromdate').value
					.trim();
			var toDateValue = document.getElementById('todate').value
					.trim();

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