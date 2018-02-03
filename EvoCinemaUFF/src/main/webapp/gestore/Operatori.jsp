<%-- 
    Document   : VisualizzaOperatori
    Created on : 30-gen-2018, 15.54.07
    Author     : Michele
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
   HttpSession s = request.getSession();
   UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");
   List<UtenteRegistrato> operatori = (List<UtenteRegistrato>)s.getAttribute("operatori");
%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizza Operatori</title>
    </head>
     <jsp:include page="Header.jsp" />
    <body>
        <% 
            if (utente==null){
            response.sendRedirect("../Login.jsp");}
            else{
        %> 
        
        <%
            if(operatori==null){
                
        %>       
        <h1>Non sono presenti Operatori registrati.</h1>
        <%
            }else{
                if(operatori.size()==0){
        %>    
        <h1>Non sono presenti Operatori registrati.</h1>
        <%
            }else{      
        %>
        
        <%      
                Iterator<?> itt = operatori.iterator();
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
                    
                        <button class="bottoneEliminaGestore" type="submit" name="<%=ut.getEmail()%>" id="<%="o"+ut.getEmail()%>" <%= disabled %>>Cancella</button>
                    
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
