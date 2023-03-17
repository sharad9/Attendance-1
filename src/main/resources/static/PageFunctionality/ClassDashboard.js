
const onLoadPopulate = () => {

	getClassStudents();

}

const classId = document.getElementById("classId").value;
const getClassStudents = () => {

	fetch(path + "/student/getAllStudents/", {
		method: 'GET',
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((res) => res.json())
		.then((students) => {
			console.log("successfully fetched all data", students);
			if (students != undefined) {
				populateStudentTable(students);
			}
		})
		.then((err) => {
			//console.log(err);
			return;
		});

}

const populateStudentTable = (students) => {

	var tableBody = document.getElementById("students");

	for (var index = 0; index < students.length; index++) {
		console.log(students[index]);
		tableBody.appendChild(
			createRowStudentTable(index + 1, students[index])
		);
	}

}


const createRowStudentTable = (srno, student) => {
console.log(student,student.name,student.rollNo);
	var tr = document.createElement("tr");

	var tdSrno = document.createElement("td");
	tdSrno.innerHTML = srno;
	tr.appendChild(tdSrno);


	var tdEmail = document.createElement("td");
	if (student.email != null) tdEmail.innerHTML = student.email;
	else tdEmail.innerHTML = "?";
	tr.appendChild(tdEmail);


	var tdName = document.createElement("td");
	if (student.name != null) tdName.innerHTML = student.name;
	else tdName.innerHTML = "?";
	tr.appendChild(tdName);

	var tdRollNo = document.createElement("td");
	if (student.rollNo != null) tdName.innerHTML = student.rollNo;
	else tdName.innerHTML = "?";
	tr.appendChild(tdRollNo);


	




	var tdOpr = document.createElement("td");

	var tdDelete = document.createElement("button");

	tdDelete.innerHTML = "Delete";
	tdDelete.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		deleteStudent(student.id);
	});
	var tdEdit = document.createElement("button");

	tdEdit.innerHTML = "Edit";
	tdEdit.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		editStudent(student);
	});




	tdOpr.appendChild(tdDelete);
	tdOpr.appendChild(tdEdit);

	tr.appendChild(tdOpr);

	return tr;
}

const addStudent = () => {

	var studentForm = document.getElementById("studentForm");
	var name = studentForm["name"].value;
	var username = studentForm["username"].value;
	var password = studentForm["password"].value;
	var email = studentForm["email"].value;
	var rollNo = studentForm["rollNo"].value;
	var id = studentForm["id"].value;



	if (id == "-1") {
		if (username != "" && password != "") {

			var student = {
				username: username,
				password: password,
				name: name,
				email: email,
				rollNo: rollNo,
				classId: classId

			};

			saveStudent(student);
			studentForm.reset();
		} else {

		}

	} else {

		if (username != "" && password != "") {

			var student = {
				id: id,
				username: username,
				password: password,
				name: name,
				email: email,
				rollNo:rollNo,
				classId: classId
			};
			alert(student.id, student.username);
			editStudentAPI(student);
			studentForm.reset();


		}
	}
}

const saveStudent = (student) => {
	console.log(student);
	fetch(path + "/student/addStudent", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(student),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("students");
			tableBody.innerHTML = "";
			getClassStudents();
		})
		.then((error) => {
			console.log("Error: ", error);
		});

}


function deleteStudent(studentId) {


	deleteStudentAPI(studentId);


}

function deleteStudentAPI(studentId) {



	fetch(path + "/student/deleteStudent/" + studentId, {

		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((response) => response)
		.then((response) => {
			var tableBody = document.getElementById("students");
			tableBody.innerHTML = "";
			getClassStudents();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}


function postStudent(student) {


	console.log(student);
	fetch(path + "/student/addStudent", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(student),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("students");
			tableBody.innerHTML = "";
			getClassStudents();
		})
		.then((error) => {
			console.log("Error: ", error);
		});


}

function editStudent(student) {

	var studentForm = document.getElementById("studentForm");

	studentForm["id"].value = student.id;
	studentForm["name"].value = student.name;

	studentForm["username"].value = student.username;
	studentForm["password"].value = student.password;
	
	studentForm["email"].value = student.email;
	studentForm["rollNo"].value = student.rollNo;
	
}


function editStudentAPI(student) {


	fetch(path + "/student/editStudent/" + student.id, {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(student),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("students");
			tableBody.innerHTML = "";
			getClassStudents();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}