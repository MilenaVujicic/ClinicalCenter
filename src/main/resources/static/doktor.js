function home() { 
	$('#examinationForm').attr('hidden', true);
	$('#schPatTable').attr('hidden', true);
	$('#allPatients').attr('hidden', true);
}

function startExamination(pacijent) {
	return function() {
		$('#examinationForm').attr('hidden', false);
		$('#schPatTable').attr('hidden', true);
		$('#allPatients').attr('hidden', true);
		$('#patientName').val(pacijent.ime);
		$('#patientSurname').val(pacijent.prezime);
		$('#patientJMBG').val(pacijent.jmbg);
		$('#patientID').val(pacijent.id);
	}
}

function dodajRecept() {
	$('#recipeForm').attr('hidden', false);
	$('#recipePatientName').val($('#patientName').val());
	$('#recipePatientSurname').val($('#patientSurname').val());
	$('#recipePatientJMBG').val($('#patientJMBG').val());
	$('#recipePatientID').val($('#patientID').val());
}

function cancelRecipe() {
	$('#recipeForm').attr('hidden', true);
	$('#recipePatientName').val('');
	$('#recipePatientSurname').val('');
	$('#recipePatientJMBG').val('');
}

function dodajPregled() {
	alert('Work in progress');
}

function dodajOperaciju() {
	alert('Work in progress');
}

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
	let id = $('#patientID').val();
	let naziv = $('#examinationName').val();
	let anamneza = $('#examinationAnamnesis').val();
	let cena = $('#examinationPrice').val();
	let tipPregleda = $('#examinationType').val();
	let url = 'doktor/posalji_pregled/' + id;
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
   		        data: JSON.stringify({naziv, anamneza, cena, tipPregleda}),
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
       		alert('Desila se greska');
       	}
 	});
}

function editPatient() {
	alert('Uspesno');
}

function prikaziPregled(pregled) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+pregled.naziv+'</td>');
	let tdDate = $('<td>'+pregled.datumIVremePregleda.toString().substr(0, 10)+'</td>');
	let tdType = $('<td>'+pregled.tipPregleda+'</td>');
	let tdAnamnesis = $('<td>'+pregled.anamneza+'</td>');
	let tdPrice = $('<td>'+pregled.cena+'</td>');
	let aDiagnosis = $('<td><a class="btn btn-primary">Diagnosis</a></td>');
	let aRecipes = $('<td><a class="btn btn-primary">Recipes</a></td>');
	let aEditExamination = $('<td><a class="btn btn-success">Edit examination</a></td>');
	tr.append(tdName).append(tdDate).append(tdType).append(tdAnamnesis).append(tdPrice).append(aDiagnosis).append(aRecipes).append(aEditExamination);
	$('#allExaminations tbody').append(tr);
}

function examinations(pacijent) {
	let url = "/pregled/sviPregledi/" + pacijent.id;
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
           		$('#allPatients').attr('hidden', true);
           		$('#aboutPatient').attr('hidden', false);
           		document.getElementById("title").innerHTML = "";
           		examinations(pacijent);
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
	$('#allPatients').attr('hidden', false);
	$.ajax({
        url:"/doktor/svi_pacijenti",
        type:"GET",
       	success: function(pacijenti) {
       		$('#allPatients tbody').html('');
       		let i = 0;
       		for (let pacijent of pacijenti) {
       			i = i + 1;
       			prikaziPacijenta(pacijent, i);
       		}
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
	
}
