const addDepartment =()=>{
	const departmentForm = document.getElementById("departmentForm");
	const name = departmentForm["name"].value;
	if(name !=""){
		const department = {
			name:name
		};
		console.log(department);
		postDepartment(department);
		departmentForm.reset();
		
	}else{
		error();
	}
}

const postDepartment = (department)=>{
	
	const adminId = document.getElementById("adminId").value;
	
	if(adminId != undefined){
		fetch(path+"/department/addDepartment/"+adminId,{
			method:"POST",
			headers:{
				"Content-Type":"application/json",
				
			},
			body:JSON.stringify(department),
		})
		.then((response)=> response.json())
		.then((departmentData)=>{
			if(departmentData != undefined || departmentData != null){
				
				window.location.href = path+"/createDepartmentProcess?departmentId="+department.id;
			}else{
				alert("Error occurred !!");
			}
		})
	}
}