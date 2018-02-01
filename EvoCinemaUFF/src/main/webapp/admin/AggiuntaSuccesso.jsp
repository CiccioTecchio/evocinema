<%-- 
    Document   : AggiuntaSuccesso
    Created on : 1-feb-2018, 21.30.37
    Author     : luca
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Spettacolo"%>
<% request.setAttribute("title", "Aggiunta Spettacolo con Successo"); %>
<jsp:include page="HeaderAdmin.jsp" />
<% 
    Spettacolo s = (Spettacolo) request.getAttribute("spettacolo"); 
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdfOra = new SimpleDateFormat("HH:mm");
%>

<div class="container">
    <div class="row">
        <p><h6>Aggiunta dello spettacolo avvenuta con successo.</h6></p>
    </div>
    <div class="row">
        <div class="card">
            <h5 class="card-header">Riepilogo</h5>
            <div class="card-body">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            ID del film rappresentato:
                        </div>
                        <div class="col">
                            <%= s.getIdFilm() %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Titolo dello spettacolo:
                        </div>
                        <div class="col">
                            <%= s.getTitolo()%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Sala:
                        </div>
                        <div class="col">
                            <%= s.getIdSala()%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Data inizio:
                        </div>
                        <div class="col">
                            <%= sdf.format(s.getDataInizio()) %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Data fine:
                        </div>
                        <div class="col">
                            <%= sdf.format(s.getDataFine())  %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Ora inizio:
                        </div>
                        <div class="col">
                            <%= sdfOra.format(s.getOraInizio()) %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Ora fine:
                        </div>
                        <div class="col">
                            <%= sdfOra.format(s.getOraFine()) %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Prezzo:
                        </div>
                        <div class="col">
                            <%= s.getPrezzo()%>
                        </div>
                    </div>                        
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="FooterAdmin.jsp" />