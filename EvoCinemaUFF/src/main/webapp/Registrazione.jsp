<%-- 
    Document   : Registrazione
    Created on : 18-gen-2018, 10.46.22
    Author     : Giuseppe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="Header.jsp"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <%
            HttpSession s = request.getSession();
        
            UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");

            Boolean registrazioneImpossibile = (Boolean) s.getAttribute("registrazioneImpossibile");
            if (utente == null) {
                if (registrazioneImpossibile != null) {
        %>
        <h2>Registrazione</h2>
        <h3>Esiste gi&agrave; un account con questo nome utente.</h3>

        <%
        } else {
        %>

        <h2>Registrazione</h2>
        <p>Inserisci i dati per la creazione dell'account</p>
        <%
            }
        %>
        <form
            action="RegistrazioneCNT"
            method="post">
            <label class="labelRegistrazione">Nome: </label>
            <input type="text"
                   id="nomeRegistrazione" onchange='controlloCaratteri()'
                   name="nomeRegistrazione">
            <span id="hideNome">Il campo non pu&ograve; essere vuoto.</span> 

            <br>

            <label class="labelRegistrazione">Cognome: </label>
            <input type="text" name="cognomeRegistrazione" id="cognomeRegistrazione"
                   onchange='controlloCaratteri()'>
            <span id="hideCognome">Il campo non pu&ograve; essere vuoto.</span>

            <br>

            <label class="labelRegistrazione">Sesso: </label>
            <select name="sessoRegistrazione">
                <option value="maschio">Maschio</option>
                <option value="femmina">Femmina</option>
            </select>

            <br>

            <label class="labelRegistrazione">Data di nascita: </label>
            <input type="date" name="dataRegistrazione" id="dataRegistrazione">

            <br>

            <label class="labelRegistrazione">Indirizzo: </label>
            <input type="text" name="indirizzoRegistrazione" id="indirizzoRegistrazione"
                   onchange='controlloCaratteri()'>
            <span id="hideIndirizzo">Il campo non pu&ograve; essere vuoto.</span>

            <br>

            <label class="labelRegistrazione">Citt&agrave;: </label>
            <input type="text" name="cittaRegistrazione" id="cittaRegistrazione"
                   onchange='controlloCaratteri()'>
            <span id="hideCitta">Il campo non pu&ograve; essere vuoto.</span>

            <br>

            <label class="labelRegistrazione">E-mail: </label>
            <input type="email" name="emailRegistrazione" id="emailRegistrazione"
                   onchange='controlloCaratteri()'>
            <span id="hideEmail">Il campo non pu&ograve; essere vuoto.</span>

            <br>

            <label class="labelRegistrazione">Cellulare: </label>
            <input type="text" name="cellulareRegistrazione" id="cellulareRegistrazione"
                   onchange='controlloCaratteri()'>
            <span id="hideCellulare">Il campo non pu&ograve; essere vuoto.</span>

            <br>

            <label class="labelRegistrazione">Username: </label>
            <input type="text" name="userRegistrazione" id="usernameRegistrazione"
                   onchange='controlloCaratteri()'>
            <span id="hideUsername">Il campo non pu&ograve; essere vuoto.</span>

            <br>

            <label class="labelRegistrazione">Password: </label>
            <input type="password" name="passwordRegistrazione" id="passwordRegistrazione"
                   onchange='controlloCaratteri()'>
            <span id="hidePw1">Il campo non pu&ograve; essere vuoto.</span>

            <br>

            <label class="labelRegistrazione">Conferma password: </label>
            <input type="password" name="password1Registrazione" id="confermaPasswordRegistrazione"
                   onchange='controlloCaratteri()'> <span id="hidePw2">Il
                campo non pu&ograve; essere vuoto.</span>

            <br>

            <input type="submit" id="bottoneRegistrazione" name="bottoneRegistrazione"
                   value="Conferma">
        </form>


        <%
        } else {
        %>
        <h2>Hai già fatto l'accesso con un account!</h2>
        <%
            }
        %>

    </body>
    <%@ include file="Footer.jsp"%>
</html>
