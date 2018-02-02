<%-- 
    Document   : inserisciFilmLibreria
    Created on : 20-gen-2018, 18.42.10
    Author     : GiuseppeDelGaudio
--%>

<%@page contentType="text/html"%>
<%    request.setAttribute("title", "Inserisci Film Libreria"); %>
<jsp:include page="Header.jsp"/>

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
        
        <form action="inserisciLibreria" id="formInsertLibreria" method="POST" >
            
            <div class="form-group">
                <label for="titolo">Titolo</label>
                <input name="titolo" type="text" class="form-control insfilm" id="titolo" placeholder="Inserisci Titolo">
            </div>
            <div class="form-group">
                <label for="durata">Durata MIN</label>
                <input name="durata" type="number" class="form-control insfilm" id="durata" placeholder="Inserisci Durata"> 
            </div>
            <div class="form-group">
                <label for="regia">Regia</label>
                <input name="regia" type="text"  class="form-control insfilm" id="regia" placeholder="Inserisci Regia">
            </div>
            <div class="form-group">
                <label for="cast"> Cast </label>
                <input name="cast" type="text" class="form-control insfilm" id="cast" placeholder="Inserisci Cast">
            </div>
            <div class="form-group">
                <label for="genere"> Genere </label>
                <input name="genere"  type="text" class="form-control insfilm" id="genere" placeholder="Inserisci Genere">
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
                <select name="censura" class="form-control insfilm" id="censura">
                    <option value="T">T</option>
                    <option value="VM14">VM14</option>
                    <option value="VM16">VM16</option>
                    <option value="VM18">VM18</option>
                </select>
            </div>
            <div class="form-group">
                <%
                String explorerCompa = ""; 
                if( request.getHeader("User-Agent").contains("Trident") ) explorerCompa="yyyy-mm-dd";  
                
                %>
                <label for="dataUscita"> Data Uscita <%= explorerCompa %></label>
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
                <textarea name="trama" class="form-control insfilm" id="trama" placeholder="Inserisci Trama"></textarea>
            </div>
            <div class="form-group">
                <label for="trailer"> Trailer </label>
                <input name="trailer" type="text" class="form-control insfilm" id="genere" placeholder="Inserisci Codice Incorporamento">
            </div>
            <input type="hidden" name="Nomelocandina" id="locandinaform" value="" >
            <button type="button" onclick="validation()" class="btn btn-primary insfilm">Inserisci</button>
            
            
            
        </form>
        
        
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
            <div class="modal-body" id="testoModal" >Controlla i campi. Sono presenti uno o piu errori</div>
            <div class="modal-footer">
                <button class="btn btn-primary btn-lg btn-block" type="button" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>

<script>
    
    
    function validation() {
        
       
        
        var fl=0; 
        
        if(  ! isEmpty($('#titolo')))  fl++;  
        
        if( ! isNumber($('#durata') )) fl++; 
        
        if(  ! isEmpty($('#regia')))  fl++;  
        
        if( ! checkCalendar($('#dataUscita')) ) fl++; 
        
        if( ! isEmpty($('#cast')) ) fl++;
        
        if( ! isEmpty($('#genere')) ) fl++; 
        
        if( ! isEmpty($('#trama') )) fl++; 
        
        console.log(fl); 
        
        if( fl === 7 ) $('#formInsertLibreria').submit(); 
         else{
             
             $("#erroreCampi").modal({
                backdrop: true
                    });
             
         }
    }; 
    
    
    function checkCalendar( x ){
        
        var regExp = /\d{4}\-(0?[1-9]|1[012])\-[0-3][0-9]/; 
        var value = x.val();  
        console.log(value.match(regExp)); 
       if(  value === '' || ! value.match(regExp)  ){ x.addClass("is-invalid");    return true;} 
	
	else{ 
            
            x.removeClass("is-invalid"); 
            x.addClass("is-valid"); 
            
            return false;} 
        
    };
    
    function isNumber( x ){
	
        var regExp =  /^[0-9]+$/;
	var value = x.val(); 
	console.log(value); 
	
	if(  value === '' || ! value.match(regExp)  ){ x.addClass("is-invalid");    return true;} 
	
	else{ 
            
            x.removeClass("is-invalid"); 
              x.addClass("is-valid"); 
            return false;} 
	
};
    function isEmpty( x ){
	
        console.log(x); 
	var value = x.val(); 
	console.log(value); 
        
         var regExp = /[a-z]+\w*/i;
	
	if(  value === '' || ! value.match(regExp)  ){ x.addClass("is-invalid");    return true;} 
	
	else{ 
            
            x.removeClass("is-invalid"); 
              x.addClass("is-valid"); 
            return false;} 
	
};
    
    
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
    
   

<jsp:include page="Footer.jsp"/>
       

   