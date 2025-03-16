document.addEventListener("DOMContentLoaded", function() {
  const dreamTeamData = {
    old: JSON.parse(oldDreamTeamJson || "[]"),
    new: JSON.parse(newDreamTeamJson || "[]"),
    my11: JSON.parse(my11CirceTeamJson || "[]")
  };

  const season = seasonYear;

  // Ensure the cricket field container exists
  const cricketFieldHtml = `
    <div id="cricketField" class="cricket-field">
      <div id="wicketKeeperSection" class="player-section wicket-keeper-section"></div>
      <div id="battersSection" class="player-section batters-section"></div>
      <div id="allRoundersSection" class="player-section all-rounders-section"></div>
      <div id="bowlersSection" class="player-section bowlers-section"></div>
    </div>
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>
    <div id="noDataMessage" class="alert alert-info" style="display: none;">No team data available</div>
  `;

  // Insert the cricket field HTML if it doesn't exist
  const dreamTeamSection = document.getElementById("dreamTeamSection");
  if (dreamTeamSection && !document.getElementById("cricketField")) {
    dreamTeamSection.innerHTML = cricketFieldHtml;
  }

  initializeView(season, dreamTeamData);
});

function initializeView(season, dreamTeamData) {
  const dreamTeamBtn = document.getElementById("dreamTeamBtn");
  const playerStatsBtn = document.getElementById("playerStatsBtn");
  const dreamTeamDropdown = document.getElementById("dreamTeamDropdown");
  const totalPointsContainer = document.getElementById("totalPointsContainer");

  if (season === "2025") {
    // Hide points-related elements for 2025
    if (totalPointsContainer) totalPointsContainer.style.display = "none";
    if (dreamTeamDropdown) dreamTeamDropdown.style.display = "none";
  }

  // Initialize with Dream Team view
  displayDreamTeam(dreamTeamData.old, season);

  // Event Listeners
  if (dreamTeamBtn) {
    dreamTeamBtn.addEventListener("click", function() {
      toggleView("dream-team", dreamTeamData, season);
    });
  }

  if (playerStatsBtn) {
    playerStatsBtn.addEventListener("click", function() {
      toggleView("player-stats", dreamTeamData, season);
    });
  }

  const pointSystemSelect = document.getElementById("pointSystemSelect");
  if (pointSystemSelect) {
    pointSystemSelect.addEventListener("change", function() {
      const selectedSystem = this.value;
      displayDreamTeam(dreamTeamData[selectedSystem], season);
    });
  }
}

function toggleView(view, data, season) {
  const dreamTeamSection = document.getElementById("dreamTeamSection");
  const playerStatsSection = document.getElementById("playerStatsSection");
  const dreamTeamBtn = document.getElementById("dreamTeamBtn");
  const playerStatsBtn = document.getElementById("playerStatsBtn");
  const dreamTeamDropdown = document.getElementById("dreamTeamDropdown");

  if (view === "player-stats") {
    dreamTeamSection.style.display = "none";
    playerStatsSection.style.display = "block";
    dreamTeamDropdown.style.display = "none";
    playerStatsBtn.classList.add("active", "btn-primary");
    playerStatsBtn.classList.remove("btn-secondary");
    dreamTeamBtn.classList.remove("active", "btn-primary");
    dreamTeamBtn.classList.add("btn-secondary");
    displayPlayerStats(data);
  } else {
    dreamTeamSection.style.display = "block";
    playerStatsSection.style.display = "none";
    if (season !== "2025") dreamTeamDropdown.style.display = "block";
    dreamTeamBtn.classList.add("active", "btn-primary");
    dreamTeamBtn.classList.remove("btn-secondary");
    playerStatsBtn.classList.remove("active", "btn-primary");
    playerStatsBtn.classList.add("btn-secondary");
    const currentSystem = document.getElementById("pointSystemSelect").value;
    displayDreamTeam(data[currentSystem], season);
  }
}

