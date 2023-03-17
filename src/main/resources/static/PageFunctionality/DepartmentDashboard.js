
const onLoadPopulate = () => {

	getClassSis();

}
const hodId = document.getElementById("hodId").value;
const departmentId = document.getElementById("departmentId").value;
const getClassSis = () => {

	fetch(path + "/si/getAllSis/", {
		method: 'GET',
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((res) => res.json())
		.then((sis) => {
			console.log("successfully fetched all data", sis);
			if (sis != undefined) {
				populateSiTable(sis);
			}
		})
		.then((err) => {
			//console.log(err);
			return;
		});

}

const populateSiTable = (sis) => {

	var tableBody = document.getElementById("sis");

	for (var index = 0; index < sis.length; index++) {
		console.log(sis[index]);
		tableBody.appendChild(
			createRowSiTable(index + 1, sis[index])
		);
	}

}


const createRowSiTable = (srno, si) => {

	var tr = document.createElement("tr");

	var tdSrno = document.createElement("td");
	tdSrno.innerHTML = srno;
	tr.appendChild(tdSrno);


	var tdEmail = document.createElement("td");
	if (si.email != null) tdEmail.innerHTML = si.email;
	else tdEmail.innerHTML = "?";
	tr.appendChild(tdEmail);


	var tdName = document.createElement("td");
	if (si.name != null) tdName.innerHTML = si.name;
	else tdName.innerHTML = "?";
	tr.appendChild(tdName);


	var tdBranch = document.createElement("td");
	if (si.branch != null) tdBranch.innerHTML = si.branch;
	else tdBranch.innerHTML = "?";
	tr.appendChild(tdBranch);


	var tdSection = document.createElement("td");
	if (si.section != null) tdSection.innerHTML = si.section;
	else tdSection.innerHTML = "?";
	tr.appendChild(tdSection);

	var tdYear = document.createElement("td");
	if (si.year != null) tdYear.innerHTML = si.year;
	else tdYear.innerHTML = "?";
	tr.appendChild(tdYear);

	var tdSemester = document.createElement("td");
	if (si.semester != null) tdSemester.innerHTML = si.semester;
	else tdSemester.innerHTML = "?";
	tr.appendChild(tdSemester);





	var tdOpr = document.createElement("td");

	var tdDelete = document.createElement("button");

	tdDelete.innerHTML = "Delete";
	tdDelete.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		deleteSi(si.id);
	});
	var tdEdit = document.createElement("button");

	tdEdit.innerHTML = "Edit";
	tdEdit.addEventListener("click", () => {
		//console.log("DELETE FACULTY ");
		editSi(si);
	});




	tdOpr.appendChild(tdDelete);
	tdOpr.appendChild(tdEdit);

	tr.appendChild(tdOpr);

	return tr;
}

const addSi = () => {

	var siForm = document.getElementById("siForm");
	var name = siForm["name"].value;
	var username = siForm["username"].value;
	var password = siForm["password"].value;
	var email = siForm["email"].value;
	var semester = siForm["semester"].value;
	var branch = siForm["branch"].value;
	var section = siForm["section"].value;
	var year = siForm["year"].value;
	var id = siForm["id"].value;



	if (id == "-1") {
		if (username != "" && password != "") {

			var si = {
				username: username,
				password: password,
				email: email,
				name: name,
				branch: branch,
				semester: semester,
				section: section,
				year: year,
				departmentId: departmentId,

			};

			saveSi(si);
			siForm.reset();
		} else {

		}

	} else {

		if (username != "" && password != "") {

			var si = {
				id: id,
				username: username,
				password: password,
				email: email,
				branch: branch,
				semester: semester,
				section: section,
				year: year,
				departmentId: departmentId
			};
			alert(si.id, si.username);
			editSiAPI(si);
			siForm.reset();


		}
	}
}

const saveSi = (si) => {
	console.log(si);
	fetch(path + "/si/addSi", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(si),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("sis");
			tableBody.innerHTML = "";
			getClassSis();
		})
		.then((error) => {
			console.log("Error: ", error);
		});

}


function deleteSi(siId) {


	deleteSiAPI(siId);


}

function deleteSiAPI(siId) {



	fetch(path + "/si/deleteSi/" + siId, {

		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
		},
	})
		.then((response) => response)
		.then((response) => {
			var tableBody = document.getElementById("sis");
			tableBody.innerHTML = "";
			getClassSis();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}


function postSi(si) {


	console.log(si);
	fetch(path + "/si/addSi", {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(si),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("sis");
			tableBody.innerHTML = "";
			getClassSis();
		})
		.then((error) => {
			console.log("Error: ", error);
		});


}

function editSi(si) {

	var siForm = document.getElementById("siForm");

	siForm["id"].value = si.id;
	siForm["name"].value = si.name;

	siForm["username"].value = si.username;
	siForm["password"].value = si.password;
	siForm["semester"].value = si.semester;
	siForm["email"].value = si.email;
	siForm["branch"].value = si.branch;
	siForm["section"].value = si.section;
	siForm["year"].value = si.year;

}


function editSiAPI(si) {


	fetch(path + "/si/editSi/" + si.id, {

		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(si),

	})
		.then((response) => response.json())
		.then((response) => {
			var tableBody = document.getElementById("sis");
			tableBody.innerHTML = "";
			getClassSis();
		})
		.then((error) => {
			console.log("Error: ", error);
		});



}