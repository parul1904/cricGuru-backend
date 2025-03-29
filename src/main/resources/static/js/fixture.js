document.addEventListener('DOMContentLoaded', function() {
    const seasonOptions = document.querySelectorAll('.season-option');
    const matchContainer = document.querySelector('.match-container');
    const slider = document.querySelector('.slider');
    const defaultSeason = document.getElementById('defaultSeason')?.value || '2025';

    function sortMatches() {
        if (!matchContainer) return; // Guard clause

        const matchCards = Array.from(document.querySelectorAll('.match-card'));
        
        matchCards.sort((a, b) => {
            const statusA = a.getAttribute('data-status');
            const statusB = b.getAttribute('data-status');
            const matchNoA = parseInt(a.getAttribute('data-match-no'));
            const matchNoB = parseInt(b.getAttribute('data-match-no'));
            const timeA = a.getAttribute('data-match-time');
            const timeB = b.getAttribute('data-match-time');

            // First, sort by status (UPCOMING/TODAY first, COMPLETED last)
            if (statusA === 'COMPLETED' && statusB !== 'COMPLETED') return 1;
            if (statusA !== 'COMPLETED' && statusB === 'COMPLETED') return -1;

            // Then sort by match number
            if (matchNoA !== matchNoB) return matchNoA - matchNoB;

            // Finally sort by time if match numbers are equal
            return timeA.localeCompare(timeB);
        });

        // Clear the container before appending sorted cards
        while (matchContainer.firstChild) {
            matchContainer.removeChild(matchContainer.firstChild);
        }

        // Append sorted cards
        matchCards.forEach(card => matchContainer.appendChild(card));
    }

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
        document.querySelectorAll('.match-card').forEach(card => {
            const shouldShow = card.getAttribute('data-season') === seasonYear;
            card.style.display = shouldShow ? 'block' : 'none';
        });

        // Sort matches after showing/hiding
        sortMatches();
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
            window.location.href = `/dreamTeam/${matchId}`; // Updated URL path
        });
    });

    // Add handler for stats analysis buttons
    document.querySelectorAll('.stats-analysis-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const matchId = this.getAttribute('data-match-id');
            window.location.href = `/dreamTeam${matchId}`; // Updated URL path
        });
    });
});
