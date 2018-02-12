<%-- 
    Document   : VisualizzaPrenotazioniGestore
    Created on : 29-gen-2018, 13.18.56
    Author     : giuseppeapuzzo
--%>

<%@page import="model.UtenteRegistrato.ruolo"%>
<%@page import="model.Sconto.tipo"%>
<%@page import="model.Sconto"%>
<%@page import="model.UtenteRegistrato"%>
<%@page import="database.FilmDAO"%>
<%@page import="model.Sala"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.Spettacolo"%>
<%@page import="database.SpettacoloDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Prenotazione"%>
<%@page import="model.Film"%>
<%@page import="database.OperazioneDAO"%>
<% request.setAttribute("title", "Visualizza prenotazioni"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>

<%
    HttpSession s = request.getSession();
    UtenteRegistrato utente =(UtenteRegistrato) s.getAttribute("user");
    if ((utente == null)){
        response.sendRedirect("Login.jsp");
    }else{
        String email = request.getParameter("emailUtente");
        if(email == null) {
            email = (String) s.getAttribute("emailUtente");
        }
        s.setAttribute("emailUtente", email);
        OperazioneDAO opdao = new OperazioneDAO();
        List<Prenotazione> prenotazioni = opdao.getPrenotazioniUtente(email);
        SpettacoloDAO spettdao = new SpettacoloDAO();
        String titolo;
        Calendar cal;
        if ((prenotazioni == null) || (prenotazioni.size() == 0)) {
%>

<div >

    <p class="m-0 text-center" > Nessuna prenotazione da visualizzare per l'utente inserito</p>
    <div align="center">
        <input type="button" name="Home" value="Ritorna alla Home Operatore" onclick="window.location.href='index.jsp' " />
    </div>

</div>
<%    
    } else {
%>


<div class="card-header">
    <i class="fa fa-table"></i> Lista prenotazioni dell'utente inserito</div>
    <div class="card-body" id="idcorpo">

    <div class="table-responsive">
        <table id="listaPrenotazioni" style="border-" class="table table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Locandina</th>
                    <th>Titolo</th>
                    <th>Data spettacolo</th>   
                    <th>Ora inizio proiezione</th>
                    <th>Posto</th>
                    <th>Sala</th>
                    <th>Prezzo spettacolo</th>
                    <th>Sconto ricevuto</th>
                    <th>Costo biglietto</th>

                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < prenotazioni.size(); i++) {
                        String idOperazione = ""+prenotazioni.get(i).getIdOperazione();
                        Spettacolo x = spettdao.foundByID(prenotazioni.get(i).getIdSpettacolo());
                %>
                <tr class="selezionato" onclick="window.location.href='VisualizzaPrenotazione.jsp?IdPrenotazione='+<%= idOperazione %>" style="cursor: pointer;">
                    <%
                        FilmDAO fd = new FilmDAO();
                        Film film = fd.foundByID(x.getIdFilm());
                    %>
                    <td><img class="img-fluid" src="<%= film.getLocandina() %>" ></td>
                    <% 
                        titolo = x.getTitolo();
                    %>
                    <td><%= titolo %></td>
                    <%
                        cal = x.getDataInizio();
                        int offset = prenotazioni.get(i).getOffset();
                        cal.add(Calendar.DAY_OF_MONTH, offset);
                        String data = cal.get(Calendar.DAY_OF_MONTH) + "-";
                        int mese = cal.get(Calendar.MONTH);
                        if ((mese >= 0) && (mese < 9)) {
                            data = data + "0" + (mese + 1);
                        } else {
                            data = data + (mese + 1);
                        }
                        data = data + "-" + cal.get(Calendar.YEAR);
                        
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
                    <td><%= prenotazioni.get(i).getPosto() %></td>
                    <%
                        Sala sala = prenotazioni.get(i).getSala();
                        int idSala = sala.getIdSala();
                    %>
                    <td><%= idSala %></td>
                    <td><%= x.getPrezzo() %></td>
                    <%
                        String ScontoPercentuale ="";
                        Sconto sc = prenotazioni.get(i).getSconto();
                        if(sc.getTipo().equals(tipo.FISSO)){
                            ScontoPercentuale =  "Prezzo fisso";
                        }
                        if(sc.getIdSconto()==0){
                            ScontoPercentuale = "Nessuno sconto";
                        }
                        if(sc.getTipo().equals(tipo.PERCENTUALE)){
                            ScontoPercentuale = ""+sc.getPercentuale()+"%";
                        }
                    %>
                    <td><%= ScontoPercentuale %></td>
                    <td><%= prenotazioni.get(i).getPrezzoFinale()%></td>

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

<% }
}%>

<jsp:include page= "/Footer.jsp"/>