"use strict"

function onAddClick() {
	if (checkValidity() == false) {
		alert("Заполните пустые поля");
		return;
	}
		
	addStudent();
	setAverageAge();
}

function checkValidity() {
	const studentName = document.getElementById("fioTextBox").value;
	const birthday = document.getElementById("datePicker").value;
	
	if (studentName == '' || birthday == '') 
		return false;
	
	return true;
}

function addStudent() {
	const studentsTab = document.getElementById("studentsTab");
	const studentName = document.getElementById("fioTextBox").value;
	const sexSelect = document.getElementById("sexSelect");
	const sex = sexSelect.options[sexSelect.selectedIndex].text;
	const birthday = document.getElementById("datePicker").value;
	const birthdayDMY = new Date(birthday).toLocaleDateString().replace(/-/g,".");
	const age = Math.floor((new Date() - new Date(birthday)) / 1000 / 60 / 60 / 24 / 365);

	const newRow = studentsTab.insertRow(studentsTab.rows.length - 2);
	
	const newCell1 = newRow.insertCell(0);
	newCell1.appendChild(document.createTextNode(studentName));
	
	const newCell2 = newRow.insertCell(1);
	newCell2.appendChild(document.createTextNode(sex));
	
	const newCell3 = newRow.insertCell(2);
	newCell3.appendChild(document.createTextNode(birthdayDMY));
	
	const newCell4 = newRow.insertCell(3);
	newCell4.appendChild(document.createTextNode(age));
	
}

function setAverageAge() {
	let averageAgeCell = document.getElementById("averageAge");
	averageAgeCell.innerHTML = String(calcAverageAge());
}

function calcAverageAge() {
	const studentsTab = document.getElementById("studentsTab");
	
	let ageSum = 0;
	const rowCount = studentsTab.rows.length - 2;
	for (let i = 1; i < rowCount; i++) {
		ageSum += parseInt(studentsTab.rows[i].cells[3].innerHTML);
	}
	return Math.round(ageSum / (rowCount - 1));
	
}





