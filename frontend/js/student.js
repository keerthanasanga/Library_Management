function logout(){

    window.location.href="login.html";

}


if (!sessionStorage.getItem("userId")) {

    window.location.href = "login.html";

}

document.getElementById("studentName").innerText =
sessionStorage.getItem("userName");