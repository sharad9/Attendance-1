
const addSubject =()=>{
	const subjectForm = document.getElementById("subjectForm");
	const id = subjectForm["id"].value;
	const name = subjectForm["name"].value;
	const code = subjectForm["code"].value;
	
	if(name != undefined){
		if(id==-1){
			var subject = {
				name:name,
				code:code
			};
			console.log(subject);
			postSubject(subject);
		}else{
			
		}
	}
};


const postSubject = (subject)=>{
	const departmentId = document.getElementById("departmentId").value;
	
	if(departmentId != undefined){
		fetch(path+"/subject/addSubject/"+departmentId,{
			method:"POST",
			headers:{
				"Content-Type":"application/json",
			},
			body:JSON.stringify(subject)
		})
		.then((response)=>response.json())
		.then((subjectResponse)=>{
			if(subjectResponse != undefined || subjectResponse != null){
				//var subjectData = [subjectResponse];
				
			}
		})
		.then((error)=>{
			console.log("Error: ", error);
		});
	}
}