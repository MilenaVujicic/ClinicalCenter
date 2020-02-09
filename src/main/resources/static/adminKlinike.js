let session = null;
$(document).ready(()=>{
	document.getElementById('aVacation').addEventListener('click', listAbsence, false);
	document.getElementById('aApt').addEventListener('click', newApt, false);
	document.getElementById('aPersonalData').addEventListener('click', seeData, false);
	document.getElementById('aEditClinic').addEventListener('click', editClinicData, false);
	document.getElementById('aDefineApt').addEventListener('click', defineNewApt, false);
	document.getElementById('aShowPrices').addEventListener('click', showPrices, false);
	//document.getElementById('aShowRooms').addEventListener('click', showRooms, false);
	document.getElementById('aStats').addEventListener('click', showStats, false);
	document.getElementById('btnSubmit').addEventListener('click', submitDate, false);
	document.getElementById('aNewRoom').addEventListener('click', showNewRoom, false);
	document.getElementById('btnSaveNewRoom').addEventListener('click', newRoom, false);
	document.getElementById('aShowRooms').addEventListener('click', showAllRooms, false);
	document.getElementById('btnSaveRoom').addEventListener('click', editRoomData, false);
	document.getElementById('aLogout').addEventListener('click', logout, false);
	document.getElementById('btnSaveNewExam').addEventListener('click', newExamination, false);
	document.getElementById('aNewAptType').addEventListener('click', newAptType, false);
	document.getElementById('btnSaveAptType').addEventListener('click', saveAptType, false);
	document.getElementById('aNewDoctor').addEventListener('click', addDoctor, false);
	document.getElementById('btnSaveDoctor').addEventListener('click', saveDoctor, false);
	document.getElementById('btnSaveRoomTime').addEventListener('click', saveRoomTime, false);
	document.getElementById('aShowApts').addEventListener('click', showApts, false);
	document.getElementById('btnSaveEditAptType').addEventListener('click', editApts, false);
	document.getElementById('aShowDoctors').addEventListener('click', showDoctors, false);
	session = sessionStorage.getItem("id");
	if(session == null){
		alert('You must be logged in to view this page!');
		window.location.href = "./index.html";
	}
	$.ajax({
		type: "GET",
		url: 'korisnik/preuzmi/' + session,
		success: function(korisnik){
			if(korisnik.uloga != 'ADMIN_KLINIKE'){
				alert('You must be a clinic administrator to access this page');
				window.location.href = "./index.html";
			}
			if(korisnik.brojPrijava === 2){
				window.location.href = './changePresonalData.html'
			}
		},
		error: function(){
			alert('Something went wrong')
		}
	});
	
})

function home() {
	$('#requestsTable').attr('hidden', true);
	$('#freeRooms').attr('hidden', true);
	$('#doctors').attr('hidden', true);
	$('#aptTable').attr('hidden', true);
	$('#freeRoomsApt').attr('hidden', true);
	$('#personalDataTable').attr('hidden', true);
	$('#tAbsence').attr('hidden', true);
	$('#pricingTable').attr('hidden', true);
	$('#divStats').attr('hidden', true);
	$('#newRoomForm').attr('hidden', true);
	$('#showRoomsTable').attr('hidden', true);
	$('#editRoom').attr('hidden', true);
	$('#divTermin').attr('hidden', true);
	$('#divAptType').attr('hidden', true);
	$('#editRoomForm').attr('hidden', true);
	$('#addTimeRoom').attr('hidden', true);
	$('#divApts').attr('hidden', true);
	$('#editAptType').attr('hidden', true);
	$('#doctorsTable').attr('hidden', true);
	$('#divNewDoctor').attr('hidden', true);
	$('#showRoomsTable').parents('div.dataTables_wrapper').first().hide();
	$('#doctorsTable').parents('div.dataTables_wrapper').first().hide();
}

function prikaziDoktora(doktor, specijalizacija) {
	let tr = $('<div class="form-group col-md-3">'+
  			'<div class="custom-control custom-checkbox" >' +
  			'<input type="checkbox" class="form-check-input" id="doktor'+doktor.id+'">'+
			'<label class="custom-control-label" for="doctors'+doktor.id+'">'+doktor.ime+' '+doktor.prezime+' ('+specijalizacija+')'+'</label></div></div>');
	$('#doctors').append(tr);
}


