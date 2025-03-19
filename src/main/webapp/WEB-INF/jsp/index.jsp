<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CricGuru - Your Ultimate IPL 2025 Analysis Platform</title>
    
    <!-- Favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
    
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    <div class="welcome-popup" id="welcomePopup">
        <div class="popup-content">
            <span class="close-popup" id="closeWelcomePopup">&times;</span>
            <div class="popup-header">
                <h3>Welcome to CricGuru! 🏏</h3>
            </div>
            <div class="popup-body">
                <p>Your ultimate destination for IPL 2025 analysis and predictions.</p>
                
                <div class="next-match-container">
                    <h4>Next Match</h4>
                    <div class="teams-vs">
                        <div class="team">
                            <img src="${pageContext.request.contextPath}${nextMatch.team1}" 
                                 alt="${nextMatch.team1Name}" 
                                 class="team-logo">
                            <span class="team-name">${nextMatch.team1Name}</span>
                        </div>
                        <div class="vs-badge">VS</div>
                        <div class="team">
                            <img src="${pageContext.request.contextPath}${nextMatch.team2}" 
                                 alt="${nextMatch.team2Name}" 
                                 class="team-logo">
                            <span class="team-name">${nextMatch.team2Name}</span>
                        </div>
                    </div>
                    <div class="match-info">
                        <p class="match-datetime">
                            <i class="fas fa-calendar"></i> ${nextMatch.matchDate}
                            <i class="fas fa-clock ml-2"></i> ${nextMatch.matchTime}
                        </p>
                        <p class="match-venue">
                            <i class="fas fa-map-marker-alt"></i> ${nextMatch.venueName}
                        </p>
                    </div>
                </div>

                <div class="popup-timer">
                    <span id="popupTimer">10</span>
                </div>
                <button class="btn btn-primary mt-3" id="startExploringBtn">Explore Dream Team</button>
            </div>
        </div>
    </div>
    <!-- Hero Section -->
    <section class="hero-section">
        <div class="cricket-illustration">
            <div class="cricket-ball"></div>
            <div class="cricket-bat">
                <div class="bat-handle"></div>
                <div class="bat-blade"></div>
            </div>
            <div class="stats-overlay">
                <div class="stat-line"></div>
                <div class="stat-line"></div>
                <div class="stat-line"></div>
            </div>
        </div>

        <div class="container position-relative">
            <div class="row align-items-center">
                <div class="col-lg-6">
                    <div class="hero-content">
                        <h1 class="animated fadeInUp">Welcome to CricGuru</h1>
                        <h2 class="animated fadeInUp">Your Ultimate IPL Analysis Platform</h2>
                        <p class="animated fadeInUp">
                            Get real-time match predictions, player statistics, and expert analysis
                            for IPL 2025. Make informed decisions with our advanced AI-powered insights.
                        </p>
                        <div class="hero-buttons animated fadeInUp">
                            <a href="${pageContext.request.contextPath}/matches/fixture" class="btn btn-primary">View Fixtures</a>
                            <a href="${pageContext.request.contextPath}/compare" class="btn btn-outline-primary ms-3">Compare Players</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Live/Upcoming Matches Section -->
    <section class="matches-section py-5">
        <div class="container">
            <h3 class="section-title text-center mb-4">Live/Upcoming Matches</h3>
            <h5 class="section-subtitle text-center mb-5">Click to view match predictions and stats</h5>
            <div class="row">
                <c:choose>
                    <c:when test="${empty liveMatches}">
                        <div class="col-12 text-center">
                            <div class="alert alert-info">
                                No live or upcoming matches at the moment.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${liveMatches}" var="match">
                            <div class="col-md-4 mb-4">
                                <a href="${pageContext.request.contextPath}/dreamTeam/${match.matchId}"
                                   class="match-card-link">
                                    <div class="match-card card shadow-sm">
                                        <div class="card-body">
                                            <div class="match-header mb-3">
                                                <span class="match-number">Match ${match.matchNo}</span>
                                                <span class="match-status ${match.matchStatus == 'Live' ? 'status-live' : 'status-upcoming'}">
                                                    ${match.matchStatus}
                                                </span>
                                            </div>
                                            <div class="team-vs-team">
                                                <!-- Team 1 -->
                                                <div class="team team-1">
                                                    <img src="${pageContext.request.contextPath}${match.team1}"
                                                         alt="${match.team1Name}" class="team-logo">
                                                    <h5 class="team-name">${match.team1Name}</h5>
                                                </div>

                                                <div class="vs-badge">VS</div>

                                                <!-- Team 2 -->
                                                <div class="team team-2">
                                                    <img src="${pageContext.request.contextPath}${match.team2}"
                                                         alt="${match.team2Name}" class="team-logo">
                                                    <h5 class="team-name">${match.team2Name}</h5>
                                                </div>
                                            </div>
                                            <div class="match-details mt-3">
                                                <p class="venue">${match.venueName}</p>
                                                <p class="match-time">
                                                    ${match.matchDate} ${match.matchTime}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>

