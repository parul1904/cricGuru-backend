<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Privacy Policy - CricGuru</title>
    
    <!-- Include your common CSS files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-pages.css">
    <meta name="keywords" content="CricGuru privacy policy, cricket platform privacy, IPL analysis data protection, cricket statistics privacy, user data protection, cricket analytics security">
    <meta name="description" content="CricGuru's privacy policy explaining how we collect, use, and protect your data while using our IPL analysis and prediction services. Learn about our commitment to user privacy and data security.">
    <meta name="author" content="Parul Gangwal">
</head>
<body>
    <jsp:include page="../includes/header.jsp" />

    <div class="page-wrapper">
        <div class="page-container">
            <div class="container my-5">
                <h1>Privacy Notice</h1>
                <p class="lead">Last updated: <%= new java.text.SimpleDateFormat("MMMM d, yyyy").format(new java.util.Date()) %></p>

                <div class="privacy-section">
                    <h2>1. Information We Collect</h2>
                    <h3>1.1 Information You Provide</h3>
                    <ul>
                        <li>Contact information (name, email address)</li>
                        <li>User preferences and settings</li>
                        <li>Feedback and correspondence</li>
                        <li>Social media handles (optional)</li>
                    </ul>

                    <h3>1.2 Automatically Collected Information</h3>
                    <ul>
                        <li>Device information (browser type, IP address)</li>
                        <li>Usage data (pages visited, time spent)</li>
                        <li>Location data (country, region)</li>
                        <li>Cookies and similar technologies</li>
                    </ul>

                    <h2>2. How We Use Your Information</h2>
                    <p>We use the collected information to:</p>
                    <ul>
                        <li>Provide and improve our services</li>
                        <li>Send notifications about matches and predictions</li>
                        <li>Respond to your inquiries</li>
                        <li>Analyze and enhance user experience</li>
                        <li>Prevent fraud and abuse</li>
                    </ul>

                    <h2>3. Information Sharing</h2>
                    <p>We do not sell your personal information. We may share data with:</p>
                    <ul>
                        <li>Service providers helping us operate our platform</li>
                        <li>Analytics partners</li>
                        <li>Law enforcement when required by law</li>
                    </ul>

                    <h2>4. Data Security</h2>
                    <p>We implement appropriate security measures to protect your information, including:</p>
                    <ul>
                        <li>Encryption of data in transit</li>
                        <li>Regular security assessments</li>
                        <li>Limited access to personal information</li>
                        <li>Secure data storage</li>
                    </ul>

                    <h2>5. Your Rights</h2>
                    <p>You have the right to:</p>
                    <ul>
                        <li>Access your personal information</li>
                        <li>Correct inaccurate data</li>
                        <li>Request deletion of your data</li>
                        <li>Opt-out of communications</li>
                    </ul>
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