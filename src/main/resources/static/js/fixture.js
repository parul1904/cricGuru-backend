document.addEventListener('DOMContentLoaded', function() {
    const tabs = document.querySelectorAll('.season-tab');
    const matchCards = document.querySelectorAll('.match-card');
    const defaultSeason = document.getElementById('defaultSeason')?.value || '2024';

    // Function to activate a season tab and show its matches
    function activateSeasonTab(seasonYear) {
        // Update active tab
        tabs.forEach(tab => {
            const isSelected = tab.getAttribute('data-season') === seasonYear;
            tab.classList.toggle('active', isSelected);
            tab.classList.toggle('btn-primary', isSelected);
            tab.classList.toggle('btn-secondary', !isSelected);
        });

        // Show/hide match cards based on season
        matchCards.forEach(card => {
            card.style.display = card.getAttribute('data-season') === seasonYear ? 'block' : 'none';
        });
    }

    // Show default season matches
    activateSeasonTab(defaultSeason);

    // Handle tab clicks
    tabs.forEach(tab => {
        tab.addEventListener('click', function() {
            const selectedSeason = this.getAttribute('data-season');
            activateSeasonTab(selectedSeason);
        });
    });

    // Initialize Match Centre buttons
    document.querySelectorAll('.match-centre-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const matchId = this.getAttribute('data-match-id');
            window.location.href = `/dreamTeam/${matchId}`;
        });
    });

    // Add handler for stats analysis buttons
    document.querySelectorAll('.stats-analysis-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const matchId = this.getAttribute('data-match-id');
            window.location.href = `/dreamTeam/${matchId}`;
        });
    });
});