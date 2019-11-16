$(document).ready(()=>{
	$('#clinic_form').submit((event)=>{
		event.preventDefault();
		
		let naziv = $('#f_naziv').val();
		let adresa = $('#f_adresa').val();
		let opis = $('#f_opis').val();
		let termini =$('#s_termini').val();
		let doktori = $('#s_doktori').val();
		let sale = $('#s_sala').val();
		let usluga = $('#s_usluga').val();
		
		$.ajax({
			type: "POST",
			contentType: "application/json",
			
		})
	});
	
	
})