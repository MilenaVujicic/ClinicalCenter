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
			if (korisnik.uloga != 'ADMIN_CENTRA') {
				alert('Nemate prava pristupa ovoj stranici!\nSistem Vas je logoutovao');
				logout();
			}

			if (korisnik.brojPrijava < 2) {
				$('#con').attr('hidden', false);
			}
			else {
				$('#main').attr('hidden', false);
			}
		},
		error: function() {
			alert('Nema ulogovanog korisnika');
		}
	});
	$('.pass_show').append('<span class="ptxt">Show</span>');  
	
});

$(document).on('click','.pass_show .ptxt', function(){ 

	$(this).text($(this).text() == "Show" ? "Hide" : "Show"); 

	$(this).prev().attr('type', function(index, attr){return attr == 'password' ? 'text' : 'password'; }); 

});  

function newClinicAdministrator() {
	let ime = $('#nameClicnicAdmin').val();
	let prezime = $('#surnameClicnicAdmin').val();
	let email = $('#emailClicnicAdmin').val();
	let adresa = $('#addressClicnicAdmin').val();
	let grad = $('#cityClicnicAdmin').val();
	let drzava = $('#stateClicnicAdmin').val();
	let telefon = $('#jmbgClicnicAdmin').val();
	let id = $('#idClinic').val() 
	let url = "/admin_klinickog_centra/novi_admin_klinike/";
	url += id;

	$.ajax({
        url:url,
        type:"POST",
        data: JSON.stringify({ime, prezime, email, adresa, grad, drzava, telefon}),
        contentType:'application/json',
        success: function(odgovor) {
        	$('#nameClicnicAdmin').val('');
  			$('#surnameClicnicAdmin').val('');
  			$('#emailClicnicAdmin').val('');
  			$('#addressClicnicAdmin').val('');
  			$('#cityClicnicAdmin').val('');
  			$('#stateClicnicAdmin').val('');
  			$('#jmbgClicnicAdmin').val('');
  			$('#idClinic').val(''); 
  			document.getElementById("title").innerHTML = "";
  			
        	$('#newAdmin').attr('hidden', true);
       		$('#adminErrorAlert').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', false);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#newClinic').attr('hidden', true);
			$('#clinicTable').attr('hidden', true);
			$('#clinicAdminTable').attr('hidden', true);
			$('#requestsTable').attr('hidden', true);
			$('#drugs').attr('hidden', true);
			$('#diagnosis').attr('hidden', true);
			$('#allDiagnosis').attr('hidden', true);
			$('#editDiagnosis').attr('hidden', true);
			$('#personalData').attr('hidden', true);
        },
        error: function() {
        	$('#nameClicnicAdmin').val('');
  			$('#surnameClicnicAdmin').val('');
  			$('#emailClicnicAdmin').val('');
  			$('#addressClicnicAdmin').val('');
  			$('#cityClicnicAdmin').val('');
  			$('#stateClicnicAdmin').val('');
  			$('#jmbgClicnicAdmin').val('');
  			$('#idClinic').val('');
  			document.getElementById("title").innerHTML = "";
        	$('#adminErrorAlert').attr('hidden', false);
  			$('#newAdmin').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', true);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#newClinic').attr('hidden', true);
			$('#clinicTable').attr('hidden', true);
			$('#clinicAdminTable').attr('hidden', true);
			$('#requestsTable').attr('hidden', true);
			$('#drugs').attr('hidden', true);
			$('#diagnosis').attr('hidden', true);
			$('#allDiagnosis').attr('hidden', true);
			$('#editDiagnosis').attr('hidden', true);
			$('#personalData').attr('hidden', true);
        }
    });
	
}


