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
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/squad.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" /> <!-- Link to external CSS -->
   <!-- font family -->
   <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
   <!-- end font family -->
   <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
   <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
   <style>
      .player-card {
         display: flex;
         background: linear-gradient(to right, #1a237e, #0d47a1); /* Example gradient */
         color: white;
         font-family: sans-serif; /* Example font */
         border-radius: 8px; /* Optional: Rounded corners */
         overflow: hidden; /* Optional: Prevent content overflow */
         box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Optional: Add a subtle shadow */
         padding: 20px;
         margin-top: 20px;
      }
      .player-image {
         flex: 0 0 30%; /* Adjust width as needed */
         position: relative;
      }
      .player-image img {
         width: 100%;
         height: auto;
         display: block;
         border-radius: 10px;
         box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Optional: Add a subtle shadow */
      }
      .player-details {
         flex: 1;
         padding: 40px;
      }
      .player-name {
         font-size: 2em;
         color: hsl(39 96% 54%);
         margin-bottom: 5px;
      }
      .player-role {
         font-size: 1.2em;
         margin-bottom: 20px;
      }
      .player-country {
         font-weight: bold;
      }
      .player-stats {
         display: flex;
         justify-content: space-around;
         margin-bottom: 20px;
      }
      .stat {
         text-align: center;
      }
      .stat-value {
         font-size: 2.5em;
         display: block;
      }
      .stat-label {
         font-size: 1em;
         display: block;
      }
      .player-info, .player-attributes {
         display: flex;
         justify-content: space-around;
         margin-bottom: 10px;
         border-collapse: collapse;
         margin-top: 20px;
      }
      .info-item, .attribute-item {
         font-size: 1em;
      }
      .stats-table {
         width: 100%;
         border-collapse: collapse;
         margin-top: 20px;
      }
      .stats-table th, .stats-table td {
         border: 2px solid hsl(39 96% 54%);
         padding: 8px;
         text-align: center;
      }
      .stats-table th {
         background-color: #4CAF50;
         color: white;
      }
      .stats-table tr:nth-child(even) {
         background-color: hsl(39 96% 54%);
      }
      .stats-table tr:hover {
         background-color: #ddd;
      }
   </style>
</head>
<body class="game_info" data-spy="scroll" data-target=".header">
<!-- ========================= header start ========================= -->
<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
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
        <div class="player-image">
          <img src="<c:out value='${playerDetails.playerImgUrl}'/>" alt="Player Image">
        </div>
        <div class="player-details">
            <table style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); border: 2px solid hsl(39 96% 54%);">
                <tr>
                    <td align="center" colspan="2"><h2 class="player-name"><c:out value="${playerDetails.playerName}"/></h2></td>
                </tr>
                <tr>
                    <center><c:out value="${playerDetails.role}"/> &nbsp;&nbsp;<c:out value="${playerDetails.country}"/></td>
                </tr>
            </table>
          
        
          <div class="player-stats">
            <div class="stat">
                <table class="stats-table" style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                    <tr>
                        <td align="center"><span class="stat-value"><c:out value="${statsDetails.matchesPlayed}"/></span></td>
                    </tr>
                    <tr>
                        <td align="center"><span class="stat-value">  <span class="stat-label">Matches</span></td>
                    </tr>
                </table>
            </div>
            <div class="stat">
                <table class="stats-table" style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                    <tr>
                        <td align="center"><span class="stat-value"><c:out value="${statsDetails.runsScored}"/></span></td>
                    </tr>
                    <tr>
                        <td align="center"><span class="stat-value">  <span class="stat-label">Runs</span></td>
                    </tr>
                </table>
            </div>
            <div class="stat">
                <table class="stats-table" style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                    <tr>
                        <td align="center"><span class="stat-value"><c:out value="${statsDetails.totalWickets}"/></span></td>
                    </tr>
                    <tr>
                        <td align="center"><span class="stat-value">  <span class="stat-label">Wickets</span></td>
                    </tr>
                </table>
            </div>
          </div>
          <div class="player-info">
            <table class="stats-table" style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                <tr>
                    <td align="center"><span class="stat-value">Batting Style</td>
                </tr>
                <tr>
                    <td align="center"><span class="stat-value"><span class="stat-label"><c:out value="${playerDetails.battingStyle}"/></span></td>
                </tr>
            </table>
            <table class="stats-table" style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                <tr>
                    <td align="center"><span class="stat-value">  <span class="stat-label">Bowling Style</span></td>
                </tr>
                <tr>
                    <td align="center"><span class="stat-value"><c:out value="${playerDetails.bowlingStyle}"/></span></td>
                </tr>
            </table>
          
          </div></div>
        </div>

    <div class="stat-section">
        <h3>Batting & Fielding Stats</h3>
        <table class="table table-responsive table-striped">
            <tr>
                <th>Matches Played</th>
                <th>Innings Played</th>
                <th>Runs Scored</th>
                <th>Balls Faced</th>
                <th>Fours</th>
                <th>Sixes</th>
                <th>Strike Rate</th>
                <th>Batting Average</th>
                <th>Half Centuries</th>
                <th>Centuries</th>
                <th>Best Score</th>
                <th>Catches Taken</th>
                <th>Stumpings</th>
            </tr>
            <tr>
                <td><span class="stat-value" id="matchesPlayed"><c:out value="${statsDetails.matchesPlayed}"/></span></td>
                <td><span class="stat-value" id="battingInnsPlayed"><c:out value="${statsDetails.battingInnsPlayed}"/></span></td>
                <td><span class="stat-value" id="runsScored"><c:out value="${statsDetails.runsScored}"/></span></td>
                <td><span class="stat-value" id="ballFaced"><c:out value="${statsDetails.ballFaced}"/></span></td>
                <td><span class="stat-value" id="fours"><c:out value="${statsDetails.fours}"/></span></td>
                <td><span class="stat-value" id="sixes"><c:out value="${statsDetails.sixes}"/></span></td>
                <td><span class="stat-value" id="strikeRate"><c:out value="${statsDetails.strikeRate != null ? String.format('%.2f', statsDetails.strikeRate) : '' }"/></span></td>
                <td><span class="stat-value" id="battingAverage"><c:out value="${statsDetails.battingAverage != null ? String.format('%.2f', statsDetails.battingAverage) : '' }"/></span></td>
                <td><span class="stat-value" id="halfCentury"><c:out value="${statsDetails.halfCentury}"/></span></td>
                <td><span class="stat-value" id="century"><c:out value="${statsDetails.century}"/></span></td>
                <td><span class="stat-value" id="bestScore"><c:out value="${statsDetails.bestScore}"/></span></td>
                <td><span class="stat-value" id="catchTaken"><c:out value="${statsDetails.catchTaken}"/></span></td>
                <td><span class="stat-value" id="stumping"><c:out value="${statsDetails.stumping}"/></span></td>
            </tr>
        </table>
    </div>

    <div class="stat-section">
    <h3>Bowling Stats</h3>
    <table class="table table-responsive table-striped">
        <tr>
            <th>Innings Played</th>
            <th>Overs</th>
            <th>Total Wickets</th>
            <th>Runs Conceded</th>
            <th>Dots</th>
            <th>Maidens</th>
            <th>Bowling Average</th>
            <th>Economy Rate</th>
            <th>Three Wicket Hauls</th>
        </tr>
        <tr>
            <td><span class="stat-value" id="bowlingInnsPlayed"><c:out value="${statsDetails.bowlingInnsPlayed}"/></span></td>
            <td><span class="stat-value" id="overs"><c:out value="${statsDetails.overs}"/></span></td>
            <td><span class="stat-value" id="totalWickets"><c:out value="${statsDetails.totalWickets}"/></span></td>
            <td><span class="stat-value" id="runsConceded"><c:out value="${statsDetails.runsConceded}"/></span></td>
            <td><span class="stat-value" id="dots"><c:out value="${statsDetails.dots}"/></span></td>
            <td><span class="stat-value" id="maidens"><c:out value="${statsDetails.maidens}"/></span></td>
            <td><span class="stat-value" id="bowlingAverage"><c:out value="${statsDetails.bowlingAverage != null ? String.format('%.2f',statsDetails.bowlingAverage) : '' }"/></span></td>
            <td><span class="stat-value" id="economyRate"><c:out value="${statsDetails.bowlingAverage != null ?  String.format('%.2f', statsDetails.economyRate) : ''}"/></span></td>
            <td><span class="stat-value" id="threeWicketHauls"><c:out value="${statsDetails.threeWicketHauls}"/></span></td>
        </tr>
    </table>
    </div>
</div>
</body>

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
</body>
</html>