<%-- 
    Document   : VisualizzaGestori
    Created on : 30-gen-2018, 16.16.27
    Author     : Michele
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
   HttpSession s = request.getSession();
   UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");
   List<UtenteRegistrato> gestori = (List<UtenteRegistrato>)s.getAttribute("gestori");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizza Operatori</title>
    </head>
     <jsp:include page="HeaderAdmin.jsp" />
    <body>
        <% 
            if (utente==null){
            response.sendRedirect("Login.jsp");}
            else{
        %> 
        
        <%
            if(gestori==null){
                
        %>       
        <h1>Non sono presenti Gestori registrati.</h1>
        <%
            }else{
                if(gestori.size()==0){
        %>    
        <h1>Non sono presenti Gestori registrati.</h1>
        <%
            }else{      
        %>
        
        <%      
                Iterator<?> itt = gestori.iterator();
                String disabled="";
                while (itt.hasNext()) {
		UtenteRegistrato ut = (UtenteRegistrato) itt.next();
                if(ut.getEmail().equals(utente.getEmail())){ 
                    disabled="disabled";
                }
       
        %>
            
        <table>
            <tr>
                <td>
                    <p>Nome: <%=  ut.getNome() %></p>
                </td>
                <td>
                    <p>Cognome <%= ut.getCognome() %></p>
                </td>
                <td>
                    <p>Email: <%= ut.getEmail() %></p>
                </td>
                <td>
                    <p>Ruolo <%= ut.getRuolo().name() %></p>
                </td>
                <td>
                    <p>username <%= ut.getNomeUtente() %></p>
                </td>
                <td>
                    
                        <button class="bottoni" type="submit" name="<%=ut.getEmail()%>" id="<%="g"+ut.getEmail()%>" <%= disabled %>>Cancella</button>
                   
                </td>
                
            </tr><br>
        </table>
                
        <%
            disabled="";}
        %>
        <%} } } %>
    </body>
     <jsp:include page="FooterAdmin.jsp" />
</html>