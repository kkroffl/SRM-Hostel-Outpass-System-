// ----------------------
// STUDENT REGISTRATION
// ----------------------
function registerStudent(name, email, password) {
  let students = JSON.parse(localStorage.getItem("students")) || [];

  // Check if email already exists
  const exists = students.find((s) => s.email === email);
  if (exists) {
    alert("Email already registered! Please login.");
    return false;
  }

  const newStudent = {
    id: Date.now(),
    name,
    email,
    password,
  };

  students.push(newStudent);
  localStorage.setItem("students", JSON.stringify(students));
  alert("Registration successful! You can now login.");
  return true;
}

// ----------------------
// STUDENT LOGIN
// ----------------------
function loginStudent(email, password) {
  const students = JSON.parse(localStorage.getItem("students")) || [];
  const student = students.find(
    (s) => s.email === email && s.password === password
  );

  if (student) {
    localStorage.setItem("loggedInStudent", JSON.stringify(student));
    alert("Login successful!");
    window.location.href = "student-dashboard.html";
  } else {
    alert("Invalid credentials!");
  }
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

  const requests = JSON.parse(localStorage.getItem("outpassRequests")) || [];

  const newRequest = {
    id: Date.now(),
    studentId: student.id,
    studentName: student.name,
    reason,
    fromDate,
    toDate,
    status: "Pending",
  };

  requests.push(newRequest);
  localStorage.setItem("outpassRequests", JSON.stringify(requests));

  alert("Outpass applied successfully!");
  displayStudentOutpasses();
}

function displayStudentOutpasses() {
  const student = JSON.parse(localStorage.getItem("loggedInStudent"));
  const container = document.getElementById("outpassCards");
  if (!container || !student) return;

  const requests = JSON.parse(localStorage.getItem("outpassRequests")) || [];
  const myRequests = requests.filter((r) => r.studentId === student.id);

  container.innerHTML = "";

  if (myRequests.length === 0) {
    container.innerHTML = "<p>No outpass requests yet.</p>";
    return;
  }

  myRequests.forEach((req) => {
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
}

if (window.location.pathname.includes("student-dashboard.html")) {
  displayStudentOutpasses();
}
