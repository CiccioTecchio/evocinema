<%-- 
    Document   : Login
    Created on : 18-gen-2018, 10.41.05
    Author     : Giuseppe
--%>

<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Login"); %>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    </head>
    <body>
        <%
            HttpSession s = request.getSession();
            UtenteRegistrato utente;
            utente = (UtenteRegistrato) s.getAttribute("user");
            
            Boolean login = (Boolean) s.getAttribute("loginErrato");
            if (utente == null) {

                if (login != null) {
        %>
        <h2>Login errato - Riprova</h2>
        <%
        } else {
        %>
        <h2>Login</h2>
        <%
            }
        %>
        <p>Inserisci i dati accedere al tuo account</p>

        <form method="post"
              action="Login">
            <label class="labelRegistrazione">E-Mail: </label> <input
                type="text" id="emailLogin" name="emailLogin"> <br> <label
                class="labelRegistrazione">Password: </label> <input type="password"
                id="passwordLogin" name="passwordLogin"> <br> <input
                type="submit" name="bottoneLogin" value="Login">
        </form>
        <a href="Registrazione.jsp">Non sei registrato? Registrati ora.</a>
        <%
        } else {
        %>
        <h2>Sei già loggato!</h2>
        <%
            }
        %>

    </body>
    <%@ include file="Footer.jsp"%>

</html>