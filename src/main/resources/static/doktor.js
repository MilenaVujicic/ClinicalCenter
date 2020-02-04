$(document).ready(()=>{
	document.getElementById ("btnExam").addEventListener("click", dodajPregled, false);
	document.getElementById ("btnOp").addEventListener("click", dodajOperaciju, false);
	document.getElementById ("btnSaveExam").addEventListener("click", savePregled, false);
	document.getElementById ("btnCancelExam").addEventListener("click", cancelPregled, false);
	document.getElementById ("btnSaveOp").addEventListener("click", saveOperation, false);
	document.getElementById ("btnCancelOp").addEventListener("click", cancelOperation, false);
	document.getElementById ("aPersonalData").addEventListener("click", showPersonalData, false);
	document.getElementById ("aDayOff").addEventListener("click", newAbsence, false);
	document.getElementById ("bRequestHoliday").addEventListener("click", requestHoliday, false);
	let session = sessionStorage.getItem("id");
	if (session == null) {
		alert('Nemate prava pristupa ovoj stranici');
		window.location.href = "http://localhost:8080/index.html";
	}
	$.ajax({
		type: "GET",
		url: 'korisnik/preuzmi/' + session,
		success: function(korisnik) {
			if (korisnik.uloga != 'LEKAR') {
				alert('Nemate prava pristupa ovoj stranici');
				window.location.href = "http://localhost:8080/doktor.html";
			}
		},
		error: function() {
			alert('Nema ulogovanog korisnika');
		}
	});
})


function home() { 
	$('#examinationForm').attr('hidden', true);
	$('#schPatTable').attr('hidden', true);
	$('#examForm').attr('hidden', true);
	$('#operationForm').attr('hidden', true);
	$('#allPatients').attr('hidden', true);
	$('#examinationDiagnosis').attr('hidden', true);
	$('#aboutPatient').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#absenceForm').attr('hidden', true);
	$('#tPersonalData').attr('hidden', true);
	$('#tPersonalDataH').attr('hidden', true);
	$('#allPatients').parents('div.dataTables_wrapper').first().hide();

}


function newAbsence(event){
	event.preventDefault();
	$('#absenceForm').attr('hidden', false);
	

}

function requestHoliday(){
	let type = $('#selectRest').val();
	let to = $('#aTo').val();
	let from = $('#aFrom').val();
	let url = "/doktor/odmor/" + type + "~" + from + "~" + to;
	$.ajax({
		url : url,
		type: "GET",
		success: function(response){
			alert(response);
			$('#restFrom').val('');
       		$('#restTo').val('');
       		home();
		},
		error: function() {
   		alert('Something went wrong');
		}
	})
}
function showPersonalData(event){
	event.preventDefault();
	var id = 5;
	$('#tPersonalData').attr('hidden', false);
	$('#tPersonalDataH').attr('hidden', false);
	$('#examinationForm').attr('hidden', true);
	$('#schPatTable').attr('hidden', true);
	$('#examForm').attr('hidden', true);
	$('#operationForm').attr('hidden', true);
	$('#allPatients').attr('hidden', true);
	$('#examinationDiagnosis').attr('hidden', true);
	$('#aboutPatient').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#absenceForm').attr('hidden', true);
	$('#allPatients').parents('div.dataTables_wrapper').first().hide();
	
	$.ajax({
		url: "/doktor/doctor_data/" + id,
		type: "GET",
		success: function(doktor){
			
			let tdName = $('<td></td>');
			tdName.append(doktor.ime);
	
			let tdSurname = $('<td></td>');
			tdSurname.append(doktor.prezime);
			
			let tdUsername = $('<td></td>');
			tdUsername.append(doktor.username);
			
			let tdEmail = $('<td></td>');
			tdEmail.append(doktor.email);
			
			let tdCity = $('<td></td>');
			tdCity.append(doktor.grad);
			
			let tdCountry = $('<td></td>');
			tdCountry.append(doktor.drzava);
			
			let tdAddress = $('<td></td>');
			tdAddress.append(doktor.adresa);
			
			let tdPhone = $('<td></td>');
			tdPhone.append(doktor.telefon);
			
			
			$('#trName').append(tdName);
			$('#trSurname').append(tdSurname);
			$('#trUsername').append(tdUsername);
			$('#trEmail').append(tdEmail);
			$('#trCity').append(tdCity);
			$('#trCountry').append(tdCountry);
			$('#trAddress').append(tdAddress);
			$('#trPhone').append(tdPhone);
			
		},
		error: function(){
			alert('Something went wrong');
		}
	
	})
}

