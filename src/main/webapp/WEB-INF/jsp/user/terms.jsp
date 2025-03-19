<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/jsp/includes/meta-tags.jsp" />
    <title>Terms and Conditions - CricGuru</title>
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
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

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
            <p>CricGuru and its team shall not be liable for any indirect, incidental, special, or consequential damages resulting from the use or inability to use our services.</p>

            <h2>9. Account Termination</h2>
            <p>We reserve the right to terminate or suspend access to our service immediately, without prior notice, for conduct that we believe violates these terms or is harmful to other users or us.</p>

            <h2>10. Changes to Terms</h2>
            <p>We reserve the right to modify these terms at any time. We will notify users of any changes by updating the date at the top of this page.</p>

            <h2>11. Governing Law</h2>
            <p>These terms shall be governed by and construed in accordance with the laws of India.</p>

            <h2>12. Contact Information</h2>
            <p>For any questions about these Terms and Conditions, please contact us at:</p>
            <ul>
                <li>Email: help.cricguru@gmail.com</li>
                <li>Instagram: @cricguru.in</li>
            </ul>
        </div>
    </div>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>