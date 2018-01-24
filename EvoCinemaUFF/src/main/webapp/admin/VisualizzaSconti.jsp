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
                    <th>Valutazione</th>



                </tr>
            </thead>
            <tbody>
          
                <%
                 float t=0;
                 String ver;
                 Sconto sc=null;
                      for (int i = 0; i < array.size(); i++) {

                        sc = (Sconto) array.get(i);
                        
                        if(sc.getTipo().equals(Sconto.tipo.FISSO)){
                            t=sc.getPrezzo();
                        }
                        else if(sc.getTipo().equals(Sconto.tipo.PERCENTUALE)){
                            t=sc.getPercentuale();
                        }
                        if(sc.getVerificabile().equals(Sconto.verificabile.FALSE)){
                            ver="NO";
                        }
                        else ver="SI";
                            
                        

                %>
                <tr class="selezionato">
                    <td><%= sc.getNome() %></td>
                    <td><%= t %></td>
                    <td><%= ver %></td>
                    <td><%= sc.getParametroTipologia() %></td>


                    <td class="text-center">
                        <div class="text-center" >
                            
                            <form action="/abilitaSconto" method="GET" >
                                <button class="btn btn-primary btn-md mb-2" > Abilita </button>
                                <input type="hidden" name="position" value="<%= i %>" >
                            </form>
                            <form action="ModificaSconto.jsp" method="GET" >
                                <button class="btn btn-primary btn-md mt-2" >Modifica</button>
                                <input type="hidden" name="position" value="<%= i %>" >
                            </form>
                        </div>
                    </td>

                </tr>

                <% }  %>

            </tbody>
        </table>
        <script>


            $(document).ready(function () {

                $("#listaSconti").DataTable({

                    "order": [[1, "asc"]],

                    "columns": [

                        {"orderable": false},
                        null,
                        null,
                        {"orderable": false},
                        null
                    ]




                });


            });




        </script>
   
    </div>
</div>




<% }
            }%>


<jsp:include page="FooterAdmin.jsp"/>