function stExam(pacijent) {
	$('#examinationForm').attr('hidden', false);
	$('#schPatTable').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#allPatients').attr('hidden', true);
	$('#examinationDiagnosis').attr('hidden', true);
	$('#aboutPatient').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#patientName').val(pacijent.ime);
	$('#patientSurname').val(pacijent.prezime);
	$('#patientJMBG').val(pacijent.jmbg);
	$('#patientID').val(pacijent.id);
	$('#examForm').attr('hidden', true);
	$('#operationForm').attr('hidden', true);
	$('#absenceForm').attr('hidden', true);
	$('#allPatients').parents('div.dataTables_wrapper').first().hide();
	$('#tPersonalData').attr('hidden', true);
	$('#tPersonalDataH').attr('hidden', true);
	dijagnoze();
}

function startExamination(pacijent) {
	return function() {
		stExam(pacijent);
		$('#examinationForm').attr('hidden', false);
		$('#schPatTable').attr('hidden', true);
		$('#examForm').attr('hidden', true);
		$('#operationForm').attr('hidden', true);
		$('#patientName').val(pacijent.ime);
		$('#patientSurname').val(pacijent.prezime);
		$('#patientJMBG').val(pacijent.jmbg);
		$('#patientID').val(pacijent.id);
		$('#examinationID').val(0);
	}
}

function dodajRecept() {
	$('#recipeForm').attr('hidden', false);
	$('#recipePatientName').val($('#patientName').val());
	$('#recipePatientSurname').val($('#patientSurname').val());
	$('#recipePatientJMBG').val($('#patientJMBG').val());
	$('#recipePatientID').val($('#patientID').val());
	$('#recipeForm').attr('hidden', false);
	$('#examinationDiagnosis').attr('hidden', true);
	$('#aboutPatient').attr('hidden', true);
	$('#calendar').attr('hidden', true);

}

function cancelRecipe() {
	$('#recipeForm').attr('hidden', true);
	$('#recipePatientName').val('');
	$('#recipePatientSurname').val('');
	$('#recipePatientJMBG').val('');
	$('#recipeDescribe').val('');
	$('#recipeName').val('');
	$('#examinationDiagnosis').attr('hidden', true);
	$('#aboutPatient').attr('hidden', true);
	$('#calendar').attr('hidden', true);
}

function dodajPregled() {
	$('#examForm').attr('hidden', false);
	$('#operationForm').attr('hidden', true);
	$('#dateOp').val('');
	$('#timeOp').val('');
	
}

function dodajOperaciju() {
	$('#operationForm').attr('hidden', false);
	$('#examForm').attr('hidden', true);
	$('#dateExam').val('');
	$('#timeExam').val('');
}

/*function prikaziPacijenta(pacijent, i) {
	let tr = $('<tr></tr>');
	let tdNum = $('<td>'+i+'</td>');
	let tdIme = $('<td>'+pacijent.ime+'</td>');
	let tdPrezime = $('<td>'+pacijent.prezime+'</td>');
	let tdJmbg = $('<td>'+pacijent.jmbg+'</td>');
	let aStart = $('<td><a>Start examination</a></td>');
	aStart.click(startExamination(pacijent));
	let idId = $('<td hidden="true"><input type="text" value="'+pacijent.id+'"></td>');
	tr.append(tdNum).append(tdIme).append(tdPrezime).append(tdJmbg).append(aStart).append(idId);
	$('#schPatTable tbody').append(tr);
}*/

function prikaziZakazanogPacijenta(pacijent, i) {
	let tr = $('<tr></tr>');
	let tdNum = $('<td>'+i+'</td>');
	let tdIme = $('<td>'+pacijent.ime+'</td>');
	let tdPrezime = $('<td>'+pacijent.prezime+'</td>');
	let tdJmbg = $('<td>'+pacijent.jmbg+'</td>');
	let aStart = $('<td><a>Start examination</a></td>');
	aStart.click(startExamination(pacijent));
	let idId = $('<td hidden="true"><input type="text" value="'+pacijent.id+'"></td>');
	tr.append(tdNum).append(tdIme).append(tdPrezime).append(tdJmbg).append(aStart).append(idId);
	$('#schPatTable tbody').append(tr);
}

function prikaziLek(lek) {
	let option = $('<option>('+lek.sifra+') '+lek.ime+'</option>');
	$('#drugs').append(option);
} 

