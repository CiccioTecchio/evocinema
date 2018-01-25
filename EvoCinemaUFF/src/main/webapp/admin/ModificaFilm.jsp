<%-- 
    Document   : ModificaFilm
    Created on : 23-gen-2018, 15.48.57
    Author     : GiuseppeDelGaudio
--%>

<%@page import="model.Film"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.FilmConValutazioneMedia"%>
<%@page import="model.Film.vistoCensura"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setAttribute("title", "Modifica Film"); %>
<jsp:include page="HeaderAdmin.jsp"/>



<div class="container-fluid">
    <%
        
        
        String idFilmAr = request.getParameter("position");
        
        if( idFilmAr == null || idFilmAr.equals("") ) idFilmAr = (String) request.getAttribute("index");
        
        ArrayList<Film> array = (ArrayList<Film>) request.getSession().getAttribute("listaFilmValutazione"); 
        request.setAttribute("index", idFilmAr );
       

        
        if (array == null || idFilmAr == null ) { %>
        
</div>
        
        


    <div class="jumbotron text-center">
        <ol class="breadcrumb-item" >

            <h4>Non Sono Presenti Elementi da Visualizzare</h4> 

        </ol>

 




    <%

    } else {

        Film film = array.get(Integer.parseInt(idFilmAr));
       
        System.out.println(film.getDataUscita());
        Calendar cal = film.getDataUscita();
        System.out.println(cal.toString());
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(cal.toString());
        String data = simple.format(cal.getTime());
       
        //String  data = simple.format(film.getDataUscita());
        int tempo = ( film.getDurata().getHours() * 60 ) + film.getDurata().getMinutes() ;
        String sel1="",sel2="",sel3="",sel4="";

        switch (film.getVistoCensura()){

        case T : sel1="selected"; break;

        case VM14 : sel2="selected";   break;

        case VM16 : sel3="selected";  break;

        case VM18 : sel4="selected"; break;

        };

    %>
    
    <div class="card mb-3" >
    
    <div class="card-header " >
        <p>Modifica Un film in Libreria</p>
    </div>
    <%
         String messaggioModifica = (String) request.getAttribute("messaggioUpdate");
         System.out.println(messaggioModifica);
        if (messaggioModifica != null) { %>
        

<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= messaggioModifica %></strong>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<% } %>
   
    
    <div class="card-body mt-3" >

        <form method="POST" onchange="postFilesData()" enctype="multipart/form-data" id="formImage" >
            <div class="form-group">
                <label for="uploadLocandina">Upload Locandina</label>
                <input  type="file" name="locandina" id="uploadLocandina"/>
                <input type="hidden" name="action" value="change"/>
                <input type="hidden" name="old" value="<%= film.getLocandina() %>"/>
            </div>
        </form>
        
            <img id="locandina" class='card-img-top img-fluid thumbnail ' style='max-width: 350px ; max-height: 500px ' src="../<%= film.getLocandina()  %>"/>
        
        <form action="modificaLibreria" method="POST" >
            
            <div class="form-group">
                <label for="titolo">Titolo</label>
                <input name="titolo" value="<%= film.getTitolo() %>" required="true" type="text" class="form-control insfilm" id="titolo" placeholder="Inserisci Titolo">
            </div>
            <div class="form-group">
                <label for="durata">Durata MIN</label>
                <input name="durata" required="true" value="<%= tempo %>" type="number" class="form-control insfilm" id="durata" placeholder="Inserisci Durata"> 
            </div>
            <div class="form-group">
                <label for="regia">Regia</label>
                <input name="regia" type="text" value="<%= film.getRegia() %>" required="true" class="form-control insfilm" id="regia" placeholder="Inserisci Regia">
            </div>
            <div class="form-group">
                <label for="cast"> Cast </label>
                <input name="cast" type="text" value="<%= film.getCast() %>" class="form-control insfilm" id="cast" placeholder="Inserisci Cast">
            </div>
            <div class="form-group">
                <label for="genere"> Genere </label>
                <input name="genere" required="true" value="<%= film.getGenere() %>" type="text" class="form-control insfilm" id="genere" placeholder="Inserisci Genere">
            </div>
            <div class="form-group" >
                <label for="tipo"> Tipo </label>
                <select  class="form-control insfilm" disabled="true" id="tipo" >
                    <option selected="true">FILM</option>
                </select>
                <input type="hidden" name="tipo" value="FILM" >
            </div>
            <div class="form-group">
                <label for="censura"> Seleziona censura </label>
                <select name="censura" required="true" class="form-control insfilm" id="censura">
                <option <%= sel1 %>>T</option>
                <option <%= sel2 %>>VM14</option>
                <option <%= sel3 %>>VM16</option>
                <option <%= sel4 %>>VM18</option>
                </select>
            </div>
            <div class="form-group">
                <label for="dataUscita"> Data Uscita </label>
                <input name="dataUscita" value="<%= data %>" type="date" class="form-control insfilm" id="dataUscita"  placeholder="Inserisci Data Uscita">
            </div>
            <div class="form-group">
                <label for="distribuzione"> Distribuzione </label>
                <input name="distribuzione" value="<%= film.getDistribuzione() %>" type="text" class="form-control insfilm" id="distribuzione" placeholder="Inserisci Distribuzione">
            </div>
            <div class="form-group">
                <label for="produzione"> Produzione </label>
                <input name="produzione" value="<%= film.getProduzione() %>" type="text" class="form-control insfilm" id="produzione" placeholder="Inserisci Produzione">
            </div>
            <div class="form-group">
                <label for="genere"> Trama </label>
                <textarea name="trama"  required="true" class="form-control insfilm" id="trama" placeholder="Inserisci Trama"><%= film.getTrama() %></textarea>
            </div>
            <div class="form-group">
                <label for="trailer"> Trailer </label>
                <input name="trailer" value="<%= film.getTrailer() %>" type="text" class="form-control insfilm" id="genere" placeholder="Inserisci Codice Incorporamento">
            </div>
            <input type="hidden" name="Nomelocandina" id="locandinaform" value="<%= film.getLocandina() %>" >
            <input type="hidden" name="idOpera" id="locandinaform" value="<%= film.getIdFilm() %>" >
            <input type="hidden" name="index" value="<%= idFilmAr %>"/>
            <button type="submit" class="btn btn-primary insfilm">Modifica</button>
            
            
            
        </form>
        
        
    </div>
    
</div>
    
    
            <% }  %>
   </div>
   <script>
            function postFilesData(){
	
        
	    //$.each(files , function(key, value)
            var form = document.getElementById('formImage');
            var data = new FormData(form);
           
    
    
	 $.ajax({
        url: 'uploadLocandina',
        type: 'POST',
        data: data,
        cache: false,
        dataType: 'json',
        processData: false, 
        contentType: false, 
        success: function(data, textStatus, jqXHR)
        {
        
        $("#locandina").attr("src" , "../images/locandine/"+data.nomeFile+"");
        $("#locandinaform").attr("value","images/locandine/"+data.nomeFile+"" ); 
        $(".insfilm").each( function (e) {
        
        
    } ); 
        $("#tipo").attr("disabled",true);
        },
        error: function(jqXHR, textStatus, errorThrown)
        {
           $("#locandina").append("<div class='alert alert-warning alert-dismissible fade show' role='alert'>\n\
                <strong>Errore durante l'upload della locandina </strong>\n\
                <button type='button' class='close' data-dismiss='alert' aria-label='Close'>\n\
                <span aria-hidden='true'>&times;</span>\n\
                </button>\n\
                </div>");
        }
	    });
	}
        
        </script>
<jsp:include page="FooterAdmin.jsp"/>