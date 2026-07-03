const api="http://localhost:8080/api/borrow";

const params=new URLSearchParams(window.location.search);

const bookId=params.get("bookId");

document.getElementById("bookId").innerHTML="Book ID : "+bookId;

async function borrowBook(){

const studentId = sessionStorage.getItem("userId");

const response=await fetch(

api+"?studentId="+studentId+"&bookId="+bookId,

{

method:"POST"

}

);

if(response.ok){

alert("Book Borrowed Successfully");

window.location.href="student-books.html";

}else{

const message=await response.text();

alert(message);

}

}