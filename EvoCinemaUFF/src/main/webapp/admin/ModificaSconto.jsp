<%--
    Document   : ModificaSconto.jsp
    Created on : 24-gen-2018, 18.11.46
    Author     : Angelo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.FilmConValutazioneMedia"%>
<%@page import="java.util.List"%>
<%@page import="model.Sconto"%>
<% request.setAttribute("title", "Modifica Sconto Esistente"); %>
<jsp:include page="HeaderAdmin.jsp"/>

<div class="container-fluid">
    <%

        Integer index = Integer.parseInt(request.getParameter("position"));

        List<Sconto> array = (List<Sconto>) request.getSession().getAttribute("listaSconti");

        if (null == index || null == array) { %>

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

        <%                String messaggio = (String) request.getAttribute("messaggioSconto");
                if (messaggio != null) {%>

        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <strong> <%= messaggio%> </strong>
            <button class="close" aria-label="Close" type="button" data-dismiss="alert">
                <span aria-hidden="true">×</span>
            </button>
        </div>

        <% }%>

        <div class="card-body mt-5 align-self-center" >

            <form action="modificaSconto" id="formInserisci" method="get">
                <input type="hidden" name="idSconto" value="<%= sconto.getIdSconto()%>">

                <div class="form-row">
                    <div class="form-group text-center">
                        <label for="nomeSconto"><strong>Nome sconto</strong></label>
                        <p>
                            <input type="text" id="nomeSconto" class="form-control" name="nomeSconto" autocomplete="off" value="<%= sconto.getNome()%>" />
                        </p>
                    </div>
                    <div class="form-group ml-5 text-center">
                        <label><strong>Tipo di sconto</strong></label>
                        <p>
                            <label class="radio-inline"><input type="radio" id="checkPercentuale" name="optTipo" value="percentuale" <%if (sconto.getTipo() == Sconto.tipo.PERCENTUALE) { %> checked <%}%> />  Percentuale  </label>
                            &nbsp;&nbsp;
                            <label class="radio-inline"><input type="radio" name="optTipo" value="fisso" <%if (sconto.getTipo() == Sconto.tipo.FISSO) { %> checked <%}%> />  Fisso  </label>
                        </p>
                    </div>
                    <div class="form-group ml-5 text-center">
                        <label><strong>Verificabile</strong></label>
                        <p>
                            <label class="radio-inline"><input type="radio" name="optVerificabile" value="True" <%if (sconto.getVerificabile() == Sconto.verificabile.TRUE) { %> checked <%}%> />  Si  </label>
                            &nbsp;&nbsp;
                            <label class="radio-inline"><input type="radio" name="optVerificabile" value="False" <%if (sconto.getVerificabile() == Sconto.verificabile.FALSE) { %> checked <%}%> />  No  </label>
                        </p>
                    </div>
                </div>

                <div class="form-group text-center">
                    <label><strong>Tipologia</strong></label>
                    <p>
                        <label class="radio-inline"><input type="radio" id="checkGiornoSettimana" name="optTipologia" value="giorno" <%if (sconto.getTipologia() == Sconto.tipologia.GIORNO_SETTIMANA) { %> checked <%}%> />  Giorno della settimana  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" id="checkGenere" name="optTipologia" value="genere" <%if (sconto.getTipologia() == Sconto.tipologia.GENERE) { %> checked <%}%> />  Genere  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" id="checkFilm" name="optTipologia" value="film" <%if (sconto.getTipologia() == Sconto.tipologia.FILM) { %> checked <%}%> />  Film  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" id="checkSpettacolo" name="optTipologia" value="spettacolo" <%if (sconto.getTipologia() == Sconto.tipologia.SPETTACOLO) { %> checked <%}%> />  Spettacolo  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" id="checkData" name="optTipologia" value="data" <%if (sconto.getTipologia() == Sconto.tipologia.DATA) { %> checked <%}%> />  Data  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" id="checkEta" name="optTipologia" value="eta" <%if (sconto.getTipologia() == Sconto.tipologia.ETA) { %> checked <%}%> />  Eta'  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" id="checkSesso" name="optTipologia" value="sesso" <%if (sconto.getTipologia() == Sconto.tipologia.SESSO) { %> checked <%}%> />  Sesso  </label>
                        &nbsp;&nbsp;
                        <label class="radio-inline"><input type="radio" id="checkAltro" name="optTipologia" value="altro" <%if (sconto.getTipologia() == Sconto.tipologia.ALTRO) { %> checked <%}%> />  Altro  </label></p>
                </div>
                <!-- parte da mostrare in base alle opzioni selezionate -->
                <div id="divPercentuale" class="form-group text-center mt-5">
                    <strong>Percentuale di sconto: </strong>
                    <p>
                        <input type="text" class="form-control" id="percentualeSconto" name="percentualeSconto" value="<%= sconto.getPercentuale()%>" />
                    </p>
                </div>
                <div id="divFisso" class="form-group text-center mt-5">
                    <strong>Sconto fisso di euro: </strong>
                    <p>
                        <input type="text" class="form-control" id="scontoFisso" name="cifraSconto" value="<%= sconto.getPrezzo()%>" />
                    </p>
                </div>

                <div id="divGiorno" class="form-group text-center mt-5 mb-5">
                    <strong>Giorno della settimana: </strong>
                    <select id="giornoDellaSettimana" name="giornoDellaSettimana" >

                        <option value="MONDAY" <% if (sconto.getParametroTipologia().compareTo("MONDAY") == 0) { %>selected<%}%> >  Lunedì  </option>
                        <option value="TUESDAY" <% if (sconto.getParametroTipologia().compareTo("TUESDAY") == 0) { %>selected<%}%> >  Martedì  </option>
                        <option value="WEDNESDAY" <% if (sconto.getParametroTipologia().compareTo("WEDNESDAY") == 0) { %>selected<%}%> >  Mercoledì  </option>
                        <option value="THURSDAY" <% if (sconto.getParametroTipologia().compareTo("THURSDAY") == 0) { %>selected<%}%> > Giovedì  </option>
                        <option value="FRIDAY" <% if (sconto.getParametroTipologia().compareTo("FRIDAY") == 0) { %>selected<%}%> >  Venerdì  </option>
                        <option value="SATURDAY" <% if (sconto.getParametroTipologia().compareTo("SATURDAY") == 0) { %>selected<%}%> > Sabato  </option>
                        <option value="SUNDAY" <% if (sconto.getParametroTipologia().compareTo("SUNDAY") == 0) { %>selected<%}%>>  Domenica  </option>

                    </select>
                </div>
                <div id="divGenere" class="form-group text-center mt-5 mb-5">
                    <strong> Genere </strong>
                    <p>
                        <input type="text" class="form-control" id="genereText" name="genere" value="<%= sconto.getParametroTipologia()%>" />
                    </p>
                </div>
                
                <script>
                    $(document).ready(function () {
                        $(".js-example-basic-single").select2();
                    });
                </script>
                <jsp:include page= "/visualizzaValutazioni"/>

                <%

                    ArrayList<FilmConValutazioneMedia> arr;
                    arr = (ArrayList<FilmConValutazioneMedia>) request.getAttribute("listaFilmValutazione");

                %>

                <div id="divFilm" class="form-group text-center mt-5 mb-5">
                    <strong> Film </strong>
                    <select class="js-example-basic-single" id="selectFilm" name="film">

                        <option value="0" >Nessuno</option>
                        <% for (FilmConValutazioneMedia f : arr) {
                            Integer id=f.getIdFilm();
                        %>
                        <option value="<%= id %>" <% if (sconto.getParametroTipologia().equals(id.toString())){%>selected<%}%> >
                            <%=f.getTitolo()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                    
                    
                <div id="divSpettacolo" class="form-group text-center mt-5 mb-5">
                    <strong> Spettacolo </strong>
                    <p>
                        <input type="text" class="form-control" id="spettacoloNum" name="spettacolo" value="<%= sconto.getParametroTipologia()%>" />
                    </p>
                </div>
                <div id="divEta" class="form-group text-center mt-5 mb-5">
                    <p>
                        <strong> Sconta se l'età è:&nbsp;&nbsp; </strong>
                        <select name="eta">

                            <option value="<" <% if (sconto.getParametroTipologia().charAt(0) == '<') {%>selected<% } %> >  minore di  </option>
                            <option value=">" <% if (sconto.getParametroTipologia().charAt(0) == '>') {%>selected<% } %> >  maggiore di  </option>

                        </select>
                    </p>
                    <p>
                        <input type="text" class="form-control" id="etaNum" name="cifraEta" <% if (sconto.getTipologia() == Sconto.tipologia.ETA) {%> value="<%= sconto.getParametroTipologia().substring(1)%>"<%}%> >
                    </p>
                </div>
                <div id="divData" class="text-center mt-5 mb-5">
                    <strong> Data </strong>
                    <p>
                        <input type="text" name="data" id="dataText" class="datepicker">
                        <img class="calendar-icon" src="../images/calendar.png" height="26" width="26">
                        <!-- caricare data nel date picker -->
                    </p>
                </div>
                <div id="divSesso" class="text-center mt-5 mb-5">
                    <strong>Sesso</strong>
                    <p>
                        <select name="sesso">

                            <option value="M" <% if (sconto.getParametroTipologia().compareTo("M") == 0) {%>selected<% } %> >  maschio  </option>
                            <option value="F" <% if (sconto.getParametroTipologia().compareTo("F") == 0) {%>selected<% }%> >  femmina  </option>

                        </select>
                    </p>
                </div>
                <div id="divAltro" class="text-center mt-5 mb-5">
                    <strong>Altro</strong>
                    <p>
                        <input type="text" id="descrizioneSconto" class="form-control" name="altro" value="<%= sconto.getParametroTipologia()%>" />                        
                    </p>
                </div>
                <!-- fine parte opzioni -->
                <div class="form-group text-center">
                    <input type="button" id="modificaSconto" onclick="validation()" class="btn-primary" value="Modifica Sconto">
                </div>
            </form>

        </div>


    </div>

    <% }%>
</div>
<script>

        function validation() {

            var fl = 0;

            if (isEmpty($('#nomeSconto'))) fl++;

            if ($("#checkPercentuale").is(":not(:checked)")) {
                
                
                if (isNumber($('#scontoFisso'))) fl++; 
                
            } else {
                 
                  
                  if(isNumber($('#percentualeSconto'))) fl++;
                  
            }
          
          if ($("#checkGiornoSettimana").is(":checked")) fl++;

          if ($("#checkGenere").is(":checked")) 
              if( ! isEmpty($('#genereText'))) fl++; 
          
          if ($("#checkFilm").is(":checked")) 
              if(!($("#selectFilm").val() === '0'))fl++;
          
          if ($("#checkSpettacolo").is(":checked"))
              
              if(! isNumber($('#spettacoloNum'))) fl++; 
          
          if ($("#checkData").is(":checked"))
              
              if( checkCalendar($('#dataText'))) fl++;
          
          if ($("#checkEta").is(":checked"))
              
              if(! isNumber($('#etaNum'))) fl++; 
          
          if ($("#checkSesso").is(":checked")) fl++; 

          if ($("#checkAltro").is(":checked"))
              
              if(! isEmpty($('#descrizioneSconto'))) fl++; 
              
          
         
         
         if(fl===1 ) $('#formInserisci').submit(); 

          

        }; 
        
        function checkCalendar( x ){
        
        var regExp = / [0-3][0-9]\\(0?[1-9]|1[012])\\\d{4} /;  
        var value = x.val();  
        console.log(value.match(regExp)); 
       if(  value === '' || ! value.match(regExp)  ){ x.addClass("is-invalid");     return true;} 
	
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
        
        }
        
        function isEmpty(x) {

            console.log(x);
            var value = x.val();
            console.log(value);

            var regExp = /[a-z]+\w*/i;

            if (value === '' || !value.match(regExp)) {
                x.addClass("is-invalid");
                return true;
            } else {

                x.removeClass("is-invalid");
                x.addClass("is-valid");
                return false;
            }

        };
        
    $("#divFisso").hide("fast");
    $("#divPercentuale").hide("fast");

    $("#divGiorno").hide("fast");
    $("#divGenere").hide("fast");
    $("#divFilm").hide("fast");
    $("#divSpettacolo").hide("fast");
    $("#divEta").hide("fast");
    $("#divSesso").hide("fast");
    $("#divData").hide("fast");
    $("#divAltro").hide("fast");

    $('.datepicker').datepicker({});

    $(document).ready(function () {

        if ($('input:radio[name=optTipo]:checked').val() == 'percentuale')
            $("#divPercentuale").show("slow");
        if ($('input:radio[name=optTipo]:checked').val() == 'fisso')
            $("#divFisso").show("slow");
        if ($('input:radio[name=optTipologia]:checked').val() == 'giorno')
            $("#divGiorno").show("slow");
        if ($('input:radio[name=optTipologia]:checked').val() == 'genere')
            $("#divGenere").show("slow");
        if ($('input:radio[name=optTipologia]:checked').val() == 'film')
            $("#divFilm").show("slow");
        if ($('input:radio[name=optTipologia]:checked').val() == 'spettacolo')
            $("#divSpettacolo").show("slow");
        if ($('input:radio[name=optTipologia]:checked').val() == 'data')
            $("#divData").show("slow");
        if ($('input:radio[name=optTipologia]:checked').val() == 'eta') {
            $("input[type=radio][name=optVerificabile]").prop('disabled', true);
            $("#divEta").show("slow");
        }
        if ($('input:radio[name=optTipologia]:checked').val() == 'sesso') {
            $("#divSesso").show("slow");
            $("input[type=radio][name=optVerificabile]").prop('disabled', true);
        }
        if ($('input:radio[name=optTipologia]:checked').val() == 'altro') {
            $("#divAltro").show("slow");
            $("input[type=radio][name=optVerificabile]").prop('disabled', true);
        }


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
                $("input[type=radio][name=optVerificabile]").prop('disabled', false);
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

