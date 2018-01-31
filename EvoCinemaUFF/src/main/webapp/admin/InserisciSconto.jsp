<%-- 
    Document   : InserisciSconto
    Created on : 26-gen-2018, 9.01.03
    Author     : Angelo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Film"%>
<%@page import="java.util.List"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.FilmConValutazioneMedia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Sconto"%>
<% request.setAttribute("title", "Inserimento Nuovo Sconto");%>
<jsp:include page="HeaderAdmin.jsp"/>

<div class="container-fluid">

    <div class="card mt-4">

        <div class="card-header">
            <p>Inserisci Sconto</p>
        </div>

        <%

            String messaggio = (String) request.getAttribute("messaggioSconto");
                if (messaggio != null) {%>

        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <strong> <%= messaggio%> </strong>
            <button class="close" aria-label="Close" type="button" data-dismiss="alert">
                <span aria-hidden="true">�</span>
            </button>
        </div>

        <% } %>

        <div class="card-body mt-5 align-self-center" >

            <form action="aggiuntaSconto" id="formInserisci" method="get">

                <div class="form-row">
                    <div class="form-group text-center">
                        <label for="nomeSconto"><strong>Nome sconto</strong></label>
                        <p>
                            <input type="text" class="form-control" name="nomeSconto"  placeholder="Inserisci nome sconto" autocomplete="off" />
                        </p>
                    </div>
                    <div class="form-group ml-5 text-center">
                        <label><strong>Tipo di sconto</strong></label>
                        <p>
                            <label class="radio-inline"><input type="radio" name="optTipo" value="percentuale" checked />  Percentuale  </label>
                            &nbsp;&nbsp;
                            <label class="radio-inline"><input type="radio" name="optTipo" value="fisso" />  Fisso  </label>
                        </p>
                    </div>
                    <div class="form-group ml-5 text-center">
                        <label><strong>Verificabile</strong></label>
                        <p>
                            <label class="radio-inline"><input type="radio" name="optVerificabile" value="True" checked />  Si  </label>
                            &nbsp;&nbsp;
                            <label class="radio-inline"><input type="radio" name="optVerificabile" value="False" />  No  </label>
                        </p>
                    </div>
                </div>

                <div class="form-group text-center">
                    <label><strong>Tipologia</strong></label>
                    <p>
                        <label class="radio-inline"><input type="radio" name="optTipologia" value="giorno" checked />  Giorno della settimana  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" name="optTipologia" value="genere" />  Genere  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" name="optTipologia" value="film" />  Film  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" name="optTipologia" value="spettacolo" />  Spettacolo  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" name="optTipologia" value="data" />  Data  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" name="optTipologia" value="eta" />  Eta'  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" name="optTipologia" value="sesso" />  Sesso  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" name="optTipologia" value="altro" />  Altro  </label>
                    </p>
                </div>
                <!-- parte da mostrare in base alle opzioni selezionate -->
                <div id="divPercentuale" class="form-group text-center mt-5">
                    <strong>Percentuale di sconto: </strong>
                    <p>
                        <input type="text" class="form-control" name="percentualeSconto"  placeholder="Inserisci percentuale da scontare" />
                    </p>
                </div>
                <div id="divFisso" class="form-group text-center mt-5">
                    <strong>Sconto fisso di euro: </strong>
                    <p>
                        <input type="text" class="form-control" name="cifraSconto"  placeholder="Inserisci la cifra da scontare" />
                    </p>
                </div>

                <div id="divGiorno" class="form-group text-center mt-5 mb-5">
                    <strong>Giorno della settimana: </strong>
                    <select name="giornoDellaSettimana">

                        <option value="MONDAY" selected>  Luned�  </option>
                        <option value="TUESDAY">  Marted�  </option>
                        <option value="WEDNESDAY">  Mercoled�  </option>
                        <option value="THURSDAY"> Gioved�  </option>
                        <option value="FRIDAY">  Venerd�  </option>
                        <option value="SATURDAY">  Sabato  </option>
                        <option value="SUNDAY">  Domenica  </option>

                    </select>
                </div>
                <div id="divGenere" class="form-group text-center mt-5 mb-5">
                    <strong> Genere </strong>
                    <p>
                        <input type="text" class="form-control" name="genere"  placeholder="Inserisci il genere per cui applicare lo sconto" />
                    </p>
                </div>
         
            
                <jsp:include page= "/VisualizzazioneProgrammazioneCNT"/>

