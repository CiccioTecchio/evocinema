<%-- 
    Document   : VisualizzazioneProgrammazione
    Created on : 5-gen-2018, 18.35.42
    Author     : luca
--%>

<%@page import="java.util.Iterator"%>
<%@page import="control.programmazioneCNT.StampaSpettacolo"%>
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


<div id="tabs" class="card">
  <div class="card-header">
    <ul class="nav nav-tabs card-header-tabs nav-justified">
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link active" href="#oggi">Oggi</a>
      </li>
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link" href="#domani">Domani</a>
      </li>
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link" href="#bo">Bo</a>
      </li>
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link" href="#">Bim</a>
      </li>
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link" href="#">Bum</a>
      </li>
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link" href="#">Ba</a>
      </li>
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link" href="#">ChePalle</a>
      </li>

    </ul>
  </div>
  <div class="card-body">
      <div class="tab-content">
          <div id="oggi" class="tab-pane active">
              <div class="container-fluid">
                <% 
                Iterator iS = spettacoli.iterator();
                Iterator iF = film.iterator();
                Film f = (Film) iF.next();
                Spettacolo s = (Spettacolo) iS.next();
                StampaSpettacolo.prepare(s, f);
                while(iS.hasNext()){
                    if(f.getIdFilm() == (s = (Spettacolo) iS.next()).getIdFilm())
                        StampaSpettacolo.aggiungiSpettacolo(s);
                    else {
                        out.print(StampaSpettacolo.getResult());
                        f = (Film) iF.next();
                        StampaSpettacolo.prepare(s, f);
                    }       
                }
%>
              </div>
          </div>
          <div id="domani" class="tab-pane">
              Domani
          </div>
          <div id="dopodomani" class="tab-pane">
              DopoDomani
          </div>
      </div>
  </div>
</div>

<script src="javascript/visualizzazione_programmazione.js"></script>

<jsp:include page="Footer.jsp" />