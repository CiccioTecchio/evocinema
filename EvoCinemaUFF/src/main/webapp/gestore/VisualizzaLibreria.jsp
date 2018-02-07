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
                

        
         <div class="card-header">
          <i class="fa fa-table"></i> Lista Film Valutazioni</div>
        <div class="card-body">
        
                    <div class="table-responsive">
                        <table id="listaFilm" style="border-" class="table table-bordered" cellspacing="0" width="100%">
				<thead>
                                    <tr>
                                        
                                                <th>Locandina</th>
						<th>Titolo</th>
						<th>Genere</th>
						<th>Trama</th>
						<th>Durata</th>
                                
                                                <th id="ValBott" >Valutazione</th>
                                                <th id="ValBottLeft"></th>
						
                                                
        
					</tr>
				</thead>
                                <tbody>
                                <% 
                                String valutazione ; 
                                
                                for( int i = 0 ; i<array.size() ; i++ ){
                                    
                                    film = (FilmConValutazioneMedia) array.get(i); 
                                
                                    if( film.getValutazioneMedia() == 0.0 ) {
                                    
                                        valutazione = "0"; 
                                        
                                        
                                    
                                    }else {
                                    
                                        valutazione = film.getValutazioneMedia()+""; 
                                    
                                    }
                                
                                %>
                                <tr class="selezionato"  onclick="window.location.href='SchedaFilm.jsp?film=<%= i %>'" style="cursor: pointer;">
                                                <td><img class="img-fluid" src="../<%= film.getLocandina() %>" ></td>
						<td><%= film.getTitolo()  %></td>
						<td><%= film.getGenere()  %></td>
						<td><%= film.getTrama() %></td>
						<td><%= film.getDurata() %></td>
                               
                                               
                                                <td class="rigaVal"><%= valutazione %></td>
                                                 <td class="rateYo rigaValLeft" ><%= valutazione %></td>
					</tr>
                                        
                                        <% }  %>
					
				</tbody>
			</table>
                                        <script>
            
            
            $(document).ready(function () {

                $("#listaFilm").DataTable({
                    
                   "order" : [[1, "asc"]],
                    
                    "columns": [
                           
                                {"orderable": false},
                               null,
                               null,
                               { "orderable": false },
                               null,
                               null,
                              { "orderable": false }]
                          
                          
                                        
                    
                });
                
                
            });
            
            

            
        </script>
                                        
                                        
                                        
                                        <script>
                                            
                                            
                                         
                                          var valore;
                                         
                                        $(".rateYo").each( function (e) {
                                            
                                              valore = $(this).text(); 
                                              
                                              if( ! (valore === "Non Disponibile")){
                                            
                                             $(this).rateYo({
                                                 
                                                 rating : valore,
                                                 readOnly: true,
                                                 precision: 4
                                                 
                                             });
                                             
                                             $("#ValBott").css("border-bottom-width", "2px"); 
                                             $("#ValBott").css("border-right-width", "0px");
                                             $("#ValBottLeft").css("border-left-width", "0px");
                                             $(".rigaVal").css("border-right-width", "0px");
                                             $(".rigaValLeft").css("border-left-width", "0px");
                                             
                                            
                                                }
                                             
                                             
                                         }); 
                                         
                                         
                                        
                                     
          
                                            
                                            
                                          
 
                                              
                                            
                                            
                                            
                                        </script>
                    
                    </div>
        </div>
        
        


        <% }}  %>
     
    
       <jsp:include page= "Footer.jsp"/>
      
   
