async function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;

    const response = await fetch("http://localhost:8080/api/login", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            email,
            password,
            role
        })

    });

    const data = await response.json();

    if (data.success) {

        if (role === "admin") {

            window.location.href = "admin-dashboard.html";

        } else {

            window.location.href = "student-dashboard.html";

        }

    } else {

        alert(data.message);

    }

}