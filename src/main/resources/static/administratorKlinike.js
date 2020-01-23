$(document).ready(()=>{
	document.getElementById('aVacation').addEventListener('click', listAbsence, false);
})

function home() {
	$('#requestsTable').attr('hidden', true);
	$('#freeRooms').attr('hidden', true);
	$('#doctors').attr('hidden', true);
}

function prikaziDoktora(doktor) {
	let tr = $('<div class="form-group col-md-3">'+
  			'<div class="custom-control custom-checkbox" >' +
  			'<input type="checkbox" class="form-check-input" id="doktor'+doktor.id+'">'+
			'<label class="custom-control-label" for="doctors'+doktor.id+'">'+doktor.ime+' '+doktor.prezime+'</label></div></div>');
	$('#doctors').append(tr);
}

function allDoctors(id) {
	$.ajax({
		url: 'doktor/svi_slobodni_sa_klinike/' + id,
		type: "GET",
		success: function(doktori) {
			$('#doctors').html('');
       		for (let doktor of doktori) {
       			prikaziDoktora(doktor);
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
	$.ajax({
		url: 'doktor/svi_slobodni_sa_klinike/' + operacija.id,
		type: "GET",
		success: function(doktori) {
			for (let doktor of doktori) {
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

function slTer(operacija) {
	$('#requestsTable').attr('hidden', true);
	$('#freeRooms').attr('hidden', false);
	
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
		slTer(operacija);
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
	$('#requestsTable').attr('hidden', false);
	$('#freeRooms').attr('hidden', true);
	$('#doctors').attr('hidden', true);
	$.ajax({
		url: '/operacija/zahtevi',
		type: "GET",
		success: function(operacije) {
			$('#requestsTable tbody').html('');
			for(let operacija of operacije) {
				$.ajax({
					url:'/korisnik/preuzmi/' + operacija.pacijent.id,
					type:"GET",
					success: function(pacijent) {
						dodajZahtev(operacija, pacijent);
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

function listAbsence(event){
	event.preventDefault();
	
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


function acceptAbsence(){
	console.log(this.id);
}


function denyAbsence(){
}