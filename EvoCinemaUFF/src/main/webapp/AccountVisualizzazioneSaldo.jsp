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
        Il tuo saldo è: <%= utente.getSaldo()%>
        
        <form action="GestioneSaldoCNT" method="POST">
            Inserisci importo da ricaricare:<br>
            <input type="text" name="ricaricaSaldo"><br>
            <button type="submit" name="ricarica" value="true">Ricarica</button> 
            
        </form>
    </body>
    <jsp:include page="Footer.jsp" />
</html>
