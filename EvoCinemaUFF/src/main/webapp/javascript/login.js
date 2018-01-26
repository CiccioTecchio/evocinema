
                $(document).ready(function() {
                    $('#error').hide();
                    $('#errorEmail').hide();
                    $('#errorPsw').hide();
                    $("#login").click(function(e) {   
                      
                     e.preventDefault();
                     var pwd =  $('#passwordLogin').val();
                     var user = $('#emailLogin').val();
                     
                     if(pwd==""){
                         $('#error').hide();
                         $('#errorPsw').show();
                         if(user!="")$('#errorEmail').hide();
                     }
                     if(user==""){
                         $('#error').hide();
                         $('#errorEmail').show();
                         if(pwd!="")$('#errorPwd').hide();
                     }
                     
                     if((pwd!="")&&(user!="")){
                         $('#errorEmail').hide();
                         $('#errorPsw').hide();
                         
                            $.ajax({
                              type: "POST",
                              url:"Login",
                              data:{"emailLogin":user,"passwordLogin":pwd},
                              success: function (data) {
                                 if(data==='loginErrato'){
                                     $("#error").show(); 
                                 }
                                 else{
                                     window.location.href = data.toString();
                                 }
                              }
                            }); 
                     }
    });
});