const addFaculty = ()=>{
	
	const facultyForm = document.getElementById("facultyForm");
	const id = facultyForm["id"].value;
	const name = facultyForm["name"].value;
	
	console.log(facultyForm);
	
	if(id == "-1"){
		if(name != ""){
			const faculty = {
				name:name
			};
			
			console.log(faculty);
			postFaculty(faculty);
			facultyForm.reset();
		}else{
			
		}
	}else{
		
		
	}
};

const postFaculty =(faculty)=>{
	
	const departmentId = document.getElementById("departmentId").value;
	if(departmentId != undefined){
		fetch(path+"/faculty/addFaculty/"+departmentId,{
			method:"POST",
			headers:{
				"Content-Type":"application/json",
			},
			body:JSON.stringify(faculty),
			
			
		})
		.then((response)=>response.json())
		.then((facultyResponse)=>{
			if(facultyResponse != undefined || facultyResponse != null){
				//const facultyData = [facultyResponse];
				//populateFacultyTable(facultyData);
				alert(JSON.stringify(facultyResponse));
				
			}else{
				
			}
		})
		.then((error)=>{
			console.log("Error:",error);
		});
	}
}
function saveFacultyCredential(){
	var dId = document.getElementById("dId").value;	
	
	var credentialForm = document.getElementById("credentialForm");
	var username = credentialForm["username"].value;
	var password = credentialForm["password"].value;
	var facultyId = credentialForm["facultyId"].value;
	
	if(facultyId!=-1 && username!="" && password!="" && dId!=undefined){
		
		var login = {
			username: username,
			password: password
		};
		
		fetch(path+"/faculty/saveCredentials/"+facultyId,{
			
		 method: "POST", 
		 headers: {
		      "Content-Type": "application/json",
		    },
		 body: JSON.stringify(login),
		
		})
		.then((response)=> response.json())
		.then((response)=>{
			var a = document.createElement("a");
			a.href = path+"/departmentDashboard?dId="+dId;
			a.click();
		})
		.then((error)=>{
			//console.log("Error: ",error);
		});
		
	}
	
}