function home() { 
	$('#examinationForm').attr('hidden', true);
	$('#schPatTable').attr('hidden', true);
}

function startExamination(pacijent) {
	return function() {
		$('#examinationForm').attr('hidden', false);
		$('#schPatTable').attr('hidden', true);
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
	$('#recipeForm').attr('hidden', false);
}

function dodajOperaciju() {
	alert('Work in progress');
}

function prikaziPacijenta(pacijent, i) {
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
       			prikaziPacijenta(pacijent, i);
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




