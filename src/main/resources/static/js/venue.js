$(document).ready(function() {
    console.log('Document ready triggered');

    // Test if jQuery is working
    if (typeof jQuery !== 'undefined') {
        console.log('jQuery is loaded');
    } else {
        console.log('jQuery is not loaded!');
    }

    // Use event delegation for dynamically loaded venue cards
    $(document).on('click', '.venue-card', function(e) {
        e.preventDefault();
        const venueId = $(this).data('venue-id');
        console.log('Venue clicked, ID:', venueId);
        // Redirect to venue details page
        window.location.href = `/venue/details/${venueId}`;
    });
});