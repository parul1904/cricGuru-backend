<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String pageName = "playerProfile";
    String playerId = request.getParameter("playerId");
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
    <title>Player Profile</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Site Icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon"/>
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/images/apple-touch-icon.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/squad.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/playerProfile.css"/>
    <!-- Link to external CSS -->
    <!-- font family -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <!-- end font family -->
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
    <style>

    </style>
</head>
<body class="game_info" data-spy="scroll" data-target=".header">
<!-- ========================= header start ========================= -->
<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>
<!-- ========================= header end ========================= -->

<!-- Start Breadcrumbs -->
<div class="breadcrumbs" data-stellar-background-ratio="0.5">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-12">
                <div class="breadcrumbs-content left">
                    <h1 class="page-title">Player Profile</h1>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumbs -->

<div class="container">
    <div class="player-card">
        <div class="player-image-section">
            <div class="player-image-wrapper">
                <img src="<c:out value='${playerDetails.playerImgUrl}'/>" alt="Player Image" class="player-image">
            </div>
        </div>

        <div class="player-details">
            <h1 class="player-name"><c:out value="${playerDetails.playerName}"/></h1>

            <div class="player-role-country">
                <i class="fas fa-user-circle"></i> <c:out value="${playerDetails.role}"/>
                <span class="separator">•</span>
                <i class="fas fa-flag"></i> <c:out value="${playerDetails.country}"/>
            </div>

            <div class="stats-grid">
                <div class="stat-box">
                    <div class="stat-value"><c:out value="${statsDetails.matchesPlayed}"/></div>
                    <div class="stat-label">Matches</div>
                </div>
                <div class="stat-box">
                    <div class="stat-value"><c:out value="${statsDetails.runsScored}"/></div>
                    <div class="stat-label">Runs</div>
                </div>
                <div class="stat-box">
                    <div class="stat-value"><c:out value="${statsDetails.totalWickets}"/></div>
                    <div class="stat-label">Wickets</div>
                </div>
            </div>

            <div class="player-info-grid">
                <div class="info-box">
                    <div class="info-label">Batting Style</div>
                    <div class="info-value"><c:out value="${playerDetails.battingStyle}"/></div>
                </div>
                <div class="info-box">
                    <div class="info-label">Bowling Style</div>
                    <div class="info-value"><c:out value="${playerDetails.bowlingStyle}"/></div>
                </div>
            </div>
        </div>
    </div>

    <div class="bowling-stats-section">
        <h3>Batting Statistics</h3>
        <div class="bowling-table-wrapper">
            <table class="bowling-table">
                <thead>
                <tr>
                    <th>Matches</th>
                    <th>Inns</th>
                    <th>Runs</th>
                    <th>Balls</th>
                    <th>4's</th>
                    <th>6's</th>
                    <th>SR</th>
                    <th>50+</th>
                    <th>100+</th>
                    <th>HS</th>
                    <th>Catches</th>
                    <th>Stumps</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><span class="stat-value" id="matchesPlayed"><c:out
                            value="${statsDetails.matchesPlayed}"/></span></td>
                    <td><span class="stat-value" id="battingInnsPlayed"><c:out
                            value="${statsDetails.battingInnsPlayed}"/></span></td>
                    <td><span class="stat-value" id="runsScored"><c:out value="${statsDetails.runsScored}"/></span></td>
                    <td><span class="stat-value" id="ballFaced"><c:out value="${statsDetails.ballFaced}"/></span></td>
                    <td><span class="stat-value" id="fours"><c:out value="${statsDetails.fours}"/></span></td>
                    <td><span class="stat-value" id="sixes"><c:out value="${statsDetails.sixes}"/></span></td>
                    <td><span class="stat-value" id="strikeRate"><c:out
                            value="${statsDetails.strikeRate != null ? String.format('%.2f', statsDetails.strikeRate) : '' }"/></span>
                    </td>
                    <td><span class="stat-value" id="halfCentury"><c:out value="${statsDetails.halfCentury}"/></span>
                    </td>
                    <td><span class="stat-value" id="century"><c:out value="${statsDetails.century}"/></span></td>
                    <td><span class="stat-value" id="bestScore"><c:out value="${statsDetails.bestScore}"/></span></td>
                    <td><span class="stat-value" id="catchTaken"><c:out value="${statsDetails.catchTaken}"/></span></td>
                    <td><span class="stat-value" id="stumping"><c:out value="${statsDetails.stumping}"/></span></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="bowling-stats-section">
        <h3>Bowling Statistics</h3>
        <div class="bowling-table-wrapper">
            <table class="bowling-table">
                <thead>
                <tr>
                    <th>Matches</th>
                    <th>Overs</th>
                    <th>Wickets</th>
                    <th>Economy</th>
                    <th>Average</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><span class="stat-value"><c:out value="${statsDetails.matchesPlayed}"/></span></td>
                    <td><span class="stat-value"><c:out value="${statsDetails.overs}"/></span></td>
                    <td><span class="stat-value"><c:out value="${statsDetails.totalWickets}"/></span></td>
                    <td><span class="stat-value"><c:out
                            value="${statsDetails.economyRate != null ? String.format('%.2f', statsDetails.economyRate) : '' }"/></span>
                    </td>
                    <td><span class="stat-value"><c:out
                            value="${statsDetails.bowlingAverage != null ? String.format('%.2f', statsDetails.bowlingAverage) : '' }"/></span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>

<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>
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
</body>
</html>