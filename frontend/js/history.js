
if (!sessionStorage.getItem("userId")) {

    window.location.href = "login.html";

}

const api = "http://localhost:8080/api/borrow";

const studentId = sessionStorage.getItem("userId");

async function loadHistory() {

    const response = await fetch(api + "/history/" + studentId);

    const history = await response.json();

    const table = document.getElementById("historyTable");

    table.innerHTML = "";

    history.forEach(record => {

        table.innerHTML += `
        <tr>

        <td>${record.book.title}</td>

        <td>${record.borrowDate}</td>

        <td>${record.dueDate}</td>

        <td>${record.returnDate ?? "-"}</td>

        <td>${record.status}</td>

        <td>₹${record.fine}</td>

        </tr>
        `;

    });

}

loadHistory();