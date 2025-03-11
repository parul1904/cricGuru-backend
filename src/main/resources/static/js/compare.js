$(document).ready(function () {
    let player1Id = null;
    let player2Id = null;
    let compareType = "player"; // Set default to player
  
    // Load players list initially
    loadOptions("players");
  
    $("#comparePlayerBtn").click(function () {
      compareType = "player";
      $(this).addClass("btn-primary").removeClass("btn-secondary");
      $("#compareTeamBtn").addClass("btn-secondary").removeClass("btn-primary");
      $("#player1Placeholder h5").text("Select Player 1");
      $("#player2Placeholder h5").text("Select Player 2");
      $("#playerSelect1")
        .empty()
        .append('<option value="">Select Player</option>');
      $("#playerSelect2")
        .empty()
        .append('<option value="">Select Player</option>');
  
      // Reset selections and hide comparison
      player1Id = null;
      player2Id = null;
      $("#player1Details, #player2Details").hide();
      $("#player1Placeholder, #player2Placeholder").show();
      $("#comparisonSection").hide();
  
      loadOptions("players");
    });
  
    // Similar update for team button
    $("#compareTeamBtn").click(function () {
      compareType = "team";
      $(this).addClass("btn-primary").removeClass("btn-secondary");
      $("#comparePlayerBtn").addClass("btn-secondary").removeClass("btn-primary");
      $("#player1Placeholder h5").text("Select Team 1");
      $("#player2Placeholder h5").text("Select Team 2");
      $("#playerSelect1").empty().append('<option value="">Select Team</option>');
      $("#playerSelect2").empty().append('<option value="">Select Team</option>');
  
      // Reset selections and hide comparison
      player1Id = null;
      player2Id = null;
      $("#player1Details, #player2Details").hide();
      $("#player1Placeholder, #player2Placeholder").show();
      $("#comparisonSection").hide();
  
      loadOptions("teams");
    });
  
    function loadOptions(type) {
      const url =
        type === "players"
          ? "http://localhost:8080/api/v1/players/all"
          : "http://localhost:8080/api/v1/teams/all";
  
      $.ajax({
        url: url,
        method: "GET",
        dataType: "json",
        success: function (response) {
          response.forEach((item) => {
            const id = type === "players" ? item.playerId : item.teamId;
            const name = type === "players" ? item.playerName : item.teamName;
            const imageUrl =
              type === "players" ? item.playerImgUrl : item.teamLogoUrl;
  
            const option = `<option value="${id}" data-image="${imageUrl}">${name}</option>`;
            $("#playerSelect1").append(option);
            $("#playerSelect2").append(option);
          });
        },
        error: function (xhr, status, error) {
          console.error(`Error loading ${type}:`, error);
        },
      });
    }
  
    // Player 1 select change handler
    $("#playerSelect1").change(function () {
        const selectedOption = $(this).find("option:selected");
        player1Id = selectedOption.val();
        const playerName = selectedOption.text();
        const playerImage = selectedOption.data("image");
    
        if (playerName && playerImage) {
            $("#player1Placeholder").hide();
            $("#player1Image").attr("src", playerImage);
            $("#player1Name").text(playerName);
            $("#player1Details").show();
            // Close modal properly
            $("#playerModal1").modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
            checkAndCompare();
        }
    });
    
    // Player 2 select change handler
    $("#playerSelect2").change(function () {
        const selectedOption = $(this).find("option:selected");
        player2Id = selectedOption.val();
        const playerName = selectedOption.text();
        const playerImage = selectedOption.data("image");
    
        if (playerName && playerImage) {
            $("#player2Placeholder").hide();
            $("#player2Image").attr("src", playerImage);
            $("#player2Name").text(playerName);
            $("#player2Details").show();
            // Close modal properly
            $("#playerModal2").modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
            checkAndCompare();
        }
    });

    // Alternative approach using modal events
    $('#playerModal1, #playerModal2').on('shown.bs.modal', function () {
        const modalId = $(this).attr('id');
        const selectId = modalId === 'playerModal1' ? '#playerSelect1' : '#playerSelect2';
        
        $(selectId).on('change', function() {
            const modal = $(this).closest('.modal');
            if ($(this).val()) {
                modal.modal('hide');
            }
        });
    });

    // Clean up modal artifacts when hidden
    $('.modal').on('hidden.bs.modal', function () {
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
    });

    function checkAndCompare() {
      if (player1Id && player2Id) {
        const url =
          compareType === "player"
            ? "/api/v1/players/comparePlayers"
            : "/api/v1/teams/compareTeams";
  
        const params =
          compareType === "player"
            ? {
                player1: player1Id,
                player2: player2Id,
              }
            : {
                team1: player1Id,
                team2: player2Id,
              };
  
        $.ajax({
          url: url,
          method: "GET",
          data: params,
          dataType: "json",
          success: function (response) {
            updateComparisonGrid(response);
          },
          error: function (xhr, status, error) {
            console.error("Error comparing:", error);
          },
        });
      }
    }
  
    function updateComparisonGrid(data) {
      // Update Player/Team 1 stats
      $("#player1Title").text(
        data.player1Details
          ? data.player1Details.playerName
          : data.team1Details.teamName
      );
      $("#matches1").text(data.statsDetails1.matchesPlayed);
      $("#battingInnsPlayed1").text(data.statsDetails1.battingInnsPlayed);
      $("#runs1").text(data.statsDetails1.runsScored);
      $("#average1").text(data.statsDetails1.battingAverage);
      $("#strikeRate1").text(data.statsDetails1.strikeRate.toFixed(2));
      $("#centuries1").text(data.statsDetails1.century);
      $("#halfCenturies1").text(data.statsDetails1.halfCentury);
      $("#fours1").text(data.statsDetails1.fours);
      $("#sixes1").text(data.statsDetails1.sixes);
      $("#notOuts1").text(data.statsDetails1.notOuts);
      $("#catchTaken1").text(data.statsDetails1.catchTaken);
      $("#stumping1").text(data.statsDetails1.stumping);
  
      // Update Player/Team 2 stats
      $("#player2Title").text(
        data.player2Details
          ? data.player2Details.playerName
          : data.team2Details.teamName
      );
      $("#matches2").text(data.statsDetails2.matchesPlayed);
      $("#battingInnsPlayed2").text(data.statsDetails2.battingInnsPlayed);
      $("#runs2").text(data.statsDetails2.runsScored);
      $("#average2").text(data.statsDetails2.battingAverage);
      $("#strikeRate2").text(data.statsDetails2.strikeRate.toFixed(2));
      $("#centuries2").text(data.statsDetails2.century);
      $("#halfCenturies2").text(data.statsDetails2.halfCentury);
      $("#fours2").text(data.statsDetails2.fours);
      $("#sixes2").text(data.statsDetails2.sixes);
      $("#notOuts2").text(data.statsDetails2.notOuts);
      $("#catchTaken2").text(data.statsDetails2.catchTaken);
      $("#stumping2").text(data.statsDetails2.stumping);
  
      // Update Player/Team 1 bowling stats
      $("#bowlingInnsPlayed1").text(data.statsDetails1.bowlingInnsPlayed);
      $("#overs1").text(data.statsDetails1.overs);
      $("#runsConceded1").text(data.statsDetails1.runsConceded);
      $("#totalWickets1").text(data.statsDetails1.totalWickets);
      $("#dots1").text(data.statsDetails1.dots);
      $("#maidens1").text(data.statsDetails1.maidens);
      $("#bowlingAverage1").text(data.statsDetails1.bowlingAverage.toFixed(2));
      $("#economyRate1").text(data.statsDetails1.economyRate.toFixed(2));
      $("#threeWicketHauls1").text(data.statsDetails1.threeWicketHauls);
  
      // Update Player/Team 2 bowling stats
      $("#bowlingInnsPlayed2").text(data.statsDetails2.bowlingInnsPlayed);
      $("#overs2").text(data.statsDetails2.overs);
      $("#runsConceded2").text(data.statsDetails2.runsConceded);
      $("#totalWickets2").text(data.statsDetails2.totalWickets);
      $("#dots2").text(data.statsDetails2.dots);
      $("#maidens2").text(data.statsDetails2.maidens);
      $("#bowlingAverage2").text(data.statsDetails2.bowlingAverage.toFixed(2));
      $("#economyRate2").text(data.statsDetails2.economyRate.toFixed(2));
      $("#threeWicketHauls2").text(data.statsDetails2.threeWicketHauls);
  
      // Add toggle button handlers
      $("#battingStatsBtn")
        .off("click")
        .on("click", function () {
          $(this).addClass("active");
          $("#bowlingStatsBtn").removeClass("active");
          $("#battingStatsContainer").show();
          $("#bowlingStatsContainer").hide();
        });
  
      $("#bowlingStatsBtn")
        .off("click")
        .on("click", function () {
          $(this).addClass("active");
          $("#battingStatsBtn").removeClass("active");
          $("#battingStatsContainer").hide();
          $("#bowlingStatsContainer").show();
        });
  
      // Set initial state
      $("#battingStatsBtn").addClass("active");
      $("#bowlingStatsBtn").removeClass("active");
      $("#battingStatsContainer").show();
      $("#bowlingStatsContainer").hide();
  
      // Show the comparison section
      $("#comparisonSection").show();
    }
  });