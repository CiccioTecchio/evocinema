<%-- 
    Document   : VisualizzaAcquisti
    Created on : 24-gen-2018, 16.09.37
    Author     : Michele
--%>

<%@page import="database.FilmDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Acquisto"%>
<%@page import="java.util.List"%>
<%@page import="model.UtenteBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();
   UtenteBase utente = (UtenteBase) s.getAttribute("user");
   List<Acquisto> acquisti = (List<Acquisto>) s.getAttribute("acquisti");
   
   FilmDAO filmModel = new FilmDAO();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account - Acquisti</title>
    </head>
    <jsp:include page="HeaderUser.jsp" />
    <body>
        Acquisti effettuati: <%= acquisti.size()%><br>
        
        <table>
        <%
		if (acquisti != null && acquisti.size() != 0) {
			Iterator<?> itt = acquisti.iterator();
			while (itt.hasNext()) {
				Acquisto a = (Acquisto) itt.next();
	%>
        
            <td>
                <tr>
                    Spettacolo: <%= filmModel.foundByID(a.getIdSpettacolo()).getTitolo()%>
                </tr>
                <tr>
                    Sala: <%= a.getSala().getIdSala()%>
                </tr>
                <tr>
                    Posto: riga <%= a.getPostoRiga()%>, colonna <%= a.getPostoColonna()%>
                </tr>
                
            </td>
            <% }} else{
            %>Non sono stati effettuati acquisti.
            <%}%>
        </table>
        
        
    </body>
    <jsp:include page="FooterUser.jsp" />
</html>