function newAdministrator() {
	let ime = $('#name').val();
	let prezime = $('#surname').val();
	let email = $('#email').val();
	let adresa = $('#address').val();
	let grad = $('#city').val();
	let drzava = $('#state').val();
	let jmbg = $('#jmbg').val();
	
	$.ajax({
        url:"/admin_klinickog_centra/novi_admin",
        type:"POST",
        data: JSON.stringify({ime, prezime, email, adresa, grad, drzava, jmbg}),
        contentType:'application/json',
       	success: function(odgovor){
       		$('#name').val('');
  			$('#surname').val('');
  			$('#email').val('');
  			$('#address').val('');
  			$('#city').val('');
  			$('#state').val('');
  			$('#jmbg').val('');
  			document.getElementById("title").innerHTML = "";
       		$('#newAdmin').attr('hidden', true);
       		$('#adminErrorAlert').attr('hidden', true);
       		$('#newClinic').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', false);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#clinicTable').attr('hidden', true);
	  		$('#requestsTable').attr('hidden', true);
	  		$('#drugs').attr('hidden', true);
	  		$('#diagnosis').attr('hidden', true);
	  		$('#allDiagnosis').attr('hidden', true);
	  		$('#editDiagnosis').attr('hidden', true);
	  		$('#personalData').attr('hidden', true);
       	},
       	error: function() {
       		$('#name').val('');
  			$('#surname').val('');
  			$('#email').val('');
  			$('#address').val('');
  			$('#city').val('');
  			$('#state').val('');
  			$('#jmbg').val('');
  			document.getElementById("title").innerHTML = "";
       		$('#adminErrorAlert').attr('hidden', false);
       		$('#newAdmin').attr('hidden', true);
	  		$('#newClinic').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', true);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#clinicTable').attr('hidden', true);
	  		$('#requestsTable').attr('hidden', true);
	  		$('#drugs').attr('hidden', true);
	  		$('#diagnosis').attr('hidden', true);
	  		$('#allDiagnosis').attr('hidden', true);
	  		$('#editDiagnosis').attr('hidden', true);
	  		$('#personalData').attr('hidden', true);
       	}
 	});
}

function prikaziAdmina(admin) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+admin.ime+'</td>');
	let tdPrezime = $('<td>'+admin.prezime+'</td>');
	let tdEmail = $('<td>'+admin.email+'</td>');
	tr.append(tdName).append(tdPrezime).append(tdEmail);
	$('#clinicAdminTable tbody').append(tr);
}

function allAdmin(klinika) {
	return function() {
		$.ajax({
            url:"/admin_klinickog_centra/svi_admini_klinike/" + klinika.id,
            type:"GET",
           	success: function(admini){
           		$('#clinicAdminTable tbody').html('');
           		document.getElementById("title").innerHTML = "All administrator of: " + klinika.ime;
           		for(let admin of admini) {
           			prikaziAdmina(admin);
           		}
           		$('#clinicAdminTable').attr('hidden', false);
           		$('#newClinicAdmin').attr('hidden', true);
  				$('#clinicTable').attr('hidden', true);
            	$('#newAdmin').attr('hidden', true);
    	  		$('#newClinic').attr('hidden', true);
    	  		$('#adminSuccessAlert').attr('hidden', true);
    	  		$('#adminErrorAlert').attr('hidden', true);	 	
    	  		$('#requestsTable').attr('hidden', true);
    	  		$('#drugs').attr('hidden', true);
    	  		$('#diagnosis').attr('hidden', true);
    	  		$('#allDiagnosis').attr('hidden', true);
    	  		$('#editDiagnosis').attr('hidden', true);
    	  		$('#personalData').attr('hidden', true);
    	  	},
           	error: function() {
           		$('#clinicAdminTable').attr('hidden', true);
           		$('#newClinicAdmin').attr('hidden', true);
  				$('#clinicTable').attr('hidden', true);
            	$('#newAdmin').attr('hidden', true);
            	$('#personalData').attr('hidden', true);
    	  		$('#newClinic').attr('hidden', true);
    	  		$('#adminSuccessAlert').attr('hidden', true);
    	  		$('#adminErrorAlert').attr('hidden', false);
    	  		$('#requestsTable').attr('hidden', true);
    	  		$('#drugs').attr('hidden', true);
    	  		$('#diagnosis').attr('hidden', true);
    	  		$('#allDiagnosis').attr('hidden', true);
    	  		$('#editDiagnosis').attr('hidden', true);
    	  		document.getElementById("title").innerHTML = "";
           	}
     	});
	}
}

