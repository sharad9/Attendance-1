const onLoadPopulate =()=>{
	const departmentId = document.getElementById("departmentId").value;
	
	if(departmentId != undefined){
		getDepartmentFacultiesDetails(departmentId);
		
	}
}

const getDepartmentFacultiesDetails =(departmentId)=>{
	fetch(path+"/department/getDepartmentFacultiesDetails/"+departmentId,{
		method:"GET",
		headers:{
			"Content-Type":"application/json",
		},
		
	})
	.then((response)=>response.json())
	.then((faculties)=>{
		console.log(faculties);
		if(faculties){
			populateFacultyTable(faculties);
		}
	})
	.then((err)=>{
		return;
	})
};

const populateFacultyTable = (faculties)=>{
	const tableBody = document.getElementById("faculty");
	
	for(var index = 0; index<faculties.length;index++){
		console.log(faculties[index]);
		tableBody.appendChild(
			createRowForFacultyTable(tableBody.childNodes.length,faculties[index])
		);
	}
}

const createRowForFacultyTable = (srno,faculty)=>{
	
	var tr = document.createElement("tr");
	var tdSrno = document.createElement("td");
	tdSrno.innerHTML = srno;
	tr.appendChild(tdSrno);
	
	var tdName = document.createElement("td");
	tdName.innerHTML = faculty.name;
	tr.appendChild(tdName);
	
	
	
	return tr;
	
	
}