function displayDreamTeam(players, season) {
  if (!Array.isArray(players) || players.length === 0) {
    showError("No team data available");
    return;
  }

  // Show cricket field
  const cricketField = document.getElementById("cricketField");
  if (cricketField) {
    cricketField.style.display = "block";
  }

  // Hide error messages
  hideMessages();

  // Determine captain and vice-captain based on points
  const pointSystem = document.getElementById("pointSystemSelect").value;
  const captain = findCaptain(players, pointSystem);
  const viceCaptain = findViceCaptain(players, captain, pointSystem);

  // Group players by role
  const groupedPlayers = groupPlayersByRole(players);
  
  // Clear existing players
  clearPlayerSections();

  // Display players by role
  Object.entries(groupedPlayers).forEach(([role, rolePlayers]) => {
    const sectionId = getSectionIdByRole(role);
    const section = document.getElementById(sectionId);
    if (section) {
      rolePlayers.forEach(player => {
        const playerCard = createPlayerCard(player, season, captain, viceCaptain);
        section.appendChild(playerCard);
      });
    }
  });

  if (season !== "2025") {
    updateTotalPoints(players, captain, viceCaptain);
  }
}

function createPlayerCard(player, season, captain, viceCaptain) {
  const isCaptain = captain && player.playerId === captain.playerId;
  const isViceCaptain = viceCaptain && player.playerId === viceCaptain.playerId;
  const points = season === "2025" ? "" : calculatePlayerPoints(player, isCaptain, isViceCaptain);

  const card = document.createElement("div");
  card.className = "player-card";
  card.innerHTML = `
        <div class="player-image-container ${
          isCaptain || isViceCaptain ? "player-image-container-captain" : ""
        }">
            <div class="player-image-container-img">
                <img src="${player.playerImgUrl}" alt="${
    player.playerNickName
  }">
            </div>
            ${
              isCaptain
                ? '<p class="player-leadership-role player-leadership-role-captain">C</p>'
                : ""
            }
            ${
              isViceCaptain
                ? '<p class="player-leadership-role player-leadership-role-vice-captain">VC</p>'
                : ""
            }
        </div>
        <div class="player-info">
            <div class="player-name ${
              isCaptain || isViceCaptain ? "player-name-captain" : ""
            }">${player.playerNickName}</div>
            ${season !== "2025" ? `
                <span class="player-separator">-</span>
                <div class="player-points">${points}</div>
            ` : ''}
        </div>
    `;
  return card;
}

function findCaptain(players, pointSystem) {
  if (!Array.isArray(players) || players.length === 0) {
    return null;
  }
  return players.reduce((prev, current) => {
    const prevPoints = getPointsBySystem(prev, pointSystem);
    const currentPoints = getPointsBySystem(current, pointSystem);
    return prevPoints > currentPoints ? prev : current;
  });
}

function findViceCaptain(players, captain, pointSystem) {
  if (!Array.isArray(players) || players.length === 0 || !captain) {
    return null;
  }
  const eligiblePlayers = players.filter(
    (player) => player.playerId !== captain.playerId
  );
  if (eligiblePlayers.length === 0) {
    return null;
  }
  return eligiblePlayers.reduce((prev, current) => {
    const prevPoints = getPointsBySystem(prev, pointSystem);
    const currentPoints = getPointsBySystem(current, pointSystem);
    return prevPoints > currentPoints ? prev : current;
  });
}

function getPointsBySystem(player, pointSystem) {
  switch (pointSystem) {
    case "old":
      return player.dream11OldPoints || 0;
    case "new":
      return player.dream11NewPoints || 0;
    case "my11":
      return player.my11CirclePoints || 0;
    default:
      return player.dream11OldPoints || 0;
  }
}

function calculatePlayerPoints(player, isCaptain, isViceCaptain) {
  const pointSystem = document.getElementById("pointSystemSelect").value;
  const basePoints = getPointsBySystem(player, pointSystem);

  if (isCaptain) return 2 * basePoints;
  if (isViceCaptain) return 1.5 * basePoints;
  return basePoints;
}

function calculateTotalPoints(players, captain, viceCaptain, pointSystem) {
  if (!Array.isArray(players) || !captain || !viceCaptain) {
    return 0;
  }
  return players.reduce((sum, player) => {
    const basePoints = getPointsBySystem(player, pointSystem);
    if (typeof basePoints !== "number") {
      return sum;
    }
    if (player.playerId === captain.playerId) {
      return sum + 2 * basePoints;
    }
    if (player.playerId === viceCaptain.playerId) {
      return sum + 1.5 * basePoints;
    }
    return sum + basePoints;
  }, 0);
}

