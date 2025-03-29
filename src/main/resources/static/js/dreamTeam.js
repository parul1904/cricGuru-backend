// Update your AJAX calls to use the new endpoints
function loadDreamTeam(matchNo) {
    $.ajax({
        url: `/dream-player-team/match/${matchNo}`,
        method: 'GET',
        success: function(response) {
            // Handle response
        }
    });
}

function saveSquadSelections(matchNo, selections) {
    $.ajax({
        url: `/dream-player-team/squad-selections/${matchNo}`,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(selections),
        success: function(response) {
            // Handle response
        }
    });
}

function saveBatchDreamTeam(dreamTeams) {
    $.ajax({
        url: '/dream-player-team/batch',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dreamTeams),
        success: function(response) {
            // Handle response
        }
    });
}

function saveDreamPlayerTeam() {
    const matchId = $('#matchSelect').val();
    if (!matchId) {
        showError('Please select a match first');
        return;
    }

    const updates = [];

    $('#playersTableBody tr[data-player-id]').each(function() {
        const playerId = $(this).data('player-id');
        if (playerId) {
            const selectionPercentage = parseFloat($(`input[name="selectionPercentage_${playerId}"]`).val()) || null;
            
            updates.push({
                playerId: playerId,
                matchId: parseInt(matchId),
                isPlaying11: $(`input[name="playing11_${playerId}"]`).is(':checked'),
                isPlaying15: $(`input[name="playing15_${playerId}"]`).is(':checked'),
                isCaptain: $(`input[name="captain_${playerId}"]`).is(':checked'),
                isViceCaptain: $(`input[name="viceCaptain_${playerId}"]`).is(':checked'),
                isDreamTeam: $(`input[name="dreamTeam_${playerId}"]`).is(':checked'),
                selectionPercentage: selectionPercentage
            });
        }
    });

    showLoading();
    $.ajax({
        url: '/dream-player-team/update-roles',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(updates),
        success: function() {
            showSuccess('Successfully saved player roles');
            // Optionally reload the data
            loadPlayers(matchId);
        },
        error: function(xhr) {
            console.error('Error saving player roles:', xhr);
            showError('Failed to save player roles');
        },
        complete: function() {
            hideLoading();
        }
    });
}
