<%-- 
    Document   : gestioneOperazioniSala
    Created on : 11-gen-2018, 11.53.26
    Author     : pietr
--%>

<%@page import="java.util.Calendar"%>
<%@page import="model.Spettacolo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Vendi biglietto"); %>
<jsp:include page="Header.jsp"/>
<jsp:include page= "GestioneAcquistiCNT"/>

<!DOCTYPE html>

<%
    List<Spettacolo> spettacoli = (List<Spettacolo>) request.getAttribute("SPETTACOLI");

    if ((null == spettacoli) || (spettacoli.size() == 0)) {

%>
<div >

    <p class="m-0 text-center" > Nessun Film Da Visualizzare </p>

</div>
<%        } else {
%>


<div class="card-header">
    <i class="fa fa-table"></i> Lista Film in programmazione</div>
<div class="card-body">

    <div class="table-responsive">
        <table id="listaFilm" style="border-" class="table table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    
                    <th>Titolo</th>
                    <th>Data inizio</th>                                               
                    <th>Data fine</th>
                    <th>Ora inizio proiezione</th>
                    <th>Ora fine proiezione</th>
                    
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < spettacoli.size(); i++) {
                        String idSpettacolo=""+spettacoli.get(i).getIdSpettacolo();
                %>

                
                <tr class="selezionato" onclick="window.location.href='VisualizzazioneDettagliSpettacolo.jsp?idSpettacolo=<%= idSpettacolo %>'" style="cursor: pointer;">
                    <td name="idSpettacolo" value="<%= spettacoli.get(i).getIdSpettacolo()%>"
                        ><%= spettacoli.get(i).getTitolo()%></td>
                    <%
                        Calendar cal = spettacoli.get(i).getDataInizio();
                        String data = cal.get(Calendar.YEAR) + "/";
                        int mese = cal.get(Calendar.MONTH);
                        if ((mese >= 0) && (mese < 9)) {
                            data = data + "0" + (mese + 1);
                        } else {
                            data = data + (mese + 1);
                        }
                        data = data + "/" + cal.get(Calendar.DAY_OF_MONTH);
                    %>       
                    <td><%= data%></td>
                    <%
                        cal = spettacoli.get(i).getDataFine();
                        data = cal.get(Calendar.YEAR) + "/";
                        mese = cal.get(Calendar.MONTH);
                        if ((mese >= 0) && (mese < 9)) {
                            data = data + "0" + (mese + 1);
                        } else {
                            data = data + (mese + 1);
                        }
                        data = data + "/" + cal.get(Calendar.DAY_OF_MONTH);
                    %>       
                    <td><%= data%></td>
                    <%
                        cal = spettacoli.get(i).getOraInizio();
                        String orario = "";
                        int ora = cal.get(Calendar.HOUR_OF_DAY);
                        int minuti = cal.get(Calendar.MINUTE);
                        if ((ora >= 0) && (ora <= 9)) {
                            orario = "0" + ora;
                        } else {
                            orario += ora;
                        }
                        if (minuti>=0 && minuti<10) {
                            orario+=":0"+minuti;
                        } else {
                            orario += ":" + cal.get(Calendar.MINUTE);
                        }
                    %>
                    <td><%= orario%></td>
                    <%
                        cal = spettacoli.get(i).getOraFine();
                        orario = "";
                        ora = cal.get(Calendar.HOUR_OF_DAY);
                        minuti = cal.get(Calendar.MINUTE);
                        if ((ora >= 0) && (ora <= 9)) {
                            orario = "0" + ora;
                        } else {
                            orario += ora;
                        }
                        if (minuti>=0 && minuti<10) {
                            orario+=":0"+minuti;
                        } else {
                            orario += ":" + cal.get(Calendar.MINUTE);
                        }
                    %>
                    <td><%= orario%></td>

                </tr>
                
                <% }  %>

               
                
            </tbody>
        </table>
    </div>
</div>

<% }%>
     

<script type="text/javascript">
$(document).ready(function() {
   /*
    $( function() {
   	 $( "#datepicker" ).datepicker();
  	} );
  */
    // Setup - add a text input to each footer cell
    
    // DataTable
    var table = $('#listaFilm').DataTable({ 
        "scrollCollapse": false,
        "paging":         true
    });
} );
</script>



<jsp:include page= "/Footer.jsp"/>


