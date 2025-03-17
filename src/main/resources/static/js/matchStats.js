document.addEventListener("DOMContentLoaded", function() {
  // Hide breadcrumbs on initial load
  const breadcrumbs = document.querySelector(".breadcrumbs");
  if (breadcrumbs) {
    breadcrumbs.style.display = "none";
  }

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
    if (totalPointsContainer) totalPointsContainer.style.display = "none";
    if (dreamTeamDropdown) dreamTeamDropdown.style.display = "none";
  }

  // Initialize with Dream Team view
  displayDreamTeam(dreamTeamData.old, season);

  // Enhanced event listeners for both desktop and mobile
  if (dreamTeamBtn) {
    ['click', 'touchend'].forEach(eventType => {
      dreamTeamBtn.addEventListener(eventType, function(e) {
        e.preventDefault();
        toggleView("dream-team", dreamTeamData, season);
      });
    });
  }

  if (playerStatsBtn) {
    ['click', 'touchend'].forEach(eventType => {
      playerStatsBtn.addEventListener(eventType, function(e) {
        e.preventDefault();
        toggleView("player-stats", dreamTeamData, season);
      });
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
  const breadcrumbs = document.querySelector(".breadcrumbs");
  const roleTabs = document.querySelectorAll('.role-tab');

  // Hide breadcrumbs for all views
  if (breadcrumbs) {
    breadcrumbs.style.display = "none";
  }

  if (view === "player-stats") {
    // Show player stats view
    dreamTeamSection.style.display = "none";
    playerStatsSection.style.display = "block";
    
    // Update button states
    dreamTeamBtn.classList.remove("active");
    playerStatsBtn.classList.add("active");
    
    // Show role tabs
    roleTabs.forEach(tab => {
      tab.style.display = "block";
    });
    
    if (dreamTeamDropdown) {
      dreamTeamDropdown.style.display = "none";
    }
    
    // Initialize player stats if not already done
    if (data && data.performanceDataJson) {
      initializePlayerStats(data);
    }
  } else {
    // Show dream team view
    dreamTeamSection.style.display = "block";
    playerStatsSection.style.display = "none";
    
    // Update button states
    dreamTeamBtn.classList.add("active");
    playerStatsBtn.classList.remove("active");
    
    // Hide role tabs
    roleTabs.forEach(tab => {
      tab.style.display = "none";
    });
    
    if (dreamTeamDropdown && season !== "2025") {
      dreamTeamDropdown.style.display = "block";
    }
  }
}

function displayDreamTeam(players, season) {
  if (!Array.isArray(players) || players.length === 0) {
    showError("No team data available");
    return;
  }

  // Show download button when dream team is displayed
  const downloadButton = document.querySelector('.download-button');
  if (downloadButton) {
    downloadButton.style.display = 'flex';
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
                <img src="${player.playerImgUrl}" crossorigin="anonymous" alt="${
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
            ${season !== "2025" ? ` ${points} ` : ''}
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
  const card = document.createElement('div');
  card.className = 'player-stats-card';

  const recentMatches = player.recentMatches || [];
  const last5Matches = recentMatches.slice(0, 5);
  const dreamTeamCount = recentMatches.filter(match => match.isPartOfDreamTeam).length;
  const averagePoints = recentMatches.length > 0 
      ? (recentMatches.reduce((sum, match) => sum + (match.points || 0), 0) / recentMatches.length).toFixed(1)
      : 0;
  const highestPoints = recentMatches.length > 0 
      ? Math.max(...recentMatches.map(match => match.points || 0))
      : 0;

  const matchPointsHtml = last5Matches.map(match => {
      const isInDreamTeam = match.isPartOfDreamTeam;
      const pointClass = isInDreamTeam ? 'dream-team-points' : 'regular-points';
      return `<div class="match-point ${pointClass}" title="${match.team1Name} vs ${match.team2Name}">${match.points || 0}</div>`;
  }).join('');

  card.innerHTML = `
      <div class="player-header-section">
          <div class="player-image">
              <img src="${player.playerImageUrl || player.playerImgUrl}" alt="${player.playerName}" 
                   onerror="this.src='../images/default-player.png'"
                   style="width: 80px; height: 80px; object-fit: cover;">
          </div>
          <div class="player-info-header">
              <h4 class="player-name">${player.playerName}</h4>
              <div class="player-role">${player.role}</div>
              <div class="player-style">
                  ${player.battingStyle || ''} ${player.battingStyle && player.bowlingStyle ? '•' : ''} ${player.bowlingStyle || ''}
              </div>
          </div>
      </div>
      <div class="player-stats-summary">
          <div class="stat-box">
              <div class="stat-label">Average</div>
              <div class="stat-value">${averagePoints}</div>
          </div>
          <div class="stat-box">
              <div class="stat-label">Highest</div>
              <div class="stat-value">${highestPoints}</div>
          </div>
          <div class="stat-box">
              <div class="stat-label">Dream Team</div>
              <div class="stat-value dream-team-count">${dreamTeamCount}</div>
          </div>
      </div>
      <div class="last-matches">
          <p>Last 5 matches</p>
          <div class="match-points-container">
              ${matchPointsHtml}
          </div>
      </div>
  `;

  card.addEventListener('click', () => showPlayerDetailModal(player));
  return card;
}

function showPlayerDetailModal(player) {
  const modal = document.createElement('div');
  modal.className = 'player-modal';

  const recentMatches = player.recentMatches || [];
  const matchesHtml = recentMatches.map(match => {
      // Format date to be more compact
      const matchDate = new Date(match.matchDate).toLocaleDateString(undefined, {
          month: 'numeric',
          day: 'numeric'
      });

      // Shorten team names
      const team1Short = match.team1Name.split(' ').pop();
      const team2Short = match.team2Name.split(' ').pop();

      // Format batting score more compactly
      const battingScore = `${match.runsScored || 0}(${match.ballFaced || 0})`;

      // Format bowling figures more compactly
      const bowlingFigures = `${match.wickets || 0}-${match.runsConceded || 0}`;

      return `
          <tr class="${match.isPartOfDreamTeam ? 'dream-team-match' : ''}">
              <td>${matchDate}</td>
              <td>${team1Short} v ${team2Short}</td>
              <td>${match.points || 0}</td>
              <td>${battingScore}</td>
              <td>${bowlingFigures}</td>
              <td>${match.isPartOfDreamTeam ? '<span class="dream-team-badge">DT</span>' : ''}</td>
          </tr>
      `;
  }).join('');

  const performanceChartId = `performanceChart_${player.playerId}_${Date.now()}`;
  const pointsDistributionId = `distributionChart_${player.playerId}_${Date.now()}`;

  modal.innerHTML = `
      <div class="modal-content">
          <button class="close-modal">&times;</button>
          <!-- Player header section -->
          <div class="player-basic-details">
              <div class="player-image-container">
                  <img src="${player.playerImageUrl || player.playerImgUrl}" 
                       alt="${player.playerName}"
                       onerror="this.src='../images/default-player.png'">
              </div>
              <div class="player-info">
                  <h3>${player.playerName}</h3>
                  <div class="player-role">${player.role}</div>
                  <div class="player-style">
                      ${player.battingStyle}${player.bowlingStyle ? ' | ' + player.bowlingStyle : ''}
                  </div>
              </div>
          </div>

          <!-- Stats summary row -->
          <div class="player-stats-summary">
              <div class="stat-box">
                  <span class="stat-label">Average Points</span>
                  <span class="stat-value">${(player.averagePoints || 0).toFixed(1)}</span>
              </div>
              <div class="stat-box">
                  <span class="stat-label">Highest Points</span>
                  <span class="stat-value">${player.highestPoints || 0}</span>
              </div>
              <div class="stat-box">
                  <span class="stat-label">Dream Team</span>
                  <span class="stat-value">${recentMatches.filter(m => m.isPartOfDreamTeam).length}</span>
              </div>
          </div>

          <!-- Charts section -->
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

          <!-- Match history section -->
          <div class="player-performance">
              <h4>Match History</h4>
              <div class="table-container">
                  <table>
                      <thead>
                          <tr>
                              <th>Date</th>
                              <th>Match</th>
                              <th>Pts</th>
                              <th>Batting</th>
                              <th>Bowl</th>
                              <th>DT</th>
                          </tr>
                      </thead>
                      <tbody>
                          ${matchesHtml}
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
  `;

  document.body.appendChild(modal);

  // Initialize charts
  initializePerformanceChart(performanceChartId, player);
  initializeDistributionChart(pointsDistributionId, player);

  // Close modal functionality
  const closeBtn = modal.querySelector('.close-modal');
  
  // Close on button click
  closeBtn.addEventListener('click', () => {
    modal.remove();
  });

  // Close on clicking outside the modal
  modal.addEventListener('click', (e) => {
    if (e.target === modal) {
      modal.remove();
    }
  });

  // Close on Escape key press
  document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape') {
      modal.remove();
    }
  });

  // Store player data for potential updates
  modal.playerData = player;
}

function initializePerformanceChart(chartId, player) {
  const ctx = document.getElementById(chartId).getContext('2d');
  
  // Remove any dynamic height/width styling from the canvas
  ctx.canvas.style.removeProperty('height');
  ctx.canvas.style.removeProperty('width');
  
  const matches = player.recentMatches || [];
  const isMobile = window.innerWidth <= 768;
  
  new Chart(ctx, {
    type: 'line',
    data: {
      labels: matches.map(m => {
        const date = new Date(m.matchDate);
        return `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear().toString().slice(-2)}`;
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
        pointRadius: 5
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
    const ctx = document.getElementById(chartId).getContext('2d');
    
    const matches = player.recentMatches || [];
    const matchCount = matches.length || 1;
    
    let totalBattingPoints = 0;
    let totalBowlingPoints = 0;
    let totalFieldingPoints = 0;

    matches.forEach(match => {
        // Log raw match data for debugging
        console.log('Processing match:', match);
        
        const battingPoints = calculateBattingPoints(match, player.role || 'Batter');
        const bowlingPoints = calculateBowlingPoints(match);
        const fieldingPoints = calculateFieldingPoints(match);

        totalBattingPoints += battingPoints;
        totalBowlingPoints += bowlingPoints;
        totalFieldingPoints += fieldingPoints;

        // Log points calculation for each match
        console.log('Match points:', {
            matchId: match.matchId || match.id,
            date: match.matchDate || match.date,
            batting: battingPoints,
            bowling: bowlingPoints,
            fielding: fieldingPoints,
            total: battingPoints + bowlingPoints + fieldingPoints
        });
    });

    const averages = {
        batting: Math.round(totalBattingPoints / matchCount),
        bowling: Math.round(totalBowlingPoints / matchCount),
        fielding: Math.round(totalFieldingPoints / matchCount)
    };

    console.log('Total points:', {
        batting: totalBattingPoints,
        bowling: totalBowlingPoints,
        fielding: totalFieldingPoints,
        matches: matchCount
    });

    // Create chart with non-zero values only
    const labels = [];
    const data = [];
    const colors = [];

    if (averages.batting > 0) {
        labels.push(`Batting (${averages.batting})`);
        data.push(averages.batting);
        colors.push('#1976d2');
    }
    if (averages.bowling > 0) {
        labels.push(`Bowling (${averages.bowling})`);
        data.push(averages.bowling);
        colors.push('#2e7d32');
    }
    if (averages.fielding > 0) {
        labels.push(`Fielding (${averages.fielding})`);
        data.push(averages.fielding);
        colors.push('#f57c00');
    }

    new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: labels,
            datasets: [{
                data: data,
                backgroundColor: colors
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: window.innerWidth <= 768 ? 'bottom' : 'right',
                    labels: {
                        font: { size: window.innerWidth <= 768 ? 11 : 12 }
                    }
                },
                tooltip: {
                    callbacks: {
                        label: (context) => {
                            const label = context.label.split(' ')[0];
                            const value = context.raw;
                            return `${label}: ${value} points`;
                        }
                    }
                }
            }
        }
    });
}

// Helper functions to calculate points (adjust based on your scoring system)

// Helper functions to calculate points (using the point system from My11CirclePointCalculator.java)
function calculateBattingPoints(match, role) {
    let points = 0;

    // Run points
    points += match.runsScored || 0;

    // Boundary points
    points += (match.fours || 0) * 4;
    points += (match.sixes || 0) * 6;

    // Milestone bonus points
    if (match.runsScored >= 25) {
        if (match.runsScored < 50) {
            points += 4;
        } else if (match.runsScored < 75) {
            points += 8;
        } else if (match.runsScored < 100) {
            points += 12;
        } else {
            points += 16;
        }
    }

    // Strike rate bonus/penalty (excluding bowlers)
    if (role !== 'Bowler') {
        if (match.runsScored === 0) {
            points -= 2;
        } else if (match.runsScored > 0 && match.ballFaced >= 10) {
            const strikeRate = (match.runsScored / match.ballFaced) * 100;
            if (strikeRate > 170) points += 6;
            else if (strikeRate > 150) points += 4;
            else if (strikeRate >= 130) points += 2;
            else if (strikeRate >= 60 && strikeRate <= 70) points -= 2;
            else if (strikeRate >= 50 && strikeRate < 60) points -= 4;
            else if (strikeRate < 50) points -= 6;
        }
    }

    return points;
}

function calculateBowlingPoints(match) {
    let points = 0;
    
    // Debug log the bowling data
    console.log('Bowling data:', {
        wickets: match.totalWickets || match.wickets,
        bowledLbw: match.bowledLbw,
        maidens: match.maiden || match.maidens,
        dots: match.dots || match.dotBalls,
        overs: match.overs,
        runsGiven: match.runsGiven || match.runsConceded,
        rawMatch: match
    });

    // Wicket points (check both possible property names)
    const wickets = match.totalWickets || match.wickets || 0;
    points += wickets * 25;
    
    // LBW/Bowled bonus
    points += (match.bowledLbw || 0) * 8;
    
    // Wicket milestone bonus
    if (wickets === 3) {
        points += 4;
    } else if (wickets === 4) {
        points += 8;
    } else if (wickets >= 5) {
        points += 16;
    }
    
    // Maiden over points (check both possible property names)
    points += (match.maiden || match.maidens || 0) * 12;
    
    // Dot ball points (check both possible property names)
    points += (match.dots || match.dotBalls || 0);
    
    // Economy rate bonus/penalty
    const overs = match.overs || 0;
    const runsConceded = match.runsGiven || match.runsConceded || 0;
    
    if (overs >= 2) {
        const economyRate = runsConceded / overs;
        if (economyRate <= 5) points += 6;
        else if (economyRate <= 6) points += 4;
        else if (economyRate <= 7) points += 2;
        else if (economyRate >= 10 && economyRate <= 11) points -= 2;
        else if (economyRate > 11 && economyRate <= 12) points -= 4;
        else if (economyRate > 12) points -= 6;
    }
    
    return points;
}

function calculateFieldingPoints(match) {
    let points = 0;
    
    // Debug log the fielding data
    console.log('Fielding data:', {
        catches: match.catchTaken || match.catches,
        stumping: match.stumping || match.stumpings,
        directRunout: match.directRunout || match.runOutDirect,
        indirectRunout: match.inDirectRunout || match.runOutIndirect,
        rawMatch: match
    });
    
    // Catch points (check both possible property names)
    const catches = match.catchTaken || match.catches || 0;
    points += catches * 8;
    
    // Catch milestone bonus
    if (catches >= 3) {
        points += 4;
    }
    
    // Stumping points (check both possible property names)
    points += (match.stumping || match.stumpings || 0) * 12;
    
    // Run out points (check both possible property names)
    points += (match.directRunout || match.runOutDirect || 0) * 12;
    points += (match.inDirectRunout || match.runOutIndirect || 0) * 6;
    
    return points;
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
        return `<div class="match-point ${pointClass}" title="${match.team1Name} vs ${match.team2Name}">${match.points || 0}</div>`;
    }).join('');

    card.innerHTML = `
        <div class="player-image">
            <img src="${player.playerImageUrl || player.playerImgUrl}" alt="${player.playerName}" 
                 onerror="this.src='../images/default-player.png'"
                 style="width: 80px; height: 80px; object-fit: cover;">
        </div>
        <div class="player-basic-info">
            <h4 class="player-name">${player.playerName}</h4>
            <div class="points-container">
                <span class="avg-points">Average Points: ${(player.averagePoints || 0).toFixed(1)}</span>
                <span class="high-points">Highest Points: ${player.highestPoints || 0}</span>
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
            
            // Reinitialize charts
            initializePerformanceChart(performanceChartId, modal.playerData);
            initializeDistributionChart(distributionChartId, modal.playerData);

            // Update scroll indicator visibility
            const scrollIndicator = modal.querySelector('.scroll-indicator');
            if (scrollIndicator) {
                scrollIndicator.style.display = window.innerWidth <= 768 ? 'block' : 'none';
            }
        }
    }, 250);
});

function downloadDreamTeamImage() {
    const dreamTeamElement = document.getElementById('dreamTeamSection');
    const downloadButton = document.querySelector('.download-button');
    
    // Temporarily hide the download button
    if (downloadButton) {
        downloadButton.style.display = 'none';
    }

    // Create loading indicator outside the dreamTeamElement
    const loadingIndicator = document.createElement('div');
    loadingIndicator.className = 'download-loading';
    loadingIndicator.innerHTML = '<div class="spinner-border text-primary" role="status"><span class="visually-hidden">Converting...</span></div>';
    document.body.appendChild(loadingIndicator);

    // First, ensure all images are loaded
    const imagePromises = [];
    const images = dreamTeamElement.getElementsByTagName('img');
    
    for (let img of images) {
        const newImg = new Image();
        newImg.crossOrigin = "anonymous";
        
        const promise = new Promise((resolve, reject) => {
            newImg.onload = () => {
                img.src = newImg.src;
                resolve();
            };
            newImg.onerror = () => {
                newImg.src = '../images/default-player.png';
                resolve();
            };
        });
        
        const timestamp = new Date().getTime();
        newImg.src = img.src.includes('?') ? 
            `${img.src}&t=${timestamp}` : 
            `${img.src}?t=${timestamp}`;
            
        imagePromises.push(promise);
    }

    Promise.all(imagePromises)
        .then(() => {
            return html2canvas(dreamTeamElement, {
                scale: 2,
                useCORS: true,
                allowTaint: true,
                backgroundColor: '#ffffff',
                logging: false,
                onclone: function(clonedDoc) {
                    const clonedImages = clonedDoc.getElementsByTagName('img');
                    for (let img of clonedImages) {
                        img.crossOrigin = "anonymous";
                        img.style.width = img.width + 'px';
                        img.style.height = img.height + 'px';
                    }
                }
            });
        })
        .then(canvas => {
            // Remove loading indicator from body
            document.body.removeChild(loadingIndicator);
            
            // Show download button again
            if (downloadButton) {
                downloadButton.style.display = 'flex';
            }

            // Generate filename using match details
            let filename = 'dream-team';

            filename = `${filename}.png`;

            // Create download link
            const link = document.createElement('a');
            link.download = filename;
            link.href = canvas.toDataURL('image/png', 1.0);
            
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        })
        .catch(error => {
            console.error('Error generating image:', error);
            
            // Remove loading indicator from body
            if (document.body.contains(loadingIndicator)) {
                document.body.removeChild(loadingIndicator);
            }
            
            // Show download button again
            if (downloadButton) {
                downloadButton.style.display = 'flex';
            }

            alert('Failed to generate image. Please try again.');
        });
}

// Update the loading indicator styles to be fixed to the viewport
const style = document.createElement('style');
style.textContent = `
    .download-loading {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 1000;
        background: rgba(255, 255, 255, 0.8);
        padding: 20px;
        border-radius: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        pointer-events: none;
    }
`;
document.head.appendChild(style);