function getSectionIdByRole(role) {
  const roleMap = {
    "Wicket Keeper": "wicketKeeperSection",
    Batter: "battersSection",
    "All Rounder": "allRoundersSection",
    Bowler: "bowlersSection",
  };
  return roleMap[role] || "battersSection";
}

function showLoading(show) {
  const spinner = document.getElementById("loadingSpinner");
  if (spinner) {
    spinner.style.display = show ? "block" : "none";
  }
}

function showError(message) {
  const errorElement = document.getElementById("errorMessage");
  const cricketField = document.getElementById("cricketField");
  const noDataMessage = document.getElementById("noDataMessage");

  if (errorElement) {
    errorElement.textContent = message;
    errorElement.style.display = "block";
  }

  if (cricketField) {
    cricketField.style.display = "none";
  }

  if (noDataMessage) {
    noDataMessage.style.display = "none";
  }
}

function showNoData() {
  const noDataMessage = document.getElementById("noDataMessage");
  const cricketField = document.getElementById("cricketField");
  const errorElement = document.getElementById("errorMessage");

  if (noDataMessage) {
    noDataMessage.style.display = "block";
  }

  if (cricketField) {
    cricketField.style.display = "none";
  }

  if (errorElement) {
    errorElement.style.display = "none";
  }
}

function hideMessages() {
  const errorElement = document.getElementById("errorMessage");
  const noDataMessage = document.getElementById("noDataMessage");

  if (errorElement) {
    errorElement.style.display = "none";
  }

  if (noDataMessage) {
    noDataMessage.style.display = "none";
  }
}

function resetTeamDisplay() {
  const cricketField = document.getElementById("cricketField");
  if (cricketField) {
    cricketField.style.display = "none";
  }
  hideMessages();
}