function allDoctors(id) {
	let session = sessionStorage.getItem("id");
	$.ajax({
		url: 'doktor/svi_slobodni_sa_klinike/' + id + '/' + session,
		type: "GET",
		success: function(doktori) {
			$('#doctors').html('');
       		for (let doktor of doktori) {
       			let url = 'doktor/specijalizacija/' + doktor.id;
       			$.ajax({
       				url: url,
       				type: "GET",
       				success: function(specijalizacija) {
       					prikaziDoktora(doktor, specijalizacija);
       				},
       				error: function() {
       					alert('Desila se greska');
       				}
       			});
       		}
       		$('#doctors').attr('hidden', false);
		},
		error: function() {
			alert('Desila se greska kod izlisavanja doktora');
		}
	});
}

function rezSala(sala, operacija) {
	let url = 'operacija/rezervisi/' + sala.id + '/' + operacija.id + '/';
	let session = sessionStorage.getItem("id");
	$.ajax({
		url: 'doktor/svi_slobodni_sa_klinike/' + operacija.id + '/' + session,
		type: "GET",
		success: function(doktori) {
			for (let doktor of doktori) {
				alert(dokotor.id);
       			let str = 'doktor' + doktor.id;
       			let x = document.getElementById(str).checked;
       			if (x == true) {
       				url += '~' + doktor.id;
       			}
       		}

			$.ajax({
				url: url,
				type: "GET",
				success: function(string) {
					alert(string);
					operationRequest();
				},
				error: function() {
					alert('Niste oznacili ni jednog lekara');
				}
			});
		},
		error: function() {
			alert('Desila se greska kod rezervisanja sale');
		}
	});
}

function rezervisiSalu(sala, operacija) {
	return function() {
		rezSala(sala, operacija);
	}
}

function slobodnaSala(sala, operacija) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+sala.ime+'</td>');
	let tdOpis = $('<td>'+sala.opis+'</td>');
	let datum = operacija.datumIVremeOperacije;
	datum = datum.replace("T", " ");
	let tdDate = $('<td>'+datum+'</td>');
	let aReservation = $('<td><a class="button">Reservation</a></td>');
	aReservation.click(rezervisiSalu(sala, operacija));
	tr.append(tdName).append(tdOpis).append(tdDate).append(aReservation);
	$('#freeRooms tbody').append(tr);
}


function slTer1(operacija) {
	home();
	$('#requestsTable').attr('hidden', true);
	$('#freeRooms').attr('hidden', false);
	let session = sessionStorage.getItem("id");
	$.ajax({
		url: '/sala/slobodni_termini/' + operacija.id,
		type: "GET",
		success: function(sale) {
			$('#freeRooms tbody').html('');
			if (sale.length > 0) {
				for (let sala of sale) {
					slobodnaSala(sala, operacija);
				} 
				allDoctors(operacija.id);
			} else {
				$.ajax({
					url: '/sala/drugi_slobodni_termini/' + operacija.id,
					type: "GET",
					success: function(sala) {
						$.ajax({
							url:'operacija/preuzmi/' + operacija.id,
							type:"GET",
							success: function(operacij) {
								slobodnaSala(sala, operacij);
								allDoctors(operacija.id);
							},
							error: function() {
								alert('Desila se greska');
							}
						});
						
					},
					error: function() {
						alert('Nema vise slobodnih sala i termina');
					}
				});
			}
		},
		error: function() {
			alert('Desila se greska');
		}
	});
}

function slobodniTermini(operacija) {
	return function() {
		slTer1(operacija);
	}
}

function dodajZahtev(operacija, pacijent) {
	let tr = $('<tr></tr>');
	let tdIme = $('<td>'+pacijent.ime+' '+pacijent.prezime+'</td>');
	let datum = operacija.datumIVremeOperacije;
	datum = datum.replace("T", " ");
	let tdDate = $('<td>'+datum+'</td>');
	let aMore = $('<td><a>More</a></td>');
	aMore.click(slobodniTermini(operacija));
	tr.append(tdIme).append(tdDate).append(aMore);
	$('#requestsTable tbody').append(tr);
}

function operationRequest() {
	home();
	$('#requestsTable').attr('hidden', false);
	$('#freeRooms').attr('hidden', true);
	$('#doctors').attr('hidden', true);
	let session = sessionStorage.getItem("id");
	$.ajax({
		url: 'operacija/zahtevi',
		type: "GET",
		success: function(operacije) {
			$('#requestsTable tbody').html('');
			for(let operacija of operacije) {
				$.ajax({
					url:'/korisnik/preuzmi/' + operacija.pacijent.idKorisnik,
					type:"GET",
					success: function(pacijent) {
						dodajZahtev(operacija, pacijent);
					},
					error: function() {
						alert('Desila se greska ovde');
					}
				});
			}
			
		},
		error: function() {
			alert('Desila se greska');
		}
	});
	
}

