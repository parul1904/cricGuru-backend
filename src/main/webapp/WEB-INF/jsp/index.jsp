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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

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
                            <a href="${pageContext.request.contextPath}/players/compare" class="btn btn-outline-primary ms-3">Compare Players</a>
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

    <style>
        .matches-section {
            background-color: #f8f9fa;
        }
        
        .match-card-link {
            text-decoration: none;
            color: inherit;
            display: block;
            cursor: pointer;
        }

        .match-card-link:hover {
            text-decoration: none;
            color: inherit;
        }

        .match-card {
            border-radius: 10px;
            transition: all 0.3s ease;
        }
        
        .match-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 15px rgba(0,0,0,0.1) !important;
        }
        
        .match-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .match-number {
            font-weight: bold;
            color: #666;
        }
        
        .match-status {
            padding: 4px 8px;
            border-radius: 4px;
            font-size: 0.9em;
        }
        
        .status-live {
            background-color: #dc3545;
            color: white;
        }
        
        .status-upcoming {
            background-color: #28a745;
            color: white;
        }
        
        .team-vs-team {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 20px 0;
        }
        
        .team {
            text-align: center;
            flex: 1;
        }
        
        .team-logo {
            width: 60px;
            height: 60px;
            object-fit: contain;
            margin-bottom: 10px;
        }
        
        .team-name {
            font-size: 1.1em;
            margin: 0;
            font-weight: 600;
        }
        
        .vs-badge {
            padding: 0 15px;
            font-weight: bold;
            color: #666;
        }
        
        .match-details {
            text-align: center;
            border-top: 1px solid #eee;
            padding-top: 15px;
        }
        
        .venue {
            margin-bottom: 5px;
            color: #666;
        }
        
        .match-time {
            font-weight: 600;
            color: #333;
        }
        
        .stat-card {
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        .stat-value {
            font-size: 2em;
            font-weight: bold;
            color: #2c3e50;
            margin-bottom: 10px;
        }
        
        .stat-label {
            color: #666;
            margin: 0;
            text-transform: uppercase;
            font-size: 0.9em;
        }

        .hero-section {
            padding: 80px 0;
            background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
            margin-bottom: 30px;
            position: relative;
            overflow: hidden;
            min-height: 500px;
        }

        .cricket-illustration {
            position: absolute;
            top: 0;
            right: 0;
            width: 100%;
            height: 100%;
            pointer-events: none;
            opacity: 0.1;
        }

        .cricket-ball {
            width: 60px;
            height: 60px;
            background: #e74c3c;
            border-radius: 50%;
            position: absolute;
            top: 40%;
            left: 60%;
            transform: translate(-50%, -50%);
            box-shadow: inset -5px -5px 15px rgba(0,0,0,0.3);
            animation: floatBall 3s ease-in-out infinite;
        }

        .cricket-bat {
            position: absolute;
            top: 50%;
            right: 20%;
            transform: translate(50%, -50%) rotate(45deg);
        }

        .bat-handle {
            width: 15px;
            height: 120px;
            background: linear-gradient(90deg, #8B4513, #A0522D);
            border-radius: 5px;
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
        }

        .bat-blade {
            width: 100px;
            height: 300px;
            background: linear-gradient(90deg, #DEB887, #D2691E);
            border-radius: 10px;
            position: absolute;
            top: 110px;
            left: 50%;
            transform: translateX(-50%);
        }

        .hero-content {
            position: relative;
            z-index: 2;
            padding-right: 30px;
        }

        .hero-content h1 {
            font-size: clamp(2.5rem, 5vw, 3.5rem);
            font-weight: 700;
            color: #2c3e50;
            margin-bottom: 20px;
            background: linear-gradient(45deg, #1a75ff, #00264d);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .hero-content h2 {
            font-size: clamp(1.5rem, 3vw, 2rem);
            color: #34495e;
            margin-bottom: 20px;
        }

        .hero-content p {
            font-size: clamp(1rem, 2vw, 1.1rem);
            color: #666;
            margin-bottom: 30px;
            line-height: 1.6;
        }

        .hero-buttons .btn {
            padding: 12px 30px;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .hero-image {
            position: relative;
        }

        .hero-image img {
            max-width: 100%;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }

        .quick-stats-section {
            background-color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .quick-stat-item {
            text-align: center;
            padding: 20px;
            transition: transform 0.3s ease;
        }

        .quick-stat-item:hover {
            transform: translateY(-5px);
        }

        .quick-stat-item i {
            font-size: 2.5rem;
            color: #1a75ff;
        }

        .stats-overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding: 20px;
        }

        .stat-line {
            height: 2px;
            background: rgba(255,255,255,0.1);
            margin: 10px 0;
            position: relative;
            overflow: hidden;
        }

        .stat-line::after {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 50%;
            height: 100%;
            background: rgba(255,255,255,0.3);
            animation: pulse 2s infinite;
        }

        @keyframes pulse {
            0% { transform: translateX(-100%); }
            100% { transform: translateX(200%); }
        }

        @keyframes floatBall {
            0%, 100% { transform: translate(-50%, -50%); }
            50% { transform: translate(-50%, -60%); }
        }

        /* Mobile Responsive Styles */
        @media (max-width: 768px) {
            .hero-section {
                min-height: 400px;
                padding: 40px 0;
            }

            .cricket-illustration {
                opacity: 0.05;
            }

            .hero-content {
                text-align: center;
                padding-right: 0;
            }

            .hero-buttons {
                display: flex;
                flex-direction: column;
                gap: 10px;
            }

            .hero-buttons .btn {
                width: 100%;
                margin: 0 !important;
            }

            .cricket-bat {
                right: 10%;
                transform: translate(50%, -50%) rotate(45deg) scale(0.8);
            }
        }
    </style>

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

    <!-- Contact Form Section -->
    <section class="contact-section py-5 bg-light">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="section-title text-center mb-5">
                        <h2>Get in Touch</h2>
                        <p>Have questions? We'd love to hear from you.</p>
                    </div>
                    <div class="card shadow-sm">
                        <div class="card-body p-4">
                            <form id="contactForm" class="contact-form">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <input type="text" class="form-control" id="name" name="name" 
                                               placeholder="Your Name" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <input type="email" class="form-control" id="email" name="email" 
                                               placeholder="Your Email" required>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" id="subject" name="subject" 
                                           placeholder="Subject" required>
                                </div>
                                <div class="mb-3">
                                    <textarea class="form-control" id="message" name="message" rows="5" 
                                              placeholder="Your Message" required></textarea>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary px-5">Send Message</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script>
    document.getElementById('contactForm').addEventListener('submit', function(e) {
        e.preventDefault();
        
        const formData = {
            name: document.getElementById('name').value,
            email: document.getElementById('email').value,
            subject: document.getElementById('subject').value,
            message: document.getElementById('message').value
        };

        fetch('${pageContext.request.contextPath}/contact/send', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Thank you for your message. We will get back to you soon!');
                this.reset();
            } else {
                alert('There was an error sending your message. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('There was an error sending your message. Please try again.');
        });
    });
    </script>
</body>
</html>
