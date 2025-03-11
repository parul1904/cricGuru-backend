<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<div class="header navbar-area">
   <div class="container">
       <div class="row align-items-center">
           <div class="col-lg-12">
           <div class="nav-inner">
               <nav class="navbar navbar-expand-lg">
                   <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">
                       <img src="${pageContext.request.contextPath}/images/logo1.png" alt="Logo">
                   </a>
                   <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                       data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                       aria-expanded="false" aria-label="Toggle navigation">
                       <span class="toggler-icon"></span>
                       <span class="toggler-icon"></span>
                       <span class="toggler-icon"></span>
                   </button>
                   <div class="collapse navbar-collapse sub-menu-bar" id="navbarSupportedContent">
                       <ul id="nav" class="navbar-nav ms-auto">
                           <li class="nav-item">
                               <a class="page-scroll ${pageName == 'home' ? 'active' : ''}" href="${pageContext.request.contextPath}/">Home</a>
                           </li>
                           <li class="nav-item">
                               <a class="page-scroll ${pageName == 'about' ? 'active' : ''}" href="${pageContext.request.contextPath}/about">About Us</a>
                           </li>
                           <li class="nav-item">
                               <a class="page-scroll ${pageName == 'squads' ? 'active' : ''}" href="${pageContext.request.contextPath}/squads">Squad</a>
                           </li>
                           <li class="nav-item">
                              <a class="page-scroll ${pageName == 'performance' ? 'active' : ''}" href="${pageContext.request.contextPath}/performance">Performance</a>
                          </li>
                          <li class="nav-item">
                            <a class="page-scroll ${pageName == 'venue' ? 'active' : ''}" href="${pageContext.request.contextPath}/venue">Venue Stats</a>
                          </li>
                           <li class="nav-item">
                               <a class="page-scroll ${pageName == 'todaysAnalysis' ? 'active' : ''}" href="javascript:void(0)">Todays' Analysis</a>
                           </li>
                           <li class="nav-item">
                               <a class="page-scroll ${pageName == 'pastAnalysis' ? 'active' : ''}" href="javascript:void(0)">Past Analysis</a>
                           </li>
                           <li class="nav-item">
                              <a class="page-scroll ${pageName == 'contact' ? 'active' : ''}" href="${pageContext.request.contextPath}/contact">Contact Us</a>
                            </li>
                       </ul>
                   </div> <!-- navbar collapse -->
               </nav> <!-- navbar -->
           </div>
           </div>
       </div> <!-- row -->
   </div> <!-- container -->
</div>