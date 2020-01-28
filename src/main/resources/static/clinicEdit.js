$(document).ready(()=>{
	$('#clinic_form').submit((event)=>{
		event.preventDefault();
		
		let naziv = $('#f_naziv').val();
		let adresa = $('#f_adresa').val();
		let opis = $('#f_opis').val();
	
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "klinika/editClinic",
			data: JSON.stringify({naziv, adresa, opis}), 
			cache: false,
			timeout: 600000,
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