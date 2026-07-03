
if (!sessionStorage.getItem("userId")) {

    window.location.href = "login.html";

}

document.getElementById("adminName").innerText =
sessionStorage.getItem("userName");

function logout(){

    window.location.href="login.html";

}

const api="http://localhost:8080/api/admin";

async function loadStats(){

const response=await fetch(api+"/stats");

const stats=await response.json();

document.getElementById("totalBooks").innerText=
stats.totalBooks;

document.getElementById("availableBooks").innerText=
stats.availableBooks;

document.getElementById("borrowedBooks").innerText=
stats.borrowedBooks;

document.getElementById("returnedBooks").innerText=
stats.returnedBooks;

document.getElementById("totalStudents").innerText=
stats.totalStudents;

document.getElementById("totalFine").innerText=
"₹"+stats.totalFine;

}

loadStats();