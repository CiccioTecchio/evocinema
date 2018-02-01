<%-- 
    Document   : VisualizzazioneAccount
    Created on : 23-gen-2018, 16.04.16
    Author     : Michele
--%>

<%@page import="model.UtenteRegistrato.sesso"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.UtenteBase"%>
<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();

UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");

if (utente==null){
    response.sendRedirect("Login.jsp");}
else{
    
    SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
    String dataN= sdf.format(utente.getDataNascita().getTime());
    String a="";
    String b="";
    if(utente.getSesso().equals(sesso.M)){
        a="checked";
        }else{
        b="checked";
        }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dettagli account</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>
       
        

        
        <div>Profilo di <%=utente.getNome()%> <%=utente.getCognome()%></div>
	
        
        
        Email: <%=utente.getEmail()%><br><br>
        Nome Utente: <%=utente.getNomeUtente()%><br><br>
        
        

        <div>Dati Personali: <br><br>
            
            
        <div class="container-left col-md-4">
            

                    <div class="card-body">
                        <form action="ModificaDettagliCNT" method="POST">
                            
                            <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Nome </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="modificaNome" maxlength="50"  value="<%=utente.getNome()%>" >
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="form-row">
                                    <label for="exampleInputEmail1">Cognome</label>
                                    <input class="form-control" type="text" name="modificaCognome" maxlength="50" value="<%=utente.getCognome()%>" >

                                </div>
                            </div>
                          
                            <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Data di nascita </label>
                                    <input class="form-control" name="modificaData" type="text" id="datepicker" value="<%=dataN%>">
                                    
                                </div>
                            </div>

                            <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Indirizzo </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="modificaIndirizzo" maxlength="50"value="<%=utente.getIndirizzo()%>" >
                                </div>
                            </div>
                            <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Città </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="modificaCitta" maxlength="50" value="<%=utente.getCittà()%>" >
                                </div>
                            </div>
                            <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Cellulare </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="modificaCellulare" maxlength="50" value="<%=utente.getCellulare()%>" >
                                </div>
                            </div>
                                
                            
                                
                                
                                <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Password </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="modificaPassword" maxlength="50" >
                                </div>
                            </div>
                                <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Conferma Password </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="modificaPassword1" maxlength="50"  >
                                </div>
                            </div>
                            
                            <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Sesso     </label>
                                    <input type="radio" name="modificaSesso" value="maschio" <%= a %> > Maschio   
                                    <input type="radio" name="modificaSesso" value="femmina" <%= b %> > Femmina     
                                </div>
                            </div>
                                
                                <br>
                                <button class="btn btn-primary btn-block" type="submit">Salva</button>
                        </form>
                                <br>
                                
                                <% if(utente.getRuolo().equals(UtenteRegistrato.ruolo.UTENTE)){ %>
                                
                                <button onclick="confermaEliminazione()" class="btn btn-primary btn-block" name="<%=utente.getEmail()%>" id="<%=utente.getEmail()%>" type="sumbit">Cancella Account</button>
                                
                                <script>
                                    function confermaEliminazione() {
                                        var txt;
                                        if (confirm("Sei sicuro di voler eliminare l'account?")) {
                                            txt = "OK";
                                        } else {
                                            txt = "ANNULLA";
                                        }

                                    if(txt==="OK"){
                                        $.post('CancellazioneAccountCNT', {

                                        }, function() {
                                                location.reload();
                                                alert("Account eliminato.");
                                                
                                        }).fail(function() {
                                                alert("Impossibile eliminare.");
                                        });
                                    }
                                }
                                </script>
                                <%}%>
                                </div>
                               
                                  
                        
                            </div>
         <% } %>
    </body>
    <jsp:include page="Footer.jsp" />
</html>
<script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>