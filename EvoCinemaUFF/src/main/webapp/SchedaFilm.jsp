<%-- 
    Document   : VisualizzaLibreria
    Created on : 13-gen-2018, 18.42.05
    Author     : GiuseppeDelGaudio
--%>
<%@page import="java.util.ArrayList"%>
<% request.setAttribute("title", "Scheda Film"); %>
<jsp:include page="Header.jsp" />
<%@ page import="model.FilmConValutazioneMedia"%>

<%         
   
    String idFilmAr =  request.getParameter("film");
     ArrayList array = (ArrayList<FilmConValutazioneMedia>) request.getSession().getAttribute("listaFilmValutazione");
     
    
    
    if( null == array ){ %>
    <div class="container">
        <div class="text-center">
            <% System.out.println( "Grandezza array" + array ); %>
       Impossibile visualizzare la scheda del film, ci dispiace.
    </div>
    </div>
 <%
    }else{

    FilmConValutazioneMedia film ; 
    film = (FilmConValutazioneMedia) array.get(Integer.parseInt(idFilmAr)); 

 %>
 <div>
     <div>
         <%= film.getLocandina() %>
     </div>
     <div>
         <H3><%= film.getTitolo() %></H3> <%= film.getValutazioneMedia() %><br>
         <strong>Genere</strong><%=film.getGenere() %> <strong>Distribusione</strong><%= film.getDistribuzione() %>
         <strong>Trama</strong>
         <%= film.getTrama() %>
         
     </div> 
 </div>
    <% }%> 
<jsp:include page="Footer.jsp" />