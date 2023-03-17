const addClass = ()=>{
	const classForm = document.getElementById("classForm");
	const id = classForm["id"].value;
	const course = classForm["course"].value;
	const branch = classForm["branch"].value;
	const section = classForm["section"].value;
	const year = classForm["year"].value;
	
	if(id != "" && course != "" && branch !="" && section != ""){
		const classObj = {
			id:id,
			course:course,
			branch:branch,
			section:section,
			year:year
		};
		
		console.log(classObj);
		
		if(classObj.id == "-1"){
			postClass(classObj);
		}else{
			
		}
	}
};


const postClass = () =>{
	const departmentId = document.getElementById("department").value;
	
	if( departmentId != undefined){
		fetch(path+"/class/addClass/"+departmentId,{
			method:"POST",
			headers :{
			"Content-Type":"application/json",
			},
			body:JSON.stringify(classObj),
		})
		.then((response)=>response.json())
		.then((classResponse)=>{
			if(classResponse != undefined || classResponse != null){
				//const classData = [classResponse];
				//populateAllClassesInTable(classData);
			}
		})
	}
}