$(document).ready(()=>{
	document.getElementById ("btnSaveChanges").addEventListener("click", saveChanges, false);
	document.getElementById ("btnCancelChanges").addEventListener("click", cancelChanges, false);
	
	$.ajax({
		url: "administrator/trenutniAdmin/" + 3,
		type: "GET",
		success: function(admin){
		
			$('#fUsername').val(admin.username);
			$('#fName').val(admin.ime);
			$('#fSurname').val(admin.prezime);
			$('#fEmail').val(admin.email);
			$('#fCity').val(admin.grad);
			$('#fAddress').val(admin.adresa);
			$('#fCountry').val(admin.drzava);
			$('#fPhone').val(admin.telefon);
		},
		error: function(){
			alert("Doslo je do greske");
		}
	})
});

function saveChanges(){
	let name = $('#fName').val();
	let surname = $('#fSurname').val();
	let email = $('#fEmail').val();
	let city = $('#fCity').val();
	let address = $('#fAddress').val();
	let country = $('#fCountry').val();
	let phone = $('#fPhone').val();
	
	$.ajax({
		url: "administrator/promeni/" + 3,
		type: "POST",
		data: JSON.stringify({name, surname, email, city, address, country, phone}),
		contentType: "application/json",
		success: function(){
			alert("Uspesna izmena podataka");
		},
		error: function(){
			alert("Doslo je do greske");
		}
	})
}

function cancelChanges(){
	$.ajax({
		url: "administrator/trenutniAdmin/" + 3,
		type: "GET",
		success: function(admin){
		
			$('#fUsername').val(admin.username);
			$('#fName').val(admin.ime);
			$('#fSurname').val(admin.prezime);
			$('#fEmail').val(admin.email);
			$('#fCity').val(admin.grad);
			$('#fAddress').val(admin.adresa);
			$('#fCountry').val(admin.drzava);
			$('#fPhone').val(admin.telefon);
		},
		error: function(){
			alert("Doslo je do greske");
		}
	})
}