<%
    
                List<Spettacolo> spettacoli = (List<Spettacolo>) request.getAttribute("spettacoli1");
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
%>
                
                <div id="divSpettacolo" class="form-group text-center mt-5 mb-5">
                <strong> Spettacolo </strong>
                    <select class="js-example-basic-single" name="spettacolo">
                       
                         <option value="0" >Nessuno</option>
                    <% for (Spettacolo s : spettacoli) {
                          %>
                          <option value="<%=s.getIdFilm() %>">
                              <%=s.getTitolo() %> Orario: <%=sdf.format(s.getOraInizio().getTime())%> 
                                </option>
                               <% }%>
                    </select>
                </div>
                              
                <jsp:include page= "/visualizzaValutazioni"/>

                <%

                    ArrayList<FilmConValutazioneMedia> array;
                    array = (ArrayList<FilmConValutazioneMedia>) request.getAttribute("listaFilmValutazione");


                %>

                <div id="divFilm" class="form-group text-center mt-5 mb-5">
                    <strong> Film </strong>
                    <select class="js-example-basic-single" name="film">

                        <option value="0" >Nessuno</option>
                        <% for (FilmConValutazioneMedia f : array) {
                        %>
                        <option value="<%=f.getIdFilm()%>">
                            <%=f.getTitolo()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                       
         
                <div id="divEta" class="form-group text-center mt-5 mb-5">
                    <p>
                        <strong> Sconta se l'et� �:&nbsp;&nbsp; </strong>
                        <select name="eta">

                            <option value="<" selected>  minore di  </option>
                            <option value=">">  maggiore di  </option>

                        </select>
                    </p>
                    <p>
                        <input type="text" class="form-control" name="cifraEta"  placeholder="Inserisci l'et�" />
                    </p>
                </div>
                <div id="divData" class="text-center mt-5 mb-5">
                    <strong> Data </strong>
                    <p>
                        <input type="text" name="data" class="datepicker">
                        <img class="calendar-icon" src="../images/calendar.png" height="26" width="26">

                    </p>
                </div>
                <div id="divSesso" class="text-center mt-5 mb-5">
                    <strong>Sesso</strong>
                    <p>
                        <select name="sesso">

                            <option value="M" selected>  maschio  </option>
                            <option value="F">  femmina  </option>

                        </select>
                    </p>
                </div>
                <div id="divAltro" class="text-center mt-5 mb-5">
                    <strong>Altro</strong>
                    <p>
                        <input type="text" class="form-control" name="altro" placeholder="Inserisci descrizione dello sconto" />                        
                    </p>
                </div>
                <!-- fine parte opzioni -->
                <div class="form-group text-center">
                    <input type="submit" id="inserisciSconto" class="btn-primary" value="Inserisci Sconto">
                </div>
            </form>

        </div>

    </div>

<script>
    $(document).ready(function () {
        // Initialize select2
        $(".js-example-basic-single").select2();
    });
</script>

<script>
    
    $("#divFisso").hide("fast");
    
    $("#divGenere").hide("fast");
    $("#divFilm").hide("fast");
    $("#divSpettacolo").hide("fast");
    $("#divEta").hide("fast");
    $("#divSesso").hide("fast");
    $("#divData").hide("fast");
    $("#divAltro").hide("fast");
    
    
    $('.datepicker').datepicker({
    });

        $("#divFisso").hide("fast");

        $("#divGenere").hide("fast");
        $("#divFilm").hide("fast");
        $("#divSpettacolo").hide("fast");
        $("#divEta").hide("fast");
        $("#divSesso").hide("fast");
        $("#divData").hide("fast");
        $("#divAltro").hide("fast");


        $('.datepicker').datepicker({
        });

        $(document).ready(function () {

            $("input[type=radio][name=optTipo]").change(function () {
                if (this.value == 'percentuale') {
                    $("#divPercentuale").show("slow");
                    $("#divFisso").hide("slow");
                }
                if (this.value == 'fisso') {
                    $("#divPercentuale").hide("slow");
                    $("#divFisso").show("slow");
                }
            });

            $("input[type=radio][name=optTipologia]").change(function () {
                if (this.value == 'giorno') {
                    $("#divGiorno").show("slow");
                    $("#divGenere").hide("slow");
                    $("#divFilm").hide("slow");
                    $("#divSpettacolo").hide("slow");
                    $("#divEta").hide("slow");
                    $("#divSesso").hide("slow");
                    $("#divData").hide("slow");
                    $("#divAltro").hide("slow");
                    $("input[type=radio][name=optVerificabile]").prop('disabled', false);
                }
                if (this.value == 'genere') {
                    $("#divGiorno").hide("slow");
                    $("#divGenere").show("slow");
                    $("#divFilm").hide("slow");
                    $("#divSpettacolo").hide("slow");
                    $("#divEta").hide("slow");
                    $("#divSesso").hide("slow");
                    $("#divData").hide("slow");
                    $("#divAltro").hide("slow");
                    $("input[type=radio][name=optVerificabile]").prop('disabled', false);
                }
                if (this.value == 'film') {
                    $("#divGiorno").hide("slow");
                    $("#divGenere").hide("slow");
                    $("#divFilm").show("slow");
                    $("#divSpettacolo").hide("slow");
                    $("#divEta").hide("slow");
                    $("#divSesso").hide("slow");
                    $("#divData").hide("slow");
                    $("#divAltro").hide("slow");
                    $("input[type=radio][name=optVerificabile]").prop('disabled', false);
                }
                if (this.value == 'spettacolo') {
                    $("#divGiorno").hide("slow");
                    $("#divGenere").hide("slow");
                    $("#divFilm").hide("slow");
                    $("#divSpettacolo").show("slow");
                    $("#divEta").hide("slow");
                    $("#divSesso").hide("slow");
                    $("#divData").hide("slow");
                    $("#divAltro").hide("slow");
                    $("input[type=radio][name=optVerificabile]").prop('disabled', false);
                }
                if (this.value == 'eta') {
                    $("#divGiorno").hide("slow");
                    $("#divGenere").hide("slow");
                    $("#divFilm").hide("slow");
                    $("#divSpettacolo").hide("slow");
                    $("#divEta").show("slow");
                    $("#divSesso").hide("slow");
                    $("#divData").hide("slow");
                    $("#divAltro").hide("slow");
                    $("input[type=radio][name=optVerificabile]").prop('disabled', true);
                }
                if (this.value == 'data') {
                    $("#divGiorno").hide("slow");
                    $("#divGenere").hide("slow");
                    $("#divFilm").hide("slow");
                    $("#divSpettacolo").hide("slow");
                    $("#divEta").hide("slow");
                    $("#divSesso").hide("slow");
                    $("#divData").show("slow");
                    $("#divAltro").hide("slow");
                    $("input[type=radio][name=optVerificabile]").prop('disabled', true);
                }
                if (this.value == 'sesso') {
                    $("#divGiorno").hide("slow");
                    $("#divGenere").hide("slow");
                    $("#divFilm").hide("slow");
                    $("#divSpettacolo").hide("slow");
                    $("#divEta").hide("slow");
                    $("#divSesso").show("slow");
                    $("#divData").hide("slow");
                    $("#divAltro").hide("slow");
                    $("input[type=radio][name=optVerificabile]").prop('disabled', true);
                }
                if (this.value == 'altro') {
                    $("#divGiorno").hide("slow");
                    $("#divGenere").hide("slow");
                    $("#divFilm").hide("slow");
                    $("#divSpettacolo").hide("slow");
                    $("#divEta").hide("slow");
                    $("#divSesso").hide("slow");
                    $("#divData").hide("slow");
                    $("#divAltro").show("slow");
                    $("input[type=radio][name=optVerificabile]").prop('disabled', true);
                }
            });

        });

    </script>
    <jsp:include page="FooterAdmin.jsp"/>

