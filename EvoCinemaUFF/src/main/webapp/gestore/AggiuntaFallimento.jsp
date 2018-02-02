<%-- 
    Document   : AggiuntaFallimento
    Created on : 2-feb-2018, 12.10.27
    Author     : luca
--%>

<% request.setAttribute("title", request.getAttribute("title")); %>
<jsp:include page="Header.jsp" />
<% String msg = (String) request.getAttribute("messaggio"); %>
<div class="container">
    <div class="row">
        <div class="alert alert-danger" role="alert"><h6><%= msg %></h6></div>
    </div>
    <div class="row">
        <a href="AggiungiSpettacolo.jsp"><button class="btn btn-primary">Inserisci lo Spettacolo</button></a>
    </div>

<jsp:include page="Footer.jsp" />