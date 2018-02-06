
<%-- 
    Document   : ModificaSala.jsp
    Created on : 2-feb-2018, 17.19.34
    Author     : luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Inserimento Sala"); %>
<jsp:include page="Header.jsp" />
<% Boolean inserito = (Boolean) request.getAttribute("inserito"); %>
<div class="container-fluid">
    <% if((inserito != null) && (inserito == true)){
        out.print("<div class=\"row\">");
            out.print("<div class=\"alert alert-success alert-dismissable\" ><h6>Inserimento effettuato con successo.</h6></div>");
        out.print("</div>");
    } %>
    <hr />
    <div class="row">
        <small>Seleziona le caselle per configurare la sala:<br/>
        <img src="../images/poltrona_non_disponibile_v2.png" /> nessuna poltrona in questo punto<br/>
        <img src="../images/poltrona_disponibile_v2.png" /> poltrona acquistabile<br/>
        <img src="../images/poltrona_occupata_v2.png" /> poltrona guasta<br/>
        </small>
    </div>
    <hr />
    <div class="row">
        <div class ="col">
            <div id="seats" class="card">
                <h5 class="card-header">Configurazione Sala</h5>
                <div class="card-body" id="seats">
                    <%
                    for(int i = 0; i < 30; i++){
                        %>
                        <div class="row">
                        <% for(int j = 0; j < 30; j++){ %>
                            <img data-stato="non-selezionata" data-row="<%=i%>" data-col="<%=j%>" class="ms-casella ms-non-selezionata cell" src="../images/poltrona_non_disponibile_v2.png" />
                        <%
                           }
                        %> </div> <%
                           }
                        %>
                </div>
                <div class="card-footer">
                    <button onclick="popolaForm();" form="form" class="btn btn-primary">Inserisci Sala</button>
                </div>
            </div>
        </div>
    </div>
</div>

<form id="form" action="AggiuntaSala" method="POST" hidden>
    <input id="form-input" type="text" name="posti" value=""/>
</form>
                
<script src="../javascript/modifica_sala.js"></script>
<jsp:include page="Footer.jsp"/>
