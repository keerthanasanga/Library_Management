
if (!sessionStorage.getItem("userId")) {

    window.location.href = "login.html";

}

const api="http://localhost:8080/api/books";

document
.getElementById("bookForm")
.addEventListener("submit",addBook);

async function addBook(e){

e.preventDefault();

const book={

title:document.getElementById("title").value,

author:document.getElementById("author").value,

category:document.getElementById("category").value,

isbn:document.getElementById("isbn").value,

quantity:Number(document.getElementById("quantity").value),

shelfNo:document.getElementById("shelfNo").value

};

if(window.editId){

    await fetch(api+"/"+window.editId,{

        method:"PUT",

        headers:{

            "Content-Type":"application/json"

        },

        body:JSON.stringify(book)

    });

    window.editId=null;

}else{

    await fetch(api,{

        method:"POST",

        headers:{

            "Content-Type":"application/json"

        },

        body:JSON.stringify(book)

    });

}

document.getElementById("bookForm").reset();

loadBooks();

}

async function loadBooks(){

const response=await fetch(api);

const books=await response.json();

const table=document.getElementById("bookTable");

table.innerHTML="";

books.forEach(book=>{

table.innerHTML+=`

<tr>

<td>${book.bookId}</td>

<td>${book.title}</td>

<td>${book.author}</td>

<td>${book.category}</td>

<td>${book.quantity}</td>

<td>${book.availableQuantity}</td>

<td>

<button onclick="editBook(${book.bookId})">Edit</button>

<button onclick="deleteBook(${book.bookId})">Delete</button>

</td>

</tr>

`;

});

}

loadBooks();



function editBook(id){

    const row = event.target.parentElement.parentElement;

    document.getElementById("title").value =
    row.cells[1].innerText;

    document.getElementById("author").value =
    row.cells[2].innerText;

    document.getElementById("category").value =
    row.cells[3].innerText;

    document.getElementById("quantity").value =
    row.cells[4].innerText;

    window.editId=id;

}




async function deleteBook(id){

    if(confirm("Delete this book?")){

        await fetch(api+"/"+id,{

            method:"DELETE"

        });

        loadBooks();

    }

}



async function searchBook() {

    const keyword = document.getElementById("search").value;

    const response = await fetch(
        api + "/search?keyword=" + encodeURIComponent(keyword)
    );

    const books = await response.json();

    const table = document.getElementById("bookTable");

    table.innerHTML = "";

    books.forEach(book => {

        table.innerHTML += `
        <tr>

            <td>${book.bookId}</td>

            <td>${book.title}</td>

            <td>${book.author}</td>

            <td>${book.category}</td>

            <td>${book.quantity}</td>

            <td>${book.availableQuantity}</td>

            <td>

                <button onclick="editBook(${book.bookId})">Edit</button>

                <button onclick="deleteBook(${book.bookId})">Delete</button>

            </td>

        </tr>
        `;

    });

}