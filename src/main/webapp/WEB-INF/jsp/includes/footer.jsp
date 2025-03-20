<footer class="footer">
    <div class="footer-top">
        <div class="container">
            <div class="row g-4">
                <!-- Company Info - Full width on mobile -->
                <div class="col-12">
                    <div class="footer-info text-center text-md-start">
                        <h3>CricGuru</h3>
                        <p>
                            Your Ultimate Cricket Analytics Platform
                            <br>
                            Making Fantasy Cricket Smarter
                        </p>
                        <div class="social-links">
                            <a href="https://www.instagram.com/cricguru.in/?hl=en" target="_blank" class="instagram"><i class="fab fa-instagram"></i></a>
                            <a href="https://www.youtube.com/@CricGuruExpert" target="_blank" class="youtube"><i class="fab fa-youtube"></i></a>
                        </div>
                    </div>
                </div>

                <!-- Two columns layout for mobile -->
                <div class="col-6 col-md-3">
                    <div class="footer-links">
                        <h4>Quick Links</h4>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/"><i class="fas fa-chevron-right"></i>Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/about"><i class="fas fa-chevron-right"></i>About</a></li>
                            <li><a href="${pageContext.request.contextPath}/guide"><i class="fas fa-chevron-right"></i>User Manual</a></li>
                            <li><a href="${pageContext.request.contextPath}/privacy"><i class="fas fa-chevron-right"></i>Privacy Notice</a></li>
                            <li><a href="${pageContext.request.contextPath}/terms"><i class="fas fa-chevron-right"></i>Terms & Conditions</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-6 col-md-3">
                    <div class="footer-contact">
                        <h4>Contact Us</h4>
                        <p>
                            <i class="fas fa-envelope"></i>help.cricguru@gmail.com<br>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>

<div class="footer-bottom">
    <div class="container">
        <div class="copyright">
            &copy; <span id="currentYear"></span> <strong>CricGuru</strong>. All Rights Reserved
        </div>
    </div>
</div>

<script>
    document.getElementById('currentYear').textContent = new Date().getFullYear();
</script>