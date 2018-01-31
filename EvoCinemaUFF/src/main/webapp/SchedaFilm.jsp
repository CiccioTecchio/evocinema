<%-- 
    Document   : VisualizzaLibreria
    Created on : 13-gen-2018, 18.42.05
    Author     : GiuseppeDelGaudio
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Recensione"%>
<%@page import="java.util.Collection"%>
<%@page import="model.UtenteBase"%>
<%@page import="model.UtenteRegistrato"%>
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
        String idFilmAr = request.getParameter("film");
        if (idFilmAr == null) {
            idFilmAr = (String) request.getAttribute("index");
        }
        ArrayList<FilmConValutazioneMedia> array = (ArrayList<FilmConValutazioneMedia>) request.getSession().getAttribute("listaFilmValutazione");

        if (array == null || idFilmAr == null || idFilmAr == "") { %>



    <div class="jumbotron text-center">
        <ol class="breadcrumb-item" >

            <h4>Non sono presenti film da visualizzare</h4> 

        </ol>

    </div>

    <%

    } else {

        FilmConValutazioneMedia film = array.get(Integer.parseInt(idFilmAr));
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(film.getDataUscita());
        Calendar cal = film.getDataUscita();
        System.out.println(cal.toString());
        String data = simple.format(cal.getTime());

    %>
    <div class="row ml-3 mt-5">

        <div class="card mb-3">
            <img class="card-img-top img-fluid thumbnail " style="max-width: 350px ; max-height: 500px " src="<%= film.getLocandina()%>">
        </div>

    <div class="card mb-3">
        <span class="card-header" > Informazioni Film </span>

        <div class="card-body pt-1 mt-4 pl-5 pr-5" >
            <table class="table-responsive">
                    <tr>
                        <td class="text-right pr-3"> <H3>Voto :</H3></td><td><p id="valutazioneFilm" class="card-body mr-2"> <%= film.getValutazioneMedia()%></p> </td>

                    </tr>
                    <tr>
                        <td class="text-right pr-3"><H5><strong> Titolo :</strong></H5></td><td> <H2> <%= film.getTitolo()%></H2></td></tr>
                    <tr>
                        <td class="text-right pr-3"><H5><strong> Genere :</strong></H5></td><td><H5> <%= film.getGenere()%></H5></td></tr>
                    <tr>
                        <td class="text-right pr-3"><strong> Durata :</strong></td><td> <%= film.getDurata()%></td></tr>
                    <tr>
                        <td class="text-right pr-3"><strong> Regia :</strong></td><td> <%= film.getRegia()%></td></tr>
                    <tr>
                        <td class="text-right pr-3"><strong> Cast :</strong></td><td> <%= film.getCast()%></td></tr>
                    <tr>
                        <td class="text-right pr-3"><strong> Produzione :</strong></td><td> <%= film.getProduzione()%></td></tr>
                </table>

            </div> 

    </div>

</div>
<div class="card mb-3">

    <span class="card-header">Trama</span>

    <div class="card-body">
        <%=film.getTrama() %>
    </div>
</div>
<%    
    if ((film.getTrailer()!=null)&&(film.getTrailer()!="")){
    %>
    <div class="card mb-3" >

    <span class="card-header">Trailer</span>

    <div class="card-body text-center " >
        
        <iframe  style=" height: 320px" class="col-sm-6" src="<%= film.getTrailer()%> " frameborder="0" allowfullscreen></iframe
    </div>
</div>
        <%
            }
        %>
<%
    String messaggio = (String) request.getAttribute("messaggio");
    if (messaggio != null) {%>

<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= messaggio%></strong>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<% }%>
<div class="card mb-3">

    <span class="card-header" > Valuta il film </span>

    <div class="card-body mx-auto mt-2" id="votaUser" >

        <!-- Qui Le stelle  -->

    </div>

    <div class="card-body">


        <form id="form" method="POST" action="insertRecensioniFilm"  >

            <input name="rate" type="hidden" id="valoreRate" value="" >
            <input name="index" type="hidden" value="<%= idFilmAr%>" >
            <textarea  name="testo"style="width: 100%; resize: none;  " placeholder="Inserisci qui la tua Recensione "  id="testoRecensione" ></textarea>
            <button type="button" class="btn btn-primary btn-lg btn-block" onclick="check()" > Invia la tua recensione </button>
        </form>
    </div>


</div>
<% request.setAttribute("idFilm", "" + film.getIdFilm());%>
<div class="card mb-3">

    <span class="card-header" > Recensioni Utenti </span>

    <div class="card-body">

        <jsp:include page="/recensioniFilm"/>
        <%
            List<Recensione> recensioni;
            recensioni = (List<Recensione>) request.getAttribute("recensioni");
            if ((null == recensioni)||(recensioni.isEmpty())) {
        %>

        Questo film non ha ancora nessuna recensione. Se lo hai già visto, scrivine una tu! ;)

        <%
        } else {
            int i = 0;
            for (Recensione rec : recensioni) {
        %>
        <div class="list-group-item list-group-item-action">
            <div class="media">
                <i class="fa fa-user fa-2x mr-3"></i>
                <div class="media-body">
                    <p class="card-body mr-2 valutazione" data-index="<%= i%>"> <%= rec.getValutazione()%></p>
                    <p><strong><%= rec.getNomeUtente()%></strong>&nbsp;&nbsp;&nbsp;&nbsp;</p>
                    <p><%= rec.getTesto()%>

                    <div class="text-muted smaller">
                        <%
                            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");

                        %>

                        <%= s.format(rec.getDataImmissione().getTime())%>
                        </p>    
                    </div>

                </div>
            </div>
        </div>
        <%                      i++;
                }
            }
        %>
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
            <div class="modal-body" id="testoModal" >Devi completare tutti i campi prima di recensire</div>
            <div class="modal-footer">
                <button class="btn btn-primary btn-lg btn-block" type="button" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>
<%}%> 
</div>
<script>

    var valore = $("#valutazioneFilm").text();

    $("#valutazioneFilm").rateYo({

        rating: valore,
        readOnly: true
        

    });


    $("#votaUser").rateYo({
        
        precision : 1, 
        onSet: function (rating, rateYoInstance) {

            
            $("#valoreRate").attr("value", rating);

        }

    });

    $('.valutazione').each(function (index, item) {
        $(item).rateYo({
            rating: $(item).text(),
            readOnly: true
        });
    });

    function check() {

        var valoreRate = $("#valoreRate").attr("value");
        
        
        var regExp = /[a-z]+\w*/i; 
        var testoRecensione = escape($("#testoRecensione").val()); 
        
        if ((valoreRate !== "" ) && testoRecensione !== '')
        {
            if( testoRecensione.match(regExp)) { 
                
                if( valoreRate >= 0.5 ) $("form").submit();
                
                else {
                    
                $('#testoModal').text("Devi inserire una valutazione superiore o uguale a 0.5"); 
                $("#erroreCampi").modal({
                backdrop: true
                    });
                    
                }
            }else {
                
                $('#testoModal').text("Devi inserire una recensione valida"); 
                $("#erroreCampi").modal({
                backdrop: true
                    });
                
                
            }
            
            

        } else {

           
           $("#erroreCampi").modal({
                backdrop: true
            });

        }


    }

</script>
<jsp:include page="Footer.jsp" />