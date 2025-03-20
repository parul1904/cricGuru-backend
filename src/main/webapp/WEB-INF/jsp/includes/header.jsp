<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- Site Metas -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<!-- Favicon -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/images/apple-touch-icon.png">
<!-- CSS -->
<!-- Site Icons -->
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />

<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">

<header class="header-area">
    <div class="main-navbar">
        <div class="container">
            <nav class="navbar navbar-expand-lg">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                    <img src="${pageContext.request.contextPath}/images/logo1.png" alt="CricGuru Logo" class="main-logo">
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMain">
                    <span class="toggler-icon"></span>
                    <span class="toggler-icon"></span>
                    <span class="toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarMain">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'home' ? 'active' : ''}" href="${pageContext.request.contextPath}/">
                                <i class="fas fa-home"></i> Home
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'home' ? 'active' : ''}" href="${pageContext.request.contextPath}/about">
                                <i class="fas fa-info"></i> About
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'squads' ? 'active' : ''}" href="${pageContext.request.contextPath}/squads">
                                <i class="fas fa-users"></i> Squad
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'performance' ? 'active' : ''}" href="${pageContext.request.contextPath}/compare">
                                <i class="fas fa-chart-line"></i> Performance Check
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'venue' ? 'active' : ''}" href="${pageContext.request.contextPath}/venues">
                                <i class="fas fa-map-marker-alt"></i> Venue Stats
                            </a>
                        </li>
                    </ul>

                </div>
            </nav>
        </div>
    </div>
</header>
