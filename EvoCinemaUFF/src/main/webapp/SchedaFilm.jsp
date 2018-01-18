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
    <div class="row ml-5" >
        
        <div class="card mb-3 ml-5">
                
        
            
                
               
                <img class="card-img-top img-fluid thumbnail " style="max-width: 350px ; max-height: 500px " src="<%= film.getLocandina()  %>"
            
            
            </div>
                
            
            
        </div>
    
        <div class="card mb-3 ml-5">
            <span class="card-header" > Informazioni Film </span>
            <div class="row"> 
                    <div class="card-body pt-5 mt-4" >
                        <table>
                            <tr>
                                <td>Titolo :</td><td> <strong><%= film.getTitolo() %></strong></td></tr>
                            <tr>
                                <td> Genere :</td><td> <%= film.getGenere() %></td></tr>
                            <tr>
                                <td>Durata :</td><td> <%= film.getDurata() %></td></tr>
                            <tr>
                                <td>Regia :</td><td> <%= film.getRegia() %></td></tr>
                            <tr>
                                <td>Cast :</td><td> <%= film.getCast() %></td></tr>
                            <tr>
                                <td>Produzione :</td><td> <%= film.getProduzione() %></td></tr>
                        </table>
                    
                </div>
                    <div class="card-body pt-5 mt-4" >
                        <strong>Valutazione Utenti</strong>
                        <p id="valutazioneFilm" class="card-body mr-2"> <%= film.getValutazioneMedia() %></p>  
                        
                    </div>
            </div>
        
            
        </div>
    </div>
    <div class="row">
     
        <div class="card mb-3"  >
            
            
            <span class="card-header"> Trama </span>
            
            <p class="card-body">
                    
                    <%= film.getTrama() %>
                    
                </p>
            
     
            
            
        </div>
        
    
    
    </div>
    
        <div class="card mb-3">
            
            <span class="card-header" > Valuta il film </span>
            
            <div class="card-body mx-auto mt-2" id="votaUser" >
                
                <!-- Qui Le stelle  -->
                
            </div>
            
            <div class="card-body">
                
                <form id="form" method="GET" action="nuovaRecensione"  >
                
                    <input name="rate" type="hidden" id="valoreRate" value="" >
                    <textarea  name="testo"style="width: 100%; resize: none;  " placeholder="Inserisci qui la tua Recensione "  id="testoRecensione" ></textarea>
                    <button type="button" class="btn btn-primary btn-lg btn-block" onclick="check()" > Invia la tua recensione </button>
                </form>
            </div>
            
           
        </div>
    </div>
    
    <div class="modal fade" id="erroreCampi" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Errore</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Devi completare tutti i campi prima di recensire</div>
          <div class="modal-footer">
            <button class="btn btn-primary btn-lg btn-block" type="button" data-dismiss="modal">OK</button>
          </div>
        </div>
      </div>
    </div>
    <%} %> 
    
    <script>
                        
                        var valore= $("#valutazioneFilm").text();
                        console.log(valore); 
                        
                        $("#valutazioneFilm").rateYo({
                            
                            rating : valore,
                            readOnly : true
                            
                            
                        }); 
                        
                        
                        $("#votaUser").rateYo({
                            
                            
                            onSet : function ( rating , rateYoInstance ){
                                
                                alert("Il valore è "+ rating ); 
                                $("#valoreRate").attr("value",rating); 
                                
                            }
                            
                        }); 
                        
                        
                        
                        function check() {
                            
                            var valoreRate = $("#valoreRate").attr("value"); 
                            var text = $("#testoRecensione").val();
                            if( ( valoreRate !== ""  ) && ( text !== null && text !== "" && text !== "  " ) ){
                                
                                     $("form").submit(); 
                                
                            }else{
                                
                                
                                $("#erroreCampi").modal({
                                                    backdrop: true
                                                                        });
                                
                            }
                            
                            
                        }
                        
                       
                      
 
          
                        
                    </script>
<jsp:include page="Footer.jsp" />