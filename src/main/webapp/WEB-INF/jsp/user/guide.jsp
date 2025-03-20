<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Guide - CricGuru</title>
    
    <!-- Include your common CSS files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LineIcons.2.0.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-pages.css">
    
    <style>
        .guide-section {
            margin-bottom: 2rem;
            padding: 1.5rem;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
        }
        
        .guide-section h2 {
            color: #2c3e50;
            margin-bottom: 1.5rem;
            font-weight: 600;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }
        
        .guide-section h3 {
            color: #34495e;
            margin: 1.2rem 0;
            font-weight: 500;
        }
        
        .feature-list {
            padding-left: 1.5rem;
            margin-bottom: 1rem;
        }
        
        .feature-list li {
            margin-bottom: 0.8rem;
            line-height: 1.6;
        }
        
        .sub-feature-list {
            padding-left: 2rem;
            list-style-type: circle;
            margin-top: 0.5rem;
        }
        
        .highlight-box {
            background: #f8f9fa;
            border-left: 4px solid #007bff;
            padding: 1.5rem;
            margin: 1.5rem 0;
            border-radius: 0 8px 8px 0;
        }
        
        .pro-tip {
            background: #e8f4ff;
            border-radius: 8px;
            padding: 1.5rem;
            margin: 1.5rem 0;
            border: 1px solid #cce5ff;
        }
        
        .page-header {
            text-align: center;
            margin-bottom: 3rem;
        }
        
        .page-header h1 {
            font-size: 2.5rem;
            color: #2c3e50;
            margin-bottom: 1rem;
        }
        
        .page-header .lead {
            font-size: 1.2rem;
            color: #666;
        }

        /* Responsive styles */
        @media (max-width: 768px) {
            .page-container {
                padding: 15px;
            }

            .guide-section {
                padding: 1rem;
                margin-bottom: 1.5rem;
            }

            .page-header h1 {
                font-size: 1.8rem;
            }

            .page-header .lead {
                font-size: 1rem;
            }

            .guide-section h2 {
                font-size: 1.4rem;
                margin-bottom: 1rem;
            }

            .guide-section h3 {
                font-size: 1.2rem;
            }

            .feature-list {
                padding-left: 1rem;
            }

            .feature-list li {
                margin-bottom: 0.6rem;
                font-size: 0.95rem;
            }

            .sub-feature-list {
                padding-left: 1.5rem;
            }

            .highlight-box,
            .pro-tip {
                padding: 1rem;
                margin: 1rem 0;
            }
        }

        @media (max-width: 480px) {
            .page-header h1 {
                font-size: 1.5rem;
            }

            .guide-section h2 {
                font-size: 1.2rem;
            }

            .guide-section h3 {
                font-size: 1.1rem;
            }

            .feature-list li {
                font-size: 0.9rem;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="../includes/header.jsp" />

    <div class="page-wrapper">
        <div class="page-container">
            <div class="page-header">
                <h1>📱 CricGuru - Your IPL 2025 Fantasy Helper</h1>
                <p class="lead">A Comprehensive Step-by-Step Guide</p>
            </div>

            <div class="page-content">
                <div class="guide-section">
                    <h2>1. Homepage (cricguru.in) 🏠</h2>
                    <ul class="feature-list">
                        <li>Welcome popup shows latest match details</li>
                        <li>Quick access buttons for Fixtures and Player Comparison</li>
                        <li>Live/upcoming matches section with predictions</li>
                        <li>Easy navigation menu at the top</li>
                    </ul>
                </div>

                <div class="guide-section">
                    <h2>2. Main Features 🎯</h2>
                    
                    <h3>A. Match Fixtures</h3>
                    <ul class="feature-list">
                        <li>Click "View Fixtures" on homepage</li>
                        <li>See complete IPL 2025 schedule</li>
                        <li>Each match shows:
                            <ul class="sub-feature-list">
                                <li>Teams playing</li>
                                <li>Venue details</li>
                                <li>Match timing</li>
                                <li>Prediction status</li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <div class="guide-section">
                    <h2>3. Special Features ⭐</h2>
                    <div class="pro-tip">
                        <ul class="feature-list">
                            <li>Dream Team Analysis with AI-powered insights</li>
                            <li>Performance Check with detailed statistics</li>
                            <li>Head-to-head comparisons</li>
                            <li>Venue-specific predictions</li>
                        </ul>
                    </div>
                </div>

                <div class="guide-section">
                    <h2>4. Stay Updated 📱</h2>
                    <ul class="feature-list">
                        <li>Enable notifications for match updates</li>
                        <li>Follow our social media channels</li>
                        <li>Check the news section daily</li>
                    </ul>
                </div>

                <div class="guide-section">
                    <h2>5. Pro Tips 💡</h2>
                    <div class="pro-tip">
                        <ul class="feature-list">
                            <li>Visit 2-3 hours before match for final predictions</li>
                            <li>Check venue stats before making teams</li>
                            <li>Use player comparison for close calls</li>
                            <li>Always review recent form in Performance Check</li>
                            <li>Follow our Instagram for last-minute changes</li>
                        </ul>
                    </div>
                </div>

                <!-- Continue with remaining sections -->

                <div class="highlight-box">
                    <h3>Remember:</h3>
                    <ul class="feature-list">
                        <li>✅ Predictions are based on data analysis</li>
                        <li>✅ Regular updates before every match</li>
                        <li>✅ Easy to navigate on both mobile and desktop</li>
                        <li>✅ Free access to all basic features</li>
                        <li>✅ Reliable statistics and analysis</li>
                    </ul>
                </div>

                <div class="guide-section">
                    <h2>Need Help?</h2>
                    <ul class="feature-list">
                        <li>Contact form available on homepage</li>
                        <li>Direct messages on Instagram</li>
                        <li>Regular FAQs updated on website</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>