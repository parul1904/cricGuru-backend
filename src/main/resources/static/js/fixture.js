document.addEventListener('DOMContentLoaded', function() {
    const seasonOptions = document.querySelectorAll('.season-option');
    const matchCards = document.querySelectorAll('.match-card');
    const slider = document.querySelector('.slider');
    const defaultSeason = document.getElementById('defaultSeason')?.value || '2025';

    // Function to activate a season option and show its matches
    function activateSeasonOption(seasonYear) {
        // Update active option and slider
        seasonOptions.forEach(option => {
            const isSelected = option.getAttribute('data-season') === seasonYear;
            option.classList.toggle('active', isSelected);
            
            if (isSelected) {
                slider.classList.toggle('right', seasonYear === '2024');
            }
        });

        // Show/hide match cards based on season
        matchCards.forEach(card => {
            card.style.display = card.getAttribute('data-season') === seasonYear ? 'block' : 'none';
        });
    }

    // Show default season matches
    activateSeasonOption(defaultSeason);

    // Handle option clicks
    seasonOptions.forEach(option => {
        option.addEventListener('click', function() {
            const selectedSeason = this.getAttribute('data-season');
            activateSeasonOption(selectedSeason);
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