if (!sessionStorage.getItem("userId")) {

    window.location.href = "login.html";

}

const api="http://localhost:8080/api/students";

const studentId = sessionStorage.getItem("userId");

async function loadProfile(){

const response=await fetch(api+"/"+studentId);

const student=await response.json();

document.getElementById("name").value=student.name;

document.getElementById("email").value=student.email;

document.getElementById("phone").value=student.phone;

document.getElementById("department").value=student.department;

document.getElementById("year").value=student.year;

}

document.getElementById("profileForm")
.addEventListener("submit",updateProfile);

async function updateProfile(e){

e.preventDefault();

const student={

name:name.value,

email:email.value,

phone:phone.value,

department:department.value,

year:year.value

};

await fetch(api+"/"+studentId,{

method:"PUT",

headers:{

"Content-Type":"application/json"

},

body:JSON.stringify(student)

});

alert("Profile Updated Successfully");

}

loadProfile();