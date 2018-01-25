<%-- 
    Document   : VisualizzazioneAccount
    Created on : 23-gen-2018, 16.04.16
    Author     : Michele
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.UtenteBase"%>
<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();
   UtenteBase utente = (UtenteBase) s.getAttribute("user");
   
   String passwordCoincidono="";
   String x = ((String) s.getAttribute("passwordCoincidono"));
   s.setAttribute("passwordCoincidono", "true");
   if(x.equals("true")){} else{ passwordCoincidono="Le password non coincidono";}
   
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dataN=sdf.format(utente.getDataNascita().getTime());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dettagli account</title>
    </head>
    <jsp:include page="HeaderUser.jsp" />
    <body>
        
        <div>Profilo di <%=utente.getNome()%> <%=utente.getCognome()%></div>
	
        
        
        Email: <%=utente.getEmail()%><br><br>
        Nome Utente: <%=utente.getNomeUtente()%><br><br>
        
        

        <div>Dati Personali: <br><br>
        
        <form action="ModificaDettagliCNT" method="POST">
            
            Password: <input type="password" name="modificaPassword"> <br>
            Conferma password: <input type="password" name="modificaPassword1">
            <%=passwordCoincidono%><br><br>
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
                if(utente.getSesso().toString().equalsIgnoreCase("MASCHIO")){
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
    </body>
    <jsp:include page="FooterUser.jsp" />
</html>
