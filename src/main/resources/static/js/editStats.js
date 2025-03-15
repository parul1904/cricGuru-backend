document.addEventListener('DOMContentLoaded', function() {
    // Initialize Select2
    $('.select2').select2();

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
    $('#editStatsForm').on('submit', function(e) {
        e.preventDefault();
        updateStats();
    });
});

function loadSeasons() {
    fetch('/dropdown/seasons')
        .then(response => response.json())
        .then(data => {
            const select = $('#seasonSelect');
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

function convertOversToBalls(overs) {
    const [wholeOvers, partialOvers] = overs.toString().split('.').map(Number);
    return (wholeOvers * 6) + (partialOvers || 0);
}

function updateStats() {
    const formData = {};
    const form = $('#editStatsForm');
    
    // Collect form data
    form.find('input, select').each(function() {
        const input = $(this);
        const name = input.attr('name');
        
        if (input.attr('type') === 'radio') {
            if (input.is(':checked')) {
                // Convert string 'true'/'false' to actual boolean
                formData[name] = input.val() === 'true';
            }
        } else {
            formData[name] = input.val();
        }
    });

    const statsId = $('#matchStatsId').val();

    // Make the API call
    fetch(`/stats/admin/edit/${statsId}`, {
        method: 'PUT',
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
        alert('Statistics updated successfully!');
        window.location.href = '/stats/admin/all';
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error updating statistics. Please try again.');
    });
}
