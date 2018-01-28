<%-- 
    Document   : UserMenu
    Created on : 24-gen-2018, 16.08.37
    Author     : Michele
--%>

<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();
   UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account - Menù</title>
    </head>
    <jsp:include page="AccountHeader.jsp" />
    <body>
        
        Ciao <%=utente.getNome()%>, scegli cosa fare: <br>
        <form action="AccountCNT" method="POST">
            <button type="sumbit" name="operazione" value="1"> Visualizza dettagli Account</button><br>
            <button type="sumbit" name="operazione" value="2"> Visualizza saldo</button><br>
            <button type="sumbit" name="operazione" value="3"> Visualizza acquisti</button><br>
            <button type="sumbit" name="operazione" value="4"> Visualizza prenotazioni</button><br>
        </form>
    </body>
    <jsp:include page="AccountFooter.jsp" />
</html>
