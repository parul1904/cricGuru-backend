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
   <meta name="keywords" content="">
   <meta name="description" content="">
   <meta name="author" content="">
   <!-- Site Icons -->
   <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" />
   <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/images/apple-touch-icon.png">
   <!-- Bootstrap CSS -->
   <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
   <link href="https://cdn.jsdelivr.net/npm/select2-bootstrap-theme@0.1.0-beta.10/dist/select2-bootstrap.min.css" rel="stylesheet" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addStats.css" />
   
</head>
<body class="game_info" data-spy="scroll" data-target=".header">
    <!-- ========================= header start ========================= -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    <div class="container mt-4">
        <h2 class="text-center mb-4">Add Match Statistics</h2>
        <form id="statsForm">
            <!-- Player Section -->
            <div class="card mb-4">
                <div class="card-header bg-primary text-white">
                    Player Section
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Season</label>
                                <select name="seasonId" class="form-control" id="seasonSelect"></select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Match</label>
                                <select name="matchId" class="form-control select2" id="matchSelect"></select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Player</label>
                                <select name="playerId" class="form-control select2" id="playerSelect"></select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Batting Section -->
            <div class="card mb-4">
                <div class="card-header bg-success text-white">
                    Batting Section
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Runs Scored</label>
                                <input type="text" name="runsScored" class="form-control" >
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Balls Faced</label>
                                <input type="text" name="ballFaced" class="form-control" >
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Strike Rate</label>
                                <input type="text" name="strikeRate" class="form-control" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                    
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Fours</label>
                                <input type="text" name="fours" class="form-control">
                            </div>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Sixes</label>
                                <input type="text" name="sixes" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>Dismissal Status</label>
                            <div class="form-check">
                                <input type="radio" name="isDismissed" value="true" class="form-check-input" id="dismissed">
                                <label class="form-check-label" for="dismissed">Out</label>
                            </div>
                            <div class="form-check">
                                <input type="radio" name="isDismissed" value="false" class="form-check-input" id="notDismissed">
                                <label class="form-check-label" for="notDismissed">Not Out</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>Innings Played</label>
                            <div class="form-check">
                                <input type="radio" name="inningPlayed" value="true" class="form-check-input" id="firstInning">
                                <label class="form-check-label" for="firstInning">Yes</label>
                            </div>
                            <div class="form-check">
                                <input type="radio" name="inningPlayed" value="false" class="form-check-input" id="secondInning">
                                <label class="form-check-label" for="secondInning">No</label>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>

            <!-- Fielding Section -->
            <div class="card mb-4">
                <div class="card-header bg-info text-white">
                    Fielding Section
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Catches Taken</label>
                                <input type="text" name="catchTaken" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Stumping</label>
                                <input type="text" name="stumping" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Direct Runout</label>
                                <input type="text" name="directRunout" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Indirect Runout</label>
                                <input type="text" name="inDirectRunout" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Impact Player</label>
                                <div class="form-check">
                                    <input type="radio" name="isImpactPlayer" value="true" class="form-check-input" id="impactYes">
                                    <label class="form-check-label" for="impactYes">Yes</label>
                                </div>
                                <div class="form-check">
                                    <input type="radio" name="isImpactPlayer" value="false" class="form-check-input" id="impactNo">
                                    <label class="form-check-label" for="impactNo">No</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Bowling Section -->
            <div class="card mb-4">
                <div class="card-header bg-warning">
                    Bowling Section
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Overs</label>
                                <input type="text" step="0.1" name="overs" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Runs Conceded</label>
                                <input type="text" name="runsConceded" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Economy Rate</label>
                                <input type="text" step="0.01" name="economyRate" class="form-control" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                   
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Total Wickets</label>
                                <input type="text" name="totalWickets" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Bowled/LBW</label>
                                <input type="text" name="bowledLbw" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Dot Balls</label>
                                <input type="text" name="dots" class="form-control" />
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Maiden Overs</label>
                                <input type="text" name="maiden" class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary btn-lg">Save Statistics</button>
            </div>
        </form>
    </div>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
    <!-- Add these before closing </body> tag, in this specific order -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/addStats.js"></script>
    
    </body>
    </html>