function newClinicAdmin(klinika) {
	return function() {
		$('#newClinicAdmin').attr('hidden', false);
		$('#clinicTable').attr('hidden', true);
    	$('#newAdmin').attr('hidden', true);
  		$('#newClinic').attr('hidden', true);
  		$('#adminSuccessAlert').attr('hidden', true);
  		$('#adminErrorAlert').attr('hidden', true);
  		$('#clinicAdminTable').attr('hidden', true);
  		$('#requestsTable').attr('hidden', true);
  		$('#drugs').attr('hidden', true);
  		$('#diagnosis').attr('hidden', true);
  		$('#allDiagnosis').attr('hidden', true);
  		$('#editDiagnosis').attr('hidden', true);
  		$('#personalData').attr('hidden', true);
  		$('#idClinic').val(klinika.id);
  		document.getElementById("title").innerHTML = "New administrator of: " + klinika.ime;
	}
}

function prikaziKliniku(klinika) {
	let tr = $('<tr></tr>');
	let tdName = $('<td>'+klinika.ime+'</td>');
	let tdAddress = $('<td>'+klinika.adresa+'</td>');
	let tdDescribe = $('<td>'+klinika.opis+'</td>');
	let tdAddAdmin = $('<td><a href="#'+klinika.ime+'_newAdministrator">Add administrator</a></td>');
	tdAddAdmin.click(newClinicAdmin(klinika));
	let tdAllAdmins = $('<td><a href="#"'+klinika.ime+'_allAdmins>All administrators</a></td>');
	tdAllAdmins.click(allAdmin(klinika));
	tr.append(tdName).append(tdAddress).append(tdDescribe).append(tdAddAdmin).append(tdAllAdmins);
	$('#clinicTable tbody').append(tr);
}

function newClinicAndAdmin() {
	let ime = $('#clinicName').val();
	let adresa = $('#clinicAddress').val();
	let opis = $('#clinicDescribe').val();
	
	$.ajax({
        url:"/admin_klinickog_centra/nova_klinika",
        type:"POST",
        data: JSON.stringify({ime, adresa, opis}),
        contentType:'application/json',
        success: function(klinike) {
        	allClinics();
        },
        error: function() {
        	$('#clinicName').val('');
	  		$('#clinicAddress').val('');
	  		$('#clinicDescribe').val('');
	  		$('#adminErrorAlert').attr('hidden', false);
       		$('#newAdmin').attr('hidden', true);
       		$('#clinicAdminTable').attr('hidden', true);
	  		$('#newClinic').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', true);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#clinicTable').attr('hidden', true);
	  		$('#requestsTable').attr('hidden', true);
	  		$('#drugs').attr('hidden', true);
	  		$('#diagnosis').attr('hidden', true);
	  		$('#personalData').attr('hidden', true);
	  		$('#allDiagnosis').attr('hidden', true);
	  		$('#editDiagnosis').attr('hidden', true);
	  		document.getElementById("title").innerHTML = "";
        }
    });
}

function addNewDrug() {
	let sifra = $('#codeDrug').val();
	let ime = $('#nameDrug').val();
	let opis = $('#describeDrug').val();
	$.ajax({
        url:"/admin_klinickog_centra/novi_lek",
        type:"POST",
        data: JSON.stringify({sifra, ime, opis}),
        contentType:'application/json',
        success: function(odgovor) {
        	alert(odgovor);
        	$('#codeDrug').val('');
        	$('#nameDrug').val('');
        	$('#describeDrug').val('');
        	home();
        },
        error: function() {
        	alert('Desila se greska');
        }
    });
}

function addNewDiagnose() {
	let sifra = $('#codeDiag').val();
	let ime = $('#nameDiag').val();
	let opis = $('#describeDiag').val();
	$.ajax({
        url:"/admin_klinickog_centra/nova_dijanoza",
        type:"POST",
        data: JSON.stringify({sifra, ime, opis}),
        contentType:'application/json',
        success: function(odgovor) {
        	alert(odgovor);
        	$('#codeDiag').val('');
        	$('#nameDiag').val('');
        	$('#describeDiag').val('');
        	home();
        },
        error: function() {
        	alert('Desila se greska');
        }
    });
}

