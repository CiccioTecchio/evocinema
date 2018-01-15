<%-- 
    Document   : VisualizzaLibreria
    Created on : 13-gen-2018, 18.42.05
    Author     : GiuseppeDelGaudio
--%>


<%@page import="java.sql.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="model.FilmConValutazioneMedia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Visualizza Libreria"); %>
<jsp:include page="Header.jsp"/>
<jsp:include page= "/visualizzaValutazioni"/>
<!DOCTYPE html>

<%
    
    ArrayList<FilmConValutazioneMedia> array;
    array = (ArrayList<FilmConValutazioneMedia>) request.getAttribute("listaFilmValutazione");
    
   
%>
    <head>
       
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" >
         
    </head>
   
    

        <%            
            
            if (array == null) {
            
                       
                            
        %>
        <div >

            <p class="m-0 text-center" > Nessun Film Da Visualizzare </p>

        </div>
        <%
        } else {

            request.getSession().setAttribute("listaFilmValutazione", array);
            FilmConValutazioneMedia film = null;

            if( array.size() == 0 ){
            
        %>

        <div >

            <p class="m-0 text-center" >Nessun Film Da Visualizzare </p>

        </div>
        
        <% }else{ %>
                <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

        <script>
            
            
            $(document).ready(function () {

                $("#listaFilm").DataTable();

            });

            
        </script>
         <div class="card-header">
          <i class="fa fa-table"></i> Lista Film VAlutazioni</div>
        <div class="card-body">
        
                    <div class="table-responsive">
                    	<table id="listaFilm" class="table table-bordered" cellspacing="0" width="100%">
				<thead>
                                    <tr>
                                                <th></th>
						<th>Titolo</th>
						<th>Genere</th>
						<th>Trama</th>
						<th>Durata</th>
						<th>Valutazione</th>
					</tr>
				</thead>
                                <tbody>
                                <% 
                                
                                for( int i = 0 ; i<array.size() ; i++ ){
                                    
                                    film = (FilmConValutazioneMedia) array.get(i); 
                                    
                                
                                %>
                                <tr  onclick="window.location.href='SchedaFilm.jsp?film=<%= i %>'" >
                                                <td><img class="img-fluid" width="30%" src="images/locandine/locandina.jpg" ></td>
						<td><%= film.getTitolo()  %></td>
						<td><%= film.getGenere()  %></td>
						<td><%= film.getTrama() %></td>
						<td><%= film.getDurata() %></td>
						<td><%= film.getValutazioneMedia() %></td>
					</tr>
                                        
                                        <% } // visualizza pure film non votati   %>
					
				</tbody>
			</table>
                    
                    </div>
        </div>
        
        


        <% }}  %>
     
    
       <jsp:include page= "/Footer.jsp"/>
      
   
