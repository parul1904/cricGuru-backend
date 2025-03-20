<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="java.util.*" %>
            <% String pageName="fixture" ; %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <!-- Basic -->
                    <meta charset="utf-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <!-- Mobile Metas -->
                    <meta name="viewport"
                        content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
                    <!-- Site Metas -->
                    <title>CricGuru - Matches</title>
                    <meta name="keywords" content="IPL 2025 schedule, IPL 2024 matches, cricket fixtures, IPL match dates, IPL venues, cricket match timings, IPL teams, cricket match analysis, IPL match predictions, cricket statistics">
                    <meta name="description" content="View complete IPL 2024 and 2025 match schedules, fixtures, and timings. Get detailed match information including venue details, team lineups, and post-match analysis on CricGuru.">
                    <meta name="author" content="Parul Gangwal">
                    <!-- Site Icons -->
                    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico"
                        type="image/x-icon" />
                    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/images/apple-touch-icon.png">
                    <!-- Bootstrap CSS -->
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css" />
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css" />
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" /> 
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixture.css" />
                    <!-- Fonts -->
                    <link
                        href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
                        rel="stylesheet">
                </head>

                <body class="game_info" data-spy="scroll" data-target=".header">
                    <!-- Include Header -->
                    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

                    <!-- Start Breadcrumbs -->
    <div class="breadcrumbs">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-12">
                    <div class="breadcrumbs-content left">
                        <h1 class="page-title">Fixture</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Season Selector -->
    <div class="season-selector">
        <button class="season-option" data-season="2025">IPL 2025</button>
        <button class="season-option" data-season="2024">IPL 2024</button>
        <div class="slider"></div>
    </div>

    <!-- Matches Section -->
    <section class="matches-section">
        <div class="container">
            <c:forEach items="${matches2024}" var="match">
                <div class="match-card" data-season="2024">
                    <div class="match-header">
                        <div>
                            <span class="match-number">MATCH ${match.matchNo}</span>
                            <span class="match-date">${match.matchDate}</span>
                        </div>
                        <div class="match-info">
                            <span>🕒 ${match.matchTime} | 📍 ${match.venueName}</span>
                        </div>
                    </div>

                    <div class="teams-container">
                        <div class="team">
                            <img src="${match.team1}" />
                        </div>
                        <span class="vs-text"><img src="${pageContext.request.contextPath}/images/vs.png" width="75px;" height="75px;" alt="VS Badge"/></span>
                        <div class="team">
                            <img src="${match.team2}"/>
                        </div>
                    </div>

                    <c:if test="${not empty match.winnerTeam}">
                        <div class="stats-analysis-btn" data-match-id="${match.matchId}">
                            Stats Analysis
                        </div>
                    </c:if>

                    <div class="matches-footer">
                        <c:choose>
                            <c:when test="${not empty match.winnerTeam}">
                                <div class="match-result">
                                    <p>${match.winningMargin}</p>
                                    <c:if test="${not empty match.playerOfTheMatch}">
                                        <div class="match-player">
                                            <p>Player of the Match</p>
                                            <p>${match.playerOfTheMatch}</p>
                                        </div>
                                    </c:if>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="match-centre-btn" data-match-id="${match.matchId}">
                                    Match Centre
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>

            <c:forEach items="${matches2025}" var="match">
                <div class="match-card" data-season="2025">
                    <div class="match-header">
                        <div>
                            <span class="match-number">MATCH ${match.matchNo}</span>
                            <span class="match-date">${match.matchDate}</span>
                        </div>
                        <div class="match-info">
                            <span>🕒 ${match.matchTime} | 📍 ${match.venueName}</span>
                        </div>
                    </div>

                    <div class="teams-container">
                        <div class="team">
                            <img src="${match.team1}"/>
                        </div>
                        <span class="vs-text"><img src="${pageContext.request.contextPath}/images/vs.png" alt="VS Badge"/></span>
                        <div class="team">
                            <img src="${match.team2}"/>
                        </div>
                    </div>

                    <c:if test="${not empty match.winnerTeam}">
                        <div class="stats-analysis-btn" data-match-id="${match.matchId}">
                            Stats Analysis
                        </div>
                    </c:if>

                    <div class="matches-footer">
                        <c:choose>
                            <c:when test="${not empty match.winnerTeam}">
                                <div class="match-result">
                                    <p>${match.winningMargin}</p>
                                    <c:if test="${not empty match.playerOfTheMatch}">
                                        <div class="match-player">
                                            <p>Player of the Match</p>
                                            <p>${match.playerOfTheMatch}</p>
                                        </div>
                                    </c:if>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="match-centre-btn" data-match-id="${match.matchId}">
                                    Match Centre
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>

    <!-- Include Footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

                    <!-- Scripts -->
                    <script src="${pageContext.request.contextPath}/js/all.js"></script>
                    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/count-up.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/tiny-slider.js"></script>
                    <script src="${pageContext.request.contextPath}/js/glightbox.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/imagesloaded.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/isotope.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/main.js"></script>
                    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/fixture.js"></script>
                </body>

                </html>