<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String pageName = "venues";
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
   <title>CricGuru - Venues</title>
    <meta name="keywords" content="IPL venues, cricket stadiums, IPL 2025 grounds, cricket pitch analysis, stadium statistics, IPL match venues, cricket ground conditions">
    <meta name="description" content="Explore detailed information about IPL venues including pitch reports, weather conditions, and historical statistics. Make informed decisions based on venue-specific cricket analysis.">
    <meta name="author" content="Parul Gangwal">
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
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/venue.css" />
   <!-- Font Family -->
   <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
</head>
<body class="game_info" data-spy="scroll" data-target=".header">
    <!-- Header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

    <!-- Start Breadcrumbs -->
    <div class="breadcrumbs" data-stellar-background-ratio="0.5">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-12">
                    <div class="breadcrumbs-content left">
                        <h1 class="page-title">IPL 2025 Venues</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Breadcrumbs -->

    <!-- Venue cards container -->
    <div class="container mt-4">
        <div class="row">
            <c:forEach items="${venues}" var="venue">
                <div class="col-lg-4 col-md-6 col-12 mb-4">
                    <a href="${pageContext.request.contextPath}/venues/details/${venue.venueId}" class="text-decoration-none">
                        <div class="venue-card" data-venue-id="${venue.venueId}">
                            <div class="venue-image">
                                <img src="${venue.venueImageUrl}" alt="${venue.venueName}" class="img-fluid">
                            </div>
                            <div class="venue-info my-2">
                                <h5 class="venue-name text-center">${venue.venueName}</h5>
                                <div class="venue-details">
                                    <p class="venue-city"><i class="fas fa-map-marker-alt me-2"></i>${venue.city}</p>
                                    <p><i class="fas fa-users me-2"></i>Capacity: ${venue.capacity}</p>
                                </div>

                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="venueDetailsModal" tabindex="-1" role="dialog" aria-labelledby="venueDetailsModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="venueDetailsModalLabel">Venue Details</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="venueDetailsContent"></div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />

    <!-- Scripts -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/count-up.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/tiny-slider.js"></script>
    <script src="${pageContext.request.contextPath}/js/glightbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/imagesloaded.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/isotope.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/venue.js"></script>
</body>
</html>