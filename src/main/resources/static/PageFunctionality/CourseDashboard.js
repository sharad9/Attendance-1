
const onLoadPopulate = () => {

	getDepartmentHods();

}
const courseId = document.getElementById("courseId").value;

const getDepartmentHods = () => {

	fetch(path + "/hod/getAllHods/", {
		method: 'GET',
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((res) => res.json())
		.then((hods) => {
			console.log("successfully fetched all data", hods);
			if (hods != undefined) {
				populateHodTable(hods);
			}
		})
		.then((err) => {
			//console.log(err);
			return;
		});

}

const populateHodTable = (hods) => {

	var tableBody = document.getElementById("hods");

	for (var index = 0; index < hods.length; index++) {
		console.log(hods[index]);
		tableBody.appendChild(
			createRowHodTable(index + 1, hods[index])
		);
	}

}


const createRowHodTable = (srno, hod) => {

	var tr = document.createElement("tr");

	var tdSrno = document.createElement("td");
	tdSrno.innerHTML = srno;
	tr.appendChild(tdSrno);
	var tdName = document.createElement("td");
	if (hod.name != null) tdName.innerHTML = hod.name;
	else tdName.innerHTML = "Name";
	tr.appendChild(tdName);



	var tdEmail = document.createElement("td");
	if (hod.email != null) tdEmail.innerHTML = hod.email;
	else tdEmail.innerHTML = "test@gmail.com";
	tr.appendChild(tdEmail);

	var tdDepartment = document.createElement("td");

	if (hod.department != null) tdDepartment.innerHTML = hod.department;
	else tdName.innerHTML = "?";

	tr.appendChild(tdDepartment);





	var tdOpr = document.createElement("td");

	var tdDelete = document.createElement("button");

	tdDelete.innerHTML = "Delete";
	tdDelete.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		deleteHod(hod.id);
	});
	var tdEdit = document.createElement("button");

	tdEdit.innerHTML = "Edit";
	tdEdit.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		editHod(hod);
	});




	tdOpr.appendChild(tdDelete);
	tdOpr.appendChild(tdEdit);

	tr.appendChild(tdOpr);

	return tr;
}

const addHod = () => {

	var hodForm = document.getElementById("hodForm");
	var name = hodForm["name"].value;
	var username = hodForm["username"].value;
	var password = hodForm["password"].value;
	var email = hodForm["email"].value;
	var department = hodForm["department"].value;
	var id = hodForm["id"].value;



	if (id == "-1") {
		if (username != "" && password != "") {

			var hod = {
				username: username,
				password: password,
				email: email,
				name: name,
				department: department,
				courseId:courseId
			};

			saveHod(hod);
			hodForm.reset();
		} else {

		}

	} else {

		if (username != "" && password != "") {

			var hod = {
				id: id,
				username: username,
				password: password,
				email: email,
				name: name,
				department: department,
				courseId:courseId
			};
			
			editHodAPI(hod);
			hodForm.reset();


		}
	}
}

const saveHod = (hod) => {
	alert(courseId);
	fetch(path + "/hod/addHod", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(hod),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("hods");
			tableBody.innerHTML = "";
			getDepartmentHods();
		})
		.then((error) => {
			console.log("Error: ", error);
		});

}


function deleteHod(hodId) {


	deleteHodAPI(hodId);


}

function deleteHodAPI(hodId) {



	fetch(path + "/hod/deleteHod/" + hodId, {

		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((response) => response)
		.then((response) => {
			var tableBody = document.getElementById("hods");
			tableBody.innerHTML = "";
			getDepartmentHods();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}


function postHod(hod) {


	console.log(hod);
	fetch(path + "/hod/addHod", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(hod),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("hods");
			tableBody.innerHTML = "";
			getDepartmentHods();
		})
		.then((error) => {
			console.log("Error: ", error);
		});


}

function editHod(hod) {

	var hodForm = document.getElementById("hodForm");

	hodForm["id"].value = hod.id;
	hodForm["name"].value = hod.name;

	hodForm["username"].value = hod.username;
	hodForm["password"].value = hod.password;
	hodForm["department"].value = hod.department;
	hodForm["email"].value = hod.email;
	

}


function editHodAPI(hod) {


	fetch(path + "/hod/editHod/" + hod.id, {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(hod),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("hods");
			tableBody.innerHTML = "";
			getDepartmentHods();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}