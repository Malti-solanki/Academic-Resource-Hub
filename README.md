📚 Academic Resource Hub

A full-stack intelligent web application designed to provide students with a centralized platform to access academic resources like Notes, Previous Year Question Papers (PYQs), Syllabus, and an AI-powered learning assistant in a structured and organized way.

🚀 Features
🔐 Core Features
🔐 User Authentication (JWT Based)
👨‍🎓 Role-Based Access Control (Admin & Student)
📂 Upload Academic Resources (Admin)
📥 Download & View Resources (Students)
🏫 Branch & Semester-wise Organization
📊 Interactive Dashboard for Easy Navigation
🗂️ Subject-wise Resource Filtering
⚡ Fast REST API Integration

🤖 AI-Powered Features
🧠 AI Study Assistant (Ask subject-related questions)
📄 Smart Notes Summarization (Auto summary generation)
🔍 Intelligent Search (Context-based results)
📚 Personalized Study Recommendations
💬 AI Chat Support for Doubt Solving

🛠️ Tech Stack
Backend
Java
Spring Boot
Spring Security
JWT Authentication
MySQL
Frontend
HTML5
CSS3
JavaScript
Bootstrap
Axios / Fetch API

🤖 AI Integration
AI API (OpenAI / Custom LLM-ready integration)
REST-based AI microservice/module

📁 Project Structure

academic-resource-hub
│
├── backend (Spring Boot)
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── config
│   └── security (JWT)
│
├── frontend
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── css/
│   ├── js/
│   └── assets/
│
├── ai-module
│   ├── ai-controller
│   ├── ai-service
│   └── prompt-engine/
│
└── database
    └── schema.sql
    
⚙️ Installation & Setup
1️⃣ Clone the Repository
git clone https://github.com/your-username/academic-resource-hub.git
cd academic-resource-hub

2️⃣ Backend Setup (Spring Boot)
Open project in IntelliJ / Eclipse
Configure MySQL database in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/academic_hub
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080

Run backend server:

http://localhost:8080
3️⃣ Frontend Setup
Open frontend/ folder
Run using Live Server OR open index.html

🤖 AI Module Workflow
User asks a question / selects AI feature
Frontend sends request to backend AI API
Backend processes request via AI service
AI generates response (answer / summary / recommendation)
Response displayed in UI in real-time

📡 AI API Endpoints
POST /api/ai/ask
POST /api/ai/summarize
POST /api/ai/recommend

🔐 Authentication Flow (JWT)
User registers or logs in
Backend validates credentials
JWT token is generated
Token stored in browser (localStorage)
Token sent in API headers for protected routes
Authorization: Bearer <token>

📡 API Endpoints
🔐 Auth APIs
POST /api/auth/register
POST /api/auth/login

👨‍💻 Admin APIs
POST /api/admin/create
POST /api/resource/upload
DELETE /api/resource/{id}

👨‍🎓 User APIs
GET /api/resource/all
GET /api/resource/{id}

🤖 AI APIs
POST /api/ai/ask
POST /api/ai/summarize
POST /api/ai/recommend

👨‍💻 User Roles
🛠️ Admin
Manage users
Upload/delete resources
Manage branches, semesters, subjects
Control content moderation

🎓 Student
Register & login
Browse study materials
Download resources
Use AI learning assistant

🗄️ Database Design (Overview)
Main Tables
User
Role
Branch
Semester
Subject
Resource
AI_Interaction_Log
Relationships
Branch → Semester → Subject → Resource
User → Role
User → AI_Interaction_Log

🧪 Testing
API Testing using Postman
Manual UI Testing
JWT Authentication Testing
Role-based Access Testing
📸 Screenshots

(Add screenshots here)

🏠 Home Page
🔐 Login Page
📊 Dashboard
📂 Resource Page
🤖 AI Assistant UI
🚧 Limitations
No mobile application
Basic UI design (can be improved with React)
Limited advanced search system
🔮 Future Enhancements
📱 Mobile App (Android/iOS)
🔍 Advanced AI Search Engine
🤖 AI Chatbot with Voice Input
💬 Real-time Student Chat System
📊 Analytics Dashboard for Admin
🧠 Smart Exam Preparation Mode
🙌 Contribution

Contributions are welcome!

Fork the repository → Create branch → Commit changes → Open Pull Request

📄 License

This project is developed for educational purposes only.

👤 Author

Malti Solanki
🎓 B.Tech CSE (2025–26)
🏫 Jawaharlal Institute of Technology, Borawan

⭐ Support
If you like this project, don’t forget to star ⭐ the repository.