function listAbsence(event){
	event.preventDefault();
	home();
	$('#tAbsence tbody').empty();
	$('#tAbsence').attr('hidden', false);
	
	$.ajax({
		url: 'administrator/sva_odsustva',
		success: function(odsustva){
			
			for(let o of odsustva){
				let id = o.korisnik.id;
				let ido = o.id;
				let name = o.korisnik.ime;
				let surname = o.korisnik.prezime;
				let start = o.pocetakOdsustva;
				start = start.substring(0,10);
				let end = o.zavrsetakOdsustva;
				end = end.substring(0,10);
				let type = o.vrstaOdsustva;
				type = type.toLowerCase();
				
				
				let tdName = $('<td>' + name + ' ' + surname + '</td>');
				let tdStart = $('<td>' + start + '</td>');
				let tdEnd = $('<td>' + end + '</td>');
				let tdType = $('<td>' + type + '</td>');
				let btnAccept = $('<td><button class = \"aButton\" id = \"a' + id + '-' + ido + '\">Accept</button>');
				
				btnAccept.click(function(event){
					//let id = btnAccept.attr('id');
					
					let urlId = 'a' + id + '-' + ido;
					let disableIdMsg = 'msg' + id + '-' + ido;
					let disableIdField =  'txt' + id + '-' + ido;
					let disableIdD = 'd' + id + '-' + ido;
					$.ajax({
						url: 'administrator/odobreno_odsustvo/' + urlId,
						type: 'POST',
						success: function(){
							alert('Successfully allowed the absence');
						},
						error: function(){
							alert('Something went wrong');
						}
						
					})
					$('#' + urlId ).attr('disabled', true);
					$('#' + disableIdMsg ).attr('disabled', true);
					$('#' + disableIdField ).attr('disabled', true);
					$('#' + disableIdD ).attr('disabled', true);
				});
				
				let btnDeny = $('<td><button class = \"dButton\" id = \"d' + id + '-' + ido + '\">Deny</button>');
				btnDeny.click(function(event){
					let urlId = 'd' + id + '-' + ido;
					let enableIdMsg = 'msg' + id + '-' + ido;
					let enableIdField = 'txt' + id + '-' + ido;
					let disableIdA = 'a' + id + '-' + ido;
					$('#' + disableIdA).attr('disabled', true);
					$('#' + enableIdMsg).attr('disabled', false);
					$('#' + enableIdField).attr('disabled', false);
				});
				let txtExp = $('<input type = \"text\" class = \"tField\" disabled = \"true\" id = \"txt' + id +'-' + ido + '\">');
				let btnAddMessage = $('<td><button disabled = \"true\" class = \"msgButton\" id = \"msg' + id + '-' + ido + '\">Add Message</button>');
				btnAddMessage.click(function(event){
					let urlId = 'd' + id + '-' + ido;
					let aId = 'a' + id + '-' + ido;
					let txtId = 'txt' + id + '-' + ido;
					let message = $('#' + txtId).val();
					if(message === ''){
						alert('Message must not be empty');
						return;
					}else{
						$.ajax({
							url: 'administrator/odbijeno_odsustvo/' + urlId,
							type: 'POST',
							data: {message: message},
							success: function(){
								alert('Successfully denied the absence');
							},
							error: function(){
								alert('Something went wrong');
							}
						})
					}
				})
				
				let tr = $('<tr></tr');
				tr.append(tdName).append(tdStart).append(tdEnd).append(tdType).append(btnAccept).append(btnDeny).append(txtExp).append(btnAddMessage);
				$('#tAbsence tbody').append(tr);
				
			}
			
		},
		error: function(){
			alert('Something went wrong');
		}
	})
	
	
}

//#############################################################
//#############################################################
//#############################################################
//#############################################################
//#############################################################

function newApt(event){
	event.preventDefault();
	home();
	$('#aptTable').attr('hidden', false);
	aptRequest();
}

function rezSalaApt(sala, pregled) {
	let url = 'pregled/rezervisi/' + sala.id + '/' + pregled.id + '/';
	$.ajax({
		url: 'doktor/svi_slobodni_sa_klinike_apt/' + pregled.id,
		type: "GET",
		success: function(doktori) {
			for (let doktor of doktori) {
       			let str = 'doktor' + doktor.id;
       			alert(str);
       			let x = document.getElementById(str).checked;
       			if (x == true) {
       				url += '~' + doktor.id;
       			}
       		}
			
			$.ajax({
				url: url,
				type: "GET",
				success: function(string) {
					alert(string);
					operationRequest();
				},
				error: function() {
					alert('Niste oznacili ni jednog lekara');
				}
			});
		},
		error: function() {
			alert('Desila se greska kod rezervisanja sale');
		}
	});
}

