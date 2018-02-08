
$(document).ready(function () {
    $('#errorNomeVisualizza').hide();
    $('#errorCognomeVisualizza').hide();
    $('#errorIndirizzoVisualizza').hide();
    $('#errorCittaVisualizza').hide();
    $('#errorEmail2Visualizza').hide();
    $('#errorEmail3Visualizza').hide();
    $('#errorDataNascitaVisualizza').hide();
    $('#errorCellulareVisualizza').hide();
    $('#errorPasswordVisualizza').hide();
    $('#errorPassword2Visualizza').hide();
    $('#errorPasswordMatchVisualizza').hide();
 
    $("#dettagliUtente").click(function (e) {
 
        e.preventDefault();
 
        var nome = $('#nomeRegistrazioneVisualizza').val();
        var cognome = $('#cognomeRegistrazioneVisualizza').val();
 
        var indirizzo = $('#indirizzoRegistrazioneVisualizza').val();
        var citta = $('#cittaRegistrazioneVisualizza').val();
        var cellulare = $('#cellulareRegistrazioneVisualizza').val();
        var dataNascita = $('#datepicker').val();
        var sesso = $('#sessoRegistrazioneVisualizza').val();
          var sesso = $('#sessoRegistrazioneVisualizza').val();
        var password = $('#passwordRegistrazioneVisualizza').val();
        var password2 = $('#passwordRegistrazione2Visualizza').val();
        var bool = true;
 
        if (nome.length < 2) {
            $('#errorNomeVisualizza').show();
            bool = false;
        } else {
            $('#errorNomeVisualizza').hide();
            //bool=true;
        }
 
        if (cognome.length < 2) {
 
            $('#errorCognomeVisualizza').show();
            bool = false;
        } else {
            $('#errorCognomeVisualizza').hide();
            //bool=true;
        }
                     
         if (indirizzo.length < 2) {
            $('#errorIndirizzoVisualizza').show();
            bool = false;
        } else {
            $('#errorIndirizzoVisualizza').hide();
            //bool=true;
        }
        if (cellulare.length < 9) {
            $('#errorCellulareVisualizza').show();
            bool = false;
        } else {
            $('#errorCellulareVisualizza').hide();
            //bool=true;
        }
        
        if (password.length < 8) {
            $('#errorPasswordVisualizza').show();
            bool = false;
        } else {
            $('#errorPasswordVisualizza').hide();
            //bool=true;
        }
        if (password2.length < 8) {
            $('#errorPassword2Visualizza').show();
            bool = false;
        } else {
            $('#errorPassword2Visualizza').hide();
            //bool=true;
        }
 
        if (citta.length < 2) {
            $('#errorCittaVisualizza').show();
            bool = false;
        } else {
            $('#errorCittaVisualizza').hide();
            //bool=true;
        } if ((password.length >= 8) && (password2.length >= 8)) {
            if (password !== password2) {
                $('#errorPasswordMatchVisualizza').show();
                bool = false;
            } else {
                $('#errorPasswordMatchVisualizza').hide();
                //bool=true;
            }
        }
                     
                     if (bool === true) {
 
            $.ajax({
 
                type: "POST",
                url: "../ModificaDettagliCNT",
 
                data: {"nomeRegistrazioneVisualizza": nome,
                    "cognomeRegistrazioneVisualizza": cognome,
                    "indirizzoRegistrazioneVisualizza": indirizzo,
                    "cittaRegistrazioneVisualizza": citta,
                    "cellulareRegistrazioneVisualizza": cellulare,
                    "dataRegistrazioneVisualizza": dataNascita,
                    "passwordRegistrazioneVisualizza": password,
                    "passwordRegistrazione2Visualizza": password2,
                    "sessoRegistrazioneVisualizza": sesso},
                success: function () {
                    location.reload();
                    alert("Le modifiche sono state salvate!");
                }, error: function () {
                    alert("Impossibile modificare!");
                }
            });
        }
    });
});

    function onlyNumbers(evt) {
      var theEvent = evt || window.event;
      var key = theEvent.keyCode || theEvent.which;
      key = String.fromCharCode( key );
      var regex = /[0-9]/;
      if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
      }
    }  
    
   $(function() {
  $( "#datepicker" ).datepicker({  maxDate: new Date() });
 });
    
