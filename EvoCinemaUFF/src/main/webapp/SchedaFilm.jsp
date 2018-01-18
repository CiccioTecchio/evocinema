<%-- 
    Document   : VisualizzaLibreria
    Created on : 13-gen-2018, 18.42.05
    Author     : GiuseppeDelGaudio
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="model.Film"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<% request.setAttribute("title", "Scheda Film"); %>
<jsp:include page="Header.jsp" />
<%@ page import="model.FilmConValutazioneMedia"%>



<div class="container-fluid">
    <%         
   
    String idFilmAr =  request.getParameter("film");
    ArrayList<FilmConValutazioneMedia> array = (ArrayList<FilmConValutazioneMedia>) request.getSession().getAttribute("listaFilmValutazione");
    
    if( array == null || idFilmAr == null || idFilmAr == " "){ %>
    

  
        <div class="jumbotron text-center">
            <ol class="breadcrumb-item" >
                
                <h4>Non Sono Presenti Elementi da Visualizzare</h4> 
                
            </ol>
        
        </div>
    
      
      
    
    <%
    
    }else{
    
        FilmConValutazioneMedia film = array.get(Integer.parseInt(idFilmAr)); 
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(film.getDataUscita());
        Calendar cal = film.getDataUscita(); 
        System.out.println(cal.toString());
        String data = simple.format(cal.getTime()); 
        
    %>
    <div class="row"style="margin-top: 2%">
        
        <div class="col-sm-6">
        
            <div class="text-sm-right">
                
               
                <img class="card-img-top img-fluid rounded " style="max-width: 350px ; max-height: 500px " src="<%= film.getLocandina()  %>"
            
            
            </div>
                
            </div>
            
        </div>
    
        <div class="col-sm-6">
            <fieldset class="border border-dark rounded p-5 mr-5 mt-2 ">
                <legend style="width: auto"> Informazioni Film </legend>
                
                
                
                <table class="table-responsive">
                
                    <tr>
                        <td>Titolo : <%= film.getTitolo() %></td>
                        <td id="valutazioneFilm"> <%= film.getValutazioneMedia() %></td> </tr>
                   
                 
                    <tr><td>Data di Uscita : <%= data %></td> <td></td> </tr>
                    <tr><td>Genere : <%= film.getGenere() %></td>   <td></td>   </tr>
                <tr><td>Durata : <%= film.getDurata() %></td>   <td></td>   </tr>
                <tr><td>Regia : <%= film.getRegia() %></td>   <td></td>   </tr>
                <tr><td>Cast : <%= film.getCast() %></td>   <td></td>   </tr>
                <tr><td>Produzione : <%= film.getProduzione() %></td>   <td></td>   </tr>
                
                    
            
                                       
                   
                </table> 
            </fieldset>
        </div>
    </div>
    <div class="row">
     
        <div class="col-sm-12"  >
            
            <fieldset class="border border-dark rounded p-5 mr-5 mt-2 ">
                <legend style="width: auto"> Trama </legend>
            
                <p>
                    
                    <%= film.getTrama() %>
                    
                </p>
            
            </fieldset>
            
            
            
        </div>
        
    
    
    </div>
    <div class="row">
        <div class="col-sm-12" style="background-color: blue; width: 500px; height: 500px" >
            
            
            
            
        </div>
    </div>
    <%} %> 
    </div>
    
    <script>
                        
                        var valore= $("#valutazioneFilm").text();
                        console.log(valore); 
                        
                        $("#valutazioneFilm").rateYo({
                            
                            rating : valore,
                            readOnly : true
                            
                            
                        }); 
                        
                        
                        
                    </script>
<jsp:include page="Footer.jsp" />