function newAdmin() {
	$('#newAdmin').attr('hidden', false);
	$('#newClinic').attr('hidden', true);
	$('#adminSuccessAlert').attr('hidden', true);
	$('#adminErrorAlert').attr('hidden', true);
	$('#clinicTable').attr('hidden', true);
	$('#newClinicAdmin').attr('hidden', true);
	$('#clinicAdminTable').attr('hidden', true);
	$('#requestsTable').attr('hidden', true);
	$('#newDiagnose').attr('hidden', true);
	$('#drugs').attr('hidden', true);
	$('#diagnosis').attr('hidden', true);
	$('#allDiagnosis').attr('hidden', true);
	$('#editDiagnosis').attr('hidden', true);
	$('#personalData').attr('hidden', true);
	document.getElementById("title").innerHTML = "New clinic administrator";
}

function newClinic() {
	$('#newClinic').attr('hidden', false);
	$('#newAdmin').attr('hidden', true);
	$('#adminSuccessAlert').attr('hidden', true);
	$('#adminErrorAlert').attr('hidden', true);
	$('#clinicTable').attr('hidden', true);
	$('#newClinicAdmin').attr('hidden', true);
	$('#clinicAdminTable').attr('hidden', true);
	$('#requestsTable').attr('hidden', true);
	$('#newDiagnose').attr('hidden', true);
	$('#newDrug').attr('hidden', true);
	$('#drugs').attr('hidden', true);
	$('#diagnosis').attr('hidden', true);
	$('#allDiagnosis').attr('hidden', true);
	$('#editDiagnosis').attr('hidden', true);
	$('#personalData').attr('hidden', true);
	document.getElementById("title").innerHTML = "New clinic";
}
function newDiagnose() {
	$('#newDrug').attr('hidden', true);
	$('#newDiagnose').attr('hidden', false);
	$('#newClinic').attr('hidden', true);
	$('#newAdmin').attr('hidden', true);
	$('#adminSuccessAlert').attr('hidden', true);
	$('#adminErrorAlert').attr('hidden', true);
	$('#clinicTable').attr('hidden', true);
	$('#newClinicAdmin').attr('hidden', true);
	$('#clinicAdminTable').attr('hidden', true);
	$('#drugs').attr('hidden', true);
	$('#diagnosis').attr('hidden', true);
	$('#allDiagnosis').attr('hidden', true);
	$('#editDiagnosis').attr('hidden', true);
	$('#requestsTable').attr('hidden', true);
	$('#personalData').attr('hidden', true);
	document.getElementById("title").innerHTML = "New diagnose";
}
function newDrug() {
	$('#newDrug').attr('hidden', false);
	$('#newDiagnose').attr('hidden', true);
	$('#newClinic').attr('hidden', true);
	$('#newAdmin').attr('hidden', true);
	$('#adminSuccessAlert').attr('hidden', true);
	$('#adminErrorAlert').attr('hidden', true);
	$('#clinicTable').attr('hidden', true);
	$('#newClinicAdmin').attr('hidden', true);
	$('#clinicAdminTable').attr('hidden', true);
	$('#requestsTable').attr('hidden', true);
	$('#drugs').attr('hidden', true);
	$('#diagnosis').attr('hidden', true);
	$('#allDiagnosis').attr('hidden', true);
	$('#editDiagnosis').attr('hidden', true);
	$('#personalData').attr('hidden', true);
	document.getElementById("title").innerHTML = "New drug";
}
function home() {
	$('#newDiagnose').attr('hidden', true);
	$('#newClinic').attr('hidden', true);
	$('#newAdmin').attr('hidden', true);
	$('#adminSuccessAlert').attr('hidden', true);
	$('#adminErrorAlert').attr('hidden', true);
	$('#clinicTable').attr('hidden', true);
	$('#clinicAdminTable').attr('hidden', true);
	$('#newClinicAdmin').attr('hidden', true);
	$('#newDrug').attr('hidden', true);
	$('#drugs').attr('hidden', true);
	$('#allDrugs').attr('hidden', true);
	$('#diagnosis').attr('hidden', true);
	$('#allDiagnosis').attr('hidden', true);
	$('#editDiagnosis').attr('hidden', true);
	$('#editDrugs').attr('hidden', true);
	$('#requestsTable').attr('hidden', true);
	$('#personalData').attr('hidden', true);
	document.getElementById("title").innerHTML = "";
}

