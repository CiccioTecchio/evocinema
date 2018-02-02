<%-- 
    Document   : ModificaLibreria
    Created on : 23-gen-2018, 14.39.30
    Author     : GiuseppeDelGaudio
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.FilmConValutazioneMedia"%>
<%  request.setAttribute("title", "Modifica Libreria"); %>
<jsp:include page="Header.jsp"/>
<jsp:include page= "/visualizzaValutazioni"/>

<%
    
    ArrayList<FilmConValutazioneMedia> array;
    array = (ArrayList<FilmConValutazioneMedia>) request.getAttribute("listaFilmValutazione");


%>



<%            if (array == null) {


%>
<div >

    <p class="m-0 text-center" > Nessun Film Da Visualizzare </p>

</div>
<%        } else {

    request.getSession().setAttribute("listaFilmValutazione", array);
    FilmConValutazioneMedia film = null;

    if (array.size() == 0) {

%>

<div >

    <p class="m-0 text-center" >Nessun Film Da Visualizzare </p>

</div>

<% } else { %>



<div class="card-header">
    <i class="fa fa-table"></i> Lista Film Valutazioni</div>
<div class="card-body">

    <%
        String messaggioDelete = (String) request.getAttribute("messageDelete");
        System.out.println(messaggioDelete);
                 if (messaggioDelete != null) {%>


    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <strong><%= messaggioDelete%></strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <% } %>

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




                </tr>
            </thead>
            <tbody>
                <%
                    String valutazione;

                    for (int i = 0; i < array.size(); i++) {

                        film = (FilmConValutazioneMedia) array.get(i);

                        if (film.getValutazioneMedia() == 0.0) {

                            valutazione = "0";

                        } else {

                            valutazione = film.getValutazioneMedia() + "";

                        }

                %>
                <tr class="selezionato">
                    <td><img class="img-fluid" src="../<%= film.getLocandina()%>" ></td>
                    <td><%= film.getTitolo()%></td>
                    <td><%= film.getGenere()%></td>
                    <td><%= film.getTrama()%></td>
                    <td><%= film.getDurata()%></td>


                    <td class="text-center">
                        <div class="text-center" >
                            <form action="rimuoviFilm" method="GET" >
                                <button class="btn btn-primary btn-md mb-2" > Elimina </button>
                                <input type="hidden" name="position" value="<%= i%>" >
                            </form>
                            <form action="ModificaFilm.jsp" method="GET" >
                                <button class="btn btn-primary btn-md mt-2" >Modifica</button>
                                <input type="hidden" name="position" value="<%= i%>" >
                            </form>
                        </div>
                    </td>

                </tr>

                <% }  %>

            </tbody>
        </table>
        <script>


            $(document).ready(function () {

                $("#listaFilm").DataTable({

                    "order": [[1, "asc"]],

                    "columns": [

                        {"orderable": false},
                        null,
                        null,
                        {"orderable": false},
                        null,
                        {"orderable": false}]




                });


            });




        </script>



        <script>



            var valore;

            $(".rateYo").each(function (e) {

                valore = $(this).text();

                if (!(valore === "Non Disponibile")) {

                    $(this).rateYo({

                        rating: valore,
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




<% }
            }%>




<jsp:include page="Footer.jsp"/>


