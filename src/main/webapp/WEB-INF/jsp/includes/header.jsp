<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'home' ? 'active' : ''}" href="${pageContext.request.contextPath}/">
                                <i class="fas fa-home"></i> Home
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'about' ? 'active' : ''}" href="${pageContext.request.contextPath}/about">
                                <i class="fas fa-info-circle"></i> About
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

                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'contact' ? 'active' : ''}" href="${pageContext.request.contextPath}/contact">
                                <i class="fas fa-envelope"></i> Contact
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</header>
