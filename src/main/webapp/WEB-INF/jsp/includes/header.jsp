<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
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

                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-info-circle"></i> More
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li>
                                    <a class="dropdown-item ${pageName == 'about' ? 'active' : ''}" href="${pageContext.request.contextPath}/about">
                                        <i class="fas fa-info-circle"></i> About Us
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item ${pageName == 'guide' ? 'active' : ''}" href="${pageContext.request.contextPath}/guide">
                                        <i class="fas fa-book"></i> User Guide
                                    </a>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <a class="dropdown-item ${pageName == 'privacy' ? 'active' : ''}" href="${pageContext.request.contextPath}/privacy">
                                        <i class="fas fa-shield-alt"></i> Privacy Policy
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item ${pageName == 'terms' ? 'active' : ''}" href="${pageContext.request.contextPath}/terms">
                                        <i class="fas fa-file-contract"></i> Terms of Service
                                    </a>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <a class="dropdown-item" href="#contact-form">
                                        <i class="fas fa-envelope"></i> Contact Us
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</header>