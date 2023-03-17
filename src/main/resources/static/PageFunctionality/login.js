
const checkCredentials = () => {

	let form = document.getElementById("loginform");
	let username = form["username"].value;
	let password = form["password"].value;

	if (username != "" && password != "") {
		isSuperAdmin(username, password);
	}
	else invalid();
}

const verifyAdmin = (username, password) => {
	let admin = {
		username: username,
		password: password
	};

	fetch(path + "/admin/verifyLoginCredentials/", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(admin),
	})
		.then((response) => response.json())
		.then((isAdmin) => {
			alert(isAdmin);
			if (isAdmin == true) {
				valid();
		
				window.location.href = path + "/courseDashboard";

			} else {
				verifyHOD(username, password);
			}
		}).then((error) => {
		//	console.log("Error: ", error);
		});
}
const verifyHOD = (username, password) => {
	let login = {
		username: username,
		password: password
	};

	fetch(path + "/hod/verifyLoginCredentials/", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(login),
	})
		.then((response) => response.json())
		.then((isHOD) => {
			alert(isHOD);
			if (isHOD == true) {
				valid();
		
				window.location.href = path + "/departmentDashboard";

			} else {
				verifyFaculty(username, password);
			}
		}).then((error) => {
		//	console.log("Error: ", error);
		});
}

const verifyFaculty = (username, password) => {
	let login = {
		username: username,
		password: password
	};

	fetch(path + "/faculty/verifyLoginCredentials/", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(login),
	})
		.then((response) => response.json())
		.then((isFaculty) => {
			alert(isFaculty);
			if (isFaculty == true) {
				valid();
				window.location.href = path + "/lectureDashboard";

			} else {
				verifySI(username, password);
			}
		}).then((error) => {
			//console.log("Error: ", error);
		});
}
const verifySI = (username, password) => {
	let login = {
		username: username,
		password: password
	};

	fetch(path + "/si/verifyLoginCredentials/", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(login),
	})
		.then((response) => response.json())
		.then((isSI) => {
			alert(isSI);
			if (isSI == true) {
				valid();
				window.location.href = path + "/classDashboard";

			} else {
				invalid();
			}
		}).then((error) => {
			//console.log("Error: ", error);
		});
}

const valid = () => {
	let msg = document.getElementById("msg");
	msg.innerHTML = "Succesfully Login !!";
	msg.style.color = "chartreuse";

}

const invalid = () => {
	let msg = document.getElementById("msg");
	msg.innerHTML = "Invalid Credentials !! <br> Kindly check your username/password";
	msg.style.color = "red";
	msg.style.fontStyle= "Italic";

	document.getElementById("loginform")["password"].value = "";

}


const isSuperAdmin = (username, password) => {
	let login = {
		username: username,
		password: password
	};

	fetch(path + "/superAdmin/verifyLoginCredentials/", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(login),
	})
		.then((response) => response.json())
		.then((isSuperAdmin) => {
			alert(isSuperAdmin);
			if (isSuperAdmin == true) {
				valid();
				
				window.location.href = path + "/organizationDashboard";

			} else {
				verifyAdmin(username, password);
			}
		}).then((error) => {
			//console.log("Error: ", error);
		});
		
		return undefined;
}

