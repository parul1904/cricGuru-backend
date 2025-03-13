$(document).ready(function() {
    $('#statsTable').DataTable({
        responsive: true,
        dom: '<"top"Bfl>rt<"bottom"ip><"clear">',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ],
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Search...",
            lengthMenu: "Show _MENU_ entries",
            info: "Showing _START_ to _END_ of _TOTAL_ entries",
            infoEmpty: "Showing 0 to 0 of 0 entries",
            infoFiltered: "(filtered from _MAX_ total entries)",
            paginate: {
                first: "First",
                last: "Last",
                next: "Next",
                previous: "Previous"
            }
        },
        pageLength: 10,
        lengthMenu: [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        order: [[2, 'desc']], // Sort by Match Date column by default
        columnDefs: [
            {
                targets: -1, // Actions column
                orderable: false,
                searchable: false
            },
            {
                targets: 1, // Match column with images
                orderable: true,
                searchable: true
            }
        ]
    });
});

function updateStats(id) {
    window.location.href = `/admin/cricguru/edit-stats/${id}`;
}

function removeStats(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/stats/admin/delete/${id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                Swal.fire(
                    'Deleted!',
                    'Record has been deleted.',
                    'success'
                ).then(() => {
                    location.reload();
                });
            })
            .catch(error => {
                console.error('Error:', error);
                Swal.fire(
                    'Error!',
                    'Failed to delete record.',
                    'error'
                );
            });
        }
    });
}
