let session = null;
$(document).ready(()=>{
	session = sessionStorage.getItem("id");
	if(session == null){
		alert('You must be logged in to view this page!');
		window.location.href = "./index.html";
	}
	$.ajax({
		type: "GET",
		url: 'korisnik/preuzmi/' +  sessionStorage.getItem("id"),
		success: function(korisnik){
			if(korisnik.uloga != 'ADMIN_KLINIKE'){
				alert('You must be a clinic administrator to access this page');
				window.location.href = "./adminKlinike.html";
			}else{
				$.ajax({
					type: "GET",
					url: 'administrator/klinika_admin/' + session,
					success: function(klinika){
						if(klinika != null){
							$('#f_naziv').val(klinika.ime);
							$('#f_adresa').val(klinika.adresa);
							$('#f_opis').val(klinika.opis);
						}
					},
					error: function(){
						alert('Something went wrong');
					}
				});
			}
		},
		error: function(){
			alert('Something went wrong')
		}
	});
	
	
	$('#clinic_form').submit((event)=>{
		event.preventDefault();
		
		let naziv = $('#f_naziv').val();
		let adresa = $('#f_adresa').val();
		let opis = $('#f_opis').val();
	
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "klinika/editClinic/" + session,
			data: JSON.stringify({naziv, adresa, opis}), 
			success: function(ret){
				console.log("SUCCESS: ");
				alert("Klinika je uspesno izmenjena");

			},
			error: function(e){
				console.log("ERROR: ", e);
				alert("Doslo je do greske");
			}
		})
	});
	
	
})