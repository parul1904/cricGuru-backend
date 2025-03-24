<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Team Roles - CricGuru</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Site Icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" />
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/images/apple-touch-icon.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/select2-bootstrap-theme@0.1.0-beta.10/dist/select2-bootstrap.min.css" rel="stylesheet" />

    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.5.0/css/responsive.bootstrap5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageTeamRoles.css">
</head>
<body>
    <!-- Include Header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h1>Manage Team Roles</h1>
                <p class="subtitle">Update player roles and playing status</p>
            </div>

            <div class="team-selection">
                <div class="dropdown-container">
                    <div class="select-wrapper">
                        <label for="team1Dropdown">Select Team</label>
                        <select id="team1Dropdown" onchange="loadTeamPlayers(1)">
                            <option value="">Choose a team...</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="teams-container">
                <div class="team-players" id="team1Players">
                    <div class="team-header">
                        <h3>Team Players</h3>
                        <div class="legend">
                            <span class="legend-item">
                                <span class="dot playing11"></span> Captain
                            </span>
                            <span class="legend-item">
                                <span class="dot captain"></span> Captain
                            </span>
                            <span class="legend-item">
                                <span class="dot vice-captain"></span> Vice Captain
                            </span>
                            <span class="legend-item">
                                <span class="dot playing"></span> Playing 15
                            </span>
                        </div>
                    </div>
                    <div class="players-list" id="team1PlayersList"></div>
                </div>
            </div>

            <div class="actions">
                <button onclick="saveTeamRoles()" class="save-button">
                    <span class="button-icon">💾</span>
                    Save Changes
                </button>
            </div>
        </div>
    </main>

    <!-- Include Footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>

    <!-- ALL JS FILES -->
    <script src="${pageContext.request.contextPath}/js/all.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/count-up.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/tiny-slider.js"></script>
    <script src="${pageContext.request.contextPath}/js/glightbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/imagesloaded.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/isotope.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.5.0/js/responsive.bootstrap5.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/manageTeamRoles.js"></script>
</body>
</html>