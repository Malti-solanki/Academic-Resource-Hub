document.getElementById("loginForm")?.addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch(`${BASE_URL}/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        });

        // ✅ Read response ONLY ONCE
        const text = await response.text();

        let data;
        try {
            data = JSON.parse(text);
        } catch {
            data = {};
        }

        if (response.ok) {
            // ✅ Save token + role
            localStorage.setItem("token", data.token);
            localStorage.setItem("role", data.role);

            // ✅ Redirect based on role
            if (data.role === "ADMIN") {
                window.location.href = "admin-dashboard.html";
            } else {
                window.location.href = "student-dashboard.html";
            }

        } else {
            alert("Error: " + (data.message || text));
        }

    } catch (error) {
        console.error(error);
        alert("Server error");
    }
});





document.getElementById("registerForm")?.addEventListener("submit", async (e) => {
    e.preventDefault();

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch(`${BASE_URL}/auth/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name, email, password })
        });

        const data = await response.json();

        if (response.ok) {
            alert("Registration Successful ✅");
            window.location.href = "login.html";
        } else {
            alert(data.message || "Registration failed ❌");
        }

    } catch (error) {
        console.error(error);
        alert("Server error");
    }
});