<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Your existing head content -->
</head>
<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/jsp/includes/header.jsp" />
    
    <!-- Main Content Wrapper -->
    <div class="content-wrapper">
        <!-- Your page content -->
        <jsp:doBody/>
    </div>
    
    <!-- Footer -->
    <jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>