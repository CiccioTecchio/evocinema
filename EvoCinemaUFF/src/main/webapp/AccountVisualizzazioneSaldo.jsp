<%-- 
    Document   : VisualizzazioneSaldo
    Created on : 23-gen-2018, 19.52.58
    Author     : Michele
--%>

<%@page import="model.UtenteBase"%>
<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();
   UtenteBase utente = (UtenteBase) s.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account - Saldo</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>
        <% 
            if (utente==null){
            response.sendRedirect("Login.jsp");}
            else{
        %>   
        Il tuo saldo è: <%= utente.getSaldo()%>
        
        
            Inserisci importo da ricaricare:<br>
            
            <input type="text" id="ricaricaSaldo" name="ricaricaSaldo"><br><br>
            
        
        <button onclick="confermaRicarica()" class="btn btn-primary btn-block" type="sumbit">Ricarica</button>
                                
                                <script>
                                    function confermaRicarica() {
                                        var txt;
                                        if (confirm("Sei sicuro di voler ricaricare il saldo?")) {
                                            txt = "OK";
                                        } else {
                                            txt = "ANNULLA";
                                        }

                                    if(txt==="OK"){
                                        var ricarica= $('#ricaricaSaldo').val();
                                        
                                        $.post('GestioneSaldoCNT', {
                                         "ricaricaSaldo" : ricarica
                                        }, function() {
                                                
                                                alert("Ricarica effettuata.");
                                                location.reload();
                                        }).fail(function() {
                                                alert("Impossibile ricaricare.");
                                                location.reload();
                                        });
                                    }
                                }
                                </script>
        
        <% } %>
    </body>
    <jsp:include page="Footer.jsp" />
</html>
