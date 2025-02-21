document.addEventListener("DOMContentLoaded", function () {

    // Session stuff
    const sessionTimeOutMinutes = 60;
    const warningMinutesBefore = 5;
    const sessionTimeOutMS = sessionTimeOutMinutes * 60 * 1000;
    const warningTimeMS = (sessionTimeOutMinutes - warningMinutesBefore) * 60 * 1000;


    // Function to show session warning
    function showSessionWarning() {
        const warningBox = document.createElement("div"); // make warning box div
        warningBox.id = "sessionWarning"; // give warning box an id
        warningBox.innerHTML = ` 
            <div style="position:fixed; top:10px; left:50%; transform:translateX(-50%); background-color:rgba(0,0,0,0.8); color:white; padding:15px; border-radius:5px;">
                Your session is about to expire!
                <button onclick="resetSessionTimer()">Stay Logged In</button>
            </div>`; // ` allows strings to continue for many lines without <br>
        document.body.appendChild(warningBox);
    }

    // Function to reset session timer (window is there because it needed to be global)
    window.resetSessionTimer = function() {
        clearTimeout(window.sessionWarningTimer); // gets rid of old timers
        clearTimeout(window.sessionTimeoutTimer); // gets rid of old timers
        window.sessionWarningTimer = setTimeout(showSessionWarning, warningTimeMS); // shows the warning message
        window.sessionTimeoutTimer = setTimeout(() => alert("Session expired! Please log in again."), sessionTimeOutMS); // shows expired message

        const warningBox = document.getElementById("sessionWarning"); // gets rid of expired message if it already appeared
        if (warningBox) {
            warningBox.remove();
        }
    }

    resetSessionTimer(); // resets timer when page loads
});
