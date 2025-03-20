(function () {

	"use strict";

	//===== Prealoder

	window.onload = function () {
		window.setTimeout(fadeout, 200);
	}

	function fadeout() {
		document.querySelector('.preloader').style.opacity = '0';
		document.querySelector('.preloader').style.display = 'none';
	}

	// Sticky navbar handler
	function handleScroll() {
		var header_navbar = document.querySelector(".navbar-area");
		var sticky = header_navbar.offsetTop;
		var backToTo = document.querySelector(".scroll-top");

		if (window.pageYOffset > sticky) {
			header_navbar.classList.add("sticky");
		} else {
			header_navbar.classList.remove("sticky");
		}

		if (backToTo) {
			if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
				backToTo.style.display = "block";
			} else {
				backToTo.style.display = "none";
			}
		}
	}

	window.onscroll = handleScroll;

	//===== back to top
	document.querySelector('.scroll-top').onclick = function () {
		scrollTo(document.documentElement);
	}

	// Mobile Navigation Handler
	document.addEventListener('DOMContentLoaded', function () {
		const navbarToggler = document.querySelector(".navbar-toggler");
		const navbarCollapse = document.querySelector(".navbar-collapse");

		if (!navbarToggler || !navbarCollapse) return;

		// Toggle menu on button click
		navbarToggler.addEventListener('click', function (e) {
			e.preventDefault();
			e.stopPropagation();

			this.classList.toggle("active");
			navbarCollapse.classList.toggle("show");
		});

		// Close menu when clicking outside
		document.addEventListener('click', function (e) {
			if (!navbarCollapse.contains(e.target) && !navbarToggler.contains(e.target)) {
				if (navbarCollapse.classList.contains('show')) {
					navbarCollapse.classList.remove('show');
					navbarToggler.classList.remove("active");
				}
			}
		});

		// Close menu when clicking nav links
		const navLinks = document.querySelectorAll('.navbar-nav .nav-link');
		navLinks.forEach(link => {
			link.addEventListener('click', () => {
				navbarCollapse.classList.remove('show');
				navbarToggler.classList.remove("active");
			});
		});
	});

})();

// ====== scroll top js
window.onscroll = function () {
        var header_navbar = document.querySelector(".navbar-area");
        var sticky = header_navbar.offsetTop;
        if (window.pageYOffset > sticky) {
            header_navbar.classList.add("sticky");
        } else {
            header_navbar.classList.remove("sticky");
        }
        var backToTo = document.querySelector(".scroll-top");
        if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
            backToTo.style.display = "block";
        } else {
            backToTo.style.display = "none";
        }
    };

Math.easeInOutQuad = function (t, b, c, d) {

	t /= d/2;
	if (t < 1) return c/2*t*t + b;
	t--;
	return -c/2 * (t*(t-2) - 1) + b;
};

document.querySelector('.scroll-top').onclick = function () {
	scrollTo(document.documentElement); 
}

