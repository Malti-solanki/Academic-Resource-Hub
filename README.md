# 📚 Academic Resource Hub

A full-stack web application designed to provide students with a **centralized platform** to access academic resources like Notes, Previous Year Question Papers (PYQs), and Syllabus in a structured and organized way.

---

## 🚀 Features

* 🔐 **User Authentication (JWT Based)**
* 👨‍🎓 **Role-Based Access (Admin & Student)**
* 📂 **Upload Academic Resources (Admin)**
* 📥 **Download & View Resources (Students)**
* 🏫 **Branch & Semester-wise Organization**
* 📊 **Dashboard for Easy Navigation**
* 🗂️ **Subject-wise Resource Filtering**
* ⚡ **Fast REST API Integration**

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* MySQL

### Frontend

* HTML
* CSS
* JavaScript
* Bootstrap
* Axios / Fetch API

---

## 📁 Project Structure

```
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
└── database
    └── schema.sql
```

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository

```
git clone https://github.com/your-username/academic-resource-hub.git
cd academic-resource-hub
```

### 2️⃣ Backend Setup (Spring Boot)

* Open project in IntelliJ / Eclipse
* Configure MySQL database in `application.properties`

Example:

```
spring.datasource.url=jdbc:mysql://localhost:3306/academic_hub
spring.datasource.username=root
spring.datasource.password=your_password
```

* Run the Spring Boot application

Server will start at:

```
http://localhost:8080
```

---

### 3️⃣ Frontend Setup

* Open frontend folder
* Run using Live Server OR open `index.html`

---

## 🔐 Authentication Flow (JWT)

1. User registers or logs in
2. Backend validates credentials
3. JWT token is generated
4. Token is stored in browser (localStorage)
5. Token is sent in API headers for protected routes

Example Header:

```
Authorization: Bearer <token>
```

---

## 📡 API Endpoints

### Auth APIs

* POST `/api/auth/register`
* POST `/api/auth/login`

### Admin APIs

* POST `/api/admin/create`
* POST `/api/resource/upload`
* DELETE `/api/resource/{id}`

### User APIs

* GET `/api/resource/all`
* GET `/api/resource/{id}`

---

## 👨‍💻 User Roles

### Admin

* Manage users
* Upload/delete resources
* Manage branches, semesters, subjects

### Student

* Register & login
* Browse resources
* Download study materials

---

## 🗄️ Database Design (Overview)

Main Tables:

* User
* Role
* Branch
* Semester
* Subject
* Resource

Relationships:

* Branch → Semester → Subject → Resource
* User → Role

---

## 🧪 Testing

* API Testing using Postman
* Manual UI Testing
* Authentication & Authorization testing

---

## 📸 Screenshots

(Add your screenshots here)

* Home Page
* Login Page
* Dashboard
* Resource Page

---

## 🚧 Limitations

* No mobile application
* Basic UI design
* Limited search functionality

---

## 🔮 Future Enhancements

* 📱 Mobile App (Android/iOS)
* 🔍 Advanced Search & Filters
* 🤖 AI-based Recommendation System
* 💬 Chat System for Students
* 📊 Analytics Dashboard

---

## 🙌 Contribution

Contributions are welcome! Feel free to fork the repository and submit a pull request.

---

## 📄 License

This project is developed for educational purposes.

---

## 👤 Author

**Malti Solanki**
B.Tech CSE (2025-26)
Jawaharlal Institute of Technology, Borawan

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