function slobodnaProstorija(sala, pregled) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+sala.ime+'</td>');
	let tdOpis = $('<td>'+sala.opis+'</td>');
	let datum = pregled.datumIVremePregleda;
	datum = datum.replace("T", " ");
	let tdDate = $('<td>'+datum+'</td>');
	let aReservation = $('<td><a class="button">Reservation</a></td>');
	aReservation.click(rezervisiProstoriju(sala, pregled));
	tr.append(tdName).append(tdOpis).append(tdDate).append(aReservation);
	$('#freeRoomsApt tbody').append(tr);
}

function aptRequest() {
	home();
	$('#aptTable').attr('hidden', false);
	$('#freeRoomsApt').attr('hidden', true);
	$('#doctors').attr('hidden', true);
	$.ajax({
		url: '/pregled/zahtevi/' + sessionStorage.getItem("id"),
		type: "GET",
		success: function(pregledi) {
			$('#aptTable tbody').html('');
			for(let pregled of pregledi) {
				$.ajax({
					url:'/korisnik/preuzmi/' + pregled.pacijent.id,
					type:"GET",
					success: function(pacijent) {
						dodajZahtevApt(pregled, pacijent);
					},
					error: function() {
						alert('Desila se greska');
					}
				});
			}
			
		},
		error: function() {
			alert('Desila se greska');
		}
	});
	
}

function dodajZahtevApt(pregled, pacijent) {
	let tr = $('<tr></tr>');
	let tdIme = $('<td>'+pacijent.ime+' '+pacijent.prezime+'</td>');
	let datum = pregled.datumIVremePregleda;
	datum = datum.replace("T", " ");
	let tdDate = $('<td>'+datum.substring(0,10)+'</td>');
	let aMore = $('<td><a class = "more">More</a></td>');
	aMore.click(slobodniTerminiApt(pregled));
	tr.append(tdIme).append(tdDate).append(aMore);
	$('#aptTable tbody').append(tr);
}



function slTer(pregled) {
	home();
	$('#aptTable').attr('hidden', true);
	$('#freeRoomsApt').attr('hidden', false);
	
	$.ajax({
		url: '/sala/termini_pregled/' + pregled.id,
		type: "GET",
		success: function(sale) {
			$('#freeRooms tbody').html('');
			if (sale.length > 0) {
				for (let sala of sale) {
					slobodnaProstorija(sala, pregled);
				} 
				allDoctors(pregled.id);
			} else {
				$.ajax({
					url: '/sala/drugi_termini_pregled/' + pregled.id,
					type: "GET",
					success: function(sala) {
						$.ajax({
							url:'pregled/preuzmi/' + pregled.id,
							type:"GET",
							success: function(pregled2) {
								slobodnaProstorija(sala, pregled2);
								allDoctors(pregled2.id);
							},
							error: function() {
								alert('Desila se greska');
							}
						});
						
					},
					error: function() {
						alert('Nema vise slobodnih sala i termina');
					}
				});
			}
		},
		error: function() {
			alert('Desila se greska');
		}
	});
}

function seeData(event){
	event.preventDefault();
	home();
	$('#personalDataTable').attr('hidden', false);
	$('#personalDataTable tbody').empty();
	$.ajax({
		url: 'korisnik/preuzmi/' + session,
		type: 'GET',
		success: function(korisnik){
			let tr = $('<tr></tr>');
			let tdName = $('<td>' + korisnik.ime + '</td>');

			let tdSurname = $('<td>' + korisnik.prezime + '</td>');
			
			let tdJmbg = $('<td>' + korisnik.jmbg + '</td>');
			
			let tdCity = $('<td>' + korisnik.grad + '</td>');
		
			let tdCountry = $('<td>' + korisnik.drzava + '</td>');
			
			let tdAddress = $('<td>' + korisnik.adresa + '</td>');

			let tdDob = $('<td>' + korisnik.datumRodjenja.substring(0,10) + '</td>');
			
			let tdPhone = $('<td>' + korisnik.telefon + '</td>');
			
			let tdEmail = $('<td>' + korisnik.email + '</td>');
			
			let tdAction = $('<td><a href = "#" id = "aEditPersonalData" >Edit data </a></td>');
			
			tdAction.on('click', function(event){
				event.preventDefault();
				window.location.href = './changePresonalData.html';
			});
			tr.append(tdName).append(tdSurname).append(tdJmbg).append(tdCity).append(tdCountry)
			.append(tdAddress).append(tdDob).append(tdPhone).append(tdEmail).append(tdAction);
			
			$('#personalDataTable tbody').append(tr);
		},
		error: function(){
			alert('Something went wrong');
		}
	})
}

function slobodniTerminiApt(pregled) {
	return function() {
		slTer(pregled);
	}
}

