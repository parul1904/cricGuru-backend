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
                            <a class="nav-link ${pageName == 'about' ? 'active' : ''}" href="${pageContext.request.contextPath}/about">
                                <i class="fas fa-info-circle"></i> About Us
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'squads' ? 'active' : ''}" href="${pageContext.request.contextPath}/squads">
                                <i class="fas fa-users"></i> Squad
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${pageName == 'fixture' ? 'active' : ''}" href="${pageContext.request.contextPath}/matches/fixture">
                                <i class="fa-solid fa-calendar-days"></i> Fixtures
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