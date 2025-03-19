<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/jsp/includes/meta-tags.jsp" />
    <title>Privacy Notice - CricGuru</title>
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
                <li>Opt-out