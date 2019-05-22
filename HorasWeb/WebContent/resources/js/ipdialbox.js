function guardarNuevaGestion(codigoTipificacion) {	
	var tipificacion = codigoTipificacion;
	console.log(codigoTipificacion);
	console.log(tipificacion);
	$.getJSON('http://localhost:80/apiagentbox?action=chur&cod='
			+ tipificacion +'&comm=FIN_LLAMADA', function(res) {
		res = JSON.stringify(res);
		var status = JSON.parse(res);
	});
}


function fun_include_dial(telefono) {
	
	console.log(telefono);
	$.getJSON('http://localhost:80/apiagentbox?action=dial&phone=9' + telefono,
			function(res) {
				res = JSON.stringify(res);
				var status = JSON.parse(res);

			});
}

function fun_include_aux(letra) {

	$.getJSON('http://localhost:80/apiagentbox?action=' + letra, function(res) {
		res = JSON.stringify(res);
		var status = JSON.parse(res);
	});
}

function fun_include_hold(letra) {

	$.getJSON('http://localhost:80/apiagentbox?action=hold', function(res) {
		res = JSON.stringify(res);

	});
}

function fun_include_mute(letra) {

	$.getJSON('http://localhost:80/apiagentbox?action=mute', function(res) {
		res = JSON.stringify(res);
	});
}

function fun_include_haup(letra) {

	$.getJSON('http://localhost:80/apiagentbox?action=haup', function(res) {
		res = JSON.stringify(res);
	});
}