function rezervisiProstoriju(sala, pregled) {
	return function() {
		rezSalaApt(sala, pregled);
	}
}

function editClinicData(event){
	event.preventDefault();
	window.location = './clinicEdit.html';
}




function showDoctors(event){
	event.preventDefault();
	home();
	$('#doctorsTable').attr('hidden', false);
	//$('#doctorsTable tbody').empty();
	$('#doctorsTable').parents('div.dataTables_wrapper').first().show();
	$.ajax({
		url: 'doktor/svi_sa_klinike/' + sessionStorage.getItem("id"),
		type: 'GET',
		success: function(doktori){
			var table = $('#doctorsTable').DataTable();
       		table.destroy();
       	    $('#doctorsTable tbody').html('');
			for(let i = 0; i < doktori.length; i++)
				formatDoctor(doktori[i], i);
			
			$('#doctorsTable').DataTable({
       	        "columnDefs": [ {
       	          "targets": 'no-sort',
       	          "orderable": false,
       	        }]
       		});
		},
		error: function(){
			alert('Something went wrong');
		}
		
	});
	
	
}


function formatDoctor(doctor, i){
	let tr = $('<tr></tr>');
	let tdName = $('<td>' + doctor.ime + '</td>');
	let tdSurname = $('<td>' + doctor.prezime + '</td>');
	let tdI = $('<td>' + i + '</td>');
	let tdSpecialization = $('<td></td>')
	let tdRating = $('<td></td>');
	let tdActions = $('<td></td>')
	$.ajax({
		url: '/doktor/korisnik_doktor/' + doctor.id,
		type:'GET',
		success: function(drData){
			tdSpecialization.append(drData.specijalizacija);
			tdRating.append(drData.prosecnaOcena);

			let aDelete = $('<a href = "#">Delete</a>');
			aDelete.on('click', function(event){
				event.preventDefault();
				
				$.ajax({
					url: 'doktor/obrisiLekara/' + doctor.id,
					type: 'DELETE',
					success: function(dr){
						if(dr == null){
							alert("You cannot delete a doctor with appointments");
						}else{
							alert("The doctor has been deleted");
							location.reload();
						}
					},
					error: function(){
						alert('Something went wrong');
					}
				});
			});
			tdActions.append(aDelete);
		
		},
		error: function(){
			alert("Something went wrong");
		}
	});
	tr.append(tdI).append(tdName).append(tdSurname).append(tdSpecialization).append(tdRating).append(tdActions);
	$('#doctorsTable tbody').append(tr);
}


function showPrices(event){
	event.preventDefault();
	home();
	$('#pricingTable').attr('hidden', false);
	$('#pricingTable tbody').empty();
	
	$.ajax({
		url:'pregled/svi_tipovi',
		type:'GET',
		success: function(examinations){
			for(let ex of examinations){
				let tr = $('<tr></tr>');
				let tdName = $('<td>' + ex.naziv + '</td>');
				let tdPrice = $('<td>' + ex.cena + '</td>');
				tr.append(tdName).append(tdPrice);
				$('#pricingTable tbody').append(tr);
			}
		},
		error: function(){
			alert('Something went wrong');
		}
	});
	
}	

function showRooms(event){
	event.preventDefault();
	window.location = './sale.html';
}

function formatDoctor2(doctor){
	let tr = $('<tr></tr>');
	let tdName = $('<td>' + doctor.ime + '</td>');
	let tdSurname = $('<td>' + doctor.prezime + '</td>');
	
	
	tr.append(tdName).append(tdSurname);

	$.ajax({
		url: '/doktor/korisnik_doktor/' + doctor.id,
		type:'GET',
		success: function(drData){
			let tdRating = $('<td>' + drData.prosecnaOcena + '</td>');
			tr.append(tdRating);
			
			
		},
		error: function(){
			alert("Something went wrong");
		}
	});
	
	$('#doctorsRatingTable tbody').append(tr);
}

