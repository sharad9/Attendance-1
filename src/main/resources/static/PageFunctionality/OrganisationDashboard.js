
const onLoadPopulate = () => {

	getCourseAdmins();
onLoadPopulateFaculty();
}

const getCourseAdmins = () => {

	fetch(path + "/admin/getAllAdmins/", {
		method: 'GET',
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((res) => res.json())
		.then((admins) => {
			console.log("successfully fetched all data", admins);
			if (admins != undefined) {
				populateAdminTable(admins);
			}
		})
		.then((err) => {
			//console.log(err);
			return;
		});

}

const populateAdminTable = (admins) => {

	var tableBody = document.getElementById("admins");

	for (var index = 0; index < admins.length; index++) {
		console.log(admins[index]);
		tableBody.appendChild(
			createRowAdminTable(index + 1, admins[index])
		);
	}

}


const createRowAdminTable = (srno, admin) => {

	var tr = document.createElement("tr");

	var tdSrno = document.createElement("td");
	tdSrno.innerHTML = srno;
	tr.appendChild(tdSrno);
	var tdName = document.createElement("td");
	if (admin.name != null) tdName.innerHTML = admin.name;
	else tdName.innerHTML = "Name";
	tr.appendChild(tdName);



	var tdEmail = document.createElement("td");
	if (admin.email != null) tdEmail.innerHTML = admin.email;
	else tdEmail.innerHTML = "test@gmail.com";
	tr.appendChild(tdEmail);

	var tdCourse = document.createElement("td");

	if (admin.course != null) tdCourse.innerHTML = admin.course;
	else tdName.innerHTML = "?";

	tr.appendChild(tdCourse);





	var tdOpr = document.createElement("td");

	var tdDelete = document.createElement("button");

	tdDelete.innerHTML = "Delete";
	tdDelete.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		deleteAdmin(admin.id);
	});
	var tdEdit = document.createElement("button");

	tdEdit.innerHTML = "Edit";
	tdEdit.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		editAdmin(admin);
	});




	tdOpr.appendChild(tdDelete);
	tdOpr.appendChild(tdEdit);

	tr.appendChild(tdOpr);

	return tr;
}

const addAdmin = () => {

	var adminForm = document.getElementById("adminForm");
	var name = adminForm["name"].value;
	var username = adminForm["username"].value;
	var password = adminForm["password"].value;
	var email = adminForm["email"].value;
	var course = adminForm["course"].value;
	var id = adminForm["id"].value;



	if (id == "-1") {
		if (username != "" && password != "") {

			var admin = {
				username: username,
				password: password,
				email: email,
				name: name,
				course: course
			};

			saveAdmin(admin);
			adminForm.reset();
		} else {

		}

	} else {

		if (username != "" && password != "") {

			var admin = {
				id: id,
				username: username,
				password: password,
				email: email,
				name: name,
				course: course
			};
			alert(admin.id,admin.username);
			editAdminAPI(admin);
			adminForm.reset();


		}
	}
}

const saveAdmin = (admin) => {
	console.log(admin);
	fetch(path + "/admin/addAdmin", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(admin),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("admins");
			tableBody.innerHTML = "";
			getCourseAdmins();
		})
		.then((error) => {
			console.log("Error: ", error);
		});

}


function deleteAdmin(adminId) {


	deleteAdminAPI(adminId);


}

