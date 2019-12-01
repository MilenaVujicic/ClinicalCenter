function home() { 
	alert('Home');
}

function prikaziPacijenta(pacijent) {
	let tr = $('<tr></tr>');
	let td = $('<td></td>');
	let aStart = $('<td><a>Start examination</a></td>');
	tr.append(td).append(td).append(td).append(aStart);
	$('#schPatTable tbody').append(tr);
}

function scheduledPatients() {
	$('#schPatTable').attr('hidden', false);
	$.ajax({
        url:"/doktor/zakazani_pacijenti",
        type:"GET",
       	success: function(pacijenti) {
       		$('#schPatTable').html('');
       		for (let pacijent of pacijenti) {
       			prikaziPacijenta(pacijent);
       		}
       	},
       	error: function() {
       		let tr = $('<tr></tr>');
       		let td = $('<td></td>');
       		let aStart = $('<td><a>Start examination</a></td>');
       		tr.append(td).append(td).append(td).append(aStart);
       		$('#schPatTable tbody').append(tr);
       		//alert('Desila se greska');
       	}
 	});
}