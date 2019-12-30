$(document).ready(()=>{
	document.getElementById ("btnExam").addEventListener("click", dodajPregled, false);
	document.getElementById ("btnOp").addEventListener("click", dodajOperaciju, false);
	document.getElementById ("btnSaveExam").addEventListener("click", savePregled, false);
	document.getElementById ("btnCancelExam").addEventListener("click", cancelPregled, false);
	document.getElementById ("btnSaveOp").addEventListener("click", saveOperation, false);
	document.getElementById ("btnCancelOp").addEventListener("click", cancelOperation, false);
})

function home() { 
	$('#examinationForm').attr('hidden', true);
	$('#schPatTable').attr('hidden', true);
	$('#examForm').attr('hidden', true);
	$('#operationForm').attr('hidden', true);
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



function showCalendar(odsustva, pregledi) {
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
	   	       	let id = korisnik.id;
	   			if (pregled.status == "ZAVRSEN") {
	   				color = 'purple';
	   				id = 0;
	   			}
	   		    let end = new Date(pregled.datumIVremePregleda);
	   		    let start = new Date(pregled.datumIVremePregleda);
	   		    end.setMinutes(end.getMinutes() + 30);
	   		    let title = "Pregled:" + korisnik.ime + " " + korisnik.prezime;
	   			var event={title: title , start:start, end:end, color:color, id:id};
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
	$('#examinationDiagnosis').attr('hidden', true);
	$('#aboutPatient').attr('hidden', true);
	$.ajax({
		url:"/doktor/odsustva",
        type:"GET",
       	success: function(odsustva){
       		$.ajax({
       			url:"/doktor/zakazani_pregledi",
       	        type:"GET",
       	       	success: function(pregledi){
       	       		$('#calendar').fullCalendar('removeEvents');
       	       		showCalendar(odsustva, pregledi);
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

