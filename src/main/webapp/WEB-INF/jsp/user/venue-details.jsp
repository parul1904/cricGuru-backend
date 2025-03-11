<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Venue Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/venue-details.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <div class="container mt-4">
        <div class="venue-card">
            <div class="row">
                <div class="col-md-6">
                    <div class="venue-image">
                        <img src="${venue.venueImageUrl}" alt="${venue.venueName}" class="img-fluid">
                    </div>
                </div>
                <div class="col-md-6">
                    <table style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); border: 2px solid hsl(39 96% 54%);">
                        <tr>
                            <td align="center" colspan="2"><h2 class="venue-name">${venue.venueName}</h2></td>
                        </tr>
                        <tr>
                            <td align="center">${venue.city}, ${venue.country}</td>
                        </tr>
                        <tr>
                            <td align="center">Capacity: ${venue.capacity}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div class="stat-section mt-4">
            <h3>Batting Statistics</h3>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Matches</th>
                            <th scope="col">Innings</th>
                            <th scope="col">Runs</th>
                            <th scope="col">Balls</th>
                            <th scope="col">4s</th>
                            <th scope="col">6s</th>
                            <th scope="col">S/R</th>
                            <th scope="col">Avg</th>
                            <th scope="col">50s</th>
                            <th scope="col">100s</th>
                            <th scope="col">Best</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td data-label="Matches"><span class="stat-value"><c:out value="${statsDetails.matchesPlayed}"/></span></td>
                            <td data-label="Innings"><span class="stat-value"><c:out value="${statsDetails.battingInnsPlayed}"/></span></td>
                            <td data-label="Runs"><span class="stat-value"><c:out value="${statsDetails.runsScored}"/></span></td>
                            <td data-label="Balls"><span class="stat-value"><c:out value="${statsDetails.ballFaced}"/></span></td>
                            <td data-label="4s"><span class="stat-value"><c:out value="${statsDetails.fours}"/></span></td>
                            <td data-label="6s"><span class="stat-value"><c:out value="${statsDetails.sixes}"/></span></td>
                            <td data-label="S/R"><span class="stat-value"><c:out value="${statsDetails.strikeRate != null ? String.format('%.2f', statsDetails.strikeRate) : ''}"/></span></td>
                            <td data-label="Avg"><span class="stat-value"><c:out value="${statsDetails.battingAverage != null ? String.format('%.2f', statsDetails.battingAverage) : ''}"/></span></td>
                            <td data-label="50s"><span class="stat-value"><c:out value="${statsDetails.halfCentury}"/></span></td>
                            <td data-label="100s"><span class="stat-value"><c:out value="${statsDetails.century}"/></span></td>
                            <td data-label="Best"><span class="stat-value"><c:out value="${statsDetails.bestScore}"/></span></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="stat-section mt-4">
            <h3>Bowling Statistics</h3>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Innings</th>
                            <th scope="col">Overs</th>
                            <th scope="col">Wickets</th>
                            <th scope="col">Runs</th>
                            <th scope="col">Dots</th>
                            <th scope="col">Maidens</th>
                            <th scope="col">Avg</th>
                            <th scope="col">Econ</th>
                            <th scope="col">3W+</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td data-label="Innings"><span class="stat-value"><c:out value="${statsDetails.bowlingInnsPlayed}"/></span></td>
                            <td data-label="Overs"><span class="stat-value"><c:out value="${statsDetails.overs}"/></span></td>
                            <td data-label="Wickets"><span class="stat-value"><c:out value="${statsDetails.totalWickets}"/></span></td>
                            <td data-label="Runs"><span class="stat-value"><c:out value="${statsDetails.runsConceded}"/></span></td>
                            <td data-label="Dots"><span class="stat-value"><c:out value="${statsDetails.dots}"/></span></td>
                            <td data-label="Maidens"><span class="stat-value"><c:out value="${statsDetails.maidens}"/></span></td>
                            <td data-label="Avg"><span class="stat-value"><c:out value="${statsDetails.bowlingAverage != null ? String.format('%.2f', statsDetails.bowlingAverage) : ''}"/></span></td>
                            <td data-label="Econ"><span class="stat-value"><c:out value="${statsDetails.economyRate != null ? String.format('%.2f', statsDetails.economyRate) : ''}"/></span></td>
                            <td data-label="3W+"><span class="stat-value"><c:out value="${statsDetails.threeWicketHauls}"/></span></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="stat-section mt-4">
            <h3>Fielding Statistics</h3>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Catches</th>
                            <th scope="col">Stumpings</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td data-label="Catches"><span class="stat-value"><c:out value="${statsDetails.catchTaken}"/></span></td>
                            <td data-label="Stumpings"><span class="stat-value"><c:out value="${statsDetails.stumping}"/></span></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

    <!-- Scripts -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/count-up.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/tiny-slider.js"></script>
</body>
</html>