<!-- Stats Highlight Section -->
    <section class="quick-stats-section py-4">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <div class="quick-stat-item">
                        <i class="lni lni-stats-up"></i>
                        <h4>15+</h4>
                        <p>Teams Analyzed</p>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="quick-stat-item">
                        <i class="lni lni-target"></i>
                        <h4>95%</h4>
                        <p>Prediction Accuracy</p>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="quick-stat-item">
                        <i class="lni lni-graph"></i>
                        <h4>1000+</h4>
                        <p>Matches Analyzed</p>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="quick-stat-item">
                        <i class="lni lni-crown"></i>
                        <h4>10+</h4>
                        <p>Expert Analysts</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Contact Form Section -->
    <section class="contact-section py-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="section-title text-center mb-5">
                        <h2 class="contact-heading">Get in Touch</h2>
                        <div class="heading-underline"></div>
                        <p class="contact-subtitle">Have questions? We'd love to hear from you.</p>
                    </div>
                    <div class="contact-card">
                        <div id="messageStatus" class="alert" style="display: none;"></div>
                        <form id="contactForm" class="contact-form">
                            <div class="row">
                                <div class="col-md-6 form-group">
                                    <div class="input-wrapper">
                                        <i class="fas fa-user input-icon"></i>
                                        <input type="text" class="form-control custom-input" id="name" name="name" 
                                               placeholder="Your Name" required>
                                    </div>
                                </div>
                                <div class="col-md-6 form-group">
                                    <div class="input-wrapper">
                                        <i class="fas fa-envelope input-icon"></i>
                                        <input type="email" class="form-control custom-input" id="email" name="email" 
                                               placeholder="Your Email" required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-wrapper">
                                    <i class="fas fa-heading input-icon"></i>
                                    <input type="text" class="form-control custom-input" id="subject" name="subject" 
                                           placeholder="Subject" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-wrapper">
                                    <i class="fas fa-comment-alt input-icon"></i>
                                    <textarea class="form-control custom-input" id="message" name="message" rows="5" 
                                              placeholder="Your Message" required></textarea>
                                </div>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn custom-button">
                                    <span>Send Message</span>
                                    <i class="fas fa-paper-plane"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

       <!-- Footer -->
       <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

       <!-- Scripts -->
       <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
       <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
       <script src="${pageContext.request.contextPath}/js/count-up.min.js"></script>
       <script src="${pageContext.request.contextPath}/js/waypoints.min.js"></script>
       <script src="${pageContext.request.contextPath}/js/jquery.counterup.min.js"></script>
       <script src="${pageContext.request.contextPath}/js/glightbox.min.js"></script>
       <script src="${pageContext.request.contextPath}/js/tiny-slider.js"></script>
       <script src="${pageContext.request.contextPath}/js/main.js"></script>
       <script src="${pageContext.request.contextPath}/js/index.js"></script>
       <input type="hidden" id="latestMatchId" value="${nextMatch.matchId}">
</body>
</html>