function showStats(event){
	event.preventDefault();
	home();
	$('#divStats').attr('hidden', false);
	$('#pClRating').empty();

	$.ajax({
		url:'klinika/klinikaAdmin/' + sessionStorage.getItem("id"),
		type: 'GET',
		success: function(klinika){
			$('#pClRating').append('Clinic Rating: ' + klinika.prosecnaOcena);
		},
		error: function(){
			alert('Something went wrong');
		}
	});
	
	$.ajax({
		url: 'pregled/pregledi_klinika/'+ sessionStorage.getItem("id"),
		type: 'GET',
		success: function(brojevi){
			canvasReset();
			var canvas = document.getElementById("cGraph");
			var ctx = canvas.getContext("2d");
			var X = 0;
			var width = 50;
			
			var Y = 0;
			var height = 100;
			ctx.fillStyle = "#fff1c4";
			
			for(var i = 0; i < 3; i++){
				var h = brojevi[i];
				ctx.fillRect(X, canvas.height-h, width, h);
				
				X += width+50;
				
				if(i==0){
					ctx.fillText("Day: " + brojevi[i],0,h+20);
				}else if(i==1){
					ctx.fillText("Week: " + brojevi[i],100,h+20);
				}else if(i==2){
					ctx.fillText("Month: " + brojevi[i],200,h+20);
				}
				
			}
			/*ctx.fillText("Day",0,h+20  );
			ctx.fillText("Week",100,h+20 );
			ctx.fillText("Month",200,h+20 );*/
		},
		error: function(){
			alert('Something went wrong');
		}
	});
	$('#doctorsRatingTable tbody').empty();
	$.ajax({
		url:'doktor/svi_sa_klinike/' + sessionStorage.getItem("id"),
		type: 'GET',
		success: function(doktori){
			for(let doktor of doktori){
				formatDoctor2(doktor);
			}
		},
		error: function(){
			alert('Something went wrong');
		}
	});
}

function canvasReset(){
	var canvas = document.getElementById("cGraph");
	var ctx = canvas.getContext("2d");
	ctx.clearRect(0,0, canvas.width, canvas.height);
}
function submitDate(event){
	event.preventDefault();
	
	let beginDate = $('#dBegin').val();
	let endDate = $('#dEnd').val();
	if(beginDate == '' || endDate == ''){
		alert('Dates must not be empty');
		return;
	}
	$('#hEarnings').empty();
	$.ajax({
		url: 'klinika/getZarada/' + sessionStorage.getItem("id"),
		type: 'POST',
		data: JSON.stringify({beginDate : beginDate, endDate : endDate}),
		contentType: 'application/json',
		success: function(zarada){
			$('#hEarnings').append("Total earnings: " + zarada);
		},
		error: function(){
			alert('Something went wrong');
		}
	})
}

function showNewRoom(event){
	event.preventDefault();
	home();
	$('#newRoomForm').attr('hidden', false);
	
	
}

function newRoom(event){
	event.preventDefault();
	let name = $('#uRoomName').val();
	let desc = $('#uRoomDesc').val();
	
	if(name === '' || desc === ''){
		alert('Fields must not be empty');
		return;
	}
	
	$.ajax({
		url:'sala/nova_sala/' + sessionStorage.getItem("id"),
		type:'POST',
		data: JSON.stringify({name, desc}),
		contentType:'application/json',
		success: function(){
			alert("The room has been added");
			$('#uRoomName').val('');
			$('#uRoomDesc').val('');
			home();
		},
		error: function(){
			alert('Something went wrong');
		}
	});
}

function showTableRoom(room, i){
	
	let tr = $('<tr></tr>');
	let tdNum = $('<td>' + i + '</td>');
	let tdName = $('<td>' + room.ime + '</td>');
	let tdDesc = $('<td>' + room.opis + '</td>');
	let tdDates = $('<td></td>');
	$.ajax({
		url:'sala/sve_sale_klinika_termini/' + room.id,
		type: 'GET',
		success: function(termin){
			for(let t of termin){
				tdDates.append(t.datumStr);
				tdDates.append('<br/>');
			}
		},
		error: function(){
			alert('Something went wrong');
		}
	});
	
	let tdActions = $('<td></td>');
	
	let aEdit = $('<a href = "#">Edit Room </a><br/>');
	let aNewDate = $('<a href = "#">New Date</a><br/>');
	let aDelete = $('<a href = "#">Delete</a>');
	
	aEdit.on('click', function(event){
		event.preventDefault();
		home();
		$('#editRoomForm').attr('hidden', false);
		$('#euRoomName').val(room.ime);
		$('#euRoomDesc').val(room.opis);
		$('#btnSaveEditRoom').on('click', function(event){
			event.preventDefault();
			let name = $('#euRoomName').val();
			let desc = $('#euRoomDesc').val();
			let id = room.id;
			$.ajax({
				url: 'sala/izmena_sale/' + sessionStorage.getItem("id"),
				type: 'PUT',
				data: JSON.stringify({name, desc, id}),
				contentType: 'application/json',
				success: function(str){
					alert('The room data has been changed');
					$('#euRoomName').val("");
					$('#euRoomDesc').val("");
					home();
				},
				error: function(){
					alert('Something went wrong');
				}
			});
		});
	});
	
	aNewDate.on('click', function(event){
		event.preventDefault();
		home();
		$('#addTimeRoom').attr('hidden', false);
		$('#lId').append(room.id);
	});
	
	aDelete.on('click', function(event){
		event.preventDefault();
		$.ajax({
			url:'sala/obrisi_salu/' + room.id,
			type:'DELETE',
			success: function(free){
				if(free === 1){
					alert('The room was removed');
					home();
				}else{
					alert('The room is already reserved and cannot be deleted');
				}
			},
			error: function(){
				alert('Something went wrong');
			}
		})
	});
	
	tdActions.append(aEdit).append(aNewDate).append(aDelete);
	
	tr.append(tdNum).append(tdName).append(tdDesc).append(tdDates).append(tdActions);
	
	$('#showRoomsTable tbody').append(tr);
}
function showAllRooms(event){
	event.preventDefault();
	home();
	//$('#showRoomsTable tbody').empty();
	$('#showRoomsTable').attr('hidden', false);
	$('#showRoomsTable').parents('div.dataTables_wrapper').first().show();
	$.ajax({
		url:'sala/sve_sale_klinika/' + sessionStorage.getItem("id"),
		type:'GET',
		success: function(sale){
			var table = $('#showRoomsTable').DataTable();
			table.destroy();
			 $('#showRoomsTable tbody').html('');
			 let i = 0;
			for(let s of sale){
				showTableRoom(s, i);
				i = i + 1;
			}
			$('#showRoomsTable').DataTable({
       	        "columnDefs": [ {
       	          "targets": 'no-sort',
       	          "orderable": false,
       	        }]
       		});
		},
		error: function(){
			alert('Something went wrong');
		}
	});
}

