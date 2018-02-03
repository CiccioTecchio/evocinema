$(document).ready(function() {

	$(".bottoneEliminaGestore").click(function() {

		var email = $(this).attr('id');

		$.post('../CancellazioneGestore', {
			"emailCancellaUtente" : email
		}, function() {

			alert("Account eliminato.");
			
		}).fail(function() {
			alert("Impossibile eliminare.");
		});
	});

});
