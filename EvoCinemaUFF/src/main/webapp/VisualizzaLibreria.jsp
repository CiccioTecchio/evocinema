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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
    

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
                

        <script>
            
            
            $(document).ready(function () {

                $("#listaFilm").DataTable();

            });

            
        </script>
         <div class="card-header">
          <i class="fa fa-table"></i> Lista Film Valutazioni</div>
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
                                String valutazione ; 
                                
                                for( int i = 0 ; i<array.size() ; i++ ){
                                    
                                    film = (FilmConValutazioneMedia) array.get(i); 
                                
                                    if( film.getValutazioneMedia() == 0.0 ) {
                                    
                                        valutazione = "Non Disponibile"; // -1 default errore 
                                        
                                    
                                    }else {
                                    
                                        valutazione = film.getValutazioneMedia()+""; 
                                    
                                    }
                                
                                %>
                                <tr  onclick="window.location.href='SchedaFilm.jsp?film=<%= i %>'" >
                                                <td><img class="img-fluid" src="<%= film.getLocandina() %>" ></td>
						<td><%= film.getTitolo()  %></td>
						<td><%= film.getGenere()  %></td>
						<td><%= film.getTrama() %></td>
						<td><%= film.getDurata() %></td>
                                                <td class="rateYo" ><%= valutazione %></td>
					</tr>
                                        
                                        <% }  %>
					
				</tbody>
			</table>
                                        <script>
                                           
                                         
                                        $(".rateYo").each( function (e) {
                                             
                                              valore = $(this).text(); 

                                              if( ! (valore === "Non Disponibile")){
        
                                             $(this).rateYo({
                                                 
                                                 rating : valore,
                                                 readOnly: true,
                                                 precision: 4
                                                 
                                             });
                                 
                                                }
                                             
                                             
                                         });
                                         
                                     
          
                                            
                                            
                                          
 
                                              
                                            
                                            
                                            
                                        </script>
                    
                    </div>
        </div>
        
        


        <% }}  %>
     
    
       <jsp:include page= "/Footer.jsp"/>
      
   
