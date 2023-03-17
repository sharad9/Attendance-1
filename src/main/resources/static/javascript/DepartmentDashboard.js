const showForm = (formSectionId, button) => {

			var formSectionIds = ['subjectFormSection', 'facultyFormSection', 'classFormSection', 'studentFormSection'];

			for (const child of button.parentNode.children) {
				child.style.color = "black";
			}
			for (let id of formSectionIds) {

				if (id == formSectionId) {
					document.getElementById(id).style.display = "block";
					button.style.color = "red";

				} else {
					document.getElementById(id).style.display = "none";

				}


			}

		};
