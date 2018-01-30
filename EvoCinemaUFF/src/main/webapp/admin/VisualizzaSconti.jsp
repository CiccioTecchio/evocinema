<%-- 
    Document   : visualizzaSconti
    Created on : 24-gen-2018, 16.55.50
    Author     : francescodefeo
--%>


<%@page import="model.Sconto.disponibile"%>
<%@page import="java.util.List"%>
<%@page import="model.Sconto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  request.setAttribute("title", "Sconti"); %>
<jsp:include page="HeaderAdmin.jsp"/>
<jsp:include page= "/visualizzaSconto"/>



<%

    List<Sconto> array;
    array = (List<Sconto>) request.getAttribute("listaSconti");


%>


<%            if (array == null) {


%>
<div >

    <p class="m-0 text-center" > Nessuno Sconto Da Visualizzare 1 </p>

</div>
<%        } else {

    request.getSession().setAttribute("listaSconti", array);
    Sconto sconto = null;

    if (array.size() == 0) {

%>

<div >

    <p class="m-0 text-center" >Nessuno Sconto Da Visualizzare 2</p>

</div>

<% } else { %>



<div class="card-header">
    <i class="fa fa-table"></i> Lista Sconti </div>

<div id="messaggio"></div>

</div>
<div class="table-responsive">
    <table id="listaSconti" style="border-" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>

                <th>Nome</th>
                <th>Tipo</th>
                <th>Verificabile</th>
                <th>Tipologia</th>
                <th>Parametro</th>
                <th></th>



            </tr>
        </thead>
        <tbody>

            <%
                float t = 0;
                String tip = null;
                String ver;
                Sconto sc = null;
                String onOff = "";
                String onOffValue = "off";
                for (int i = 0; i < array.size(); i++) {

                    sc = (Sconto) array.get(i);

                    if (sc.getTipo().equals(Sconto.tipo.FISSO)) {
                        t = sc.getPrezzo();
                        tip = "Prezzo Fisso".concat(" " + t).concat(" euro");
                    } else if (sc.getTipo().equals(Sconto.tipo.PERCENTUALE)) {
                        t = sc.getPercentuale();
                        tip = "Percentuale ".concat("" + String.format("%.0f", t)).concat("%");
                    }
                    if (sc.getVerificabile().equals(Sconto.verificabile.FALSE)) {
                        ver = "NO";
                    } else {
                        ver = "SI";
                    }


            %>
            <tr class="selezionato">
                <td><%= sc.getNome()%></td>
                <td><%= tip%></td>
                <td><%= ver%></td>
                <td><%= sc.getTipologia()%></td>
                <td><%= sc.getParametroTipologia()%></td>

                <td class="text-center">
                    <div class="text-center" >

                        <form  onclick="onOffAjax(this)" class="formSconti" style="width: 100%" >

                            <%

                                if (sc.getDisponibile() == disponibile.TRUE) {
                                    onOff = "checked";
                                    onOffValue = "on";
                                } else {
                                    onOffValue = "off";
                                    onOff = "";
                                };


                            %>

                            <input  type="checkbox" id="<%= i%>" name="switch"  <%= onOff%> value="<%= onOffValue%>" data-toggle="toggle" data-onstyle="success" data-offstyle="danger" >
                            <input type="hidden" name="position" value="<%= i%>">
                        </form>



                        <form action="ModificaSconto.jsp" method="GET" >
                            <button class="btn btn-primary btn-md mt-2" >Modifica</button>
                            <input type="hidden" name="position" value="<%= i%>" >
                        </form>
                    </div>
                </td>

            </tr>

            <% }  %>

        </tbody>

    </table>
</form>

<script>
    var i = 0;
    function onOffAjax(form) {


        var posizione = $(form).children().next().attr("value");
        var sw = $("#" + posizione).attr("value");


        $.ajax({
            url: 'abilitaSconto',
            type: 'POST',
            data: {"position": posizione, "switch": sw},
            dataType: 'json',
            success: function (data, textStatus, jqXHR)
            {

                var erCode = "alert-success";

                if (data.errore === "true")
                    erCode = "alert-danger";

                $("#messaggio").append("<div class='alert " + erCode + " alert-dismissible fade show' role='alert'>\n\
                <strong>" + data.messaggio + "</strong>\n\
                <button type='button' class='close' data-dismiss='alert' aria-label='Close'>\n\
                <span aria-hidden='true'>&times;</span>\n\
                </button>\n\
                </div>");



            },
            error: function (jqXHR, textStatus, errorThrown)
            {

                alert("Errore");

            }
        });


    }


</script>
<script>


    $(document).ready(function () {

        $("#listaSconti").DataTable({
            "order": [[1, "asc"]],
            "columns": [
                null,
                null,
                {"orderable": false},
                null,
                {"orderable": false},
                {"orderable": false}
            ]




        });


    });




</script>

</div>
</div>




<% }
    }%>


<jsp:include page="FooterAdmin.jsp"/>


