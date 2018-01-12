<% request.setAttribute("title", "Scheda Film"); %>
<jsp:include page="Header.jsp" />
<%@ page import="model.FilmConValutazioneMedia"%>

<%         
    FilmConValutazioneMedia film=null;
    film = (FilmConValutazioneMedia) request.getAttribute("Film");
    
    System.out.println(film);
    
    if( null == film ){ %>
    <div class="container">
        <div class="text-center">
       Impossibile visualizzare la scheda del film, ci dispiace.
    </div>
    </div>
 <%
    }else{
 %>
 <div>
     <div>
         <img src="<% film.getFilm().getLocandina();%>">
     </div>
     <div>
         <H3><% film.getFilm().getTitolo(); %></H3> printValutazione(film.getValutazioneMedia()); %><br>
         <strong>Genere</strong><% film.getFilm().getGenere(); %> <strong>Distribusione</strong><% film.getFilm().getDistribuzione(); %>
         <strong>Trama</strong>
         <% film.getFilm().getTrama(); %>
         
     </div> 
 </div>
    <% }%> 
<jsp:include page="Footer.jsp" />