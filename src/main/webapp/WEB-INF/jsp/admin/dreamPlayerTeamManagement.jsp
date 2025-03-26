<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dream Player Team Management</title>
    
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-bootstrap-4@4/bootstrap-4.css">
    
    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        // Verify SweetAlert2 is loaded
        if (typeof Swal === 'undefined') {
            console.error('SweetAlert2 failed to load!');
        }
    </script>
    <script src="${pageContext.request.contextPath}/js/dreamPlayerTeamManagement.js"></script>
</head>
<body>
    <!-- Include Header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <div class="container mt-4">
        <!-- Alert container for notifications -->
        <div id="alertContainer"></div>

        <!-- Loading spinner -->
        <div id="loadingSpinner" style="display: none;">
            <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>

        <!-- Match selection -->
        <div class="form-group mb-4">
            <label for="matchSelect">Select Match:</label>
            <select id="matchSelect" class="form-control" required>
                <option value="">Select a match...</option>
                <c:forEach items="${matches}" var="match">
                    <option value="${match.matchNo}">${match.description}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Players table -->
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Player Name</th>
                        <th>Team</th>
                        <th>Role</th>
                        <th>Playing 11</th>
                        <th>Playing 15</th>
                        <th>Captain</th>
                        <th>Vice Captain</th>
                        <th>Dream Team</th>
                        <th>% Selection</th>
                    </tr>
                </thead>
                <tbody id="playersTableBody">
                    <c:forEach items="${players}" var="player">
                        <tr data-player-id="${player.playerId}">
                            <td>${player.playerName}</td>
                            <td>${player.teamShortName}</td>
                            <td>${player.playerRole}</td>
                            <td>
                                <input type="checkbox" class="form-check-input" 
                                       name="playing11_${player.playerId}"
                                       <c:if test="${player.playing11}">checked</c:if>>
                            </td>
                            <td>
                                <input type="checkbox" class="form-check-input" 
                                       name="playing15_${player.playerId}"
                                       <c:if test="${player.playing15}">checked</c:if>>
                            </td>
                            <td>
                                <input type="checkbox" class="form-check-input" 
                                       name="captain_${player.playerId}"
                                       <c:if test="${player.captain}">checked</c:if>>
                            </td>
                            <td>
                                <input type="checkbox" class="form-check-input" 
                                       name="viceCaptain_${player.playerId}"
                                       <c:if test="${player.viceCaptain}">checked</c:if>>
                            </td>
                            <td>
                                <input type="checkbox" class="form-check-input" 
                                       name="dreamTeam_${player.playerId}"
                                       <c:if test="${player.dreamTeam}">checked</c:if>>
                            </td>
                            <td>
                                <input type="text" class="form-control"
                                       name="selectionPercentage_${player.playerId}"
                                       value="${player.selectionPercentage}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="text-center mt-4">
            <button id="saveChangesBtn" class="btn btn-primary">
                <i class="fas fa-save"></i> Save Changes
            </button>
        </div>
    </div>

    <!-- Include Footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

    <!-- Scripts - Make sure these are at the end of body -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- SweetAlert2 JS -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/js/dreamPlayerTeamManagement.js"></script>
    <script src="${pageContext.request.contextPath}/js/dreamTeam.js"></script>
    <!-- Add this at the bottom of body for debugging -->
</body>
</html>