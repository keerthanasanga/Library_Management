const api="http://localhost:8080/api/books";

async function loadBooks(){

const response=await fetch(api);

const books=await response.json();

const table=document.getElementById("bookTable");

table.innerHTML="";

books.forEach(book=>{

if(book.availableQuantity>0){

table.innerHTML+=`

<tr>

<td>${book.bookId}</td>

<td>${book.title}</td>

<td>${book.author}</td>

<td>${book.category}</td>

<td>${book.availableQuantity}</td>

<td>

<button onclick="borrowBook(${book.bookId})">

Borrow

</button>

</td>

</tr>

`;

}

});

}

async function searchBook(){

const keyword=document.getElementById("search").value;

const response=await fetch(api+"/search?keyword="+encodeURIComponent(keyword));

const books=await response.json();

const table=document.getElementById("bookTable");

table.innerHTML="";

books.forEach(book=>{

if(book.availableQuantity>0){

table.innerHTML+=`

<tr>

<td>${book.bookId}</td>

<td>${book.title}</td>

<td>${book.author}</td>

<td>${book.category}</td>

<td>${book.availableQuantity}</td>

<td>

<button onclick="borrowBook(${book.bookId})">

Borrow

</button>

</td>

</tr>

`;

}

});

}

function borrowBook(id){

window.location.href="borrow.html?bookId="+id;

}

loadBooks();