function lekovi() {
	$.ajax({
        url:"/lek/svi_lekovi",
        type:"GET",
       	success: function(lekovi) {
       		$('#drugs').html('');
       		let option =$('<option selected>Open this select menu</option>');
       		$('#drugs').append(option);
       		for (let lek of lekovi) {
       			prikaziLek(lek);
       		}
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function printDrug(lek) {
	let option = $('<option>('+lek.sifra+') '+lek.ime+'</option>');
	$('#selectRecipeDrugs').append(option);
} 


function drugs() {
	$.ajax({
        url:"/lek/svi_lekovi",
        type:"GET",
       	success: function(lekovi) {
       		$('#selectRecipeDrugs').html('');
       		for (let lek of lekovi) {
       			printDrug(lek);
       		}
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function prikaziDijanognozu(dijagnoza) {
	let tr = $('<div class="form-group col-md-3">'+
  			'<div class="custom-control custom-checkbox" >' +
  			'<input type="checkbox" class="form-check-input" id="diagnosis'+dijagnoza.id+'">'+
			'<label class="custom-control-label" for="diagnosis'+dijagnoza.id+'">('+dijagnoza.sifra+') '+dijagnoza.ime+'</label></div></div>');
	$('#diagnosis').append(tr);
}

function dijagnoze() {
	$.ajax({
        url:"/dijagnoza/sve_dijagnoze",
        type:"GET",
       	success: function(dijagnoze) {
       		$('#diagnosis').html('');
       		for (let dijagnoza of dijagnoze) {
       			prikaziDijanognozu(dijagnoza)
       		}
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function scheduledPatients() {
	$('#schPatTable').attr('hidden', false);
	$('#examinationForm').attr('hidden', true);
	$('#allPatients').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#examinationDiagnosis').attr('hidden', true);
	$('#allPatients').parents('div.dataTables_wrapper').first().hide();
	$('#aboutPatient').attr('hidden', true);
	$('#tPersonalData').attr('hidden', true);
	$('#tPersonalDataH').attr('hidden', true);
	dijagnoze();
	lekovi();
	$.ajax({
        url:"/doktor/svi_pacijenti",
        type:"GET",
       	success: function(pacijenti) {
       		$('#schPatTable tbody').html('');
       		let i = 0;
       		for (let pacijent of pacijenti) {
       			i = i + 1;
       			prikaziZakazanogPacijenta(pacijent, i);
       		}
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function saveExamination() {
	let id_pac = $('#patientID').val();
	let naziv = $('#examinationName').val();
	let anamneza = $('#examinationAnamnesis').val();
	let cena = $('#examinationPrice').val();
	let tipPregleda = $('#examinationType').val();
	let id = $('#examinationID').val();
	let url = 'doktor/posalji_pregled/' + id_pac;
	$.ajax({
        url:"/dijagnoza/sve_dijagnoze",
        type:"GET",
       	success: function(dijagnoze) {
       		for (let dijagnoza of dijagnoze) {
       			let str = 'diagnosis' + dijagnoza.id;
       			let x = document.getElementById(str).checked;
       			if (x == true) {
       				url += '~' + dijagnoza.id;
       			}
       		}
   			$.ajax({
   		        url:url,
   		        type:"POST",
   		        data: JSON.stringify({naziv, anamneza, cena, tipPregleda, id}),
   		        contentType:'application/json',
   		       	success: function(pregled) {
   		       		alert('Pregled uspesno unesen');
   		       		$('#examinationName').val('');
   		       		$('#examinationAnamnesis').val('');
   		       		$('#examinationPrice').val('');
   		       		$('#examinationType').val('');
   		       	},
   		       	error: function() {
   		       		alert('Desila se greska');
   		       	}
   		 	});
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function saveRecipe() {
	let id = $('#recipePatientID').val();
	var e = document.getElementById("drugs");
	var recipe = e.options[e.selectedIndex].value;
	let url = 'doktor/posalji_recept/' + id + '~' + recipe;
	let naziv = $('#recipeName').val();
	let opis = $('#recipeDescribe').val();
	$.ajax({
        url:url,
        type:"POST",
        data: JSON.stringify({naziv, opis}),
        contentType:'application/json',
       	success: function(odgovor) {
       		alert('Uspesno ispisan recept');
       		cancelRecipe();
       	},
       	error: function() {
       		alert('Desila se greska.\nProverite da li ste odabrali lek.');
       	}
 	});
}

function editExaminations() {
	let naziv = $('#editExaminationName').val();
	let anamneza = $('#editExaminationAnamnesis').val();
	let tipPregleda = $('#editExaminationType').val();
	let cena = $('#editExaminationPrice').val();
	let id = $('#editExaminationID').val();
	let session = sessionStorage.getItem("id");
	$.ajax({
		url: 'pregled/izmeni',
		type:"PUT",
        data: JSON.stringify({id, naziv, anamneza, tipPregleda, cena}),
        contentType:'application/json',
        success: function() {
        	pacijent_id = document.getElementById("aboutPatientID").innerHTML;
        	examinations(pacijent_id);
        	$('#editExamination').attr('hidden', true);
        	$('#examinationDiagnosis').attr('hidden', true);
        },
        error: function() {
        	alert('Pregled moze da izmeni samo lekar koji je taj pregled izvrsio');
        	$('#editExamination').attr('hidden', true);
        }
	});
}

function editExamination(pregled) {
	return function() {
		$('#editExamination').attr('hidden', false);
		$('#examinationDiagnosis').attr('hidden', true);
		$('#calendar').attr('hidden', true);
		$('#editExaminationName').val(pregled.naziv);
		$('#editExaminationAnamnesis').val(pregled.anamneza);
		$('#editExaminationType').val(pregled.tipPregleda);
		$('#editExaminationPrice').val(pregled.cena);
		$('#editExaminationID').val(pregled.id);
	}
}

function deleteExamination(id) {
	return function() {
		let session = sessionStorage.getItem("id");
		$.ajax({
			url: 'pregled/obrisi/' + id,
			type:"DELETE",
			success: function() {
				pacijent_id = document.getElementById("aboutPatientID").innerHTML;
	        	examinations(pacijent_id);
			},
			error: function() {
				alert('Niste vi izvrsili pregled, te ne mozete ga ni obrisati');
			}
		});
	}
}


function showDiagnose(dijagnoza) {
	let tr = $('<tr></tr>');
	let tdCode = $('<td>'+dijagnoza.sifra+'</td>');
	let tdName = $('<td>'+dijagnoza.ime+'</td>');
	let tdDesc = $('<td>'+dijagnoza.opis+'</td>');
	tr.append(tdCode).append(tdName).append(tdDesc);
	$('#examinationDiagnosisTable tbody').append(tr);
}

function addDiag() {
	let id = document.getElementById("examID").innerHTML;
	let url = 'dijagnoza/izmeni_pregled/' + id;
	$.ajax({
		url:"dijagnoza/sve_dijagnoze",
        type:"GET",
       	success: function(dijagnoze) {
       		for (let dijagnoza of dijagnoze) {
       			let str = 'diagnosisExam' + dijagnoza.id;
       			let x = document.getElementById(str).checked;
       			if (x == true) {
       				url += '~' + dijagnoza.id;
       			}
       		}
       		$.ajax({
       			url: url,
       			type: "GET",
       			success: function() {
       				allDiagn(id);
       				$('#diagnosisExam').attr('hidden', true);
       				$('#buttonAddNewDiag').attr('hidden', true);
       			},
       			error: function() {
       				alert('Ne mozete vi da izmenite dijagnoze, kad ih niste vi uneli');
       			}
       		});
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
	});
}

function showDiag(dijagnoza) {
	let tr = $('<div class="form-group col-md-3">'+
  			'<div class="custom-control custom-checkbox" >' +
  			'<input type="checkbox" class="form-check-input" id="diagnosisExam'+dijagnoza.id+'">'+
			'<label class="custom-control-label" for="diagnosisExam'+dijagnoza.id+'">('+dijagnoza.sifra+') '+
			dijagnoza.ime+'</label></div></div>');
	$('#diagnosisExam').append(tr);
}

function closeDiagnosis() {
	$('#examinationDiagnosis').attr('hidden', true);
}

function getDiagnosis() {
	$.ajax({
        url:"/dijagnoza/sve_dijagnoze",
        type:"GET",
       	success: function(dijagnoze) {
       		$('#diagnosisExam').html('');
       		for (let dijagnoza of dijagnoze) {
       			showDiag(dijagnoza);
       		}
       		$('#buttonAddNewDiag').attr('hidden', false);
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function allDiagn(id) {
	$.ajax({
		url: 'dijagnoza/pregled/' + id,
		type: "GET",
		success: function(dijagnoze) {
			$('#examinationDiagnosisTable tbody').html('');
			for (let dijagnoza of dijagnoze) {
				showDiagnose(dijagnoza);
			}
			$('#examinationDiagnosisTable').attr('hidden', false);
			$('#examinationDiagnosis').attr('hidden', false);
			document.getElementById("examID").innerHTML = id;
		},
		error: function() {
			alert('Desila se greska');
		}
	});
}

function allDiagnosis(id) {
	return function() {
		allDiagn(id);
	}
}



function savePregled(){
	let examDate = $('#dateExam').val();
	let examTime = $('#timeExam').val();
	let name = $('#patientName').val();
	let surname = $('#patientSurname').val();
	let patient = name + '_' + surname;
	let id = 4;
	$.ajax({
		url: 'doktor/pregled/' + patient + '/' + id,
		type: "POST",
		data: JSON.stringify({examDate, examTime}),
		contentType: 'application/json',
		succes: function(ret){
			alert('The request has been sent');
		},
		error: function(){
			alert('Desila se greska');
		}
	})
}


function prikaziPregled(pregled) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+pregled.naziv+'</td>');
	let tdDate = $('<td>'+pregled.datumIVremePregleda.toString().substr(0, 10)+'</td>');
	let tdType = $('<td>'+pregled.tipPregleda+'</td>');
	let tdAnamnesis = $('<td>'+pregled.anamneza+'</td>');
	let tdPrice = $('<td>'+pregled.cena+'</td>');
	let aDiagnosis = $('<td><a class="btn btn-primary">Diagnosis</a></td>');
	aDiagnosis.click(allDiagnosis(pregled.id));
	let aEditExamination = $('<td><a class="btn btn-success">Edit examination</a></td>');
	aEditExamination.click(editExamination(pregled));
	let aDeleteExamination = $('<td><a class="btn btn-danger">Delete examination</a></td>');
	aDeleteExamination.click(deleteExamination(pregled.id));
	tr.append(tdName).append(tdDate).append(tdType).append(tdAnamnesis).append(tdPrice).append(aDiagnosis)
	.append(aEditExamination).append(aDeleteExamination);
	$('#allExaminations tbody').append(tr);
}

function examinations(id) {
	let url = "/pregled/sviPregledi/" + id;
	$.ajax({
        url:url,
        type:"GET",
        success: function(pregledi) {
        	$('#allExaminations tbody').html('');
        	for(let pregled of pregledi) {
        		prikaziPregled(pregled);
        	}
        },
        error: function() {
        	alert('Desila se greska ovde');
        }
	});
}

function editOperation() {
	let opis = document.getElementById("editOperationDesc").value;
	let id = $('#editOperationID').val();
	$.ajax({
		url: 'operacija/izmeni',
		type: "PUT",
		data: JSON.stringify({id, opis}),
        contentType:'application/json',
        success: function() {
        	$('#editOperation').attr('hidden', true);
        	pacijent_id = document.getElementById("aboutPatientID").innerHTML;
        	operations(pacijent_id);
        },
        error: function() {
        	alert('Niste vi izvrsili operaciju, te ne mozete da izmenite');
        	$('#editOperation').attr('hidden', true);
        }
		
	});
}

function editOperations(operacija) {
	return function() {
		$('#editOperation').attr('hidden', false);
		document.getElementById("editOperationDesc").value = operacija.opis;
		$('#editOperationID').val(operacija.id);
	}
}

function deleteOperation(operacija) {
	return function() {
		$.ajax({
			url: 'operacija/obrisi/' + operacija.id,
			type: "DELETE",
			success: function() {
				operations(operacija.pacijent.id);
			},
			error: function() {
				alert('Niste vi izvrsili operaciju te ne mozete ni da ga obrisete');
			}
		});
	}
}

function prikaziOperaciju(operacija) {
	let tr = $('<tr></tr>');
	let tdDescribe = $('<td>'+operacija.opis+'</td>');
	let datum = operacija.datumIVremeOperacije.toString().substr(0, 10);
	let tdDate =$('<td>'+datum+'</td>'); 
	let aEditOperation = $('<td><a class="btn btn-success">Edit operation</a></td>');
	aEditOperation.click(editOperations(operacija));
	let aDeleteOperation = $('<td><a class="btn btn-danger">Delete operation</a></td>');
	aDeleteOperation.click(deleteOperation(operacija));
	tr.append(tdDescribe).append(tdDate).append(aEditOperation).append(aDeleteOperation);
	$('#allOperation tbody').append(tr);
}

function operations(id) {
	let url = "/operacija/sveOperacije/" + id;
	$.ajax({
        url:url,
        type:"GET",
        success: function(operacije) {
        	$('#allOperation tbody').html('');
        	for(let operacija of operacije) {
        		prikaziOperaciju(operacija);
        	}
        },
        error: function() {
        	alert('Desila se greska ovde');
        }
	});
}

function addAllergie() {
	let naziv = $('#addAllergieName').val();
	let opis = $('#addAllergieDesc').val();
	let id = $('#addAllergiePatientID').val();
	$.ajax({
		url: "alergija/dodaj/" + id,
		type:"POST",
        data: JSON.stringify({naziv, opis}),
        contentType:'application/json',
        success: function() {
        	$('#addAllergy').attr('hidden', true);
        	pacijent_id = document.getElementById("aboutPatientID").innerHTML;
        	alergies(pacijent_id);
        },
        error: function() {
        	alert('Desila se greska');
        }
	});
}

function addAllergies() {
	$('#addAllergy').attr('hidden', false);
	$('#examinationDiagnosis').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#addAllergieName').val('');
	$('#addAllergieDesc').val('');
	$('#addAllergiePatientID').val(document.getElementById("aboutPatientID").innerHTML);
}

function editAllergie() {
	let naziv = $('#editAllergieName').val();
	let opis = $('#editAllergieDesc').val();
	let id = $('#editAllergieID').val();
	$.ajax({
		url: "alergija/izmeni/" + id,
		type:"PUT",
        data: JSON.stringify({id, naziv, opis}),
        contentType:'application/json',
        success: function() {
        	$('#editAllergie').attr('hidden', true);
        	pacijent_id = document.getElementById("aboutPatientID").innerHTML;
        	alergies(pacijent_id);
        },
        error: function() {
        	alert('Desila se greska');
        }
	});
}

function editAlergy(alergija) {
	return function() {
		$('#editAllergie').attr('hidden', false);
		$('#examinationDiagnosis').attr('hidden', true);
		$('#calendar').attr('hidden', true);
		$('#editAllergieName').val(alergija.naziv);
		$('#editAllergieDesc').val(alergija.opis);
		$('#editAllergieID').val(alergija.id);
	}
}

function deleteAlergy(id) {
	return function() {
		$.ajax({
			url: "alergija/obrisi/" + id,
			type:"DELETE",
	        success: function() {
	        	pacijent_id = document.getElementById("aboutPatientID").innerHTML;
	        	alergies(pacijent_id);
	        },
	        error: function() {
	        	alert('Desila se greska');
	        }
		});
	}
}

function prikaziAlergiju(alergija) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+alergija.naziv+'</td>');
	let tdDesc = $('<td>'+alergija.opis+'</td>');
	let aEdit = $('<td><a class="btn btn-success">Edit allergie</a></td>');
	aEdit.click(editAlergy(alergija));
	let aDelete = $('<td><a class="btn btn-danger">Delete allergie</a></td>');
	aDelete.click(deleteAlergy(alergija.id));
	tr.append(tdName).append(tdDesc).append(aEdit).append(aDelete);
	$('#allAlergies tbody').append(tr);
}

function alergies(id) {
	let url = "alergija/sveAlergije/" + id;
	$.ajax({
        url:url,
        type:"GET",
        success: function(alergije) {
        	$('#allAlergies tbody').html('');
        	for(let alergija of alergije) {
        		prikaziAlergiju(alergija);
        	}
        },
        error: function() {
        	alert('Desila se greska ovde');
        }
	});
}

function editRecipes() {
	let id = $('#editRecipeID').val();
	let naziv = $('#editRecipeName').val();
	let opis = $('#editRecipeDescribe').val();
	let text = $('#selectRecipeDrugs').val();
	$.ajax({
		url: 'recept/izmeni/' + text,
		type:"PUT",
        data: JSON.stringify({id, naziv, opis}),
        contentType:'application/json',
        success: function() {
        	$('#editRecipe').attr('hidden', true);
        	pacijent_id = document.getElementById("aboutPatientID").innerHTML;
        	recipes(pacijent_id);
        },
        error: function() {
        	alert('Desila se greska');
        }
	});
}

function editRecipe(recept) {
	return function() {
		$('#editRecipe').attr('hidden', false);
		$('#examinationDiagnosis').attr('hidden', true);
		$('#editRecipeName').val(recept.naziv);
		$('#editRecipeDescribe').val(recept.opis);
		$('#editRecipeID').val(recept.id);
	}
}

function deleteRecipe(id) {
	return function() {
		$.ajax({
			url: "recept/obrisi/" + id,
			type: "GET",
			success: function(odgovor) {
				pacijent_id = document.getElementById("aboutPatientID").innerHTML;
	        	recipes(pacijent_id);
			},
			error: function() {
				alert('Desila se greska');
			}
		});
	}
}

function prikaziRecept(recept) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+recept.naziv+'</td>');
	let tdDesc = $('<td>'+recept.opis+'</td>');
	let tdStatus = $('<td>'+recept.status+'</td>');
	let tdDate = $('<td>'+recept.datumIspisa+'</td>');
	let tdVerify = $('<td>'+recept.datumOvere+'</td>');
	let tdCode = $('<td>'+recept.sifraLek+'</td>');
	let tdDrug = $('<td>'+recept.lek+'</td>');
	let aEdit = $('<td><a class="btn btn-success">Edit recipe</a></td>');
	aEdit.click(editRecipe(recept));
	let aDelete = $('<td><a class="btn btn-danger">Delete recipe</a></td>');
	aDelete.click(deleteRecipe(recept.id));
	tr.append(tdName).append(tdDesc).append(tdStatus).append(tdDate).append(tdVerify).append(tdCode).append(tdDrug)
			.append(aEdit).append(aDelete);
	$('#allRecipes tbody').append(tr);
}

function recipes(id) {
	let url = "recept/sviRecepti/" + id;
	$.ajax({
        url:url,
        type:"GET",
        success: function(recepti) {
        	$('#allRecipes tbody').html('');
        	for(let recept of recepti) {
        		prikaziRecept(recept);
        	}
        	drugs();
        },
        error: function() {
        	alert('Desila se greska ovde');
        }
	});
}

function medicalRecord(korisnik) {
	return function() {
		$.ajax({
            url:"/doktor/pacijent/" + korisnik.id,
            type:"GET",
           	success: function(pacijent){
           		document.getElementById("profilePatientName").innerHTML = korisnik.ime + " " + korisnik.prezime;
           		document.getElementById("aboutPatientName").innerHTML = korisnik.ime + " " + korisnik.prezime;
           		document.getElementById("aboutPatientEmail").innerHTML = korisnik.email;
           		document.getElementById("aboutPatientPhone").innerHTML = "+381" + korisnik.telefon;
           		document.getElementById("aboutPatientJMBG").innerHTML = korisnik.jmbg;
           		document.getElementById("aboutPatientDateOfBirth").innerHTML = korisnik.datumRodjenja.toString().substr(0, 10);
           		document.getElementById("aboutPatientHeight").innerHTML = pacijent.visina + "cm";
           		document.getElementById("aboutPatientWeight").innerHTML = pacijent.tezina + "kg";
           		document.getElementById("aboutPatientDioptre").innerHTML = pacijent.dioptrija;
           		document.getElementById("aboutPatientID").innerHTML = pacijent.id;
           		document.getElementById("aboutPatientBloodType").innerHTML = pacijent.krvna_grupa;
           		$('#allPatients').attr('hidden', true);
           		$('#aboutPatient').attr('hidden', false);
           		$('#allPatients').parents('div.dataTables_wrapper').first().hide();
           		document.getElementById("title").innerHTML = "";
           		examinations(pacijent.id);
           		alergies(pacijent.id);
           		recipes(pacijent.id);
           		operations(pacijent.id);
	        },
           	error: function() {
           		alert('Desila se greska');
           		document.getElementById("title").innerHTML = "";
           	}
     	});
	}
}

function prikaziPacijenta(pacijent, i) {
	let tr = $('<tr></tr>');
	let tdNum = $('<td>'+i+'</td>');
	let tdIme = $('<td>'+pacijent.ime+'</td>');
	let tdPrezime = $('<td>'+pacijent.prezime+'</td>');
	let tdJmbg = $('<td>'+pacijent.jmbg+'</td>');
	let aRecord = $('<td><a>Medical record</a></td>');
	aRecord.click(medicalRecord(pacijent));
	let idId = $('<td hidden="true"><input type="text" value="'+pacijent.id+'"></td>');
	tr.append(tdNum).append(tdIme).append(tdPrezime).append(tdJmbg).append(aRecord).append(idId);
	$('#allPatients tbody').append(tr);
}

function allPatients() {
	
	$('#examinationForm').attr('hidden', true);
	$('#schPatTable').attr('hidden', true);
	$('#examForm').attr('hidden', true);
	$('#operationForm').attr('hidden', true);

	$('#examinationDiagnosis').attr('hidden', true);
	$('#aboutPatient').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#absenceForm').attr('hidden', true);
	$('#tPersonalData').attr('hidden', true);
	$('#tPersonalDataH').attr('hidden', true);
	
	$('#allPatients').parents('div.dataTables_wrapper').first().show();

	$.ajax({
        url:"/doktor/svi_pacijenti",
        type:"GET",
       	success: function(pacijenti) {
       		var table = $('#allPatients').DataTable();
       		table.destroy();
       	    $('#allPatients tbody').html('');
       		let i = 0;
       		for (let pacijent of pacijenti) {
       			i = i + 1;
       			prikaziPacijenta(pacijent, i);
       		}
       		$('#allPatients').DataTable({
       	        "columnDefs": [ {
       	          "targets": 'no-sort',
       	          "orderable": false,
       	        }]
       		});
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
	
	$('#examinationForm').attr('hidden', true);
	$('#schPatTable').attr('hidden', true);
	$('#allPatients').attr('hidden', false);
	//$('#allPatients').empty();
	$('#examinationDiagnosis').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#absenceForm').attr('hidden', true);
	
}

function editPatient() {
	$('#editAbout').attr('hidden', false);
	$('#examinationDiagnosis').attr('hidden', true);
	let visina = document.getElementById("aboutPatientHeight").innerHTML;
	visina = visina.substring(0, visina.length - 2);
	let tezina = document.getElementById("aboutPatientWeight").innerHTML;
	tezina = tezina.substring(0, tezina.length - 2);
	$('#editHeigth').val(visina);
	$('#editWidth').val(tezina);
	$('#editDioptre').val(document.getElementById("aboutPatientDioptre").innerHTML);
}

function editAbout() {
	let id = document.getElementById("aboutPatientID").innerHTML;
	let url = "pacijent/izmeni";
	let visina = $('#editHeigth').val();
	let tezina = $('#editWidth').val();
	let dioptrija = $('#editDioptre').val();
	let krvna_grupa = $('#editBloodType').val();
	$.ajax({
        url:url,
        type:"PUT",
        data: JSON.stringify({id, visina, tezina, dioptrija, krvna_grupa}),
        contentType:'application/json',
        success: function() {
        	$('#editAbout').attr('hidden', true);
        	document.getElementById("aboutPatientHeight").innerHTML = visina + "cm";
        	document.getElementById("aboutPatientWeight").innerHTML = tezina + "kg";
        	document.getElementById("aboutPatientDioptre").innerHTML = dioptrija;
        	document.getElementById("aboutPatientBloodType").innerHTML = krvna_grupa;
        },
        error: function() {
        	alert('Desila se greska');
        }
    });
}

function examinationFor(id) {
	if (id != 0) {
		$.ajax({
			url: 'pregled/preuzmi/' + id,
			type:"GET",
			success: function(pregled) {
				$.ajax({
					url:"/korisnik/preuzmi/" + pregled.pacijent.idKorisnik,
				    type:"GET",
				   	success: function(korisnik){
				   		stExam(korisnik);
				   		$('#examinationID').val(id);
					},
				   	error: function() {
				   		alert('Desila se greska ovde');
				   	}
				});
			},
			error: function() {
				alert('Greska kod preuzimanja pregleda');
			}
		});
		
	}
}


function saveOperation(){
	let opDate = $('#dateOp').val();
	let opTime = $('#timeOp').val();
	let name = $('#patientName').val();
	let surname = $('#patientSurname').val();
	let patient = name + '_' + surname;
	let id = 4;
	$.ajax({
		url: 'doktor/operacija/' + patient +'/' + id,
		type: "POST",
		data: JSON.stringify({opDate, opTime}),
		contentType: 'application/json',
		succes: function(ret){
			alert('The request has been sent');
		},
		error: function(){
			alert('Desila se greska');
		}
	})
	
}

function cancelPregled(){
	$('#examForm').attr('hidden', true);
	$('#dateExam').val('');
	$('#timeExam').val('');
}



function showCalendar(odsustva, pregledi, operacije) {
	$('#calendar').attr('hidden', false);
	let today = new Date();
	$('#calendar').fullCalendar({
	    header: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'month,agendaWeek,agendaDay,listWeek'
	    },
	    defaultDate: today,
	    navLinks: true,
	    eventLimit: true,
	    eventClick: function(event) {
	    	examinationFor(event.id);
        }
	});
	
	for (let odsustsvo of odsustva) {
		let color = 'red';
		if (odsustsvo.odobren) {
			color = 'green';
		}
		var event={title: odsustsvo.vrstaOdsustva , start:odsustsvo.pocetakOdsustva, end:odsustsvo.zavrsetakOdsustva, color:color, id:0};
		$('#calendar').fullCalendar( 'renderEvent', event, true);
	}
	
	for (let pregled of pregledi) {
		$.ajax({
   			url:"/doktor/pacijent_korisnik/" + pregled.pacijent.id,
   	        type:"GET",
   	       	success: function(korisnik){
	   	       	let color = 'blue';
	   	       	let id = pregled.id;
	   			if (pregled.status == "ZAVRSEN") {
	   				color = 'purple';
	   				id = 0;
	   			}

	   		    let end = new Date(pregled.datumIVremePregleda);
	   		    let start = new Date(pregled.datumIVremePregleda);
	   		    end.setMinutes(end.getMinutes() + 15);
	   		    let title = "Pregled:" + korisnik.ime + " " + korisnik.prezime;
	   			var event={title: title , start:start, end:end, color:color, id:id};
	   			$('#calendar').fullCalendar('renderEvent', event, true);
   	       	},
   	       	error: function() {
   	       		alert('Desila se greska ovde');
   	       	}
   		});
	}
	
	for (let operacija of operacije) {
		$.ajax({
   			url:"/doktor/pacijent_korisnik/" + operacija.pacijent.id,
   	        type:"GET",
   	       	success: function(korisnik){
	   	       	let color = '#0492C2';
	   	       	if (operacija.status == "ZAVRSEN") {
	   				color = '#6968EC';
	   			}
	   	       	let end = new Date(operacija.datumIVremeOperacije);
	   		    let start = new Date(operacija.datumIVremeOperacije);
	   		    end.setMinutes(end.getMinutes() + 30);
	   		    let title = "Operacija:" + korisnik.ime + " " + korisnik.prezime;
	   			var event={title: title , start:start, end:end, color:color, id:0};
	   			$('#calendar').fullCalendar('renderEvent', event, true);
   	       	},
   	       	error: function() {
   	       		alert('Desila se greska ovde');
   	       	}
   		});
	}
	
}

function calendar() {
	$('#calendar').attr('hidden', false);
	$('#examinationForm').attr('hidden', true);
	$('#schPatTable').attr('hidden', true);
	$('#allPatients').attr('hidden', true);
	$('#allPatients').parents('div.dataTables_wrapper').first().hide();
	$('#examinationDiagnosis').attr('hidden', true);
	$('#aboutPatient').attr('hidden', true);
	$('#absenceForm').attr('hidden', true);
	let session = sessionStorage.getItem("id");
	$.ajax({
		url:"/doktor/odsustva/" + session,
        type:"GET",
       	success: function(odsustva){
       		$.ajax({
       			url:"/doktor/zakazani_pregledi/" + session,
       	        type:"GET",
       	       	success: function(pregledi){
       	       		$.ajax({
       	       			url: "/doktor/zakazane_operacije/" + session,
       	       			type: "GET",
       	       			success: function(operacije) {
	       	       			$('#calendar').fullCalendar('removeEvents');
	           	       		showCalendar(odsustva, pregledi, operacije);
       	       			},
       	       			error: function() {
       	       				alert('Desila se greska kod operacija');
       	       			}
       	       		});
       	       	},
       	       	error: function() {
       	       		alert('Desila se greska ovde');
       	       	}
       		});
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
	});
}


function cancelOperation(){

	$('#operationForm').attr('hidden', true);
	$('#dateOp').val('');
	$('#timeOp').val('');
}


