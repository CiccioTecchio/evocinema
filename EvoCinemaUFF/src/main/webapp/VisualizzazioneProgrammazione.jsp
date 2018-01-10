<%-- 
    Document   : VisualizzazioneProgrammazione
    Created on : 5-gen-2018, 18.35.42
    Author     : luca
--%>

<%@page import="model.Spettacolo"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Programmazione</title>
    </head>
    <body>
        <jsp:include page="VisualizzazioneProgrammazione" />
        <%
            Collection<Spettacolo> spettacoli = (Collection<Spettacolo>) request.getAttribute("spettacoli");
            
            
            
            
        %>
    </body>
    
</html>
