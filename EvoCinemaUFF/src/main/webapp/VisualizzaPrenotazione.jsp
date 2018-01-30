<%-- 
    Document   : AcquistaPrenotazione
    Created on : 19-gen-2018, 12.12.42
    Author     : giuseppeapuzzo
--%>

<%@page import="model.UtenteRegistrato.ruolo"%>
<%@page import="model.UtenteRegistrato"%>
<%@page import="model.Sconto.tipo"%>
<%@page import="database.SpettacoloDAO"%>
<%@page import="database.OperazioneDAO"%>
<%@page import="model.Operazione"%>
<%@page import="model.Film"%>
<%@page import="java.util.Collection"%>
<%@page import="database.FilmDAO"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Prenotazione"%>
<%@page import="model.Operazione.acquistato"%>
<%@page import="model.Operazione.prenotato"%>
<%@page import="model.Sala"%>
<%@page import="model.Sconto"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Visualizza prenotazione"); %>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>


<%
    int IdPrenotazione = Integer.parseInt(request.getParameter("IdPrenotazione"));
    OperazioneDAO opDAO = new OperazioneDAO();
    SpettacoloDAO spettDAO = new SpettacoloDAO();
    Operazione x = opDAO.foundByID(IdPrenotazione);
    System.out.println("Operazione:"+x.toString());
    Spettacolo spett = spettDAO.foundByID(x.getIdSpettacolo());
    session.setAttribute("operazione", x);
%>

<form id="idform" method="POST" onclick="return OnSubmit()">
    <fieldset class="fieldsetCustom">

        <legend class="legendCustom">Dettagli prenotazione </legend>

        <div class="ml-4">
            <label>Spettacolo prenotato:</label>
            <label class="float-right" id="idSpet" name="titolo"><%= spett.getTitolo() %> </label>
        </div>
        <br><br>
                    <%
                        Calendar cal = spett.getDataInizio();
                        String data = cal.get(Calendar.YEAR) + "-";
                        int mese = cal.get(Calendar.MONTH);
                        if ((mese >= 0) && (mese < 9)) {
                            data = data + "0" + (mese + 1);
                        } else {
                            data = data + (mese + 1);
                        }
                        data = data + "-" + cal.get(Calendar.DAY_OF_MONTH);
                    %>
        <div class="ml-4">
            <label>Data programmazione:</label>
            <label class="float-right" id="idData" name="data"><%= data %></label>
        </div>
        <br><br>
                    <%
                        cal = spett.getOraInizio();
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
        <div class="ml-4">
            <label>Orario prenotazione:</label>
            <label class="float-right" id="idPrenotazione" name="orario"><%= orario %></label>
        </div>
        <br><br>
                    <%
                        String posto = ""+x.getPosto();
                    %>
        <div class="ml-4">
            <label>Dettagli posto:</label>
            <label class="float-right" id="idDettagli" name="dettagli"><%= posto %></label>
        </div>
        <br><br>
        
        <div class="ml-4">
            <label>Costo spettacolo</label>
            <label class="float-right" id="idImporto" name="importo"><%= x.getPrezzoFinale() %></label>
        </div>
        <br><br>
        
        <div class="ml-4">
            <label>Sconto applicato</label>
            <%
                String sconto ="";
                Sconto sc = x.getSconto();
                if(sc.getTipo().equals(tipo.FISSO)){
                    sconto =  "Prezzo fisso: "+sc.getPrezzo();
                }
                if(sc.getIdSconto()==0){
                    sconto = "Nessuno sconto";
                }
                if(sc.getTipo().equals(tipo.PERCENTUALE)){
                    sconto = ""+sc.getPercentuale()+"%";
                }
            %>
            <label class="float-right" id="idImporto" name="importo"><%= sconto %></label>
        </div>
        <br><br>

        </br></br>
        <%
            HttpSession s = request.getSession();
            UtenteRegistrato utente =(UtenteRegistrato) s.getAttribute("user");
            String indietro ="";
            if( (utente.getRuolo()).equals(ruolo.OPERATORE) ) {
                indietro = "VisualizzaPrenotazioniOperatore.jsp";
            }
            if( (utente.getRuolo()).equals(ruolo.UTENTE) ) {
                indietro = "VisualizzaPrenotazioni.jsp";
            }
        %>
        <input type="button" name="Indietro" value="Indietro" onclick="window.location.href='<%= indietro %>' " />
        <div class="float-right">
            <%
            if( (utente.getRuolo()).equals(ruolo.OPERATORE) ) { %>
                <input class="mr-3" type="submit" name="Esito" value="Vendi la prenotazione all'utente" onclick="document.pressed=this.value" />  
            <%
                indietro = "VisualizzaPrenotazioniOperatore.jsp";
            }
            if( (utente.getRuolo()).equals(ruolo.UTENTE) ) { %>
                <input class="mr-3" type="submit" name="Esito" value="Acquista prenotazione" onclick="document.pressed=this.value" />  
                <input type="submit" name="Esito" value="Cancella prenotazione" onclick="if(confirm('Sei sicuro di voler cancellare la prenotazione?'))document.pressed=this.value"/>
            <%
            }
            %>  
            
        </div>

    </fieldset>
</form>
<script>
      function OnSubmit(){
        if(document.pressed == "Vendi la prenotazione all'utente"){
            document.getElementById("idform").action="AcquistoConPrenotazioneCNT";
        }
        if(document.pressed == 'Acquista prenotazione'){
            document.getElementById("idform").action="AcquistoConPrenotazioneCNT";
        }
        if(document.pressed == 'Cancella prenotazione'){
            document.getElementById("idform").action="DisdettaPrenotazioneCNT";
        }
        return true;
    }          
</script>
<jsp:include page="Footer.jsp" />