function allClinics() {
	$('#newClinic').attr('hidden', true);
	$('#newAdmin').attr('hidden', true);
	$('#adminSuccessAlert').attr('hidden', true);
	$('#clinicAdminTable').attr('hidden', true);
	$('#adminErrorAlert').attr('hidden', true);
	$('#clinicTable').attr('hidden', true);
	$('#newClinicAdmin').attr('hidden', true);
	$('#requestsTable').attr('hidden', true);
	$('#drugs').attr('hidden', true);
	$('#diagnosis').attr('hidden', true);
	$('#allDiagnosis').attr('hidden', true);
	$('#editDiagnosis').attr('hidden', true);
	$('#personalData').attr('hidden', true);
	$.ajax({
        url:"/admin_klinickog_centra/sve_klinike",
        type:"GET",
       	success: function(klinike){
       		$('#clinicTable tbody').html('');
       		document.getElementById("title").innerHTML = "All clinics";
        	for(let klinika of klinike) {
        		prikaziKliniku(klinika);
        	}
        	$('#clinicTable').attr('hidden', false);
       	},
       	error: function() {
       		$('#adminErrorAlert').attr('hidden', false);
       		$('#newAdmin').attr('hidden', true);
       		$('#clinicAdminTable').attr('hidden', true);
	  		$('#newClinic').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', true);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#clinicTable').attr('hidden', true);
	  		$('#requestsTable').attr('hidden', true);
	  		$('#drugs').attr('hidden', true);
	  		$('#diagnosis').attr('hidden', true);
	  		$('#allDiagnosis').attr('hidden', true);
	  		$('#editDiagnosis').attr('hidden', true);
	  		$('#personalData').attr('hidden', true);
	  		document.getElementById("title").innerHTML = "";
       	}
 	});
}

function allCenterAdmins() {
	$.ajax({
        url:"/admin_klinickog_centra/svi_admini_centra",
        type:"GET",
       	success: function(admini){
       		$('#clinicAdminTable tbody').html('');
       		document.getElementById("title").innerHTML = "All administrators of the clinical centre";
       		for(let admin of admini) {
       			prikaziAdmina(admin);
       		}
       		$('#clinicAdminTable').attr('hidden', false);
       		$('#adminErrorAlert').attr('hidden', true);
       		$('#newAdmin').attr('hidden', true);
	  		$('#newClinic').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', true);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#clinicTable').attr('hidden', true);
	  		$('#requestsTable').attr('hidden', true);
	  		$('#drugs').attr('hidden', true);
	  		$('#diagnosis').attr('hidden', true);
	  		$('#allDiagnosis').attr('hidden', true);
	  		$('#editDiagnosis').attr('hidden', true);
	  		$('#personalData').attr('hidden', true);
       	},
       	error: function() {
       		$('#adminErrorAlert').attr('hidden', false);
       		$('#newAdmin').attr('hidden', true);
       		$('#clinicAdminTable').attr('hidden', true);
	  		$('#newClinic').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', true);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#clinicTable').attr('hidden', true);
	  		$('#requestsTable').attr('hidden', true);
	  		$('#drugs').attr('hidden', true);
	  		$('#diagnosis').attr('hidden', true);
	  		$('#allDiagnosis').attr('hidden', true);
	  		$('#editDiagnosis').attr('hidden', true);
	  		$('#personalData').attr('hidden', true);
	  		document.getElementById("title").innerHTML = "";
       	}
 	});
}

function odbijanje(id) {
	return function() {
  		$('#denyText').attr('hidden', false);
  		$('#denyButton').attr('hidden', false);
  		$('#denyButton').click(odbij(id));
	}
}

