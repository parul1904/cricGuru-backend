// Wait for jQuery to load
$(document).ready(function() {
    initializeMatchSelect();
    
    // Add click handler for save button
    $('#saveChangesBtn').on('click', function(e) {
        e.preventDefault();
        saveDreamPlayerTeam();
    });
});

function initializeMatchSelect() {
    // Load matches into dropdown
    loadMatches();

    // Match selection change handler
    $('#matchSelect').off('change').on('change', function () {
        const matchNo = $(this).val();
        clearView(); // Clear view when selection changes
        if (matchNo) {
            loadPlayers(matchNo);
        }
    });

    // Form submission handler
    $('#dreamPlayerForm').off('submit').on('submit', function (e) {
        e.preventDefault();
        saveDreamPlayerTeam();
    });
}

function loadMatches() {
    showLoading();
    fetch(`/dropdown/matches/2`)
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(data => {
            const select = $('#matchSelect');
            select.empty();
            select.append('<option value="">Select Match</option>');

            data.forEach(match => {
                select.append(`<option value="${match.id}">Match ${match.name}</option>`);
            });

            // Automatically load players if a match is pre-selected
            const selectedMatch = select.val();
            if (selectedMatch) {
                loadPlayers(selectedMatch);
            }
            hideLoading();
        })
        .catch(error => {
            console.error('Error loading matches:', error);
            showError('Failed to load matches');
            hideLoading();
        });
}

function loadPlayers(matchNo) {
    // First, clear everything
    clearView();
    
    if (!matchNo) {
        return; // Exit early if no match number
    }

    showLoading();
    
    fetch(`/dream-player-team/match/${matchNo}`)
        .then(response => {
            if (!response.ok) throw new Error('Failed to fetch squad details');
            return response.json();
        })
        .then(players => {
            if (!Array.isArray(players)) {
                throw new Error('Invalid response format');
            }
            
            const filteredPlayers = players
                .filter(player => player.playerRole !== 'Staff')
                .map(player => ({
                    playerId: player.playerId,
                    playerName: player.playerName,
                    playerRole: player.playerRole,
                    teamShortName: player.teamShortName,
                    playing11: player.playing11 || false,
                    playing15: player.playing15 || false,
                    isCaptain: player.isCaptain || false,
                    isViceCaptain: player.isViceCaptain || false,
                    dreamTeam: player.dreamTeam || false,
                    selectionPercentage: player.selectionPercentage || ''
                }));
            
            // Make sure container is empty before displaying new players
            const container = $('#playersTableContainer');
            container.empty();
            displayPlayers(filteredPlayers);
        })
        .catch(error => {
            console.error('Error:', error);
            showError('Failed to load players: ' + error.message);
        })
        .finally(() => {
            hideLoading();
        });
}

function displayPlayers(players) {
    if (!Array.isArray(players)) {
        console.error('Invalid players data:', players);
        showError('Invalid players data received');
        return;
    }

    // Get and clear the container
    const container = $('#playersTableContainer');
    container.empty();

    // Create the form and table structure
    container.html(`
        <form id="dreamPlayerForm">
            <table id="playersTable" class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Team</th>
                        <th>Role</th>
                        <th>Playing 11</th>
                        <th>Playing 15</th>
                        <th>Captain</th>
                        <th>Vice Captain</th>
                        <th>Dream Team</th>
                        <th>% Selection</th>
                    </tr>
                </thead>
                <tbody id="playersTableBody"></tbody>
            </table>
            <button type="submit" class="btn btn-primary mt-3">Save Changes</button>
        </form>
    `);

    // Group players by team
    const playersByTeam = players.reduce((acc, player) => {
        const team = player.teamShortName || 'Unknown Team';
        if (!acc[team]) {
            acc[team] = [];
        }
        acc[team].push(player);
        return acc;
    }, {});

    // Clear the table body before adding new content
    const tbody = $('#playersTableBody');
    tbody.empty();

    // Add players to table, grouped by team
    Object.entries(playersByTeam).forEach(([team, teamPlayers], index) => {
        tbody.append(`
            <tr class="table-primary">
                <td colspan="9"><strong>${team}</strong></td>
            </tr>
        `);

        teamPlayers.forEach(player => {
            tbody.append(`
                <tr data-player-id="${player.playerId}">
                    <td>${player.playerName}</td>
                    <td>${player.teamShortName}</td>
                    <td>${player.playerRole}</td>
                    <td>
                        <input type="checkbox" class="form-check-input" 
                               name="playing11_${player.playerId}"
                               ${player.playing11 ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" 
                               name="playing15_${player.playerId}"
                               ${player.playing15 ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" 
                               name="captain_${player.playerId}"
                               ${player.isCaptain ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" 
                               name="viceCaptain_${player.playerId}"
                               ${player.isViceCaptain ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" 
                               name="dreamTeam_${player.playerId}"
                               ${player.dreamTeam ? 'checked' : ''}>
                    </td>
                    <td>
                        <input type="number" class="form-control" 
                               name="selectionPercentage_${player.playerId}"
                               value="${player.selectionPercentage || ''}"
                               min="0" max="100" step="0.01">
                    </td>
                </tr>
            `);
        });

        if (index < Object.keys(playersByTeam).length - 1) {
            tbody.append(`
                <tr class="table-secondary">
                    <td colspan="9" style="height: 20px;"></td>
                </tr>
            `);
        }
    });

    // Show the container after populating
    container.show();

    // Reattach form submit handler
    $('#dreamPlayerForm').off('submit').on('submit', function(e) {
        e.preventDefault();
        saveDreamPlayerTeam();
    });
}

