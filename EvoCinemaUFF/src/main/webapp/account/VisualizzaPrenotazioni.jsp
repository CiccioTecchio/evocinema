<%-- 
    Document   : VisualizzaPrenotazioni
    Created on : 24-gen-2018, 16.09.49
    Author     : Michele
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.Prenotazione"%>
<%@page import="database.FilmDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.UtenteBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();
   UtenteBase utente = (UtenteBase) s.getAttribute("user");
   List<Prenotazione> prenotazioni = (List<Prenotazione>) s.getAttribute("prenotazioni");
   
   FilmDAO filmModel = new FilmDAO();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account - Prenotazioni</title>
    </head>
     <jsp:include page="HeaderUser.jsp" />
    <body>
        Prenotazioni effettuate: <%= prenotazioni.size()%><br>
        
        <table>
        <%
		if (prenotazioni != null && prenotazioni.size() != 0) {
			Iterator<?> itt = prenotazioni.iterator();
			while (itt.hasNext()) {
				Prenotazione a = (Prenotazione) itt.next();
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
