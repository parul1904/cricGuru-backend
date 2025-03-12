document.addEventListener("DOMContentLoaded", function () {
  // Parse the JSON data from hidden fields
  const dreamTeamData = {
    old: JSON.parse(document.getElementById("oldDreamTeamData").value || "[]"),
    new: JSON.parse(document.getElementById("newDreamTeamData").value || "[]"),
    my11: JSON.parse(
      document.getElementById("my11DreamTeamData").value || "[]"
    ),
  };

  const matchStats = JSON.parse(
    document.getElementById("matchStatsData").value || "[]"
  );

  // Check if data is available
  if (!dreamTeamData.old.length) {
    console.error("Required data not found");
    showError("Failed to load team data");
    return;
  }

  // Initialize with Dream Team Old Point System
  try {
    displayTeam(dreamTeamData.old);
  } catch (error) {
    console.error("Error displaying team:", error);
    showError("Failed to display team data");
  }

  // Button click handlers
  const dreamTeamBtn = document.getElementById("dreamTeamBtn");
  const playerStatsBtn = document.getElementById("playerStatsBtn");
  const dreamTeamDropdown = document.getElementById("dreamTeamDropdown");
  const pointSystemSelect = document.getElementById("pointSystemSelect");

  pointSystemSelect.addEventListener("change", function () {
    const selectedSystem = this.value;
    try {
      displayTeam(dreamTeamData[selectedSystem]);
    } catch (error) {
      console.error("Error switching point system:", error);
      showError("Failed to switch point system");
    }
  });

  dreamTeamBtn.addEventListener("click", function () {
    dreamTeamBtn.classList.add("active", "btn-primary");
    dreamTeamBtn.classList.remove("btn-secondary");
    playerStatsBtn.classList.remove("active", "btn-primary");
    playerStatsBtn.classList.add("btn-secondary");
    dreamTeamDropdown.style.display = "block";

    const dreamTeamSection = document.getElementById("dreamTeamSection");
    const playerStatsSection = document.getElementById("playerStatsSection");

    if (dreamTeamSection) dreamTeamSection.style.display = "block";
    if (playerStatsSection) playerStatsSection.style.display = "none";

    const currentSystem = pointSystemSelect.value;
    try {
      displayTeam(dreamTeamData[currentSystem]);
    } catch (error) {
      console.error("Error displaying team:", error);
      showError("Failed to display team data");
    }
  });

  playerStatsBtn.addEventListener("click", function () {
    playerStatsBtn.classList.add("active", "btn-primary");
    playerStatsBtn.classList.remove("btn-secondary");
    dreamTeamBtn.classList.remove("active", "btn-primary");
    dreamTeamBtn.classList.add("btn-secondary");
    dreamTeamDropdown.style.display = "none";

    try {
      displayPlayerStats(matchStats);
    } catch (error) {
      console.error("Error displaying player stats:", error);
      showError("Failed to display player statistics");
    }
  });
});

function displayTeam(players) {
  if (!Array.isArray(players) || players.length === 0) {
    showNoData();
    return;
  }

  const pointSystem = document.getElementById("pointSystemSelect").value;
  const captain = findCaptain(players, pointSystem);
  if (!captain) {
    showError("Unable to determine team captain");
    return;
  }

  const viceCaptain = findViceCaptain(players, captain, pointSystem);
  if (!viceCaptain) {
    showError("Unable to determine team vice-captain");
    return;
  }

  const totalPoints = calculateTotalPoints(
    players,
    captain,
    viceCaptain,
    pointSystem
  );
  console.log("Total points:", totalPoints);

  document.getElementById("totalPoints").textContent = totalPoints;

  // Clear existing players
  const sections = [
    "wicketKeeperSection",
    "battersSection",
    "allRoundersSection",
    "bowlersSection",
  ];
  sections.forEach((section) => {
    const sectionElement = document.getElementById(section);
    if (sectionElement) {
      sectionElement.innerHTML = "";
    }
  });

  // Group and display players by role
  players.forEach((player) => {
    if (!player) return;
    const playerCard = createPlayerCard(player, captain, viceCaptain);
    const sectionId = getSectionIdByRole(player.playerRole);
    const sectionElement = document.getElementById(sectionId);
    if (sectionElement && playerCard) {
      sectionElement.appendChild(playerCard);
    }
  });

  const cricketField = document.getElementById("cricketField");
  if (cricketField) {
    cricketField.style.display = "block";
  }
  hideMessages();
}

function createPlayerCard(player, captain, viceCaptain) {
  const isCaptain = player.playerId === captain.playerId;
  const isViceCaptain = player.playerId === viceCaptain.playerId;
  const points = calculatePlayerPoints(player, isCaptain, isViceCaptain);

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
            <span class="player-separator">-</span>
            <div class="player-points">${points}</div>
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

function displayPlayerStats(matchStats) {
  const dreamTeamSection = document.getElementById("dreamTeamSection");
  const playerStatsSection = document.getElementById("playerStatsSection");

  if (dreamTeamSection) dreamTeamSection.style.display = "none";
  if (playerStatsSection) {
    playerStatsSection.style.display = "block";
    playerStatsSection.innerHTML = `
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Player Name</th>
                            <th>Runs</th>
                            <th>4s</th>
                            <th>6s</th>
                            <th>SR</th>
                            <th>Catches</th>
                            
                            <th>Overs</th>
                            <th>Runs Given</th>
                            <th>Wickets</th>
                            <th>Economy</th>
                           
                            <th>Dream 11 New Points</th>
                             <th>Dream 11 Old Points</th>
                            <th>My 11 Circle Points</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${matchStats
                          .map(
                            (stat) => `
                            <tr>
                                <td>${stat.playerName}</td>
                                <td>${stat.runsScored || 0}</td>
                                <td>${stat.fours || 0}</td>
                                <td>${stat.sixes || 0}</td>
                                <td>${
                                  stat.strikeRate
                                    ? stat.strikeRate.toFixed(2)
                                    : "0.00"
                                }</td>
                                 <td>${stat.catchTaken || 0}</td>
                                   <td>${stat.overs || 0}</td>
                                    <td>${stat.runsConceded || 0}</td>
                                <td>${stat.totalWickets || 0}</td>
                              
                               
                                <td>${
                                  stat.economyRate
                                    ? stat.economyRate.toFixed(2)
                                    : "0.00"
                                }</td>
                                <td>${stat.totalPointDream11NewSystem || 0}</td>
                                 <td>${
                                   stat.totalPointDream11OldSystem || 0
                                 }</td>
                                  <td>${
                                    stat.totalPointMy11CircleSystem || 0
                                  }</td> 
                               
                            </tr>
                        `
                          )
                          .join("")}
                    </tbody>
                </table>
            </div>
        `;
  }
}