function handleMatchSelection(matchId) {
  if (!matchId) {
    showError("Invalid match ID");
    return;
  }

  showLoading(true);
  fetch(`/dreamTeam/${matchId}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      showLoading(false);
      if (data && Array.isArray(data)) {
        displayTeam(data);
      } else {
        showError("Invalid data format received");
      }
    })
    .catch((error) => {
      showLoading(false);
      showError("Failed to load team data");
      console.error("Error:", error);
    });
}

const matchSelect = document.getElementById("matchSelect");
if (matchSelect) {
  matchSelect.addEventListener("change", (e) => {
    const matchId = e.target.value;
    if (matchId) {
      handleMatchSelection(matchId);
    } else {
      resetTeamDisplay();
    }
  });
}

function displayPlayerStats(data) {
  console.log("Displaying player stats with data:", data);

  if (!data || !data.performanceDataJson) {
    console.error("Invalid data format:", data);
    showError("No player data available");
    return;
  }

  const players = Array.isArray(data.performanceDataJson) 
    ? data.performanceDataJson 
    : data.performanceDataJson;  // Already parsed from JSTL
  
  console.log("Processed players:", players);

  const container = document.getElementById('playerStatsContainer');
  if (!container) {
    console.error("Container element not found");
    return;
  }
  
  container.innerHTML = ''; // Clear existing content

  // Group players by role
  const groupedPlayers = players.reduce((acc, player) => {
    const role = player.role || 'Unknown';
    if (!acc[role]) acc[role] = [];
    acc[role].push(player);
    return acc;
  }, {});

  console.log("Grouped players:", groupedPlayers);

  // Create section for each role
  const roles = ['Wicket Keeper', 'Batter', 'All Rounder', 'Bowler'];
  roles.forEach(role => {
    if (groupedPlayers[role] && groupedPlayers[role].length > 0) {
      const section = createRoleSection(role, groupedPlayers[role]);
      container.appendChild(section);
    }
  });
}

function createRoleSection(role, players) {
  console.log(`Creating section for role: ${role}`, players);
  
  const section = document.createElement('div');
  section.className = 'role-section';
  
  const header = document.createElement('h3');
  header.textContent = role;
  section.appendChild(header);

  const playerGrid = document.createElement('div');
  playerGrid.className = 'player-grid';

  players.forEach(player => {
    const card = createPlayerStatsCard(player);
    playerGrid.appendChild(card);
  });

  section.appendChild(playerGrid);
  return section;
}

function createPlayerStatsCard(player) {
  console.log("Creating card for player:", player);

  const card = document.createElement('div');
  card.className = 'player-stats-card';

  // Get last 5 matches
  const recentMatches = player.recentMatches || [];
  const last5Matches = recentMatches.slice(0, 5);

  // Create match points display
  const matchPointsHtml = last5Matches.map(match => {
    const isInDreamTeam = match.isPartOfDreamTeam;
    const pointClass = isInDreamTeam ? 'dream-team-points' : 'regular-points';
    return `<div class="match-point ${pointClass}">${match.points || 0}</div>`;
  }).join('');

  card.innerHTML = `
    <div class="player-image">
      <img src="${player.playerImageUrl || player.playerImgUrl}" alt="${player.playerName}" 
           onerror="this.src='../images/default-player.png'">
    </div>
    <div class="player-basic-info">
      <div class="name-points-row">
        <h4 class="player-name">${player.playerName}</h4>
        <div class="points-container">
          <span class="avg-points">Average Points: ${(player.averagePoints || 0).toFixed(1)}</span>
          <span class="high-points">Highest Points: ${player.highestPoints || 0}</span>
        </div>
      </div>
      <div class="last-matches">
        <p>Last 5 matches</p>
        <div class="match-points-container">
          ${matchPointsHtml}
        </div>
      </div>
    </div>
  `;

  // Add click handler for detailed view
  card.addEventListener('click', () => showPlayerDetailModal(player));
  return card;
}

function showPlayerDetailModal(player) {
  const modal = document.createElement('div');
  modal.className = 'player-modal';
  
  // Store player data for resize handling
  modal.playerData = player;

  const recentMatches = player.recentMatches || [];
  const matchesHtml = recentMatches.map(match => `
      <tr class="${match.isPartOfDreamTeam ? 'dream-team-match' : ''}">
          <td>${new Date(match.matchDate).toLocaleDateString()}</td>
          <td>${match.team1Name} vs ${match.team2Name}</td>
          <td>${match.points}</td>
          <td>${match.runsScored || 0}(${match.ballFaced || 0})</td>
          <td>${match.wickets || 0}-${match.runsConceded || 0}</td>
      </tr>
  `).join('');

  // Generate unique IDs for charts
  const performanceChartId = `performanceChart_${player.playerId}_${Date.now()}`;
  const pointsDistributionId = `distributionChart_${player.playerId}_${Date.now()}`;

  modal.innerHTML = `
      <div class="modal-content">
          <span class="close-modal">&times;</span>
          <div class="player-header">
              <img src="${player.playerImageUrl}" alt="${player.playerName}">
              <div>
                  <h3>${player.playerName}</h3>
                  <p>${player.role}</p>
                  <p>Batting: ${player.battingStyle}</p>
                  <p>Bowling: ${player.bowlingStyle || 'N/A'}</p>
              </div>
          </div>
          
          <div class="performance-charts">
              <div class="chart-container">
                  <h4>Performance Trend</h4>
                  <canvas id="${performanceChartId}"></canvas>
              </div>
              <div class="chart-container">
                  <h4>Points Distribution</h4>
                  <canvas id="${pointsDistributionId}"></canvas>
              </div>
          </div>

          <div class="performance-summary">
              <div class="summary-box">
                  <span class="summary-label">Average Points</span>
                  <span class="summary-value">${(player.averagePoints || 0).toFixed(1)}</span>
              </div>
              <div class="summary-box">
                  <span class="summary-label">Highest Score</span>
                  <span class="summary-value">${player.highestPoints || 0}</span>
              </div>
              <div class="summary-box">
                  <span class="summary-label">Dream Team Appearances</span>
                  <span class="summary-value">${recentMatches.filter(m => m.isPartOfDreamTeam).length}</span>
              </div>
          </div>

          <div class="player-performance">
              <h4>Recent Matches</h4>
              <table>
                  <thead>
                      <tr>
                          <th>Date</th>
                          <th>Match</th>
                          <th>Points</th>
                          <th>Batting</th>
                          <th>Bowling</th>
                      </tr>
                  </thead>
                  <tbody>
                      ${matchesHtml}
                  </tbody>
              </table>
          </div>
      </div>
  `;

  document.body.appendChild(modal);

  // Wait for DOM to be ready
  setTimeout(() => {
    initializePerformanceChart(performanceChartId, player);
    initializeDistributionChart(pointsDistributionId, player);
  }, 100);

  // Close modal functionality
  const closeBtn = modal.querySelector('.close-modal');
  closeBtn.onclick = () => modal.remove();
  modal.onclick = (e) => {
    if (e.target === modal) modal.remove();
  };
}

function initializePerformanceChart(chartId, player) {
  const ctx = document.getElementById(chartId);
  if (!ctx) return;

  const matches = player.recentMatches || [];
  const isMobile = window.innerWidth <= 768;
  
  new Chart(ctx, {
    type: 'line',
    data: {
      labels: matches.map(m => {
        const date = new Date(m.matchDate);
        return isMobile ? 
          `${date.getDate()}/${date.getMonth() + 1}` : 
          date.toLocaleDateString();
      }),
      datasets: [{
        label: 'Match Points',
        data: matches.map(m => m.points),
        borderColor: '#1976d2',
        backgroundColor: 'rgba(25, 118, 210, 0.1)',
        fill: true,
        tension: 0.4,
        pointBackgroundColor: matches.map(m => 
          m.isPartOfDreamTeam ? '#1976d2' : '#64b5f6'
        ),
        pointRadius: isMobile ? 3 : 5
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          display: false
        }
      },
      scales: {
        x: {
          ticks: {
            maxRotation: isMobile ? 45 : 0,
            font: {
              size: isMobile ? 10 : 12
            }
          }
        },
        y: {
          beginAtZero: true,
          ticks: {
            font: {
              size: isMobile ? 10 : 12
            }
          }
        }
      }
    }
  });
}

function initializeDistributionChart(chartId, player) {
  const ctx = document.getElementById(chartId);
  if (!ctx) return;

  const matches = player.recentMatches || [];
  const isMobile = window.innerWidth <= 768;
  
  const battingPoints = matches.reduce((sum, m) => sum + calculateBattingPoints(m), 0) / matches.length;
  const bowlingPoints = matches.reduce((sum, m) => sum + calculateBowlingPoints(m), 0) / matches.length;
  const fieldingPoints = matches.reduce((sum, m) => sum + calculateFieldingPoints(m), 0) / matches.length;

  new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: ['Batting', 'Bowling', 'Fielding'],
      datasets: [{
        data: [battingPoints, bowlingPoints, fieldingPoints],
        backgroundColor: ['#1976d2', '#64b5f6', '#bbdefb']
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: isMobile ? 'bottom' : 'right',
          labels: {
            font: {
              size: isMobile ? 11 : 12
            }
          }
        }
      }
    }
  });
}

// Helper functions to calculate points (adjust based on your scoring system)
function calculateBattingPoints(match) {
  return (match.runsScored || 0) * 1;  // Simplified scoring
}

function calculateBowlingPoints(match) {
  return (match.wickets || 0) * 25;  // Simplified scoring
}

function calculateFieldingPoints(match) {
  return ((match.points || 0) - calculateBattingPoints(match) - calculateBowlingPoints(match));
}
// Helper functions
function groupPlayersByRole(players) {
  if (!Array.isArray(players)) return {};
  
  return players.reduce((groups, player) => {
    const role = player.playerRole || 'Batter';
    if (!groups[role]) {
      groups[role] = [];
    }
    groups[role].push(player);
    return groups;
  }, {});
}

function clearPlayerSections() {
  const sections = [
    'wicketKeeperSection',
    'battersSection',
    'allRoundersSection',
    'bowlersSection'
  ];
  
  sections.forEach(sectionId => {
    const section = document.getElementById(sectionId);
    if (section) {
      section.innerHTML = '';
    }
  });
}

function showError(message) {
  // Implement error display logic
}

function updateTotalPoints(players, captain, viceCaptain) {
  const pointSystem = document.getElementById("pointSystemSelect").value;
  const totalPoints = calculateTotalPoints(players, captain, viceCaptain, pointSystem);
  const totalPointsElement = document.getElementById("totalPoints");
  if (totalPointsElement) {
    totalPointsElement.textContent = totalPoints.toFixed(2);
  }
}

function generateLastFiveMatchesHtml(player) {
  // Implement last 5 matches points display
  return '<div class="match-points">Points history here</div>';
}


let globalPlayersData = null;

function initializePlayerStats(data) {
    if (!data || !data.performanceDataJson) {
        console.error("Invalid data format:", data);
        showError("No player data available");
        return;
    }

    globalPlayersData = Array.isArray(data.performanceDataJson) 
        ? data.performanceDataJson 
        : data.performanceDataJson;

    // Initialize tab click handlers
    const roleTabs = document.querySelectorAll('.role-tab');
    roleTabs.forEach(tab => {
        tab.addEventListener('click', () => {
            // Remove active class from all tabs
            roleTabs.forEach(t => t.classList.remove('active'));
            // Add active class to clicked tab
            tab.classList.add('active');
            // Display players for selected role
            displayPlayersByRole(tab.dataset.role);
        });
    });

    // Show Wicket Keeper tab by default
    displayPlayersByRole('Wicket Keeper');
}

function displayPlayersByRole(selectedRole) {
    console.log("Displaying players for role:", selectedRole);

    const container = document.getElementById('playerStatsContainer');
    if (!container) {
        console.error("Container element not found");
        return;
    }
    
    container.innerHTML = ''; // Clear existing content

    // Filter players by selected role
    const playersInRole = globalPlayersData.filter(player => player.role === selectedRole);

    if (playersInRole.length === 0) {
        container.innerHTML = `<div class="no-players-message">No ${selectedRole}s found</div>`;
        return;
    }

    const playerGrid = document.createElement('div');
    playerGrid.className = 'player-grid';

    playersInRole.forEach(player => {
        const card = createPlayerStatsCard(player);
        playerGrid.appendChild(card);
    });

    container.appendChild(playerGrid);
}

function createPlayerStatsCard(player) {
    const card = document.createElement('div');
    card.className = 'player-stats-card';

    const recentMatches = player.recentMatches || [];
    const last5Matches = recentMatches.slice(0, 5);

    const matchPointsHtml = last5Matches.map(match => {
        const isInDreamTeam = match.isPartOfDreamTeam;
        const pointClass = isInDreamTeam ? 'dream-team-points' : 'regular-points';
        return `<div class="match-point ${pointClass}">${match.points || 0}</div>`;
    }).join('');

    card.innerHTML = `
        <div class="player-image">
            <img src="${player.playerImageUrl || player.playerImgUrl}" alt="${player.playerName}" 
                 onerror="this.src='../images/default-player.png'">
        </div>
        <div class="player-basic-info">
            <div class="name-points-row">
                <h4 class="player-name">${player.playerName}</h4>
                <div class="points-container">
                    <span class="avg-points">Average Points: ${(player.averagePoints || 0).toFixed(1)}</span>
                    <span class="high-points">Highest Points: ${player.highestPoints || 0}</span>
                </div>
            </div>
            <div class="last-matches">
                <p>Last 5 matches</p>
                <div class="match-points-container">
                    ${matchPointsHtml}
                </div>
            </div>
        </div>
    `;

    card.addEventListener('click', () => showPlayerDetailModal(player));
    return card;
}

// Add window resize handler
let resizeTimeout;
window.addEventListener('resize', () => {
  clearTimeout(resizeTimeout);
  resizeTimeout = setTimeout(() => {
    const modal = document.querySelector('.player-modal');
    if (modal && modal.playerData) {
      const performanceChartId = modal.querySelector('canvas:first-of-type').id;
      const distributionChartId = modal.querySelector('canvas:last-of-type').id;
      
      initializePerformanceChart(performanceChartId, modal.playerData);
      initializeDistributionChart(distributionChartId, modal.playerData);
    }
  }, 250);
});
