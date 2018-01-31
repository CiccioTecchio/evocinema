$(document).ready(function() {

	$(".bottoni").click(function() {

		var email = $(this).attr('id');
                var page = $(this).attr('value')

		$.post('../CancellazioneGestore', {
			"emailCancellaUtente" : email
		}, function() {

			alert("Account eliminato.");
			
		}).fail(function() {
			alert("Impossibile eliminare.");
		});
	});

});
