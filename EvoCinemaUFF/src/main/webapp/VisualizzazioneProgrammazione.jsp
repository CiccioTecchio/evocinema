<%-- 
    Document   : VisualizzazioneProgrammazione
    Created on : 5-gen-2018, 18.35.42
    Author     : luca
--%>

<%@page import="model.Spettacolo"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
<jsp:include page="VisualizzazioneProgrammazioneCNT" /> 
<jsp:include page="Header.jsp" />
    
<%
  Collection<Spettacolo> spettacoli = (Collection<Spettacolo>) request.getAttribute("spettacoli");
            
            
            
            
    %>
    
    <body>
        
        <div> Qui non ci sta niente</div>
        
    </body>
    
<jsp:include page="Footer.jsp" />