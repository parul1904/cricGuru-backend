$(document).ready(function() {
    $(document).on('click', '.team-logo img', function() {
        var teamId = $(this).data('team-id');
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
                            '<h4>' + player.playerName + '</h4>' +
                            '<p>' +
                            '<img src="https://fantasyteams.s3.ap-south-1.amazonaws.com/others/' + player.playerRole + '.png" style="width:75px; height:50px; padding:5px; float:left;" alt="' + player.playerRole + '">' +
                            '<img src="https://fantasyteams.s3.ap-south-1.amazonaws.com/others/' + player.playerCountry + '.png" style="width:75px; height:50px; padding:5px; float:right;" alt="' + player.playerCountry + '">' +
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
            }
        });
    });

    $(document).on('click', '.squad-member img', function() {
        var playerId = $(this).data('player-id');
        window.location.href = '/api/v1/players?playerId=' + playerId;
    });
});