<%-- 
    Document   : VisualizzazioneProgrammazione
    Created on : 5-gen-2018, 18.35.42
    Author     : luca
--%>

<%@page import="java.util.Calendar"%>
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
    Collection<Spettacolo> spettacoli = (Collection<Spettacolo>) request.getAttribute("spettacoli1");
    Collection<Film> film = (Collection<Film>) request.getAttribute("film1");
    String[] giorni = {"Domenica","Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato"};
    Calendar day = Calendar.getInstance();
%>

<div id="tabs" class="card">
  <div class="card-header">
    <ul class="nav nav-tabs card-header-tabs">
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link active" href="#oggi">Oggi - <%= giorni[(day.get(Calendar.DAY_OF_WEEK) - 1) % 7]%></a>
      </li>
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link" href="#domani"><%= giorni[day.get(Calendar.DAY_OF_WEEK)]%></a>
      </li>
      <li class="nav-item">
        <a data-toggle="tab" class="nav-link" href="#bo"><%= giorni[(day.get(Calendar.DAY_OF_WEEK) + 1) % 7]%></a>
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
              <div class="container-fluid">
                <% 
                iS = ((Collection<Spettacolo>) request.getAttribute("spettacoli2")).iterator();
                iF = ((Collection<Film>) request.getAttribute("film2")).iterator();
                f = (Film) iF.next();
                s = (Spettacolo) iS.next();
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
          <div id="dopodomani" class="tab-pane">
                <% 
                iS = ((Collection<Spettacolo>) request.getAttribute("spettacoli3")).iterator();
                iF = ((Collection<Film>) request.getAttribute("film3")).iterator();
                f = (Film) iF.next();
                s = (Spettacolo) iS.next();
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
      </div>
  </div>
</div>

<script src="javascript/visualizzazione_programmazione.js"></script>

<jsp:include page="Footer.jsp" />