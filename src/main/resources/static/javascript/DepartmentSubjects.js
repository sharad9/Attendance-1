const onLoadPopulate =()=>{
	const departmentId = document.getElementById("departmentId").value;
	
	if(departmentId != undefined){
		getDepartmentSubjectsDetails(departmentId);
		
	}
}

const getDepartmentSubjectsDetails =(departmentId)=>{
	fetch(path+"/department/getDepartmentSubjectsDetails/"+departmentId,{
		method:"GET",
		headers:{
			"Content-Type":"application/json",
		},
		
	})
	.then((response)=>response.json())
	.then((subjects)=>{
		console.log(subjects);
		if(subjects){
			populateSubjectTable(subjects);
		}
	})
	.then((err)=>{
		return;
	})
};

const populateSubjectTable = (subjects)=>{
	const tableBody = document.getElementById("subject");
	
	for(var index = 0; index<subjects.length;index++){
		console.log(subjects[index]);
		tableBody.appendChild(
			createRowForSubjectTable(tableBody.childNodes.length,subjects[index])
		);
	}
}

const createRowForSubjectTable = (srno,subject)=>{
	
	var tr = document.createElement("tr");
	var tdSrno = document.createElement("td");
	tdSrno.innerHTML = srno;
	tr.appendChild(tdSrno);
	
	var tdName = document.createElement("td");
	tdName.innerHTML = subject.name;
	tr.appendChild(tdName);
	
	var tdCode = document.createElement("td");
	tdName.innerHTML = subject.name;
	tr.appendChild(tdCode);
	
	return tr;
	
	
}
