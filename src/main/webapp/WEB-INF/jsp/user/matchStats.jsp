<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String pageName = "matchStats";
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
   <meta name="keywords" content="">
   <meta name="description" content="">
   <meta name="author" content="">
   <!-- Site Icons -->
   <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" />
   <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/images/apple-touch-icon.png">
   <!-- Bootstrap CSS -->
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/matchStats.css" />
   <!-- font family -->
   <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
   <!-- end font family -->
   <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
   <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">

</head>
<body class="game_info" data-spy="scroll" data-target=".header">
<!-- ========================= header start ========================= -->
<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

<!-- ========================= header end ========================= -->

 <!-- Breadcrumbs -->
 <div class="breadcrumbs" data-stellar-background-ratio="0.5">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-12">
                <div class="breadcrumbs-content left">
                    <h1 class="page-title">Match Stats - </h1>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="navigation-container mb-4">
        <input type="hidden" id="season" value="${season}">
       <!-- Add this in the view toggle section -->
       <div class="view-toggle-container no-select">
           <button id="dreamTeamBtn" class="active" type="button">Dream Team</button>
           <button id="playerStatsBtn" type="button">Player Stats</button>
       </div>
        
        <div id="dreamTeamDropdown" class="mt-2">
            <select id="pointSystemSelect" class="form-select">
                <option value="old">Dream11 Old Point System</option>
                <option value="new">Dream11 New Point System</option>
                <option value="my11">My11 Circle Point System</option>
            </select>
        </div>
    </div>
    
    <input type="hidden" id="performanceDataJson" value='${performanceDataJson}'>
    <input type="hidden" id="seasonYear" value='${seasonYear}'>
    
    <div id="dreamTeamSection">
        <!-- Add the download button at the top -->
        <div class="download-button" onclick="downloadDreamTeamImage()">
            <i class="fas fa-download"></i>
        </div>
        
        <!-- Loading Spinner -->
        <div id="loadingSpinner" class="text-center" style="display: none;">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>

        <!-- Error Message -->
        <div id="errorMessage" class="alert alert-danger text-center" style="display: none;" role="alert"></div>

        <!-- No Data Message -->
        <div id="noDataMessage" class="alert alert-info text-center" style="display: none;" role="alert">
            No team data available for this match
        </div>

        <!-- Cricket Field -->
        <div id="cricketField" class="cricket-field" style="display: none;">
            <div class="total-points-container" id="totalPointsContainer">
                <div id="totalPoints" class="total-points">0</div>
                <div class="total-points-label">Total Points</div>
            </div>

            <!-- Player Sections -->
            <div class="player-sections">
                <div class="section wicket-keeper-section">
                    <h3 class="section-title">Wicket Keeper</h3>
                    <div id="wicketKeeperSection" class="players-container"></div>
                </div>

                <div class="section batters-section">
                    <h3 class="section-title">Batters</h3>
                    <div id="battersSection" class="players-container"></div>
                </div>

                <div class="section all-rounders-section">
                    <h3 class="section-title">All Rounders</h3>
                    <div id="allRoundersSection" class="players-container"></div>
                </div>

                <div class="section bowlers-section">
                    <h3 class="section-title">Bowlers</h3>
                    <div id="bowlersSection" class="players-container"></div>
                </div>
            </div>
        </div>
    </div>

    <div id="playerStatsSection" class="player-stats-section" style="display: none;">
        <!-- Content will be dynamically populated by JavaScript -->
         <!-- Add this where you want the player stats to appear -->
<div class="stats-content">
    <!-- Role tabs -->
    <div class="role-tabs">
        <button class="role-tab active" data-role="Wicket Keeper">WK</button>
        <button class="role-tab" data-role="Batter">BAT</button>
        <button class="role-tab" data-role="All Rounder">AR</button>
        <button class="role-tab" data-role="Bowler">BOWL</button>
    </div>
    
    <div id="playerStatsContainer" class="player-stats-container"></div>
</div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
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
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="${pageContext.request.contextPath}/js/matchStats.js"></script>

<script type="text/javascript">
    const seasonYear = "${seasonYear}";
    const oldDreamTeamJson = '${oldDreamTeamJson}';
    const newDreamTeamJson = '${newDreamTeamJson}';
    const my11CirceTeamJson = '${my11CirceTeamJson}';
    // Add match details
    const matchDetails = {
        matchNo: "${match.matchNo}"
    };
</script>
<!-- ALL PLUGINS -->
<script>
    // Initialize player stats when document is ready
    document.addEventListener('DOMContentLoaded', function() {
        const performanceData = {
            performanceDataJson: ${performanceDataJson}
        };
        initializePlayerStats(performanceData);
    });
</script>
</body>
</html></html>
