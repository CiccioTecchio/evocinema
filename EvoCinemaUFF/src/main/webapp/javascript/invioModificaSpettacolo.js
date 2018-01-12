$(document).ready(function() {

	$("#bottoneSalva").click(function() {

		var prezzo = $("#passwordRegistrazione").val();
		var orario = $("#indirizzoRegistrazione").val();
		var sala = $("#cittaRegistrazione").val();
		var dataInizio = $("#capRegistrazione").val();
		var dataFine = $("#emailRegistrazione").val();

		$.get('..//ModificaSpettacoloInvioCNT', {
			"prezzo" : prezzo,
			"orario" : orario,
			"sala" : sala,
			"dataInizio" : dataInizio,
			"dataFine" : dataFine,
		}, function() {
			alert("Spettacolo modificato con successo");
			$("#bottoneSalva").attr('disabled', 'disabled');
		}).fail(function() {
			alert("Impossibile salvare.");
		});
	});

});