<%-- 
    Document   : OperatorePrelevaEmailUtente
    Created on : 29-gen-2018, 13.53.07
    Author     : giuseppeapuzzo
--%>

<%@page import="model.UtenteRegistrato.ruolo"%>
<%@page import="model.UtenteRegistrato"%>
<% request.setAttribute("title", "Visualizza prenotazioni gestore"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>
<%
    HttpSession s = request.getSession();
    UtenteRegistrato utente =(UtenteRegistrato) s.getAttribute("user");
    if ((utente == null)){
        response.sendRedirect("Login.jsp");
    }
    if( (utente.getRuolo()).equals(ruolo.OPERATORE) ) { %>
    <div >

    <p class="m-0 text-center" > Visualizza le prenotazioni di un utente </p>

    </div>
    <div class="container" id="divEmail">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Ricerca utente per email</div>
                <div class="card-body">
                    <form method="POST" action="VisualizzaPrenotazioniOperatore.jsp">
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">E-mail </label>
                                    <input class="text" type="text" id="emailUtente" name="emailUtente" maxlength="50">
                                </div>
                            </div>
                        </div>
                        <div class="text-center">
                            <input type="submit" name="go" value="Visualizza le prenotazioni dell'utente" />
                        </div>
                    </form>
    </div>
<%
}
%>

<jsp:include page= "/Footer.jsp"/>