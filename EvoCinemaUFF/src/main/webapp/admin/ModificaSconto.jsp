<%--
    Document   : ModificaSconto.jsp
    Created on : 24-gen-2018, 18.11.46
    Author     : Angelo
--%>

<%@page import="java.util.List"%>
<%@page import="model.Sconto"%>
<% request.setAttribute("title", "Modifica Sconto Esistente"); %>
<jsp:include page="HeaderAdmin.jsp"/>

<div class="container-fluid">
    <%

        Integer index = Integer.parseInt( request.getParameter("position"));
        
        List<Sconto> array = (List<Sconto>) request.getSession().getAttribute("listaSconti");
              
        if ( null == index || null == array ) { %>

    <div class="jumbotron text-center">
        <ol class="breadcrumb-item" >

            <h4>Sconto da modificare non trovato</h4>

        </ol>
    </div>
    <%

    } else {

        Sconto sconto = array.get(index);       

    %>

    <div class="card mt-4" >

        <div class="card-header " >
            <p>Modifica Sconto</p>
        </div>
    
        
        <div class="card-body mt-5 align-self-center" >
            
            <form action="modificaSconto" id="formInserisci" method="get">
                <input type="hidden" name="idSconto" value="<%= sconto.getIdSconto()%>">
                <input type="hidden" id="parametroTipologiaSconto" value="<%= sconto.getParametroTipologia() %>"> 

                <div class="form-row">
                    <div class="form-group text-center">
                        <label for="nomeSconto"><strong>Nome sconto</strong></label>
                        <p>
                        <input type="text" class="form-control" name="nomeSconto" autocomplete="off" value="<%= sconto.getNome() %>" />
                        </p>
                    </div>
                    <div class="form-group ml-5 text-center">
                        <label><strong>Tipo di sconto</strong></label>
                        <p>
                            <label class="radio-inline"><input type="radio" name="optTipo" value="percentuale" <%if (sconto.getTipo()==Sconto.tipo.PERCENTUALE){ %> checked <%}%> />  Percentuale  </label>
                            &nbsp;&nbsp;
                            <label class="radio-inline"><input type="radio" name="optTipo" value="fisso" <%if (sconto.getTipo()==Sconto.tipo.FISSO){ %> checked <%}%> />  Fisso  </label>
                        </p>
                    </div>
                    <div class="form-group ml-5 text-center">
                        <label><strong>Verificabile</strong></label>
                        <p>
                            <label class="radio-inline"><input type="radio" name="optVerificabile" value="True" <%if (sconto.getVerificabile()==Sconto.verificabile.TRUE){ %> checked <%}%> />  Si  </label>
                            &nbsp;&nbsp;
                            <label class="radio-inline"><input type="radio" name="optVerificabile" value="False" <%if (sconto.getVerificabile()==Sconto.verificabile.FALSE){ %> checked <%}%> />  No  </label>
                        </p>
                    </div>
                </div>

                <div class="form-group text-center">
                    <label><strong>Tipologia</strong></label>
                    <p>
                    <label class="radio-inline"><input type="radio" name="optTipologia" value="giorno" <%if (sconto.getTipologia()==Sconto.tipologia.GIORNO_SETTIMANA){ %> checked <%}%> />  Giorno della settimana  </label>
                    &nbsp;&nbsp;
                    <label class="radio-inline"><input type="radio" name="optTipologia" value="genere" <%if (sconto.getTipologia()==Sconto.tipologia.GENERE){ %> checked <%}%> />  Genere  </label>
                    &nbsp;&nbsp;
                    <label class="radio-inline"><input type="radio" name="optTipologia" value="film" <%if (sconto.getTipologia()==Sconto.tipologia.FILM){ %> checked <%}%> />  Film  </label>
                    &nbsp;&nbsp;
                    <label class="radio-inline"><input type="radio" name="optTipologia" value="spettacolo" <%if (sconto.getTipologia()==Sconto.tipologia.SPETTACOLO){ %> checked <%}%> />  Spettacolo  </label>
                    &nbsp;&nbsp;
                    <label class="radio-inline"><input type="radio" name="optTipologia" value="data" <%if (sconto.getTipologia()==Sconto.tipologia.DATA){ %> checked <%}%> />  Data  </label>
                    &nbsp;&nbsp;
                    <label class="radio-inline"><input type="radio" name="optTipologia" value="eta" <%if (sconto.getTipologia()==Sconto.tipologia.CAT_PERSONE){ %> checked <%}%> />  Eta'  </label>
                    &nbsp;&nbsp;
                    <label class="radio-inline"><input type="radio" name="optTipologia" value="sesso" <%if (sconto.getTipologia()==Sconto.tipologia.SESSO){ %> checked <%}%> />  Sesso  </label>
                    </p>
                </div>
                <!-- parte da mostrare in base alle opzioni selezionate -->
                <div id="divPercentuale" class="form-group text-center mt-5">
                    <strong>Percentuale di sconto: </strong>
                    <p>
                        <input type="text" class="form-control" name="percentualeSconto" value="<%= sconto.getPercentuale() %>" />
                    </p>
                </div>
                <div id="divFisso" class="form-group text-center mt-5">
                    <strong>Sconto fisso di euro: </strong>
                    <p>
                        <input type="text" class="form-control" name="cifraSconto" value="<%= sconto.getPrezzo() %>" />
                    </p>
                </div>
                                    
                <div id="divGiorno" class="form-group text-center mt-5 mb-5">
                    <strong>Giorno della settimana: </strong>
                    <select id="giornoDellaSettimana" name="giornoDellaSettimana" >

                        <option value="MONDAY" <% if (sconto.getParametroTipologia().compareTo("MONDAY") == 0){ %>selected<%}%> >  Lunedì  </option>
                        <option value="TUESDAY" <% if (sconto.getParametroTipologia().compareTo("TUESDAY") == 0){ %>selected<%}%> >  Martedì  </option>
                        <option value="WEDNESDAY" <% if (sconto.getParametroTipologia().compareTo("WEDNESDAY") == 0){ %>selected<%}%> >  Mercoledì  </option>
                        <option value="THURSDAY" <% if (sconto.getParametroTipologia().compareTo("THURSDAY") == 0){ %>selected<%}%> > Giovedì  </option>
                        <option value="FRIDAY" <% if (sconto.getParametroTipologia().compareTo("FRIDAY") == 0){ %>selected<%}%> >  Venerdì  </option>
                        <option value="SATURDAY" <% if (sconto.getParametroTipologia().compareTo("SATURDAY") == 0){ %>selected<%}%> > Sabato  </option>
                        <option value="SUNDAY" <% if (sconto.getParametroTipologia().compareTo("SUNDAY") == 0){ %>selected<%}%>>  Domenica  </option>

                    </select>
                </div>
                <div id="divGenere" class="form-group text-center mt-5 mb-5">
                    <strong> Genere </strong>
                    <p>
                        <input type="text" class="form-control" name="genere" value="<%= sconto.getParametroTipologia() %>" />
                    </p>
                </div>
                <div id="divFilm" class="form-group text-center mt-5 mb-5">
                    <strong> Film </strong>
                    <p>
                        <input type="text" class="form-control" name="film" value="<%= sconto.getParametroTipologia() %>" />
                    </p>
                </div>
                <div id="divSpettacolo" class="form-group text-center mt-5 mb-5">
                    <strong> Spettacolo </strong>
                    <p>
                        <input type="text" class="form-control" name="spettacolo" value="<%= sconto.getParametroTipologia() %>" />
                    </p>
                </div>
                <div id="divEta" class="form-group text-center mt-5 mb-5">
                    <p>
                        <strong> Sconta se l'età è:&nbsp;&nbsp; </strong>
                        <select name="eta">
                        
                            <option value="<" <% if(sconto.getParametroTipologia().charAt(0) == '<'){%>selected<% } %> >  minore di  </option>
                            <option value=">" <% if(sconto.getParametroTipologia().charAt(0) == '>'){%>selected<% } %> >  maggiore di  </option>

                        </select>
                    </p>
                    <p>
                        <input type="text" class="form-control" name="cifraEta" <% if(sconto.getTipologia()==Sconto.tipologia.CAT_PERSONE) {%> value="<%= sconto.getParametroTipologia().substring(1) %>"<%}%> />
                    </p>
                </div>
                <div id="divData" class="text-center mt-5 mb-5">
                    <strong> Data </strong>
                    <p>
                        <input type="text" name="data" class="datepicker">
                        <img class="calendar-icon" src="../images/calendar.png" height="26" width="26">
                        <!-- caricare data nel date picker -->
                    </p>
                </div>
                <div id="divSesso" class="form-control text-center mt-5 mb-5">
                        <strong>Sesso</strong>
                    <p>
                        <select name="sesso">
                        
                            <option value="M" <% if(sconto.getParametroTipologia().compareTo("M") == 0){%>selected<% } %> >  maschio  </option>
                            <option value="F" <% if(sconto.getParametroTipologia().compareTo("F") == 0){%>selected<% } %> >  femmina  </option>

                        </select>
                    </p>
                </div>
                <!-- fine parte opzioni -->
                <div class="form-group text-center">
                    <input type="submit" id="modificaSconto" class="btn-primary" value="Modifica Sconto">
                </div>
            </form>

        </div>

        
    </div>

<% }%>
</div>
<script>
    
    $("#divFisso").hide("fast");
    $("#divPercentuale").hide("fast");
    
    $("#divGiorno").hide("fast");
    $("#divGenere").hide("fast");
    $("#divFilm").hide("fast");
    $("#divSpettacolo").hide("fast");
    $("#divEta").hide("fast");
    $("#divSesso").hide("fast");
    $("#divData").hide("fast");
    
    $('.datepicker').datepicker({});
    
    $(document).ready(function () {      

        if($('input:radio[name=optTipo]:checked').val() == 'percentuale') $("#divPercentuale").show("slow");
        if($('input:radio[name=optTipo]:checked').val() == 'fisso') $("#divFisso").show("slow");
        if($('input:radio[name=optTipologia]:checked').val() == 'giorno') $("#divGiorno").show("slow");
        if($('input:radio[name=optTipologia]:checked').val() == 'genere') $("#divGenere").show("slow");
        if($('input:radio[name=optTipologia]:checked').val() == 'film') $("#divFilm").show("slow");
        if($('input:radio[name=optTipologia]:checked').val() == 'eta') $("#divEta").show("slow");
        if($('input:radio[name=optTipologia]:checked').val() == 'sesso') $("#divSesso").show("slow");
        if($('input:radio[name=optTipologia]:checked').val() == 'data') $("#divData").show("slow");
        

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
            }
            if (this.value == 'genere') {
                $("#divGiorno").hide("slow");
                $("#divGenere").show("slow");
                $("#divFilm").hide("slow");
                $("#divSpettacolo").hide("slow");
                $("#divEta").hide("slow");
                $("#divSesso").hide("slow");
                $("#divData").hide("slow");

            }
            if (this.value == 'film') {
                $("#divGiorno").hide("slow");
                $("#divGenere").hide("slow");
                $("#divFilm").show("slow");
                $("#divSpettacolo").hide("slow");
                $("#divEta").hide("slow");
                $("#divSesso").hide("slow");
                $("#divData").hide("slow");
            }
            if (this.value == 'spettacolo') {
                $("#divGiorno").hide("slow");
                $("#divGenere").hide("slow");
                $("#divFilm").hide("slow");
                $("#divSpettacolo").show("slow");
                $("#divEta").hide("slow");
                $("#divSesso").hide("slow");
                $("#divData").hide("slow");
            }
            if (this.value == 'eta') {
                $("#divGiorno").hide("slow");
                $("#divGenere").hide("slow");
                $("#divFilm").hide("slow");
                $("#divSpettacolo").hide("slow");
                $("#divEta").show("slow");
                $("#divSesso").hide("slow");
                $("#divData").hide("slow");
            }
            if (this.value == 'data') {
                $("#divGiorno").hide("slow");
                $("#divGenere").hide("slow");
                $("#divFilm").hide("slow");
                $("#divSpettacolo").hide("slow");
                $("#divEta").hide("slow");
                $("#divSesso").hide("slow");
                $("#divData").show("slow");
            }
            if (this.value == 'sesso') {
                $("#divGiorno").hide("slow");
                $("#divGenere").hide("slow");
                $("#divFilm").hide("slow");
                $("#divSpettacolo").hide("slow");
                $("#divEta").hide("slow");
                $("#divSesso").show("slow");
                $("#divData").hide("slow");
            }
        });
        
    });
    
</script>
<jsp:include page="FooterAdmin.jsp"/>

