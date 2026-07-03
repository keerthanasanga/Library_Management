const api = "http://localhost:8080/api/students";

let editId = null;

loadStudents();

document.getElementById("studentForm")
.addEventListener("submit", saveStudent);

async function loadStudents() {

    const response = await fetch(api);

    const students = await response.json();

    displayStudents(students);

}

function displayStudents(students) {

    const table = document.getElementById("studentTable");

    table.innerHTML = "";

    students.forEach(student => {

        table.innerHTML += `

<tr>

<td>${student.studentId}</td>

<td>${student.name}</td>

<td>${student.email}</td>

<td>${student.phone}</td>

<td>${student.department}</td>

<td>${student.year}</td>


<td>
    <button class="btn btn-edit" onclick="editStudent(id)">Edit</button>
    <button class="btn btn-danger" onclick="deleteStudent(id)">Delete</button>
</td>

</td>

</tr>

`;

    });

}

async function saveStudent(e) {

    e.preventDefault();

    const student = {

        name: name.value,

        email: email.value,

        password: password.value,

        phone: phone.value,

        department: department.value,

        year: year.value

    };

    if (editId == null) {

        await fetch(api, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(student)

        });

    } else {

        await fetch(api + "/" + editId, {

            method: "PUT",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(student)

        });

        editId = null;

    }

    document.getElementById("studentForm").reset();

    loadStudents();

}

async function editStudent(id) {

    const response = await fetch(api + "/" + id);

    const student = await response.json();

    name.value = student.name;

    email.value = student.email;

    password.value = student.password;

    phone.value = student.phone;

    department.value = student.department;

    year.value = student.year;

    editId = id;

}

async function deleteStudent(id) {

    if (!confirm("Delete Student?")) return;

    await fetch(api + "/" + id, {

        method: "DELETE"

    });

    loadStudents();

}

async function searchStudent() {

    const keyword = document.getElementById("search").value;

    if (keyword == "") {

        loadStudents();

        return;

    }

    const response = await fetch(api + "/search?keyword=" + keyword);

    const students = await response.json();

    displayStudents(students);

}