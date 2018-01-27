<%-- 
    Document   : MainPage
    Created on : 23-gen-2018, 11.06.31
    Author     : Michele
--%>

<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();
   UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");
   System.out.println("Utente in sessione nella jsp MainPage: "+utente);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
    </head>
    <jsp:include page="AccountHeader.jsp" />
    <body>
        Utente: <%= utente.getNome()%> <%= utente.getCognome()%>
        
        <a href="AccountUserMenu.jsp">Vai al menù</a>
        
        
        
    </body>
    <jsp:include page="AccountFooter.jsp" />
</html>

