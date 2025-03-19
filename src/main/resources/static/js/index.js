document.getElementById('contactForm').addEventListener('submit', function(e) {
        e.preventDefault();

        const formData = {
            name: document.getElementById('name').value,
            email: document.getElementById('email').value,
            subject: document.getElementById('subject').value,
            message: document.getElementById('message').value
        };

        fetch('/contact/send', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Thank you for your message. We will get back to you soon!');
                this.reset();
            } else {
                alert('There was an error sending your message. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('There was an error sending your message. Please try again.');
        });
    });

document.addEventListener('DOMContentLoaded', function() {
    const welcomePopup = document.getElementById('welcomePopup');
    const closeButton = document.getElementById('closeWelcomePopup');
    const startExploringBtn = document.getElementById('startExploringBtn');
    const timerDisplay = document.getElementById('popupTimer');
    let timeLeft = 30;
    let timerId = null;

    function showPopup() {
        welcomePopup.style.display = 'block';
        startTimer();
    }

    function closePopup() {
        welcomePopup.style.display = 'none';
        if (timerId) {
            clearInterval(timerId);
        }
    }

    function startTimer() {
        timerId = setInterval(() => {
            timeLeft--;
            timerDisplay.textContent = timeLeft;

            if (timeLeft <= 0) {
                closePopup();
            }
        }, 1000);
    }

    // Show popup when page loads
    setTimeout(showPopup, 500);

    // Close popup when close button is clicked
    closeButton.addEventListener('click', closePopup);

    // Close popup when Start Exploring button is clicked
    startExploringBtn.addEventListener('click', function() {
        closePopup();
        // Get the latest match number from the page
        const matchId = document.getElementById('latestMatchId')?.value || '1'; // Default to match 1 if not found
        window.location.href = `/dreamTeam/${matchId}`;
    });

    // Close popup when clicking outside
    welcomePopup.addEventListener('click', function(e) {
        if (e.target === welcomePopup) {
            closePopup();
        }
    });
});