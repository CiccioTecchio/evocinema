<%-- 
    Document   : VisualizzaLibreria
    Created on : 13-gen-2018, 18.42.05
    Author     : GiuseppeDelGaudio
--%>

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
/*
        UtenteBase ut = new UtenteBase(30.0f, "ABergamaschi@gmail.com", "Scove1991", "aishiem6Ph", UtenteRegistrato.ruolo.UTENTE, "Antonia", "Bergamaschi", new GregorianCalendar(1986, 02, 24), UtenteRegistrato.sesso.F, "3778775342", "Trento", "Via San Domenico Soriano, 93");
        request.getSession().setAttribute("utente", ut); */ // Stub prova insert Recensione
        String idFilmAr = request.getParameter("film");
        if (idFilmAr == null) {
            idFilmAr = (String) request.getAttribute("index");
        }
        ArrayList<FilmConValutazioneMedia> array = (ArrayList<FilmConValutazioneMedia>) request.getSession().getAttribute("listaFilmValutazione");
        
        if (array == null || idFilmAr == null || idFilmAr == " ") { %>



    <div class="jumbotron text-center">
        <ol class="breadcrumb-item" >

            <h4>Non Sono Presenti Elementi da Visualizzare</h4> 

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
    <div class="row" >

        <div class="card mb-3">





            <img class="card-img-top img-fluid thumbnail " style="max-width: 350px ; max-height: 500px " src="<%= film.getLocandina()%>"


        </div>



    </div>

    <div class="card mb-3 ml-5">
        <span class="card-header" > Informazioni Film </span>
        <div class="row"> 
            <div class="card-body pt-1 mt-4" >
                <table>
                    <tr>
                        <td class="text-right"> <H3>Voto :</H3></td><td><p id="valutazioneFilm" class="card-body mr-2"> <%= film.getValutazioneMedia()%></p> </td>

                    </tr>
                    <tr>
                        <td class="text-right"><H5><strong> Titolo :</strong></H5></td><td> <H2><%= film.getTitolo()%></H2></td></tr>
                    <tr>
                        <td class="text-right"><H5><strong> Genere :</strong></H5></td><td><H5> <%= film.getGenere()%></H5></td></tr>
                    <tr>
                        <td class="text-right"><strong> Durata :</strong></td><td> <%= film.getDurata()%></td></tr>
                    <tr>
                        <td class="text-right"><strong> Regia :</strong></td><td> <%= film.getRegia()%></td></tr>
                    <tr>
                        <td class="text-right"><strong> Cast :</strong></td><td> <%= film.getCast()%></td></tr>
                    <tr>
                        <td class="text-right"><strong> Produzione :</strong></td><td> <%= film.getProduzione()%></td></tr>
                </table>

            </div> 

        </div>
    </div>

</div>
<div class="row">


    <div class="card mb-3"  >


        <span class="card-header">Trama</span>

        <div class="row" >

            <p class="card-body col-sm-6 text-left pt-5 mt-5 ">

                <%= film.getTrama()%>

            </p>

            <div class="card-body col-sm-6 text-right">
                <iframe width="420" height="315"src="https://www.youtube.com/embed/tgbNymZ7vqY"></iframe>      
            </div>

        </div>
    </div>




</div>
<%
                String messaggio = (String) request.getAttribute("messaggio");
        if (messaggio != null) { %>
        

<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <strong><%= messaggio %></strong>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<% } %>
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
            
<div class="card mb-3">

    <span class="card-header" > Recensioni Utenti </span>

    <div class="card-body">
        <%
                    request.setAttribute("idFilm", idFilmAr);
                %>
                <jsp:include page="/recensioniFilm"/>
                <%
                    Collection<Recensione> recensioni = (Collection<Recensione>) request.getSession().getAttribute("recensioni");
                    if (null == recensioni) {
                %>

                Questo film non ha ancora nessuna recensione. Se lo hai gi� visto, scrivine una tu! ;)

                <%
                } else {
                    Iterator<Recensione> i = recensioni.iterator();
                    while(i.hasNext()) {
                    Recensione rec = i.next();
                %>
                <div class="list-group-item list-group-item-action">
                    <div class="media">
                        <img class="d-flex mr-3 rounded-circle" src="images/utente.png">
                        <div class="media-body">
                            <strong><%= rec.getEmailUtente() %></strong><br>
                            <%= rec.getTesto()%>
                            <div class="text-muted smaller">
                                <%
                                    SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");
                                %>
                                <%= s.format(rec.getDataImmissione())%>

                            </div>
                        </div>
                    </div>
                </div>
                <%                      }
                    }
                %>
    </div>

</div>
</div>

<div class="modal fade" id="erroreCampi" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Errore</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">�</span>
                </button>
            </div>
            <div class="modal-body">Devi completare tutti i campi prima di recensire</div>
            <div class="modal-footer">
                <button class="btn btn-primary btn-lg btn-block" type="button" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>
<%}%> 

<script>

    var valore = $("#valutazioneFilm").text();
    console.log(valore);

    $("#valutazioneFilm").rateYo({

        rating: valore,
        readOnly: true


    });


    $("#votaUser").rateYo({

        onSet: function (rating, rateYoInstance) {

            alert("Il valore � " + rating);
            $("#valoreRate").attr("value", rating);

        }

    });



    function check() {

        var valoreRate = $("#valoreRate").attr("value");
        var text = $("#testoRecensione").val();
        if ((valoreRate !== "") && (text !== null && text !== "" && text !== "  ")) {

            $("form").submit();

        } else {


            $("#erroreCampi").modal({
                backdrop: true
            });

        }


    }






</script>
<jsp:include page="Footer.jsp" />