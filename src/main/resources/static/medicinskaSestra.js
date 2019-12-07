function detaljanPrikazPacijenta(pacijent) {
	return function() {
		$.ajax({
	        url:"/medicinska_sestra/pacijent/" + pacijent.id,
	        type:"GET",
	       	success: function(pacijenti){
	       		$('#patientName').val(pacijent.ime)
	       		$('#patientSurname').val(pacijent.prezime)
	       		$('#patientBirth').val(pacijent.datumRodjenja)
	       		$('#patientHeight').val(pacijenti.visina)
	       		$('#patientWidth').val(pacijenti.tezina)
	       		$('#patientAllergies').val(pacijenti.alergije);
	       		$('#patientDioptre').val(pacijenti.dioptrija);
	       		$('#patient').attr('hidden', false);
	       		$('#patientsTable').attr('hidden', true);
	       		$('#patientID').val(pacijenti.id);
	       		document.getElementById("title").innerHTML = "Data of " + pacijent.ime + pacijent.prezime;
	       	},
	       	error: function() {
	       		alert('Desila se greska');
	       		document.getElementById("title").innerHTML = "";
	       	}
 	});
}
}

function prikaziPacijenta(pacijent) {
	let tr = $('<tr></tr>');
	let tdIme = $('<td>'+pacijent.ime+'</td>');
	let tdPrezime = $('<td>'+pacijent.prezime+'</td>');
	let tdDatumRodjenja = $('<td>'+pacijent.datumRodjenja+'</td>');
	let tdMore = $('<td><a href="#'+pacijent.ime+'_'+pacijent.prezime+'">More</a></td>');
	tdMore.click(detaljanPrikazPacijenta(pacijent));
	tr.append(tdIme).append(tdPrezime).append(tdDatumRodjenja).append(tdMore);
	$('#patientsTable tbody').append(tr);
}	

function home() {
	$('#patientsTable').attr('hidden', true);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', true);
	$('#patientID').val('');
	document.getElementById("title").innerHTML = "";
}

function prikaziRecept(recept) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+recept.naziv+'</td>');
	let tdOpis = $('<td>'+recept.opis+'</td>');
	let tdDatum = $('<td>'+recept.datumIspisa+'</td>');
	let tdKod = $('<td>'+recept.sifraLek+'</td>');
	let tdLek = $('<td>'+recept.lek+'</td>');
	tr.append(tdName).append(tdOpis).append(tdDatum).append(tdKod).append(tdLek);
	$('#recipesTable tbody').append(tr);
}

function verifyAll() {
	let id = $('#patientID').val();
	let url = "/medicinska_sestra/overi/" + id;
	$.ajax({
        url: url,
        type:"GET",
       	success: function(odgovor){
       		alert(odgovor);
       		unverifiedReciped();
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function unverifiedReciped() {
	let id = $('#patientID').val();
	$.ajax({
        url:"/medicinska_sestra/recepti/" + id,
        type:"GET",
       	success: function(recepti){
       		document.getElementById("title").innerHTML = "Unverified recipes";
       		$('#recipesTable tbody').html('');
       		$('#recipesForm').attr('hidden', false);
       		for (i = 0; i < recepti.length; i++) {
       			prikaziRecept(recepti[i]);
       		}
       		$('#patientsTable').attr('hidden', true);
			$('#patient').attr('hidden', true);
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function allPatients() {
	$.ajax({
        url:"/medicinska_sestra/sviPacijenti",
        type:"GET",
       	success: function(pacijenti){
       		document.getElementById("title").innerHTML = "All patients";
       		$('#patientsTable tbody').html('');
       		for (i = 0; i < pacijenti.length; i++) {
       			prikaziPacijenta(pacijenti[i]);
       		}
     
       		
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
	
	$('#patientsTable').attr('hidden', false);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', true);
}

function requestHoliday() {
	let type = $('#selectRest').val();
	let to = $('#restTo').val();
	let from = $('#restFrom').val();
	let url = "/medicinska_sestra/odmor/" + type + "~" + from + "~" + to;
	$.ajax({
        url:url,
        type:"GET",
       	success: function(odgovor){
       		alert(odgovor);
       		$('#restFrom').val('');
       		$('#restTo').val('');
       		home();
       	},
       	error: function() {
       		alert('Greska u datumima');
       	}
 	});
	
}

function rest() {
	$('#patientsTable').attr('hidden', true);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', false);
	document.getElementById("title").innerHTML = "Holiday request";
}

function personalData() {
	$('#patientsTable').attr('hidden', true);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', true);
	document.getElementById("title").innerHTML = "";
}

