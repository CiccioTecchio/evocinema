<%-- 
    Document   : visualizzaSconti
    Created on : 24-gen-2018, 16.55.50
    Author     : francescodefeo
--%>


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
<div class="card-body">

    <div class="table-responsive">
        <table id="listaSconti" style="border-" class="table table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>

                    <th>Nome</th>
                    <th>Tipo</th>
                    <th>Verificabile</th>
                    <th>Tipologia</th>
                    <th></th>



                </tr>
            </thead>
            <tbody>

                <%
                    float t = 0;
                    String tip = null;
                    String ver;
                    Sconto sc = null;
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
                    <td><%= sc.getParametroTipologia()%></td>


                    <td class="text-center">
                        <div class="text-center" >

                            <form action="abilitaSconto" method="GET" >
                                <button type="checkbox" checked data-toggle="toggle" data-on="Abilita" data-off="Disabilita" data-onstyle="success" data-offstyle="danger">  </button>
                                <input id="inserisci" type="checkbox" name="position" value="<%= i%>" checked data-toggle="toggle" data-on="Abilita" data-off="Disabilita" data-onstyle="success" data-offstyle="danger" >
                               
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

        <%--
        <script >

            $(document).ready(function () {

                $("#inserisci").click(function () {
                    var posizione = $("position");
                    var nome = $("#nome").val();
                    var cognome = $("#cognome").val();

                    $.ajax({
                        type: "GET",
                        url: "abilitaSconto",
                        data: {"position": posizione},
                        success: function (risposta) {
                            alert("ciao");
                            $("div#risposta").html(risposta);
                        },
                        error: function () {
                            alert("Chiamata fallita!!!");
                        }
                    });
                    return false;
                });
            });


        </script>
--%>
<script>
      document.getElementById("#inserisci").onClick=function() {myfunction()
      }
      function myFunction() {
          alert("kitastramuooo");
      document.getElementById("demo").innerHTML = "YOU CLICKED ME!";
}
</script>
        <script>


            $(document).ready(function () {

                $("#listaSconti").DataTable({
                    "order": [[1, "asc"]],
                    "columns": [
                        {"orderable": false},
                        null,
                        {"orderable": false},
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


