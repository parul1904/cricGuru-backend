<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Terms of Service - CricGuru</title>
    
    <!-- Include your common CSS files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-pages.css">
    <meta name="keywords" content="CricGuru terms, cricket analysis terms, IPL predictions terms, fantasy cricket guidelines, cricket platform policies, IPL analysis terms, cricket statistics usage">
    <meta name="description" content="Terms and conditions for using CricGuru's IPL analysis and prediction services. Learn about our policies, user responsibilities, and guidelines for using our cricket analytics platform.">
    <meta name="author" content="Parul Gangwal">
</head>
<body>
    <jsp:include page="../includes/header.jsp" />

    <div class="page-wrapper">
        <div class="page-container">
            <div class="container my-5">
                <h1>Terms and Conditions</h1>
                <p class="lead">Last updated: <%= new java.text.SimpleDateFormat("MMMM d, yyyy").format(new java.util.Date()) %></p>

                <div class="terms-section">
                    <h2>1. Acceptance of Terms</h2>
                    <p>By accessing and using CricGuru (cricguru.in), you accept and agree to be bound by these Terms and Conditions. If you disagree with any part of these terms, you may not access our service.</p>

                    <h2>2. Service Description</h2>
                    <p>CricGuru provides cricket analysis, predictions, and statistics for entertainment and informational purposes only. Our services include match predictions, player statistics, and fantasy cricket suggestions.</p>

                    <h2>3. User Responsibilities</h2>
                    <ul>
                        <li>You must be at least 18 years old to use our services</li>
                        <li>You are responsible for any decisions made based on our analysis</li>
                        <li>You agree not to misuse or attempt to manipulate our services</li>
                        <li>You will not distribute or share premium content without authorization</li>
                    </ul>

                    <h2>4. Disclaimer</h2>
                    <p>CricGuru provides analysis and predictions based on available data and algorithms. We do not guarantee:</p>
                    <ul>
                        <li>The accuracy of predictions or analysis</li>
                        <li>Financial gains from following our suggestions</li>
                        <li>Continuous, uninterrupted access to our services</li>
                        <li>Success in fantasy leagues or betting platforms</li>
                    </ul>

                    <h2>5. Intellectual Property</h2>
                    <p>All content on CricGuru, including but not limited to text, graphics, logos, and analysis, is our intellectual property and protected by copyright laws.</p>

                    <h2>6. User Content</h2>
                    <p>By submitting content to CricGuru, you grant us the right to use, modify, and distribute that content on our platform.</p>

                    <h2>7. Third-Party Links</h2>
                    <p>Our service may contain links to third-party websites. We are not responsible for the content or practices of these sites.</p>

                    <h2>8. Limitation of Liability</h2>
                    <p>CricGuru and its team shall not be liable for any indirect, incidental, special, or consequential damages resulting
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
    
    <!-- Scripts -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>