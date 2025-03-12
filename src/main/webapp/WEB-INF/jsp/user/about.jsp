<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String pageName = "about";
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
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />

    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
   <link href="https://cdn.jsdelivr.net/npm/select2-bootstrap-theme@0.1.0-beta.10/dist/select2-bootstrap.min.css" rel="stylesheet" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css" />
</head>
<body class="game_info" data-spy="scroll" data-target=".header">
    <!-- ========================= header start ========================= -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <div class="about-wrapper">
        <div class="about-container container card">
            <h1 class="about-title text-center">Your Ultimate IPL 2025 Stats Analysis Hub</h1>

            <p class="about-text lead">
                Welcome to <span class="highlight-text">Cric Guru</span>, your
                go-to platform for expert analysis and insights to help you build the
                best possible team on various betting apps during IPL 2025. We are
                redefining the way you analyze cricket matches with our
                <span class="highlight-text">
                    data-driven insights, expert analysis, and strategic team-building
                    recommendations
                </span>.
            </p>

            <h2 class="about-heading h4">What We Do</h2>
            <p class="about-text">
                With the fast-paced nature of the IPL, staying ahead of the
                competition requires more than just gut feeling. 
                <span class="highlight-text">
                    Winning isn't just about luck — it's about strategy. That's where we
                    come in!
                </span>
                We analyze player form, pitch conditions, matchups, head-to-head
                stats, and real-time trends to give you winning strategies for every
                match.
            </p>

            <h2 class="about-heading h4">Our Vision</h2>
            <p class="about-text">
                We aim to empower users with the best cricketing insights so they can
                make smart, strategic decisions in their fantasy leagues and betting
                platforms. Whether you're a casual player or a seasoned pro, we've got
                you covered!
            </p>

            <h2 class="about-heading h4">What Sets Us Apart?</h2>
            <ul class="about-list list-unstyled">
                <li class="d-flex align-items-center">
                    <span class="check-icon me-2">🏏</span>
                    <strong>In-Depth Match Insights</strong> – Get access to pitch
                    reports, head-to-head records, and performance trends.
                </li>
                <li class="d-flex align-items-center">
                    <span class="check-icon me-2">🏏</span>
                    <strong>Data-Backed Predictions</strong> – Our algorithms analyze
                    past performances to predict potential outcomes.
                </li>
                <li class="d-flex align-items-center">
                    <span class="check-icon me-2">🏏</span>
                    <strong>Real-Time Updates</strong> – Stay ahead with live news on
                    player injuries, last-minute changes, and form analysis.
                </li>
                <li class="d-flex align-items-center">
                    <span class="check-icon me-2">🏏</span>
                    <strong>Expert-Curated Strategies</strong> – Hand-picked
                    recommendations to help you maximize your chances of success.
                </li>
            </ul>

            <h2 class="about-heading h4">Our Approach</h2>
            <p class="about-text">
                We blend <strong>technology and cricket expertise</strong> to deliver
                analysis that is both <strong>accurate and easy to understand</strong>.
                Whether you're a seasoned bettor or just starting out, our platform
                ensures you have the <strong>right data at the right time</strong>.
            </p>

            <h2 class="about-heading h4">Join the Winning Team</h2>
            <p class="about-text">
                With IPL 2025 set to be an action-packed tournament, don't leave your
                game to chance. <br />
                <span class="highlight-text">
                    Leverage our insights, sharpen your strategy, and make every match
                    count!
                </span>
            </p>

            <p class="about-footer text-center">
                🔥🏏 Stay connected for daily match analysis, expert predictions, and
                the latest cricket trends. Let's make this IPL season a winning one!
                🏏🔥
            </p>
        </div>
    </div>

    <!-- Include Footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>