<%-- 
    Document   : ResocontoAcquisto
    Created on : 25-gen-2018, 11.36.13
    Author     : giuseppeapuzzo
--%>

<%@page import="model.Sconto.tipo"%>
<%@page import="model.Sconto"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.Spettacolo"%>
<%@page import="database.SpettacoloDAO"%>
<%@page import="model.Operazione"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Resoconto acquisto"); %>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>


<%
    Operazione operazione = (Operazione) session.getAttribute("operazione");
    SpettacoloDAO spettDAO = new SpettacoloDAO();
    Spettacolo spett = spettDAO.foundByID(operazione.getIdSpettacolo());
%>

<form>
    <fieldset class="fieldsetCustom">

        <legend class="legendCustom">Riepilogo acquisto </legend>

        <div class="ml-4">
            <label>Spettacolo acquistato:</label>
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
            <label>Orario inizio:</label>
            <label class="float-right" id="idPrenotazione" name="orario"><%= orario %></label>
        </div>
        <br><br>
                    <%
                        String posto = ""+operazione.getPosto();
                    %>
        <div class="ml-4">
            <label>Dettagli posto:</label>
            <label class="float-right" id="idDettagli" name="dettagli"><%= posto %></label>
        </div>
        <br><br>
            <%
                String sconto ="";
                Sconto sc = operazione.getSconto();
                
                if(sc.getIdSconto()==0){ %>
                <div class="ml-4">
                    <label>Importo pagato</label>
                    <label class="float-right" id="idImporto" name="importo"><%= operazione.getPrezzoFinale() %></label>
                </div>
                <br><br>
                <%
                }
                
                if(sc.getTipo().equals(tipo.FISSO)){ %>
                <div class="ml-4">
                    <label>Sconto applicato </label>
                    <label class="float-right" id="idImporto" name="importo">Importo a prezzo fisso</label>
                </div>
                <br><br>
                
                <div class="ml-4">
                    <label>Importo pagato</label>
                    <label class="float-right" id="idImporto" name="importo"><%= sc.getPrezzo() %></label>
                </div>
                <br><br>
                <%
                }

                if(sc.getTipo().equals(tipo.PERCENTUALE)){
                    String s = ""+sc.getPercentuale()+"%";
                    float percentuale = (float) sc.getPercentuale();
                    float price = operazione.getPrezzoFinale();
                    float dascalare = (price * percentuale) /100;  
                    String xs = ""+(price-dascalare)+ "€";
                %>
                <div class="ml-4">
                    <label>Sconto applicato </label>
                    <label class="float-right" id="idImporto" name="importo"><%= s %></label>
                </div>
                <br><br>
                
                <div class="ml-4">
                    <label>Importo pagato</label>
                    <label class="float-right" id="idImporto" name="importo"><%= xs %></label>
                </div>
                <br><br>
                <%
                }
            %>

        </br></br>
        <input type="button" name="Prenotazioni" value="Ritorna alla lista prenotazioni" onclick="window.location.href='VisualizzaPrenotazioni.jsp' " />
        <div class="float-right">
        <input type="button" name="Acquisti" value="Visualizza tutti gli acquisti" onclick="window.location.href='VisualizzaAcquisti.jsp' " />
        </div>

    </fieldset>
</form>

<jsp:include page="Footer.jsp" />

