<%-- 
    Document   : VisualizzazioneProgrammazione
    Created on : 5-gen-2018, 18.35.42
    Author     : luca
--%>

<%@page import="model.Film"%>
<%@page import="model.Spettacolo"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
<jsp:include page="VisualizzazioneProgrammazioneCNT" /> 
<% request.setAttribute("title", "Programmazione"); %>
<jsp:include page="Header.jsp" />
    
<%
    Collection<Spettacolo> spettacoli = (Collection<Spettacolo>) request.getAttribute("spettacoli");
    Collection<Film> film = (Collection<Film>) request.getAttribute("film");
            
%>


<div class="card">
  <div class="card-header">
    <ul class="nav nav-pills card-header-pills nav-justified">
      <li class="nav-item">
        <a class="nav-link active" href="#">Oggi</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Domani</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Bo</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Bim</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Bum</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Ba</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">ChePalle</a>
      </li>

    </ul>
  </div>
  <div class="card-body">

  </div>
</div>

<jsp:include page="Footer.jsp" />