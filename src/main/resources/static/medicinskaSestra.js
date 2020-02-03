function detaljanPrikazPacijenta(pacijent) {
	return function() {
		$.ajax({
	        url:"/medicinska_sestra/pacijent/" + pacijent.id,
	        type:"GET",
	       	success: function(pacijenti){
	       		$('#patientsTable').parents('div.dataTables_wrapper').first().hide();
	       		$('#patientName').val(pacijent.ime)
	       		$('#patientSurname').val(pacijent.prezime)
	       		let datum = pacijent.datumRodjenja.toString().substr(0, 10);
	       		$('#patientBirth').val(datum)
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
	let datum = pacijent.datumRodjenja.toString().substr(0, 10);
	let tdDatumRodjenja = $('<td>'+datum+'</td>');
	let tdJMBG = $('<td>'+pacijent.jmbg+'</td>');
	let tdMore = $('<td><a href="#'+pacijent.ime+'_'+pacijent.prezime+'">More</a></td>');
	tdMore.click(detaljanPrikazPacijenta(pacijent));
	tr.append(tdIme).append(tdPrezime).append(tdDatumRodjenja).append(tdJMBG).append(tdMore);
	$('#patientsTable tbody').append(tr);
}	

function home() {
	$('#patientsTable').parents('div.dataTables_wrapper').first().hide();
	$('#patientsTable').attr('hidden', true);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#personalData').attr('hidden', true);
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
	let session = sessionStorage.getItem("id");
	let url = "/medicinska_sestra/overi/" + session + '/' +id;
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
	$('#patientsTable').parents('div.dataTables_wrapper').first().hide();
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
			$('#calendar').attr('hidden', true);
			$('#personalData').attr('hidden', true);
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
 	});
}

function allPatients() {
	$('#patientsTable').parents('div.dataTables_wrapper').first().show();
	let session = sessionStorage.getItem("id");
	$.ajax({
        url:"/medicinska_sestra/sviPacijenti/" + session,
        type:"GET",
       	success: function(pacijenti){
       		document.getElementById("title").innerHTML = "All patients";
       		var table = $('#patientsTable').DataTable();
       		table.destroy();
            $('#patientsTable tbody').html('');
       		for (i = 0; i < pacijenti.length; i++) {
       			prikaziPacijenta(pacijenti[i]);
       		}
       		$('#patientsTable').DataTable({
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
	
	$('#patientsTable').attr('hidden', false);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', true);
	$('#calendar').attr('hidden', true);
	$('#personalData').attr('hidden', true);
}

function requestHoliday() {
	let type = $('#selectRest').val();
	let to = $('#restTo').val();
	let from = $('#restFrom').val();
	let session = sessionStorage.getItem("id");
	let url = "/medicinska_sestra/odmor/" + type + "~" + from + "~" + to +"/" + session;
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
	$('#patientsTable').parents('div.dataTables_wrapper').first().hide();
	$('#patientsTable').attr('hidden', true);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', false);
	$('#calendar').attr('hidden', true);
	$('#personalData').attr('hidden', true);
	document.getElementById("title").innerHTML = "Holiday request";
}

function showCalendar(odsustva) {
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
	    eventLimit: true
	});
	
	for (let odsustsvo of odsustva) {
		let color = 'red';
		if (odsustsvo.odobren) {
			color = 'green';
		}
		var event={title: odsustsvo.vrstaOdsustva , start:odsustsvo.pocetakOdsustva, end:odsustsvo.zavrsetakOdsustva, color:color};
		$('#calendar').fullCalendar( 'renderEvent', event, true);
	}
}

function calendar() {
	$('#patientsTable').parents('div.dataTables_wrapper').first().hide();
	$('#patientsTable').attr('hidden', true);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', true);
	$('#personalData').attr('hidden', true);
	document.getElementById("title").innerHTML = "";
	let session = sessionStorage.getItem("id");
	$.ajax({
		url:"/medicinska_sestra/kalendar/" + session,
        type:"GET",
       	success: function(odsustva){
       		$('#calendar').fullCalendar('removeEvents');
       		showCalendar(odsustva);
       	},
       	error: function() {
       		alert('Desila se greska');
       	}
	});
}

function personalData() {
	$('#patientsTable').parents('div.dataTables_wrapper').first().hide();
	$('#patientsTable').attr('hidden', true);
	$('#patient').attr('hidden', true);
	$('#recipesForm').attr('hidden', true);
	$('#restForm').attr('hidden', true);
	document.getElementById("title").innerHTML = "";
	$('#personalData').attr('hidden', false);
	let session = sessionStorage.getItem("id");
	$.ajax({
		type:"GET",
		url:'korisnik/preuzmi/' + session,
		success: function(korisnik) {
			$('#lblFirstName').val(korisnik.ime);
			$('#lblLastName').val(korisnik.prezime);
			$('#lblDateOfBirth').val(korisnik.datumRodjenja.toString().substr(0, 10));
			$('#lblCity').val(korisnik.grad);
			$('#lblCounty').val(korisnik.drzava);
			$('#lblAddress').val(korisnik.adresa);
			$('#lblEmail').val(korisnik.email);
			$('#lblPhone').val(korisnik.telefon);
		},
		error: function() {
			alert('Desila se greska');
		}
	});
}

function changeData() {
	let ime = $('#lblFirstName').val();
	let prezime =$('#lblLastName').val();
	let datumRodjenja = $('#lblDateOfBirth').val();
	let grad = $('#lblCity').val();
	let drzava =$('#lblCounty').val();
	let adresa = $('#lblAddress').val();
	let email = $('#lblEmail').val();
	let telefon = $('#lblPhone').val();
	let id = sessionStorage.getItem("id");
	$.ajax({
		type: "PUT",
		url: 'korisnik/izmena_podataka/' + id,
		data: JSON.stringify({ime, prezime, email, adresa, grad, drzava, telefon, datumRodjenja}),
        contentType:'application/json',
        success: function() {
        	alert('Uspesno promenjeni podaci');
        	personalData();
        },
        error: function() {
        	alert('Desila se greska');
        }
	});
}

$(document).ready(function() {
	let session = sessionStorage.getItem("id");
	if (session == null) {
		alert('Nemate prava pristupa ovoj stranici');
		window.location.href = "http://localhost:8080/index.html";
	}
	$.ajax({
		type: "GET",
		url: 'korisnik/preuzmi/' + session,
		success: function(korisnik) {
			if (korisnik.uloga != 'MEDICINSKA_SESTRA') {
				alert('Nemate prava pristupa ovoj stranici');
				window.location.href = "http://localhost:8080/medicinskaSestra.html";
			}
		},
		error: function() {
			alert('Nema ulogovanog korisnika');
		}
	});
});

function logout() {
	sessionStorage.removeItem("id");
	let session = sessionStorage.getItem("id");
	if (session == null) {
		window.location.href = "http://localhost:8080/index.html";
	}
}





