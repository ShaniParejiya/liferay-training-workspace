document
		.getElementById('searchForm')
		.addEventListener(
				'submit',
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