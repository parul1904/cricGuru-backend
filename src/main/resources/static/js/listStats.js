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
    window.location.href = `/stats/admin/update/${id}`;
}

function removeStats(id) {

 // Make the API call
 fetch(`/stats/${id}`, {
    method: 'DELETE',
    headers: {
        'Content-Type': 'application/json',
    }
})
.then(response => {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    } else {
        alert('Statistics deleted successfully!');
        window.location.href = `/stats/admin/all`;
    }
    return response.json();
});
}
