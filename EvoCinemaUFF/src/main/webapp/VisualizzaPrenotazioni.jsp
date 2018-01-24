<%-- 
    Document   : VisualizzaPrenotazioni
    Created on : 24-gen-2018, 10.50.43
    Author     : giuseppeapuzzo
--%>

<%@page import="model.Sala"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.Spettacolo"%>
<%@page import="database.SpettacoloDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Prenotazione"%>
<%@page import="database.OperazioneDAO"%>
<% request.setAttribute("title", "Visualizza prenotazioni"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>

<%
    String email ="ABergamaschi@gmail.com "; 
    OperazioneDAO opdao = new OperazioneDAO();
    List<Prenotazione> prenotazioni = opdao.getPrenotazioniUtente(email);
    SpettacoloDAO spettdao = new SpettacoloDAO();
    String titolo;
    Calendar cal;
    if ((prenotazioni == null) || (prenotazioni.size() == 0)) {
%>

<div >

    <p class="m-0 text-center" > Nessuna prenotazione da visualizzare </p>

</div>
<%        } else {
%>


<div class="card-header">
    <i class="fa fa-table"></i> Lista prenotazioni utente</div>
<div class="card-body">

    <div class="table-responsive">
        <table id="listaPrenotazioni" style="border-" class="table table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>

                    <th>Titolo</th>
                    <th>Data</th>   
                    <th>Ora inizio proiezione</th>
                    <th>Posto colonna</th>
                    <th>Posto riga</th>
                    <th>Sala</th>
                    <th>Prezzo</th>
                    <th>Sconto</th>

                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < prenotazioni.size(); i++) {
                        String idOperazione = ""+prenotazioni.get(i).getIdOperazione();
                        System.out.println("idOperazione="+idOperazione);
                %>
                <tr class="selezionato" onclick="window.location.href='AcquistaPrenotazione.jsp?IdPrenotazione='+<%= idOperazione %>" style="cursor: pointer;">
                    <% 
                        Spettacolo x = spettdao.foundByID(prenotazioni.get(i).getIdSpettacolo());
                        titolo = x.getTitolo();
                    %>
                    <td><%= titolo %></td>
                    <%
                        cal = x.getDataInizio();
                        String data = cal.get(Calendar.YEAR) + "-";
                        int mese = cal.get(Calendar.MONTH);
                        if ((mese >= 0) && (mese < 9)) {
                            data = data + "0" + (mese + 1);
                        } else {
                            data = data + (mese + 1);
                        }
                        data = data + "-" + cal.get(Calendar.DAY_OF_MONTH);
                    %>
                    <td><%= data %></td>
                    <%
                        cal = x.getOraInizio();
                        String orario = "";
                        int ora = cal.get(Calendar.HOUR_OF_DAY);
                        if ((ora <= 0) && (ora >= 9)) {
                            orario = "0" + ora;
                        } else {
                            orario += ora;
                        }
                        if (cal.get(Calendar.MINUTE) == 0) {
                            orario += ":00";
                        } else {
                            orario += ":" + cal.get(Calendar.MINUTE);
                        }
                    %>
                    <td><%= orario %></td>
                    <td><%= prenotazioni.get(i).getPostoColonna() %></td>
                    <td><%= prenotazioni.get(i).getPostoRiga()%></td>
                    <%
                        Sala sala = prenotazioni.get(i).getSala();
                        int idSala = sala.getIdSala();
                    %>
                    <td><%= idSala %></td>
                    <td><%= prenotazioni.get(i).getPrezzoFinale()%></td>
                    <%
                        int percentuale = prenotazioni.get(i).getSconto().getPercentuale();
                        String ScontoPercentuale = ""+percentuale+" %";
                    %>
                    <td><%= ScontoPercentuale %></td>

                </tr>

                <% }  %>

            </tbody>
        </table>
                
    <script type="text/javascript">
        $(document).ready(function () {
            // DataTable
            var table = $('#listaPrenotazioni').DataTable({ 
                "scrollCollapse": false,
                "paging":         true
            }
            );

            $('#listaPrenotazioni tbody').on( 'click', 'tr', function () {
            $(this).toggleClass('selected');
            } );


             $('#listaPrenotazioni tbody')
                .on( 'mouseenter', 'td', function () {
                    var colIdx = table.cell(this).index().column;

                    $( table.cells().nodes() ).removeClass( 'highlight' );
                    $( table.column( colIdx ).nodes() ).addClass( 'highlight' );
                } );


            $('#button').click( function () {
                alert( table.rows('.selected').data().length +' row(s) selected' );
            } );

        } );
</script>

    </div>
</div>

<% }%>


<jsp:include page= "/Footer.jsp"/>