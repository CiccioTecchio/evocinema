$(document).ready(function() {

	$(".cancellaAccount").click(function() {

		var email = $(this).attr('id');

		$.post('CancellazioneAccountCNT', {
			"emailCancellaProfilo" : email
		}, function() {

			alert("Account eliminato.");
			
		}).fail(function() {
			alert("Impossibile eliminare.");
		});
	});

});

