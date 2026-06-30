📚 Academic Resource Hub

A full-stack web application designed to provide students with a centralized platform to access academic resources like Notes, Previous Year Question Papers (PYQs), and Syllabus in a structured way.

🚀 Features
🔐 User Authentication (JWT Based)
👨‍🎓 Role-Based Access Control (Admin & Student)
📂 Upload Academic Resources (Admin)
📥 Download & View Resources (Students)
🏫 Branch & Semester-wise Organization
📊 Dashboard for Easy Navigation
🗂️ Subject-wise Resource Filtering
🤖 AI-powered Study Assistant (NEW)
⚡ REST API Integration

🤖 AI Features
🧠 Ask Doubts (AI Chat)
📄 Notes Summarization
🔍 Smart Search (Context-based)
📚 Personalized Recommendations

🛠️ Tech Stack
Backend
Java
Spring Boot
Spring Security
JWT
MySQL
Frontend
HTML
CSS
JavaScript
Bootstrap
Fetch API
AI Module
AI API (OpenAI / Custom LLM-ready)

📁 Project Structure
academic-resource-hub
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── config
│   └── security
│
├── frontend
│   ├── index.html
│   ├── login.html
│   ├── dashboard.html
│   ├── css
│   ├── js
│
├── ai-module
│   ├── ai-controller
│   ├── ai-service
│
└── database
    └── schema.sql

⚙️ Setup Instructions
1️⃣ Clone Repo
git clone https://github.com/your-username/academic-resource-hub.git
cd academic-resource-hub

2️⃣ Backend Setup
Open in IntelliJ / Eclipse
Configure MySQL in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/academic_hub
spring.datasource.username=root
spring.datasource.password=your_password
Run server:
http://localhost:8080

3️⃣ Frontend Setup
Open frontend/index.html
Run using Live Server

🤖 AI APIs
POST /api/ai/ask
POST /api/ai/summarize
POST /api/ai/recommend

🔐 Authentication Flow
User Login/Register
JWT Token Generate
Stored in localStorage
Sent in API Header
Authorization: Bearer <token>

👨‍💻 User Roles
Admin
Upload resources
Manage content
Manage users
Student
Access materials
Download notes
Use AI assistant

🗄️ Database
User
Role
Branch
Semester
Subject
Resource
AI Logs

🔮 Future Improvements
📱 Mobile App
🤖 Advanced AI Chatbot
💬 Real-time Chat System
📊 Analytics Dashboard
👤 Author

Malti Solanki
B.Tech CSE (2025-26)
Jawaharlal Institute of Technology, Borawan

⭐ Support

If you like this project, give it a ⭐ on GitHub.