function deleteAdminAPI(adminId) {



	fetch(path + "/admin/deleteAdmin/" + adminId, {

		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((response) => response)
		.then((response) => {
			var tableBody = document.getElementById("admins");
			tableBody.innerHTML = "";
			getCourseAdmins();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}


function postAdmin(admin) {


	console.log(admin);
	fetch(path + "/admin/addAdmin", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(admin),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("admins");
			tableBody.innerHTML = "";
			getCourseAdmins();
		})
		.then((error) => {
			console.log("Error: ", error);
		});


}

function editAdmin(admin) {

	var adminForm = document.getElementById("adminForm");

	adminForm["id"].value = admin.id;
	adminForm["name"].value = admin.name;

	adminForm["username"].value = admin.username;
	adminForm["password"].value = admin.password;
	adminForm["course"].value = admin.course;
	adminForm["email"].value = admin.email;

}


function editAdminAPI(admin) {


	fetch(path + "/admin/editAdmin/" + admin.id, {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(admin),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("admins");
			tableBody.innerHTML = "";
			getCourseAdmins();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}
/*_________________________________________________Faculty_______________________________________________________*/



const onLoadPopulateFaculty = () => {

	getOrganizationFacultys();

}

const getOrganizationFacultys = () => {

	fetch(path + "/faculty/getAllFacultys/", {
		method: 'GET',
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((res) => res.json())
		.then((facultys) => {
			console.log("successfully fetched all data", facultys);
			if (facultys != undefined) {
				populateFacultyTable(facultys);
			}
		})
		.then((err) => {
			//console.log(err);
			return;
		});

}

const populateFacultyTable = (facultys) => {

	var tableBody = document.getElementById("facultys");

	for (var index = 0; index < facultys.length; index++) {
		console.log(facultys[index]);
		tableBody.appendChild(
			createRowFacultyTable(index + 1, facultys[index])
		);
	}

}


const createRowFacultyTable = (srno, faculty) => {

	var tr = document.createElement("tr");

	var tdSrno = document.createElement("td");
	tdSrno.innerHTML = srno;
	tr.appendChild(tdSrno);
	var tdName = document.createElement("td");
	if (faculty.name != null) tdName.innerHTML = faculty.name;
	else tdName.innerHTML = "Name";
	tr.appendChild(tdName);



	var tdEmail = document.createElement("td");
	if (faculty.email != null) tdEmail.innerHTML = faculty.email;
	else tdEmail.innerHTML = "test@gmail.com";
	tr.appendChild(tdEmail);

	




	var tdOpr = document.createElement("td");

	var tdDelete = document.createElement("button");

	tdDelete.innerHTML = "Delete";
	tdDelete.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		deleteFaculty(faculty.id);
	});
	var tdEdit = document.createElement("button");

	tdEdit.innerHTML = "Edit";
	tdEdit.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		editFaculty(faculty);
	});




	tdOpr.appendChild(tdDelete);
	tdOpr.appendChild(tdEdit);

	tr.appendChild(tdOpr);

	return tr;
}

const addFaculty = () => {

	var facultyForm = document.getElementById("facultyForm");
	var name = facultyForm["name"].value;
	var username = facultyForm["username"].value;
	var password = facultyForm["password"].value;
	var email = facultyForm["email"].value;

	var id = facultyForm["id"].value;



	if (id == "-1") {
		if (username != "" && password != "") {

			var faculty = {
				username: username,
				password: password,
				email: email,
				name: name,
			
			};

			saveFaculty(faculty);
			facultyForm.reset();
		} else {

		}

	} else {

		if (username != "" && password != "") {

			var faculty = {
				id: id,
				username: username,
				password: password,
				email: email,
				name: name,
				
			};
			alert(faculty.id, faculty.username);
			editFacultyAPI(faculty);
			facultyForm.reset();


		}
	}
}

const saveFaculty = (faculty) => {
	console.log(faculty);
	fetch(path + "/faculty/addFaculty", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(faculty),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("facultys");
			tableBody.innerHTML = "";
			getOrganizationFacultys();
		})
		.then((error) => {
			console.log("Error: ", error);
		});

}


function deleteFaculty(facultyId) {


	deleteFacultyAPI(facultyId);


}

function deleteFacultyAPI(facultyId) {



	fetch(path + "/faculty/deleteFaculty/" + facultyId, {

		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((response) => response)
		.then((response) => {
			var tableBody = document.getElementById("facultys");
			tableBody.innerHTML = "";
			getOrganizationFacultys();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}


function postFaculty(faculty) {


	console.log(faculty);
	fetch(path + "/faculty/addFaculty", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(faculty),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("facultys");
			tableBody.innerHTML = "";
			getOrganizationFacultys();
		})
		.then((error) => {
			console.log("Error: ", error);
		});


}

function editFaculty(faculty) {

	var facultyForm = document.getElementById("facultyForm");

	facultyForm["id"].value = faculty.id;
	facultyForm["name"].value = faculty.name;

	facultyForm["username"].value = faculty.username;
	facultyForm["password"].value = faculty.password;

	facultyForm["email"].value = faculty.email;

}


function editFacultyAPI(faculty) {


	fetch(path + "/faculty/editFaculty/" + faculty.id, {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(faculty),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("facultys");
			tableBody.innerHTML = "";
			getOrganizationFacultys();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}