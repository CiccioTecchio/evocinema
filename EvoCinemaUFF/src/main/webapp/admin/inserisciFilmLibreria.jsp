<%-- 
    Document   : inserisciFilmLibreria
    Created on : 20-gen-2018, 18.42.10
    Author     : GiuseppeDelGaudio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%    request.setAttribute("title", "Inserisci Film Libreria"); %>
<jsp:include page="HeaderAdmin.jsp"/>

<div class="card mb-3" >
    
    <div class="card-header " >
        <p>Inserisci un film in Libreria</p>
    </div>
    <%
      
         String messaggio = (String) request.getAttribute("messaggioInsert");
        if (messaggio != null) { %>
        

<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= messaggio %></strong>
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
                <input type="hidden" name="action" value="new"/>
            </div>
            
            <div id="locandina" ></div>
            
        </form>
        
        <form action="inserisciLibreria" method="POST" >
            
            <div class="form-group">
                <label for="titolo">Titolo</label>
                <input name="titolo" required="true" type="text" class="form-control insfilm" id="titolo" placeholder="Inserisci Titolo">
            </div>
            <div class="form-group">
                <label for="durata">Durata MIN</label>
                <input name="durata" required="true" type="number" class="form-control insfilm" id="durata" placeholder="Inserisci Durata"> 
            </div>
            <div class="form-group">
                <label for="regia">Regia</label>
                <input name="regia" type="text" required="true" class="form-control insfilm" id="regia" placeholder="Inserisci Regia">
            </div>
            <div class="form-group">
                <label for="cast"> Cast </label>
                <input name="cast" type="text" class="form-control insfilm" id="cast" placeholder="Inserisci Cast">
            </div>
            <div class="form-group">
                <label for="genere"> Genere </label>
                <input name="genere" required="true" type="text" class="form-control insfilm" id="genere" placeholder="Inserisci Genere">
            </div>
            <div class="form-group" >
                <label for="tipo"> Tipo </label>
                <select  class="form-control insfilm" id="tipo" >
                    <option selected="true">FILM</option>
                    <option>TEATRO</option>
                    <option>ALTRO</option>
                </select>
                <input type="hidden" name="tipo" value="FILM" >
            </div>
            <div class="form-group">
                <label for="censura"> Seleziona censura </label>
                <select name="censura" required="true" class="form-control insfilm" id="censura">
                    <option value="T">T</option>
                    <option value="VM14">VM14</option>
                    <option value="VM16">VM16</option>
                    <option value="VM18">VM18</option>
                </select>
            </div>
            <div class="form-group">
                <label for="dataUscita"> Data Uscita </label>
            <input name="dataUscita" type="date" class="form-control insfilm" id="dataUscita"  placeholder="Inserisci Data Uscita">
            </div>
            <div class="form-group">
                <label for="distribuzione"> Distribuzione </label>
                <input name="distribuzione" type="text" class="form-control insfilm" id="distribuzione" placeholder="Inserisci Distribuzione">
            </div>
            <div class="form-group">
                <label for="produzione"> Produzione </label>
                <input name="produzione" type="text" class="form-control insfilm" id="produzione" placeholder="Inserisci Produzione">
            </div>
            <div class="form-group">
                <label for="genere"> Trama </label>
                <textarea name="trama" required="true" class="form-control insfilm" id="trama" placeholder="Inserisci Trama"></textarea>
            </div>
            <div class="form-group">
                <label for="trailer"> Trailer </label>
                <input name="trailer" type="text" class="form-control insfilm" id="genere" placeholder="Inserisci Codice Incorporamento">
            </div>
            <input type="hidden" name="Nomelocandina" id="locandinaform" value="" >
            <button type="submit" class="btn btn-primary insfilm">Inserisci</button>
            
            
            
        </form>
        
        
    </div>
    
</div>
<script>
    
  $( document ).ready(function() {
    
    $(".insfilm").each( function (e) {
        
        $(this).attr("disabled", true); 
        
        
    } ); 
    
});
	    
       
	
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
        
        $("#locandina").append("<img class='card-img-top img-fluid thumbnail ' style='max-width: 350px ; max-height: 500px ' src='../images/locandine/"+data.nomeFile+"'>");
        $("#locandinaform").attr("value",data.nomeFile ); 
        $(".insfilm").each( function (e) {
        
        $(this).attr("disabled", false); 
        
        
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
       

   