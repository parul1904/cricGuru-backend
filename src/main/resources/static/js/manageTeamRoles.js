// Load teams into dropdowns when page loads
document.addEventListener('DOMContentLoaded', function() {
    loadTeams();
});

function loadTeams() {
    fetch('/teams/all')
        .then(response => response.json())
        .then(teams => {
            const team1Dropdown = document.getElementById('team1Dropdown');
            teams.forEach(team => {
                const option = new Option(team.teamName, team.teamId);
                team1Dropdown.add(option.cloneNode(true));
            });
        })
        .catch(error => console.error('Error loading teams:', error));
}

function loadTeamPlayers(teamNumber) {
    const teamId = document.getElementById(`team${teamNumber}Dropdown`).value;
    if (!teamId) return;

    fetch(`/teams/${teamId}/players`)
        .then(response => response.json())
        .then(players => {
            const playersList = document.getElementById(`team${teamNumber}PlayersList`);
            playersList.innerHTML = '';

            players.forEach(player => {
                const playerItem = createPlayerItem(player, teamNumber);
                playersList.appendChild(playerItem);
            });
        })
        .catch(error => console.error(`Error loading team ${teamNumber} players:`, error));
}

function createPlayerItem(player, teamNumber) {
    const div = document.createElement('div');
    div.className = 'player-item';
    div.innerHTML = `
        <span class="player-name">${player.playerName}</span>
        <div class="player-roles">
             <label class="role-checkbox">
                <input type="checkbox" 
                       ${player.isPlaying11 ? 'checked' : ''} 
                       onchange="updateRole(${player.playerId}, 'isPlaying11', this.checked, ${teamNumber})"
                > Playing 11
            </label>
            <label class="role-checkbox">
                <input type="checkbox" 
                       ${player.isCaptain ? 'checked' : ''} 
                       onchange="updateRole(${player.playerId}, 'isCaptain', this.checked, ${teamNumber})"
                > Captain
            </label>
            <label class="role-checkbox">
                <input type="checkbox" 
                       ${player.isViceCaptain ? 'checked' : ''} 
                       onchange="updateRole(${player.playerId}, 'isViceCaptain', this.checked, ${teamNumber})"
                > Vice Captain
            </label>
            <label class="role-checkbox">
                <input type="checkbox" 
                       ${player.isPlaying15 ? 'checked' : ''} 
                       onchange="updateRole(${player.playerId}, 'isPlaying15', this.checked, ${teamNumber})"
                > Playing 15
            </label>
        </div>
    `;
    return div;
}

let updatedPlayers = new Map();

function updateRole(playerId, role, value, teamNumber) {
    if (!updatedPlayers.has(playerId)) {
        updatedPlayers.set(playerId, {
            playerId: playerId,
            teamNumber: teamNumber
        });
    }
    
    const player = updatedPlayers.get(playerId);
    // Only set the field that was actually changed
    player[role] = value;
}

function saveTeamRoles() {
    const updates = Array.from(updatedPlayers.values());
    
    fetch('/teams/update-roles', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updates)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(result => {
        if (result.success) {
            alert('Roles updated successfully!');
            updatedPlayers.clear();
            loadTeamPlayers(1);
        } else {
            throw new Error(result.error || 'Failed to update roles');
        }
    })
    .catch(error => {
        console.error('Error saving roles:', error);
        alert('Failed to update roles: ' + error.message);
    });
}
