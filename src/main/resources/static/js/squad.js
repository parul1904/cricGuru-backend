$(document).ready(function() {
    // Function to load team details
    function loadTeamDetails(teamId) {
        $.ajax({
            url: '/team/' + teamId + '/squads',
            method: 'GET',
            success: function(response) {
                // Display team details
                var teamDetails = response.teamDetails;
                var teamDetailsHtml = '<div class="col-md-12">' +
                    '<div class="row">' +
                    '<div class="col-md-6"><p><strong>Captain:</strong> ' + teamDetails.teamCaptain + '</p></div>' +
                    '<div class="col-md-6"><p><strong>Venue:</strong> ' + teamDetails.teamVenue + '</p></div>' +
                    '</div>' +
                    '<div class="row">' +
                    '<div class="col-md-6"><p><strong>Coach:</strong> ' + teamDetails.teamCoach + '</p></div>' +
                    '<div class="col-md-6"><p><strong>Titles Won:</strong> ' + teamDetails.titleWon + '</p></div>' +
                    '</div>' +
                    '</div>';
                $('#teamDetails').html(teamDetailsHtml).show();

                // Reset all category buttons to inactive state
                $('.category-button').removeClass('active');
                
                // Ensure "All" category button is active
                $('.category-button[data-category="All"]').addClass('active');

                // Categorize players
                var categories = {
                    All: [],
                    Batters: [],
                    "Wicket Keepers": [],
                    "All Rounders": [],
                    Bowlers: [],
                    Staff: []
                };

                response.playerDetails.forEach(function(player) {
                    categories.All.push(player);
                    switch (player.playerRole) {
                        case "Batter":
                            categories.Batters.push(player);
                            break;
                        case "Wicket Keeper":
                            categories["Wicket Keepers"].push(player);
                            break;
                        case "All Rounder":
                            categories["All Rounders"].push(player);
                            break;
                        case "Bowler":
                            categories.Bowlers.push(player);
                            break;
                        default:
                            categories.Staff.push(player);
                            break;
                    }
                });

                // Display squad members
                function displayPlayers(category) {
                    $('#squadContainer').empty();
                    categories[category].forEach(function(player) {
                        var squadMember = '<div class="col-md-3 column squad-member">' +
                            '<div class="card">' +
                            '<div class="image-container">' +
                            '<img class="img-responsive" src="' + player.playerImage + '" alt="' + player.playerName + '" style="width:100%" data-player-id="' + player.playerId + '">' +
                            (player.playerCountry !== 'India' ? '<i class="fa fa-plane top-right-icon"></i>' : '') +
                            '</div>' +
                            '<div class="">' +
                            '<p style="text-align:center">' + '<strong>' + player.playerName + '</strong>' +
                            '</p>' +
                            '<p>' +
                            '<img src="../images/others/' + player.playerRole + '.png" style="width:35px; height:25px; padding:5px; float:left;" alt="' + player.playerRole + '">' +
                            '<img src="../images/others/' + player.playerCountry + '.png" style="width:35px; height:25px; padding:5px; float:right;" alt="' + player.playerCountry + '">' +
                            '</p>' +
                            '</div></div></div>';
                        $('#squadContainer').append(squadMember);
                    });
                }

                // Initially display all players
                displayPlayers('All');
                $('#categoryButtons').show(); // Show category buttons

                // Handle category button clicks
                $('.category-button').click(function() {
                    $('.category-button').removeClass('active');
                    $(this).addClass('active');
                    var category = $(this).data('category');
                    displayPlayers(category);
                });

                // Activate the "All" category button by default
                $('.category-button[data-category="All"]').addClass('active');
            }
        });
    }

    // Handle team logo clicks
    $(document).on('click', '.team-logo img', function() {
        $('.team-logo').removeClass('active');
        $(this).parent().addClass('active');
        var teamId = $(this).data('team-id');
        loadTeamDetails(teamId);
    });

    // Automatically select and load the first team when the page loads
    function selectFirstTeam() {
        const firstTeamLogo = $('.team-logo:first');
        firstTeamLogo.addClass('active');
        const firstTeamId = firstTeamLogo.find('img').data('team-id');
        if (firstTeamId) {
            loadTeamDetails(firstTeamId);
        }
    }

    // Call selectFirstTeam when the page loads
    selectFirstTeam();

    // Handle player card clicks
    $(document).on('click', '.squad-member img', function() {
        var playerId = $(this).data('player-id');
        window.location.href = '/players?playerId=' + playerId;
    });
});