$(document).ready(function () {
			// Initialize DataTables with the fetched data
			$('#employeeTable').DataTable();

			$('#search').on('keyup', function () {
				table.search(this.value).draw();
			});
		});