function odbij(id) {
	return function() {
		let text = $('#denyText').val();
		let url = "/admin_klinickog_centra/odbij/" + id + "~" + text;
		$.ajax({
            url: url,
            type:"GET",
            success: function() {
            	$('#denyText').attr('hidden', true);
		  		$('#denyButton').attr('hidden', true);
		  		$('#denyText').val('');
            	requests();
            },
            error: function() {
            	alert('Desila se greska');
            }
     	});
	}
}

function prihvati(id) {
	return function() {
		$.ajax({
            url:"/admin_klinickog_centra/prihvati/" + id,
            type:"GET",
            success: function() {
            	requests();
            },
            error: function() {
            	alert('Desila se greska');
            }
     	});
	}
}

function prikaziKorisnika(korisnik) {
	let tr = $('<tr></tr>');
	let tdIme = $('<td>'+korisnik.ime+'</td>');
	let tdPrezime = $('<td>'+korisnik.prezime+'</td>');
	let tdUloga = $('<td>'+korisnik.uloga+'</td>');
	let tdEmail = $('<td>'+korisnik.email+'</td>');
	let tdPrihvati = $('<td><a>Accept</a></td>');
	tdPrihvati.click(prihvati(korisnik.id));
	let tdOdbij = $('<td><a>Deny</a></td>');
	tdOdbij.click(odbijanje(korisnik.id));
	tr.append(tdIme).append(tdPrezime).append(tdUloga).append(tdEmail).append(tdPrihvati).append(tdOdbij);
	$('#requestsTable tbody').append(tr);
}

function requests() {
	$.ajax({
        url:"/admin_klinickog_centra/sviZahtevi",
        type:"GET",
		success: function(korisnici) {
			$('#requestsTable tbody').html('');
			for(let korisnik of korisnici) {
				prikaziKorisnika(korisnik);
			}
			$('#requestsTable').attr('hidden', false);
			$('#adminErrorAlert').attr('hidden', true);
       		$('#newAdmin').attr('hidden', true);
       		$('#clinicAdminTable').attr('hidden', true);
	  		$('#newClinic').attr('hidden', true);
	  		$('#adminSuccessAlert').attr('hidden', true);
	  		$('#newClinicAdmin').attr('hidden', true);
	  		$('#clinicTable').attr('hidden', true);
	  		$('#drugs').attr('hidden', true);
	  		$('#diagnosis').attr('hidden', true);
	  		$('#allDiagnosis').attr('hidden', true);
	  		$('#editDiagnosis').attr('hidden', true);
	  		$('#personalData').attr('hidden', true);
		},
		error: function() {
			alert('Desila se greska');
		}
	});
}

function deleteDrug(id) {
	return function() {
		$.ajax({
			url: 'lek/obrisi/' + id,
			type: "DELETE",
			success: function() {
				allDrugs();
			},
			error: function() {
				alert('Desila se greska prilikom brisanja leka');
			}
		});
	}
}

function editDrug() {
	let id = $('#editDrugID').val();
	let ime = $('#editDrugName').val();
	let opis = document.getElementById("editDrugDesc").value;
	let sifra = $('#editDrugCode').val();
	$.ajax({
		url: 'lek/izmeni',
		type: "PUT",
		data: JSON.stringify({sifra, ime, opis, id}),
        contentType:'application/json',
		success: function() {
			allDrugs();
		},
		error: function() {
			alert('Desila se greska kod izmene leka');
		}
	});
}

function editDrugs(lek) {
	return function() {
		$('#editDrugCode').val(lek.sifra);
		$('#editDrugName').val(lek.ime);
		document.getElementById("editDrugDesc").value = lek.opis;
		$('#editDrugID').val(lek.id);
		$('#editDrugs').attr('hidden', false);
	}
}

function prikaziLek(lek) {
	let tr = $('<tr></tr>');
	let tdCode = $('<td>'+lek.sifra+'</td>');
	let tdName = $('<td>'+lek.ime+'</td>');
	let tdDesc = $('<td>'+lek.opis+'</td>');
	let tdEditDrug = $('<td><a class="btn btn-success">Edit drug</a></td>');
	tdEditDrug.click(editDrugs(lek));
	let tdDeleteDrug = $('<td><a class="btn btn-danger">Delete drug</a></td>');
	tdDeleteDrug.click(deleteDrug(lek.id));
	tr.append(tdCode).append(tdName).append(tdDesc).append(tdEditDrug).append(tdDeleteDrug);
	$('#allDrugs tbody').append(tr);
}

