
                $(document).ready(function() {
                    $('#errorNome').hide();
                    $('#errorCognome').hide();
                    $('#errorIndirizzo').hide();
                    $('#errorCitta').hide();
                    $('#errorEmail2').hide();
                    $('#errorEmail3').hide();
                    $('#errorDataNascita').hide();
                    $('#errorCellulare').hide();
                    $('#errorUsername').hide();
                    $('#errorPassword').hide();
                    $('#errorPassword2').hide();
                    $('#errorMatch').hide();
                    
                    $("#registrazioneGestore").click(function(e) {   
                  
                     e.preventDefault();
                     
                     var nome =  $('#nomeRegistrazione').val();
                     var cognome = $('#cognomeRegistrazione').val();
                     var email =  $('#emailRegistrazione').val();
                     var ruolo = $('#ruoloRegistrazione').val();
                     var indirizzo = $('#indirizzoRegistrazione').val();
                     var citta =  $('#cittaRegistrazione').val();
                     var cellulare = $('#cellulareRegistrazione').val();
                     var dataNascita =  $('#datepicker').val();
                     var sesso =  $('#sessoRegistrazione').val();
                     var username =  $('#userRegistrazione').val();
                     var password = $('#passwordRegistrazione').val();
                     var password2 = $('#passwordRegistrazione2').val();
                     var bool=true;
                     
                     if(nome.length<2){
                         $('#errorNome').show(); 
                         bool=false;
                     }
                     else{
                         $('#errorNome').hide();
                         bool=true;
                     }
                     
                     if(cognome.length<2){
                      
                         $('#errorCognome').show();
                         bool=false;
                     }
                     else{
                         $('#errorCognome').hide();   
                         bool=true;
                     }
                     
                     if(indirizzo.length<2){
                         $('#errorIndirizzo').show();
                         bool=false;   
                     }
                     else{
                         $('#errorIndirizzo').hide();
                         bool=true;
                     }
                     if(cellulare.length<9){
                         $('#errorCellulare').show();
                         bool=false;
                     }
                     else{
                         $('#errorCellulare').hide();
                         bool=true;
                     }
                     if(email.length<5){
                         $('#errorEmail2').show();
                         bool=false;
                     }
                     
                     else{
                         $('#errorEmail2').hide();
                         bool=true;
                         var str = email.split('@');

                            if((!($('#emailRegistrazione').val().indexOf('@') >= 0))) {
                                
                                $('#errorEmail3').show();
                                bool=false;
                            }
                            else{
                                $('#errorEmail3').hide();
                                bool=true;
                            }
                            if(str[1].length<1){
                            
                                $('#errorEmail3').show();
                                bool=false;
                            }
                            else{
                                $('#errorEmail3').hide();
                                bool=true;
                            }
                            
                            if((!($('#emailRegistrazione').val().indexOf('.') >= 0))) {
                                
                                $('#errorEmail3').show();
                                bool=false;
                            }
                            else{
                                $('#errorEmail3').hide();
                                bool=true;
                            }
                            var str2= email.split(".");
                            if(str2[1].length<1){
                                 $('#errorEmail3').show();
                                 bool=false;
                            }
                            else{
                                $('#errorEmail3').hide();
                                bool=true;
                            }
                     }
                     if(password.length<8){
                         $('#errorPassword').show();  
                         bool=false;
                     }
                     else{
                         $('#errorPassword').hide();
                         bool=true;
                     }
                     if(password2.length<8){
                         $('#errorPassword2').show();
                         bool=false;
                     }
                     else{
                         $('#errorPassword2').hide();
                         bool=true;
                     }
                     if(username.length<5){
                         $('#errorUsername').show();
                         bool=false;
                     }
                     else{
                         $('#errorUsername').hide();
                         bool=true;
                     }
                     if(citta.length<2){
                         $('#errorCitta').show(); 
                         bool=false;
                     }
                     else{
                         $('#errorCitta').hide();
                         bool=true;
                     }
                     if((password.length>=8)&&(password2.length>=8)){
                        if(password!==password2){
                            $('#errorMatch').show(); 
                            bool=false;
                        }
                         else{
                         $('#errorMatch').hide();
                         bool=true;
                     }
                     }
                     if(bool===true){
                     $.ajax({
                         
                              type: "POST",
                              url:"/RegistrazioneGestoreCNT",
                              data:{"nomeRegistrazione":nome, "cognomeRegistrazione":cognome, "ruoloRegistrazione":ruolo,
                              "emailRegistrazione":email, "indirizzoRegistrazione":indirizzo,
                              "cittaRegistrazione":citta, "passwordRegistrazione":password, "password1Registrazione":password2, "cellulareRegistrazione":cellulare,
                              "userRegistrazione":username, "dataRegistrazione":dataNascita, "sessoRegistrazione":sesso},
                              dataType: "text",
                              success: function () {
                                     location.reload();
                                 },error: function(){
                                     alert("Impossibile registrare.");
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
    



