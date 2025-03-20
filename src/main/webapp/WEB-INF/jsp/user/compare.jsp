<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String pageName = "comparePlayer";
%>
<!DOCTYPE html>
<html lang="en">
<head>
   <!-- Basic -->
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <!-- Mobile Metas -->
   <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
   <!-- Site Metas -->
   <title>CricGuru</title>
    <meta name="keywords" content="IPL player comparison, cricket stats comparison, team comparison, IPL statistics, player performance analysis, cricket analytics, batting stats, bowling stats, IPL 2025 players, cricket performance metrics">
    <meta name="description" content="Compare IPL players and teams head-to-head with detailed statistics. Analyze batting and bowling performances, view comprehensive stats comparison, and make informed decisions for fantasy cricket on CricGuru.">
    <meta name="author" content="Parul Gangwal">
   <!-- Site Icons -->
   <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" />
   <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/images/apple-touch-icon.png">
   <!-- Bootstrap CSS -->
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compare.css" />
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
   <link href="https://cdn.jsdelivr.net/npm/select2-bootstrap-theme@0.1.0-beta.10/dist/select2-bootstrap.min.css" rel="stylesheet" />
</head>
<body class="game_info" data-spy="scroll" data-target=".header">
    <!-- ========================= header start ========================= -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <!-- Start Breadcrumbs -->
    <div class="breadcrumbs" data-stellar-background-ratio="0.5">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-12">
                    <div class="breadcrumbs-content left">
                        <h1 class="page-title">Compare Player/Team</h1>
                    </div>
                </div>
           </div>
        </div>
    </div>
    <!-- End Breadcrumbs -->

    <!-- ========================= header end ========================= -->
    <div class="container my-3">
        <!-- Comparison Type Buttons -->
        <div class="row mb-4">
            <div class="d-flex justify-content-center align-items-center">
                <button id="comparePlayerBtn" class="btn btn-primary m-2 active">Player</button>
                <button id="compareTeamBtn" class="btn btn-secondary m-2">Team</button>
            </div>
        </div>

        <!-- Photo Grid Row -->
        <div class="photo-grid">
            <div class="compare-col-1">
                <div class="compare-card" id="player1Card" data-toggle="modal" data-target="#playerModal1">
                    <div class="card-body">
                        <div id="player1Placeholder">
                            <i class="fas fa-plus-circle plus-icon"></i>
                            <h5>Player 1</h5>
                        </div>
                        <div id="player1Details" style="display: none;">
                            <img id="player1Image" src="" alt="Player 1" class="player-image">
                            <h5 id="player1Name" class="player-name"></h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="compare-col-2">
                <div class="compare-card" id="player2Card" data-toggle="modal" data-target="#playerModal2">
                    <div class="card-body">
                        <div id="player2Placeholder">
                            <i class="fas fa-plus-circle plus-icon"></i>
                            <h5>Player 2</h5>
                        </div>
                        <div id="player2Details" style="display: none;">
                            <img id="player2Image" src="" alt="Player 2" class="player-image">
                            <h5 id="player2Name" class="player-name"></h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Player 1 Modal -->
        <div class="modal fade" id="playerModal1" tabindex="-1" role="dialog" aria-labelledby="playerModalLabel1" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="playerModalLabel1">Player 1</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <select id="playerSelect1" class="form-control">
                            <option value="">Select Player</option>
                            <c:forEach var="player" items="${players}">
                                <option value="${player.playerId}" data-image="${player.playerImgUrl}">${player.playerName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <!-- Player 2 Modal -->
        <div class="modal fade" id="playerModal2" tabindex="-1" role="dialog" aria-labelledby="playerModalLabel2" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="playerModalLabel2">Player 2</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <select id="playerSelect2" class="form-control">
                            <option value="">Select Player</option>
                            <c:forEach var="player" items="${players}">
                                <option value="${player.playerId}" data-image="${player.playerImgUrl}">${player.playerName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <!-- Comparison Section -->
        <div class="row" id="comparisonSection" style="display: none;">
            <div class="col-12">
                <div class="card comparison-card">
                    <div class="card-body">
                        <div class="stats-toggle">
                            <button id="battingStatsBtn" class="active">Batting Stats</button>
                            <button id="bowlingStatsBtn">Bowling Stats</button>
                        </div>
                    
                    <!-- Batting Stats Container -->
                    <div id="battingStatsContainer" class="stats-container">
                        <div class="stat-row">
                            <span class="stat-value" id="matches1"></span>
                            <span class="stat-label">Matches</span>
                            <span class="stat-value player2" id="matches2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="battingInnsPlayed1"></span>
                            <span class="stat-label">Innings</span>
                            <span class="stat-value player2" id="battingInnsPlayed2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="runs1"></span>
                            <span class="stat-label">Runs</span>
                            <span class="stat-value player2" id="runs2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="average1"></span>
                            <span class="stat-label">Average</span>
                            <span class="stat-value player2" id="average2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="strikeRate1"></span>
                            <span class="stat-label">Strike Rate</span>
                            <span class="stat-value player2" id="strikeRate2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="centuries1"></span>
                            <span class="stat-label">100s</span>
                            <span class="stat-value player2" id="centuries2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="halfCenturies1"></span>
                            <span class="stat-label">50s</span>
                            <span class="stat-value player2" id="halfCenturies2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="fours1"></span>
                            <span class="stat-label">4s</span>
                            <span class="stat-value player2" id="fours2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="sixes1"></span>
                            <span class="stat-label">6s</span>
                            <span class="stat-value player2" id="sixes2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="catchTaken1"></span>
                            <span class="stat-label">Catches</span>
                            <span class="stat-value player2" id="catchTaken2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="stumping1"></span>
                            <span class="stat-label">Stumpings</span>
                            <span class="stat-value player2" id="stumping2"></span>
                        </div>
                    </div>

                    <!-- Bowling Stats Container -->
                    <div id="bowlingStatsContainer" class="stats-container" style="display: none;">
                        <div class="stat-row">
                            <span class="stat-value" id="bowlingInnsPlayed1"></span>
                            <span class="stat-label">Innings</span>
                            <span class="stat-value player2" id="bowlingInnsPlayed2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="overs1"></span>
                            <span class="stat-label">Overs</span>
                            <span class="stat-value player2" id="overs2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="runsConceded1"></span>
                            <span class="stat-label">Runs Conceded</span>
                            <span class="stat-value player2" id="runsConceded2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="totalWickets1"></span>
                            <span class="stat-label">Wickets</span>
                            <span class="stat-value player2" id="totalWickets2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="dots1"></span>
                            <span class="stat-label">Dots</span>
                            <span class="stat-value player2" id="dots2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="maidens1"></span>
                            <span class="stat-label">Maidens</span>
                            <span class="stat-value player2" id="maidens2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="bowlingAverage1"></span>
                            <span class="stat-label">Average</span>
                            <span class="stat-value player2" id="bowlingAverage2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="economyRate1"></span>
                            <span class="stat-label">Economy Rate</span>
                            <span class="stat-value player2" id="economyRate2"></span>
                        </div>
                        <div class="stat-row">
                            <span class="stat-value" id="threeWicketHauls1"></span>
                            <span class="stat-label">3 Wkt Hauls</span>
                            <span class="stat-value player2" id="threeWicketHauls2"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
<!-- Update the script sources at the bottom of the file -->
<!-- ALL JS FILES -->
<script src="${pageContext.request.contextPath}/js/all.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/count-up.min.js"></script>
<script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/js/tiny-slider.js"></script>
<script src="${pageContext.request.contextPath}/js/glightbox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/imagesloaded.min.js"></script>
<script src="${pageContext.request.contextPath}/js/isotope.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/compare.js"></script>

</body>
</html>