function allDrugs() {
	$.ajax({
		url: 'lek/svi_lekovi',
		type:"GET",
		success: function(lekovi) {
			$('#allDrugs tbody').html('');
			for(let lek of lekovi) {
				prikaziLek(lek);
			}
			home();
			$('#drugs').attr('hidden', false);
			$('#allDrugs').attr('hidden', false);
		},
		error: function() {
			alert('Desila se greska kod izlistavanja lekova');
		}
	});
}

function deleteDiagnose(id) {
	return function() {
		$.ajax({
			url: 'dijagnoza/obrisi/' + id,
			type: "DELETE",
			success: function() {
				allDiagnosis();
			},
			error: function() {
				alert('Desila se greska prilikom brisanja dijagnoze');
			}
		});
	}
}

function editDiagnosis() {
	let id = $('#editDiagnosisID').val();
	let ime = $('#editDiagnosisName').val();
	let opis = document.getElementById("editDiagnoseDesc").value;
	let sifra = $('#editDiagnosisCode').val();
	$.ajax({
		url: 'dijagnoza/izmeni',
		type: "PUT",
		data: JSON.stringify({sifra, ime, opis, id}),
        contentType:'application/json',
		success: function() {
			allDiagnosis();
		},
		error: function() {
			alert('Desila se greska kod izmene dijagnoze');
		}
	});
}

function editDiagnose(dijagnoza) {
	return function() {
		$('#editDiagnosis').attr('hidden', false);
		$('#editDiagnosisCode').val(dijagnoza.sifra);
		$('#editDiagnosisID').val(dijagnoza.id);
		$('#editDiagnosisName').val(dijagnoza.ime);
		document.getElementById("editDiagnoseDesc").value = dijagnoza.opis;
	}
}

function prikaziDijagnozu(dijagnoza) {
	let tr = $('<tr></tr>');
	let tdCode = $('<td>'+dijagnoza.sifra+'</td>');
	let tdName = $('<td>'+dijagnoza.ime+'</td>');
	let tdDesc = $('<td>'+dijagnoza.opis+'</td>');
	let tdEditDiagnose = $('<td><a class="btn btn-success">Edit diagnose</a></td>');
	tdEditDiagnose.click(editDiagnose(dijagnoza));
	let tdDeleteDiagnose = $('<td><a class="btn btn-danger">Delete diagnose</a></td>');
	tdDeleteDiagnose.click(deleteDiagnose(dijagnoza.id));
	tr.append(tdCode).append(tdName).append(tdDesc).append(tdEditDiagnose).append(tdDeleteDiagnose);
	$('#allDiagnosis tbody').append(tr);
}

function allDiagnosis() {
	$.ajax({
		url: 'dijagnoza/sve_dijagnoze',
		type:"GET",
		success: function(dijagnoze) {
			$('#allDiagnosis tbody').html('');
			for(let dijagnoza of dijagnoze) {
				prikaziDijagnozu(dijagnoza);
			}
			home();
			$('#diagnosis').attr('hidden', false);
			$('#allDiagnosis').attr('hidden', false);
		},
		error: function() {
			alert('Desila se greska kod izlistavanja dijagnoza');
		}
	});
}

function logout() {
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
			alert('Desila se greska kog logouta');
		}
	});
}

function personalData() {
	home();
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

function changePassword() {
	let id = sessionStorage.getItem("id");
	let currentPassord = $('#currentPass').val();
	let newPassword = $('#newPass').val();
	let confirmPassword = $('#confirmPass').val();
	let url = 'korisnik/promeni_lozinku/' + id + '/' + currentPassord + '/' + newPassword + '/' + confirmPassword;
	$.ajax({
		type: "GET",
		url: url,
		success: function(odgovor) {
			alert(odgovor);
			$('#con').attr('hidden', true);
			$('#main').attr('hidden', false);
			home();
		},
		error : function(odgovor) {
			alert('Greska kod promene lozinke');
		}
	});
}



