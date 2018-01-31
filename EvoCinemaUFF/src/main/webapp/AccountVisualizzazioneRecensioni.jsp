<%-- 
    Document   : AccountVisualizzazioneRecensioni
    Created on : 30-gen-2018, 14.33.33
    Author     : Michele
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Recensione"%>
<%@page import="java.util.List"%>
<%@page import="model.UtenteBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
   HttpSession s = request.getSession();
   UtenteBase utente = (UtenteBase) s.getAttribute("user");
   List<Recensione> recensioni = (List<Recensione>)s.getAttribute("recensioni");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account - Recensioni</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>
        <% 
            if (utente==null){
            response.sendRedirect("Login.jsp");}
            else{
        %> 
        
        
        <%
            if(recensioni==null){
                
        %>       
        <h1>Non sono presenti Recensioni.</h1>
        <%
            }else{
                if(recensioni.size()==0){
        %>    
        <h1>Non sono presenti Recensioni.</h1>
        <%
            }else{      
        %>
        
        <%      
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
                Iterator<?> itt = recensioni.iterator();
                while (itt.hasNext()) {
		Recensione r = (Recensione) itt.next();
       
        %>
            
        <table>
            <tr>
                <td>
                    <p>Data immissione: <%=  sdf.format(r.getDataImmissione().getTime()) %></p>
                </td>
                <td>
                    <p>Film: <%= r.getFilm().getTitolo() %></p>
                </td>
                <td>
                    <p>Valutazione: <%= r.getValutazione() %></p>
                </td>
                <td>
                    <p>Testo: <%= r.getTesto() %></p>
                </td>
            </tr><br>
        </table>
                
        <%
            }
        %>
        <%} } } %>
    </body>
    <jsp:include page="Footer.jsp" />
</html>
