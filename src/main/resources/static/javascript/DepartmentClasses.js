const onLoadPopulate = () => {
	const departmentId = document.getElementById("departmentId").value;

	if (departmentId != undefined) {
		getDepartmentClassesDetails(departmentId);

	}
}

const getDepartmentClassesDetails = (departmentId) => {
	fetch(path + "/department/getDepartmentClassesDetails/" + departmentId, {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
		},

	})
		.then((response) => response.json())
		.then((classes) => {
			console.log(classes);
			if (classes) {
				populateClassTable(classes);
			}
		})
		.then((err) => {
			return;
		})
};

const populateClassTable = (classes) => {
	const tableBody = document.getElementById("class");

	for (var index = 0; index < classes.length; index++) {
		console.log(classes[index]);
		tableBody.appendChild(
			createRowForClassTable(tableBody.childNodes.length, classes[index])
		);
	}
}

const createRowForClassTable = (srno, classObj) => {

	var tr = document.createElement("tr");
	var tdSrno = document.createElement("td");
	tdSrno.innerHTML = srno;
	tr.appendChild(tdSrno);

	var tdCourse = document.createElement("td");
	tdCourse.innerHTML = classObj.course;
	tr.appendChild(tdCourse);

	var tdBranch = document.createElement("td");
	tdBranch.innerHTML = classObj.branch;
	tr.appendChild(tdBranch);

	var tdSection = document.createElement("td");
	tdSection.innerHTML = classObj.section;
	tr.appendChild(tdSection);
	
	var tdYear = document.createElement("td");
	tdYear.innerHTML = classObj.section;
	tr.appendChild(tdYear);

	return tr;


}
