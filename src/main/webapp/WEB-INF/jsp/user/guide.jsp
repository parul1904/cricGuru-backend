<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <jsp:include page="/WEB-INF/jsp/includes/meta-tags.jsp" />
    <title>User Guide - CricGuru</title>
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
    <style>
        .guide-section {
            margin-bottom: 2rem;
        }
        .guide-section h2 {
            color: #2c3e50;
            margin-bottom: 1rem;
        }
        .guide-section h3 {
            color: #34495e;
            margin: 1rem 0;
        }
        .feature-list {
            padding-left: 1.5rem;
        }
        .sub-feature-list {
            padding-left: 2rem;
            list-style-type: circle;
        }
        .highlight-box {
            background: #f8f9fa;
            border-left: 4px solid #007bff;
            padding: 1rem;
            margin: 1rem 0;
        }
        .pro-tip {
            background: #e8f4ff;
            border-radius: 5px;
            padding: 1rem;
            margin: 1rem 0;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <div class="container my-5">
        <h1>📱 CricGuru - Your IPL 2025 Fantasy Helper</h1>
        <p class="lead">A Comprehensive Step-by-Step Guide</p>

        <div class="guide-section">
            <h2>1. Homepage (cricguru.in) 🏠</h2>
            <ul class="feature-list">
                <li>Welcome popup shows latest match details</li>
                <li>Quick access buttons for Fixtures and Player Comparison</li>
                <li>Live/upcoming matches section with predictions</li>
                <li>Easy navigation menu at the top</li>
            </ul>
        </div>

        <div class="guide-section">
            <h2>2. Main Features 🎯</h2>
            
            <h3>A. Match Fixtures</h3>
            <ul class="feature-list">
                <li>Click "View Fixtures" on homepage</li>
                <li>See complete IPL 2025 schedule</li>
                <li>Each match shows:
                    <ul class="sub-feature-list">
                        <li>Teams playing</li>
                        <li>Venue details</li>
                        <li>Match timing</li>
                        <li>Prediction status</li>
                    </ul>
                </li>
            </ul>

            <!-- Continue with sections B, C, D similarly -->
        </div>

        <div class="guide-section">
            <h2>3. Special Features ⭐</h2>
            <!-- Dream Team Analysis and Performance Check sections -->
        </div>

        <div class="guide-section">
            <h2>4. Stay Updated 📱</h2>
            <!-- Regular Updates and Social Media sections -->
        </div>

        <div class="guide-section">
            <h2>5. Pro Tips 💡</h2>
            <div class="pro-tip">
                <ul class="feature-list">
                    <li>Visit 2-3 hours before match for final predictions</li>
                    <li>Check venue stats before making teams</li>
                    <li>Use player comparison for close calls</li>
                    <li>Always review recent form in Performance Check</li>
                    <li>Follow our Instagram for last-minute changes</li>
                </ul>
            </div>
        </div>

        <!-- Continue with remaining sections -->

        <div class="highlight-box">
            <h3>Remember:</h3>
            <ul class="feature-list">
                <li>✅ Predictions are based on data analysis</li>
                <li>✅ Regular updates before every match</li>
                <li>✅ Easy to navigate on both mobile and desktop</li>
                <li>✅ Free access to all basic features</li>
                <li>✅ Reliable statistics and analysis</li>
            </ul>
        </div>

        <div class="guide-section">
            <h2>Need Help?</h2>
            <ul class="feature-list">
                <li>Contact form available on homepage</li>
                <li>Direct messages on Instagram</li>
                <li>Regular FAQs updated on website</li>
            </ul>
        </div>
    </div>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
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
</body>
</html>