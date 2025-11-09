# SRM-Hostel-Outpass-System-
📘 Overview

The SRM Hostel Outpass System is a web-based application designed to digitalize and simplify the process of applying for and approving hostel outpasses.
This project aims to reduce manual paperwork, improve efficiency, and create a transparent workflow between students, hostel office superintendents (HoS), and administrators.

💡 Features
👨‍🎓 Student Portal

Register and log in using SRM Email ID

Apply for outpass requests by specifying reason and date range

View live status of applied outpasses (Pending / Approved / Rejected)

Clean dashboard with color-coded status cards

🧑‍💼 Admin / HoS Portal

View all pending outpass requests

Approve or reject requests with one click

Automatically update student dashboard status

🌐 General Features

Responsive SRM-style UI (works on mobile and laptop)

Local storage–based data handling for demo purposes

Can be easily connected to a backend (Java JDBC / Node.js / Firebase)

🧰 Tech Stack
Layer	Technology Used
Frontend	HTML5, CSS3, JavaScript
Database (Demo)	Browser LocalStorage
Design Language	SRM Academia Theme (Blue & White)
Responsive Design	Custom CSS Media Queries (No Frameworks)
🏗️ Folder Structure
Hostel_Outpass_System/
│
├── index.html                # Landing page with portal options
├── register.html             # Student registration page
├── login.html                # Student login page
├── student-dashboard.html    # Student dashboard (apply & view outpass)
├── admin-dashboard.html      # Admin/HoS dashboard (approve/reject)
├── style.css                 # SRM-themed responsive stylesheet
└── script.js                 # Handles registration, login & outpass logic

⚙️ How It Works

Student registers using their SRM email ID.

Login with the same credentials.

Apply for an outpass with reason, start date, and end date.

Admin/HoS dashboard displays pending requests for approval/rejection.

The student dashboard instantly reflects updated status.

🚀 Future Enhancements

Integration with SRM Academia Portal

Email notifications for outpass approval/rejection

QR-based gate verification system

Secure authentication using JWT or Firebase Auth

Database migration from LocalStorage → MySQL / MongoDB

🧑‍💻 Contributors

Karthick Raja K – Frontend Development & UI Design

Sri Koushik JK – Backend Development (Java JDBC Integration)

🏫 Institution

SRM Institute of Science and Technology (Kattankulathur Campus)
Department of Computer Science and Engineering – Internet of Things

📜 License

This project is developed for academic purposes under the SRM University coursework.
Free to use and modify for learning and educational demonstrations.