function showLoading() {
    console.log('Showing loading spinner');
    $('#loadingSpinner').show();
}

function hideLoading() {
    console.log('Hiding loading spinner');
    $('#loadingSpinner').hide();
}

function showError(message) {
    const alertHtml = `
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
    $('#alertContainer').html(alertHtml);
}

function showSuccess(message) {
    const alertHtml = `
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
    $('#alertContainer').html(alertHtml);
}

function saveDreamPlayerTeam() {
    const matchId = $('#matchSelect').val();
    if (!matchId) {
        window.Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Please select a match first'
        });
        return;
    }

    const updates = [];
    $('#playersTableBody tr[data-player-id]').each(function() {
        const playerId = $(this).data('player-id');
        if (playerId) {
            const update = {
                playerId: playerId,
                matchId: matchId,
                playing11: $(`input[name="playing11_${playerId}"]`).is(':checked'),
                playing15: $(`input[name="playing15_${playerId}"]`).is(':checked'),
                isCaptain: $(`input[name="captain_${playerId}"]`).is(':checked'),
                isViceCaptain: $(`input[name="viceCaptain_${playerId}"]`).is(':checked'),
                dreamTeam: $(`input[name="dreamTeam_${playerId}"]`).is(':checked'),
                selectionPercentage: parseFloat($(`input[name="selectionPercentage_${playerId}"]`).val()) || 0
            };
            updates.push(update);
        }
    });

    window.Swal.fire({
        title: 'Save Changes?',
        text: 'Do you want to save these changes?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, save it!',
        cancelButtonText: 'Cancel'
    }).then((result) => {
        if (result.isConfirmed) {
            showLoading();
            $.ajax({
                url: '/dream-player-team/update-roles',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updates),
                success: function(response) {
                    hideLoading();
                    window.Swal.fire({
                        icon: 'success',
                        title: 'Saved!',
                        text: 'Your changes have been saved successfully.',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    loadPlayers(matchId);
                },
                error: function(xhr, status, error) {
                    hideLoading();
                    window.Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Failed to save changes: ' + error
                    });
                }
            });
        }
    });
}

// Add new function to clear the view
function clearView() {
    // Remove all alerts
    $('.alert').remove();
    
    // Clear and hide the players table container
    const container = $('#playersTableContainer');
    container.empty().hide();
    
    // Clear the form if it exists
    $('#dreamPlayerForm').remove();
    
    // Clear any error messages
    $('.error-message').remove();
    
    // Clear any other potential containers or messages
    $('#totalPoints').text('');
    $('#alertContainer').empty();
}

// Add this function to check if SweetAlert2 is properly loaded
function checkSwalLoaded() {
    if (typeof window.Swal === 'undefined') {
        console.error('SweetAlert2 is not loaded!');
        return false;
    }
    console.log('SweetAlert2 is loaded properly');
    return true;
}