function editRoom(room){
	home();
	$('#editRoom').attr('hidden', false);
	$('#eRoomName').val(room.ime);
	$('#eRoomDesc').val(room.opis);
}

function editRoomData(event){
	event.preventDefault();
}

function logout(event){
	event.preventDefault();
	$.ajax({
		url: 'auth/logout',
		type: "GET",
		success: function(){
			sessionStorage.removeItem("id");
			let session = sessionStorage.getItem("id");
			if (session == null) {
				window.location.href = "http://localhost:8080/index.html";
			}
		},
		error: function() {
			alert('Something went wrong');
		}
	});
}

function defineNewApt(event){
	event.preventDefault();
	home();
	$('#divTermin').attr('hidden', false);
	
	$.ajax({
		url: 'sala/sve_sale_klinika/' + sessionStorage.getItem("id"),
		type: 'GET',
		success: function(sale){
			for(let s of sale){
				let option = $('<option id = "' + s.id + '">' + s.ime + '</option>');
				$('#sRoomTermin').append(option);
				
			}
		},
		error: function(){
			alert('Something went wrong');
		}
	});
	
	$.ajax({
		url: 'pregled/svi_tipovi',
		type: 'GET',
		success: function(pregledi){
			for(let p of pregledi){
				let option = $('<option id = "' + p.id + '">' + p.naziv + '</option>');
				$('#sExamType').append(option);
			}
		}
	});
	
	$.ajax({
		url: 'doktor/svi_sa_klinike/' + sessionStorage.getItem("id"),
		type: 'GET',
		success: function(doktori){
			for(let d of doktori){
				let option = $('<option id = "' + d.id + '">' + d.ime + " " + d.prezime + '</option>');
				$('#sDoctorTermin').append(option);
			}
		}
	});
}

function newExamination(event){
	event.preventDefault();
	
	let salaId = $('#sRoomTermin').find('option:selected').attr("id");
	let tipId = $('#sExamType').find('option:selected').attr("id");
	let doktorId = $('#sDoctorTermin').find('option:selected').attr("id");
	let datum = $("#dateNewExam").val();
	let vreme = $('#timeNewExam').val();
	
	if(datum === '' || vreme === ''){
		alert("Time and date must not be empty");
		return;
	}
	
	$.ajax({
		url: 'pregled/novi_pregled_admin/' + sessionStorage.getItem("id"),
		type: 'POST',
		data: JSON.stringify({salaId, tipId, doktorId, datum, vreme}),
		contentType: 'application/json',
		success: function(str){
			alert('The appointment has been added');
			home();
		},
		error: function(){
			alert('Something went wrong');
		}
	});
}

function newAptType(event){
	event.preventDefault();
	home();
	$('#divAptType').attr('hidden', false);
}

function saveAptType(event){
	event.preventDefault();
	
	let name = $('#nameNewApt').val();
	let price = $('#priceNewApt').val();
	
	if(name === '' || price === ''){
		alert("Fields must not be empty");
		return;
	}
	
	$.ajax({
		url:'pregled/novi_tip_pregleda',
		type: 'POST',
		data: JSON.stringify({name, price}),
		contentType: 'application/json',
		success: function(str){
			alert("New appointment type has been added");
			$('#nameNewApt').val('');
			$('#priceNewApt').val('');
			home();
		},
		error: function(){
			alert("Something went wrong");
		}
	});
}

