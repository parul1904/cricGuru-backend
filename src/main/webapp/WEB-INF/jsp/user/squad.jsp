<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String pageName = "squads";
%>
<!DOCTYPE html>
<html lang="en">
<head>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/squad.css" />
   <!-- font family -->
   <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
   <!-- end font family -->
   <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
   <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
</head>
<body class="game_info" data-spy="scroll" data-target=".header">
<!-- ========================= header start ========================= -->
<jsp:include page="/WEB-INF/jsp/includes/header.jsp" />

<!-- ========================= header end ========================= -->

    <!-- Start Breadcrumbs -->
    <div class="breadcrumbs" data-stellar-background-ratio="0.5">
      <div class="container">
          <div class="row">
              <div class="col-lg-6 col-md-6 col-12">
                  <div class="breadcrumbs-content left">
                      <h1 class="page-title">IPL 2025 Squad</h1>
                  </div>
              </div>
         </div>
      </div>
  </div>
  <!-- End Breadcrumbs -->
  </section>
   <section id="contant" class="contant main-heading team">
         <div class="container">
            <div class="team-logos" id="teamLogos">
                <c:forEach var="team" items="${teams}" varStatus="status">
                    <c:if test="${status.index != fn:length(teams) - 1}">
                        <div class="team-logo">
                            <img class="img-responsive" src="${team.teamLogoUrl}" alt="${team.teamName}" data-team-id="${team.teamId}">
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div id="teamDetails" class="team-details row" style="display: none;">
               <!-- Team details will be dynamically loaded here -->
            </div>
            <div class="category-buttons hidden" id="categoryButtons">
               <button class="category-button" data-category="All">All</button>
               <button class="category-button" data-category="Batters">Batters</button>
               <button class="category-button" data-category="Wicket Keepers">Wicket Keepers</button>
               <button class="category-button" data-category="All Rounders">All Rounders</button>
               <button class="category-button" data-category="Bowlers">Bowlers</button>
               <button class="category-button" data-category="Staff">Staff</button>
            </div>
            <div id="squadContainer" class="row">
               <!-- Squad members will be dynamically loaded here -->
            </div>
         </div>
      </section>
   <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
   <!-- ALL JS FILES -->
   <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/count-up.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/glightbox.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/imagesloaded.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/isotope.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/main.js"></script>
   <script src="${pageContext.request.contextPath}/js/squad.js"></script>
   <!-- ALL PLUGINS -->
</body>
</html>