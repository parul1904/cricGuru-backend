<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String pageName = "listStats";
%>
<!DOCTYPE html>
<html lang="en">
<head>
   <!-- Basic -->
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
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tiny-slider.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/glightbox.min.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
   <link href="https://cdn.jsdelivr.net/npm/select2-bootstrap-theme@0.1.0-beta.10/dist/select2-bootstrap.min.css" rel="stylesheet" />

   <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
   <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.5.0/css/responsive.bootstrap5.min.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/listStats.css" />
</head>
<body class="game_info" data-spy="scroll" data-target=".header">
    <!-- ========================= header start ========================= -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <div class="container mt-4">
        <h2 class="text-center mb-4">List Statistics</h2>
        
        <div class="mb-3">
            <a href="/admin/cricguru/add-stats" class="btn btn-primary">
                Add Statistics
            </a>
        </div>

        <div class="card">
            <div class="card-body">
                <table id="statsTable" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                    <thead>
                        <tr>
                            <th>Season</th>
                            <th>Match No.</th>
                            <th>Match</th>
                            <th>Match Date</th>
                            <th>Player</th>
                            <th>Dream11 Points</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${allStats}" var="stat">
                            <tr>
                                <td>${stat.seasonYear}</td>
                                <td>${stat.matchNo}</td>
                                <td class="team-match-cell">
                                    <img src="../${stat.team1}" alt="Team 1" class="img-thumbnail" style="max-width: 50px"/>
                                    <span class="vs-text">VS</span>
                                    <img src="../${stat.team2}" alt="Team 2" class="img-thumbnail" style="max-width: 50px"/>
                                </td>
                                <td>${stat.matchDate}</td>
                                <td>${stat.playerName}</td>
                                <td>${stat.dream11Points}</td>
                                <td class="action-icons">
                                    <button class="btn btn-sm" onclick="updateStats(${stat.id})" title="Edit">
                                        <i class="fas fa-edit">Edit</i>
                                    </button>
                                    <button class="btn btn-sm" onclick="removeStats(${stat.id})" title="Delete">
                                        <i class="fas fa-trash-alt">Delete</i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

    <!-- jQuery -->


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
<script src="${pageContext.request.contextPath}/js/listStats.js"></script>

</body>
</html>
