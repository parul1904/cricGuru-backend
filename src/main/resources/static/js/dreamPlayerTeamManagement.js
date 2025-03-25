// Make sure jQuery is loaded
document.addEventListener('DOMContentLoaded', function() {
    if (typeof jQuery === 'undefined') {
        console.error('jQuery is not loaded');
        return;
    }

    // Initialize match selection handler immediately
    initializeMatchSelect();
});

function initializeMatchSelect() {
    // Load matches into dropdown
    loadMatches();

    // Match selection change handler - using jQuery's on() for better event delegation
    $('#matchSelect').off('change').on('change', function() {
        const matchNo = $(this).val();
        if (matchNo) {
            loadPlayers(matchNo);
        } else {
            $('#playersTableContainer').hide();
        }
    });

    // Remove any existing form submission handlers
    $(document).off('submit', '#dreamPlayerForm');
    
    // Add single form submission handler
    $(document).on('submit', '#dreamPlayerForm', function(e) {
        e.preventDefault();
        saveDreamPlayerTeam();
    });
}

function loadMatches() {
    fetch(`/dropdown/matches/2`)
        .then(response => response.json())
        .then(data => {
            const select = $('#matchSelect');
            select.empty();
            select.append('<option value="">Select Match</option>');
            
            data.forEach(match => {
                select.append(`<option value="${match.id}">Match-${match.name}</option>`);
            });

            // If there are matches, select the first one and load its players
            if (data.length > 0) {
                const firstMatchId = data[0].id;
                select.val(firstMatchId);
                loadPlayers(firstMatchId);
            }
        })
        .catch(error => {
            console.error('Error loading matches:', error);
            showError('Failed to load matches');
        });
}

function loadPlayers(matchNo) {
    if (!matchNo) {
        $('#playersTableContainer').hide();
        return;
    }

    showLoading();
    // First get match details to get team IDs
    $.ajax({
        url: `/matches/${matchNo}`,
        method: 'GET',
        success: function(matchResponse) {
            // Now fetch squad players using team IDs
            $.ajax({
                url: `/dream-player-team/squad`,
                method: 'GET',
                data: {
                    seasonId: 2,
                    team1Id: matchResponse.team1Id,
                    team2Id: matchResponse.team2Id
                },
                success: function(players) {
                    // Initialize dream team properties
                    players.forEach(player => {
                        player.matchNo = parseInt(matchNo);
                        player.dreamTeam = false;
                        player.lastMatch = false;
                        player.last3Match = false;
                        player.last5Match = false;
                        player.isCaptain = false;
                        player.isViceCaptain = false;
                        player.playing15 = false;
                    });
                    displayPlayers(players);
                    $('#playersTableContainer').show();
                },
                error: function(xhr) {
                    console.error('Error loading squad players:', xhr);
                    showError('Failed to load squad players');
                }
            });
        },
        error: function(xhr) {
            console.error('Error loading match details:', xhr);
            showError('Failed to load match details');
            $('#playersTableContainer').hide();
        },
        complete: function() {
            hideLoading();
        }
    });
}

function displayPlayers(players) {
    if (!Array.isArray(players)) {
        console.error('Invalid players data:', players);
        showError('Invalid players data received');
        return;
    }

    const tbody = $('#playersTableBody');
    tbody.empty();
    
    // Create table if it doesn't exist
    if ($('#playersTable').length === 0) {
        $('#playersTableContainer').html(`
            <form id="dreamPlayerForm">
                <table id="playersTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Team</th>
                            <th>Role</th>
                            <th>Dream Team</th>
                            <th>Last Match</th>
                            <th>Last 3</th>
                            <th>Last 5</th>
                            <th>Captain</th>
                            <th>Vice Captain</th>
                            <th>Playing 15</th>
                        </tr>
                    </thead>
                    <tbody id="playersTableBody"></tbody>
                </table>
                <button type="submit" class="btn btn-primary mt-3">Save Changes</button>
            </form>
        `);
    }

    // Group players by team
    const playersByTeam = players.reduce((acc, player) => {
        if (!acc[player.teamShortName]) {
            acc[player.teamShortName] = [];
        }
        acc[player.teamShortName].push(player);
        return acc;
    }, {});

    // Get unique team names
    const teams = Object.keys(playersByTeam);

    // Display players team by team
    teams.forEach((team, index) => {
        // Add team header
        tbody.append(`
            <tr class="table-primary">
                <td colspan="10"><strong>${team}</strong></td>
            </tr>
        `);

        // Add players for this team
        playersByTeam[team].forEach(player => {
            tbody.append(`
                <tr data-player-id="${player.playerId}">
                    <td>${player.playerName}</td>
                    <td>${player.teamShortName}</td>
                    <td>${player.playerRole}</td>
                    <td>
                        <input type="checkbox" class="form-check-input" name="dreamTeam_${player.playerId}"
                               ${player.dreamTeam ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" name="lastMatch_${player.playerId}"
                               ${player.lastMatch ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" name="last3Match_${player.playerId}"
                               ${player.last3Match ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" name="last5Match_${player.playerId}"
                               ${player.last5Match ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" name="captain_${player.playerId}"
                               ${player.isCaptain ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" name="viceCaptain_${player.playerId}"
                               ${player.isViceCaptain ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" name="playing15_${player.playerId}"
                               ${player.playing15 ? 'checked' : ''}>
                    </td>
                </tr>
            `);
        });

        // Add a visual break between teams (except for the last team)
        if (index < teams.length - 1) {
            tbody.append(`
                <tr class="table-secondary">
                    <td colspan="10" style="height: 20px;"></td>
                </tr>
            `);
        }
    });
}

function saveDreamPlayerTeam() {
    const matchNo = $('#matchSelect').val();
    if (!matchNo) {
        showError('Please select a match first');
        return;
    }

    const players = [];
    
    $('#playersTableBody tr').each(function() {
        const playerId = $(this).data('player-id');
        players.push({
            playerId: playerId,
            matchNo: parseInt(matchNo),
            dreamTeam: $(`input[name="dreamTeam_${playerId}"]`).is(':checked'),
            lastMatch: $(`input[name="lastMatch_${playerId}"]`).is(':checked'),
            last3Match: $(`input[name="last3Match_${playerId}"]`).is(':checked'),
            last5Match: $(`input[name="last5Match_${playerId}"]`).is(':checked'),
            isCaptain: $(`input[name="captain_${playerId}"]`).is(':checked'),
            isViceCaptain: $(`input[name="viceCaptain_${playerId}"]`).is(':checked'),
            playing15: $(`input[name="playing15_${playerId}"]`).is(':checked')
        });
    });

    showLoading();
    $.ajax({
        url: '/dream-player-team/batch',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(players),
        success: function() {
            showSuccess('Successfully saved dream player team data');
        },
        error: function(xhr) {
            console.error('Error saving dream player team data:', xhr);
            showError('Failed to save dream player team data');
        },
        complete: function() {
            hideLoading();
        }
    });
}

function showLoading() {
    $('#loadingSpinner').show();
}

function hideLoading() {
    $('#loadingSpinner').hide();
}

function showSuccess(message) {
    alert(message); // You might want to replace this with a better notification system
}

function showError(message) {
    alert(message); // You might want to replace this with a better notification system
}
