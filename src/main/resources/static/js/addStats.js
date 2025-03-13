document.addEventListener('DOMContentLoaded', function() {


    // Load initial data
    loadSeasons();
    loadPlayers();

    // Season change handler
    $('#seasonSelect').on('change', function() {
        const seasonId = $(this).val();
        if (seasonId) {
            loadMatches(seasonId);
        }
    });

    // Calculate strike rate
    $('input[name="runsScored"], input[name="ballFaced"]').on('input', function() {
        calculateStrikeRate();
    });

    // Calculate economy rate
    $('input[name="overs"], input[name="runsConceded"]').on('input', function() {
        calculateEconomyRate();
    });

    // Form submission
    $('#statsForm').on('submit', function(e) {
        e.preventDefault();
        saveStats();
    });
});

function loadSeasons() {
    fetch('/dropdown/seasons')
        .then(response => response.json())
        .then(data => {
            const select = $('#seasonSelect');
            select.empty();
            select.append('<option value="">Select Season</option>');
            data.forEach(season => {
                select.append(`<option value="${season.id}">${season.name}</option>`);
            });
        })
        .catch(error => console.error('Error loading seasons:', error));
}

function loadMatches(seasonId) {
    fetch(`/dropdown/matches/${seasonId}`)
        .then(response => response.json())
        .then(data => {
            const select = $('#matchSelect');
            select.empty();
            select.append('<option value="">Select Match</option>');
            data.forEach(match => {
                select.append(`<option value="${match.id}">Match-${match.name}</option>`);
            });
        })
        .catch(error => console.error('Error loading matches:', error));
}

function loadPlayers() {
    fetch('/dropdown/players')
        .then(response => response.json())
        .then(data => {
            const select = $('#playerSelect');
            select.empty();
            select.append('<option value="">Select Player</option>');
            data.forEach(player => {
                select.append(`<option value="${player.id}">${player.name}</option>`);
            });
        })
        .catch(error => console.error('Error loading players:', error));
}

function convertOversToBalls(overs) {
    const [wholeOvers, partialOvers] = overs.toString().split('.').map(Number);
    return (wholeOvers * 6) + (partialOvers || 0);
}

function calculateStrikeRate() {
    const runsScored = parseFloat($('input[name="runsScored"]').val()) || 0;
    const ballFaced = parseFloat($('input[name="ballFaced"]').val()) || 0;
    const strikeRate = ballFaced > 0 ? ((runsScored / ballFaced) * 100).toFixed(2) : 0;
    $('input[name="strikeRate"]').val(strikeRate);
}

function calculateEconomyRate() {
    const overs = $('input[name="overs"]').val();
    const runsConceded = parseFloat($('input[name="runsConceded"]').val()) || 0;
    
    if (overs && runsConceded) {
        const balls = convertOversToBalls(overs);
        const economyRate = balls > 0 ? ((runsConceded / balls) * 6).toFixed(2) : 0;
        $('input[name="economyRate"]').val(economyRate);
    }
}

function saveStats() {
    const formData = {};
    const form = $('#statsForm');
    
    // Collect all form inputs
    form.find('input, select').each(function() {
        const name = $(this).attr('name');
        if (name) {
            let value = $(this).val();
            if ($(this).attr('type') === 'number') {
                value = value ? parseFloat(value) : 0;
            }
            formData[name] = value;
        }
    });

    // Calculate rates before sending
    formData.strikeRate = parseFloat($('input[name="strikeRate"]').val()) || 0;
    formData.economyRate = parseFloat($('input[name="economyRate"]').val()) || 0;

    fetch('/stats/add-stats', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        alert('Statistics saved successfully!');
        window.location.href = '/stats';
    })
    .catch(error => {
        console.error('Error saving stats:', error);
        alert('Error saving statistics. Please try again.');
    });
}