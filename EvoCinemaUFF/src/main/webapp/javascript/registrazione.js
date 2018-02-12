
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
                    
                    $("#registrazione").click(function(e) {   
                    var regExp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                    var regExpCal = /\d{4}\-(0?[1-9]|1[012])\-[0-3][0-9]/;
                     e.preventDefault();
                     
                     var nome =  $('#nomeRegistrazione').val();
                     var cognome = $('#cognomeRegistrazione').val();
                     var email =  $('#emailRegistrazione').val();
                     var indirizzo = $('#indirizzoRegistrazione').val();
                     var citta =  $('#cittaRegistrazione').val();
                     var cellulare = $('#cellulareRegistrazione').val();
                     var dataNascita =  $('#dateForm').val();
                     var sesso =  $('#sessoRegistrazione').val();
                     var username =  $('#userRegistrazione').val();
                     var password = $('#passwordRegistrazione').val();
                     var password2 = $('#passwordRegistrazione2').val();
                     var bool=0;
                     
                     if(nome.length<2){
                         $('#errorNome').show(); 
                        
                     }
                     else{
                         $('#errorNome').hide();
                         bool++;
                     }
                     
                     if(cognome.length<2){
                      
                         $('#errorCognome').show();
                         
                     }
                     else{
                         $('#errorCognome').hide();   
                        bool++;
                     }
                     
                     if(indirizzo.length<2){
                         $('#errorIndirizzo').show();
                           
                     }
                     else{
                         $('#errorIndirizzo').hide();
                         bool++;
                     }
                     if(cellulare.length<9){
                         $('#errorCellulare').show();
                         
                     }
                     else{
                         $('#errorCellulare').hide();
                         bool++;
                     }
                     
                         
                         if( email.match(regExp)){ $('#errorEmail3').hide(); bool++; }
                         else $('#errorEmail3').show();
                        
                        if( dataNascita.match(regExpCal)) { $('#errorDataNascita').hide(); bool++; }
                        else{ $('#errorDataNascita').show(); };
                        
                     if(password.length<8){
                         $('#errorPassword').show();  

                     }
                     else{
                         $('#errorPassword').hide();
                        bool++;
                     }
                     if(password2.length<8){
                         $('#errorPassword2').show();
                         
                     }
                     else{
                         $('#errorPassword2').hide();
                         bool++;
                     }
                     if(username.length<5){
                         $('#errorUsername').show();
                         
                     }
                     else{
                         $('#errorUsername').hide();
                         bool++;
                     }
                     if(citta.length<2){
                         $('#errorCitta').show(); 
                         
                     }
                     else{
                         $('#errorCitta').hide();
                         bool++;
                     }
                     if((password.length>=8)&&(password2.length>=8)){
                        if(password!==password2){
                            $('#errorMatch').show(); 
                            
                        }
                         else{
                         $('#errorMatch').hide();
                         bool++;
                     }
                     }
                  
                     if( bool === 11 ){
                   
                            $.ajax({
                         
                              type: "POST",
                              url:"./RegistrazioneCNT",
                              data:{"nomeRegistrazione":nome,"cognomeRegistrazione":cognome,
                              "emailRegistrazione":email,"indirizzoRegistrazione":indirizzo,
                          "cittaRegistrazione":citta,"passwordRegistrazione":password,"password1Registrazione":password2,"cellulareRegistrazione":cellulare,
                      "userRegistrazione":username,"dataRegistrazione":dataNascita,"sessoRegistrazione":sesso},
                              success: function (data) { 
                                     window.location.href = data.toString();
                                 },error: function(){
                                     alert("Impossibile registrare.");
                                     location.reload();
                                 }
                                 
                                 
                                 
                           
                            });
                     };       
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
    

