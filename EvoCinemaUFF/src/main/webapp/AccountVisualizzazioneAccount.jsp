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
SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
String dataN= sdf.format(utente.getDataNascita().getTime());

    
    
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
        
        <form >
            
            Password: <input type="password" name="modificaPassword"> <br>
            Conferma password: <input type="password" name="modificaPassword1">
            <br><br>
            Nome: <input type="text" name="modificaNome" value="<%=utente.getNome()%>"> <br>
            Cognome: <input type="text" name="modificaCognome" value="<%=utente.getCognome()%>"> <br>
            
            <div class="col-md-6">
                <label for="exampleInputLastName">Data di nascita</label>
                <input class="form-control" name="modificaData" id="exampleInputLastName" type="date" aria-describedby="nameHelp" value="<%=dataN%>">
            </div>
            
            Indirizzo: <input type="text" name="modificaIndirizzo" value="<%=utente.getIndirizzo()%>"> <br>
            Città: <input type="text" name="modificaCitta" value="<%=utente.getCittà()%>"> <br>
            Cellulare: <input type="text" name="modificaCellulare" value="<%=utente.getCellulare()%>"> <br>

            <%  String a="";
                String b="";
                if(utente.getSesso().equals(sesso.M)){
                    a="checked";
                }else{
                    b="checked";
                }
            %>
            
            Sesso:<br>
            <input type="radio" name="modificaSesso" value="maschio" <%= a %> > Maschio<br>
            <input type="radio" name="modificaSesso" value="femmina" <%= b %> > Femmina<br>
        
                   <button type="submit">Salva</button>
        </form>
        </div>
        
        <form action="CancellazioneAccountCNT" method="POST">
            <button type="sumbit">Cancella account</button>
        </form>
            
        <div class="container-left col-md-4">
            

                    <div class="card-body">
                        <form action="ModificaDettagliCNT" method="POST">
                            <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Username </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" maxlength="50" value="<%=utente.getNomeUtente()%>" >
                                </div>
                            </div>
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
                                    <label for="exampleInputName">E-Mail </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="modificaEmail" value="<%=utente.getEmail()%>">
                                </div>
                            </div>

                            <div class="form-group">

                                <div class="form-row">
                                    <label for="exampleInputName">Data di nascita </label>
                                    <input class="form-control" name="modificaData" type="text" id="datepicker" value="<%=dataN %>">
                                    
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
                                    <label for="exampleInputName">Sesso     </label>
                                    <input type="radio" name="modificaSesso" value="maschio" <%= a %> > Maschio   
                                    <input type="radio" name="modificaSesso" value="femmina" <%= b %> > Femmina     
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
                                <br>
                                <button class="btn btn-primary btn-block" type="submit">Salva</button>
                        </form>
                                <br>
                                <form action="CancellazioneAccountCNT" method="POST">
                                <button class="btn btn-primary btn-block" value="Cancella Account" type="sumbit">Cancella Account</button>
                                </form>
                                </div>
                               
                                  
                        
                            </div>
         
    </body>
    <jsp:include page="Footer.jsp" />
</html>
<script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>