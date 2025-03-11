document.addEventListener('DOMContentLoaded', function() {
    const tabs = document.querySelectorAll('.season-tab');
    const matchCards = document.querySelectorAll('.match-card');

    // Handle tab clicks
    tabs.forEach(tab => {
        tab.addEventListener('click', function() {
            // Update active tab
            tabs.forEach(t => t.classList.remove('active'));
            this.classList.add('active');

            // Get selected season
            const selectedSeason = this.getAttribute('data-season');

            // Show/hide match cards based on season
            matchCards.forEach(card => {
                if(card.getAttribute('data-season') === selectedSeason) {
                    card.classList.add('visible');
                } else {
                    card.classList.remove('visible');
                }
            });
        });
    });

    // Initialize Match Centre buttons
    document.querySelectorAll('.match-centre-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const matchId = this.getAttribute('data-match-id');
            window.location.href = `/matchcentre?matchId=${matchId}`;
        });
    });

    // Add handler for stats analysis buttons
    document.querySelectorAll('.stats-analysis-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const matchId = this.getAttribute('data-match-id');
            window.location.href = `/api/v1/dreamTeam?matchNo=${matchId}`;
        });
    });
});