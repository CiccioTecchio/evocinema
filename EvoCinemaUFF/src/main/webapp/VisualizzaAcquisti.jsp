<%-- 
    Document   : VisualizzaAcquisti
    Created on : 25-gen-2018, 16.28.11
    Author     : giuseppeapuzzo
--%>

<%@page import="model.UtenteRegistrato.ruolo"%>
<%@page import="model.Sconto.tipo"%>
<%@page import="model.Sconto"%>
<%@page import="model.UtenteRegistrato"%>
<%@page import="model.Sala"%>
<%@page import="model.Film"%>
<%@page import="database.FilmDAO"%>
<%@page import="model.Spettacolo"%>
<%@page import="java.util.Calendar"%>
<%@page import="database.SpettacoloDAO"%>
<%@page import="model.Acquisto"%>
<%@page import="java.util.List"%>
<%@page import="database.OperazioneDAO"%>
<% request.setAttribute("title", "Visualizza acquisti"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>

<%
    HttpSession s = request.getSession();
    UtenteRegistrato utente =(UtenteRegistrato) s.getAttribute("user");
    if ((utente == null)){
        response.sendRedirect("Login.jsp");
    }
    if( (utente!=null)/* && ((utente.getRuolo()).equals(ruolo.UTENTE)) */ ) {
        String email =utente.getEmail(); 
        OperazioneDAO opdao = new OperazioneDAO();
        List<Acquisto> acquisti = opdao.getAcquistiUtente(email);
        SpettacoloDAO spettdao = new SpettacoloDAO();
        String titolo;
        Calendar cal;
        if ((acquisti == null) || (acquisti.size() == 0)) {
%>

<div >

    <p class="m-0 text-center" > Nessun acquisto da visualizzare </p>

</div>
<%        } else {
%>


<div class="card-header">
    <i class="fa fa-table"></i><%
        if(utente.getRuolo().equals(ruolo.UTENTE)){
            %>Lista acquisti utente<%
        }
        if(utente.getRuolo().equals(ruolo.OPERATORE)){
            %>Lista vendite operatore<%
        }
                %></div>
<div class="card-body">

    <div class="table-responsive">
        <table id="listaAcquisti" style="border-" class="table table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Locandina</th>
                    <th>Titolo</th>
                    <th>Data spettacolo</th>   
                    <th>Ora inizio</th>
                    <th>Posto</th>
                    <th>Sala</th>
                    <th>Prezzo spettacolo</th>
                    <th>
                    <%
                        if(utente.getRuolo().equals(ruolo.UTENTE)){
                            %>Sconto ricevuto<%
                        }
                        if(utente.getRuolo().equals(ruolo.OPERATORE)){
                            %>Sconto applicato<%
                        }
                    %>
                    </th>
                    <th>
                    <%
                        if(utente.getRuolo().equals(ruolo.UTENTE)){
                            %>Importo pagato<%
                        }
                        if(utente.getRuolo().equals(ruolo.OPERATORE)){
                            %>Importo incassato<%
                        }
                    %>    
                    </th>

                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < acquisti.size(); i++) {
                        String idOperazione = ""+acquisti.get(i).getIdOperazione();
                        Spettacolo x = spettdao.foundByID(acquisti.get(i).getIdSpettacolo());
                %>
                <tr class="selezionato">
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
                        int offset = acquisti.get(i).getOffset();
                        cal.add(Calendar.DAY_OF_MONTH, offset);
                        String data = cal.get(Calendar.YEAR) + "-";
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
                    <td><%= acquisti.get(i).getPosto() %></td>
                    <%
                        Sala sala = acquisti.get(i).getSala();
                        int idSala = sala.getIdSala();
                    %>
                    <td><%= idSala %></td>
                    <td><%= x.getPrezzo() %></td>
                    <%
                        String ScontoPercentuale ="";
                        Sconto sc = acquisti.get(i).getSconto();
                        if( (sc.getTipo().equals(tipo.FISSO)) && (sc.getIdSconto()!=41) ){
                            ScontoPercentuale =  "Prezzo fisso"; %>
                            <td><%= ScontoPercentuale %></td>
                            <td><%= sc.getPrezzo() %></td>
                        <%    
                        }
                        if(sc.getIdSconto()==41){
                            ScontoPercentuale = "Nessuno sconto"; %>
                            <td><%= ScontoPercentuale %></td>
                            <td><%= acquisti.get(i).getPrezzoFinale()%></td>
                        <%    
                        }
                        if( (sc.getTipo().equals(tipo.PERCENTUALE)) && (sc.getIdSconto()!=41) ){
                            ScontoPercentuale = ""+sc.getPercentuale()+"%"; 
                            float price = acquisti.get(i).getPrezzoFinale();
                            String xs = ""+price+"€";
                        %>
                            <td><%= ScontoPercentuale %></td>
                            <td><%= xs %></td>
                        <%    
                        }
                    %>

                </tr>

                <% }  %>

            </tbody>
        </table>
                
    <script type="text/javascript">
        $(document).ready(function () {
            // DataTable
            var table = $('#listaAcquisti').DataTable({ 
                "scrollCollapse": false,
                "paging":         true
            }
            );

            $('#listaAcquisti tbody').on( 'click', 'tr', function () {
            $(this).toggleClass('selected');
            } );


             $('#listaAcquisti tbody')
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