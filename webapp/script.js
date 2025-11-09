// ----------------------
// STUDENT REGISTRATION
// ----------------------
function registerStudent(name, rId, email, password) {
    fetch("register", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `name=${encodeURIComponent(name)}&rId=${encodeURIComponent(rId)}&email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
    })
        .then((res) => res.text())
        .then((data) => {
            if (data === "exists") {
                alert("Email already registered! Please login.");
            } else if (data === "success") {
                alert("Registration successful! You can now login.");
                window.location.href = "login.html";
            } else {
                alert("Error registering student. Try again.");
            }
        })
        .catch(() => alert("Server error — check your connection or Tomcat status."));
}

// ----------------------
// STUDENT LOGIN
// ----------------------
function loginStudent(email, password) {
    fetch("login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
    })
        .then((res) => res.text())
        .then((data) => {
            if (data.startsWith("success")) {
                // The backend can return "success|name|rId|id"
                const parts = data.split("|");
                const student = {
                    id: parts[3],
                    rId: parts[2],
                    name: parts[1],
                    email,
                };
                localStorage.setItem("loggedInStudent", JSON.stringify(student));
                alert("Login successful!");
                window.location.href = "student-dashboard.html";
            } else {
                alert("Invalid credentials!");
            }
        })
        .catch(() => alert("Server error — check your connection or Tomcat status."));
}

// ----------------------
// LOGOUT
// ----------------------
function logoutStudent() {
    localStorage.removeItem("loggedInStudent");
    window.location.href = "index.html";
}

// ----------------------
// APPLY OUTPASS
// ----------------------
function applyOutpass(reason, fromDate, toDate) {
    const student = JSON.parse(localStorage.getItem("loggedInStudent"));
    if (!student) {
        alert("You must be logged in to apply for an outpass.");
        return;
    }

    fetch("applyOutpass", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `studentId=${student.id}&reason=${encodeURIComponent(reason)}&fromDate=${encodeURIComponent(fromDate)}&toDate=${encodeURIComponent(toDate)}`
    })
        .then((res) => res.text())
        .then((data) => {
            if (data === "success") {
                alert("Outpass applied successfully!");
                displayStudentOutpasses();
            } else {
                alert("Failed to apply for outpass. Try again.");
            }
        })
        .catch(() => alert("Server error — please check backend."));
}

// ----------------------
// DISPLAY STUDENT OUTPASSES
// ----------------------
function displayStudentOutpasses() {
    const student = JSON.parse(localStorage.getItem("loggedInStudent"));
    const container = document.getElementById("outpassCards");
    if (!container || !student) return;

    fetch(`displayOutpass?studentId=${student.id}`)
        .then((res) => res.json())
        .then((requests) => {
            container.innerHTML = "";

            if (requests.length === 0) {
                container.innerHTML = "<p>No outpass requests yet.</p>";
                return;
            }

            requests.forEach((req) => {
                const card = document.createElement("div");
                card.className = `card ${req.status.toLowerCase()}`;
                card.innerHTML = `
          <h3>${req.reason}</h3>
          <p><b>From:</b> ${req.fromDate}</p>
          <p><b>To:</b> ${req.toDate}</p>
          <p><b>Status:</b> ${req.status}</p>
        `;
                container.appendChild(card);
            });
        })
        .catch(() => {
            container.innerHTML = "<p>Error loading outpass records.</p>";
        });
}

// ----------------------
// AUTO DISPLAY ON DASHBOARD
// ----------------------
if (window.location.pathname.includes("student-dashboard.html")) {
    displayStudentOutpasses();
}