function addDoctor(event){
	event.preventDefault();
	home();
	$('#divNewDoctor').attr('hidden', false);
	
	$.ajax({
		url: 'korisnik/user_not_doctor/' + sessionStorage.getItem("id"),
		type:'GET',
		success: function(korisnici){
			for (let k of korisnici){
				let option = $('<option id = "' + k.id + '">' + k.ime + " " + k.prezime + '</option>');
				$('#selNewDoctor').append(option);
			}
		}
	});
}

function saveDoctor(event){
	event.preventDefault();
	let userId = $('#selNewDoctor').find('option:selected').attr("id");
	let spec = $('#selSpecialization').val();
	
	if(spec === ''){
		alert('Specialization must not be empty');
		return;
	}
	$.ajax({
		url: 'doktor/novi_doktor/' + sessionStorage.getItem("id"),
		type:'POST',
		data: JSON.stringify({userId, spec}),
		contentType: 'application/json',
		success: function(str){
			alert("Doctor added");
			home();
		},
		error: function(){
			alert('Something went wrong');
		}
	})
}

function saveRoomTime(event){
	event.preventDefault();
	let date = $('#roomFreeDate').val();
	let time = $('#roomFreeTime').val();
	let id = $('#lId').html();
	if(date === '' || time === ''){
		alert("Time and date must not be empty");
		return;
	}
	
	$.ajax({
		url: 'sala/novi_termin/' + id,
		type: 'POST',
		data: JSON.stringify({date, time}),
		contentType: 'application/json',
		success: function(str){
			alert("Time has been added");
			$('#roomFreeDate').val('');
			$('#roomFreeTime').val('');
			$('#lId').html('');
			home();
		}
	})
}

function showApts(event){
	event.preventDefault();
	home();
	$('#divApts').attr('hidden', false);
	//$('#aptsTable tbody').empty();
	$('#aptsTable').parents('div.dataTables_wrapper').first().show();
	$.ajax({
		url: 'pregled/svi_tipovi',
		type: 'GET',
		success: function(pregledi){
			var table = $('#aptsTable').DataTable();
       		table.destroy();
       	    $('#aptsTable tbody').html('');
       		let i = 0;
			for(let p of pregledi){
				showPregled(p, i);
				i = i + 1;
				
				
			}
			$('#aptsTable').DataTable({
       	        "columnDefs": [ {
       	          "targets": 'no-sort',
       	          "orderable": false,
       	        }]
       		});
		
		}
	});
}

function showPregled(p, i){
	let tr = $('<tr></tr>');
	let tdName = $('<td>' + p.naziv + '</td>');
	let tdPrice = $('<td>' + p.cena + '</td>');
	let tdActions = $('<td></td>');
	
	let aEdit = $('<a href = "#">Edit</a><br/>');
	let aDelete = $('<a href = "#">Delete </a>');
	
	aEdit.on('click', function(event){
		event.preventDefault();
		
		$.ajax({
			url: 'pregled/nadji_tip/' + p.id,
			success: function(pregled){
				home();
				$('#editAptType').attr('hidden', false);
				$('#editAptName').val(pregled.naziv);
				$('#editAptPrice').val(pregled.cena);
				$('#lAptId').html(pregled.id);
			},
			error: function(){
				alert('Something went wrong');
			}
		});
		
	});
	
	aDelete.on('click', function(event){
		event.preventDefault();
		
		$.ajax({
			url: 'pregled/obrisi_tip/' + p.id,
			type: 'DELETE',
			success: function(i){
				if(i===0)
					alert('Successfully deleted appointment');
				else
					alert("Cannot delete type");
			},
			error: function(){
				alert('Something went wrong');
			}
		});
	});
	
	tdActions.append(aEdit).append(aDelete);
	
	tr.append(tdName).append(tdPrice).append(tdActions);
	$('#aptsTable tbody').append(tr);
}
function editApts(event){
	event.preventDefault();
	
	let id = $('#lAptId').html();
	let name = $('#editAptName').val();
	let price = $('#editAptPrice').val();
	
	if(name === '' || price === ''){
		alert("Fields must not be empty");
		return;
	}
		
	$.ajax({
		url: 'pregled/uredi_tip',
		type: 'POST',
		data: JSON.stringify({id, name, price}),
		contentType: 'application/json',
		success: function(){
			alert("Successfully edited apointment type");
			 $('#lAptId').html('');
			 $('#editAptName').val('');
			 $('#editAptPrice').val('');
			 home();
		},
		error: function(){
			alert('Something went wrong');
		}
	});
}

