if (!sessionStorage.getItem("userId")) {

    window.location.href = "login.html";

}

const api = "http://localhost:8080/api/borrow";

const studentId = sessionStorage.getItem("userId");

async function loadBorrowedBooks(){

const response =
await fetch(api + "/" + studentId);

const borrows =
await response.json();

const table =
document.getElementById("borrowTable");

table.innerHTML="";

borrows.forEach(borrow=>{

table.innerHTML +=`

<tr>

<td>${borrow.borrowId}</td>

<td>${borrow.book.title}</td>

<td>${borrow.borrowDate}</td>

<td>${borrow.dueDate}</td>

<td>

<button onclick="returnBook(${borrow.borrowId})">

Return

</button>

</td>

</tr>

`;

});

}

async function returnBook(id){

await fetch(

api+"/return/"+id,

{

method:"PUT"

}

);

alert("Book Returned Successfully");

loadBorrowedBooks();

}

loadBorrowedBooks();