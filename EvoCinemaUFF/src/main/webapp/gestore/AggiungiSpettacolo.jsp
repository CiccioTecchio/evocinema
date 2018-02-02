<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Film"%>
<%@page import="model.Sala"%>
<%@page import="java.util.List"%>
<% request.setAttribute("title", "Aggiungi Spettacolo"); %>
<jsp:include page="Header.jsp" />
<jsp:include page="PopolamentoListe"/>
<% List<Sala> sale = (List<Sala>) request.getAttribute("sale"); 
   List<Film> film = (List<Film>) request.getAttribute("film");
   SimpleDateFormat sdf = (SimpleDateFormat) request.getAttribute("sdf");
%>
<div class="card">
    <h6 class="card-header">Form Inserimento Spettacolo</h6>
    <div class="card-body">

        <form accept-charset="utf-8" method="POST" action="AggiungiSpettacolo">
          <input id="id-film" type="hidden" name="id-film" value="" />
          <div class="form-group">
            <label for="select-film" class="col-form-label">Opera: </label>
            <select class="form-control" id="select-film" required>
                <% 
                for(Film f : film){
                    out.print("<option data-id=\"" + f.getIdFilm() +"\" value=\"" + f.getTitolo() +"\">" + f.getTitolo() + " - " + sdf.format(f.getDataUscita().getTime()) + "</option>");
                }
                %>
            </select>
            <small id="select-film-help" class="form-text text-muted">Seleziona uno dei film presenti in libreria, o <a href="inserisciFilmLibreria.jsp">aggiungine uno prima.</a></small>

          </div>
          <div class="form-group">
            <label for="titolo-spettacolo" >Titolo dello spettacolo:</label>
            <input name="titolo"type="text" class="form-control" placeholder="Titolo" id="titolo-spettacolo" required>
          </div>
          <div class="form-group">
            <label for="select-sala" >Sala </label>
            <select class="form-control" name="sala" id="select-sala" required>
                <% 
                for(Sala s : sale){
                    out.print("<option value=\"" + s.getIdSala()+ "\">" + s.getIdSala() + "</option>");
                }
                %>
            </select>
          </div>
          <div class="form-group">
            <label for="data-inizio" >Data della prima proiezione:</label>
            <input type="date" name="data-inizio" class="form-control" id="data-inizio" required>
            <small id="data-inizio-help" class="form-text text-muted">Seleziona la data della prima proiezione dello spettacolo.</small>
          </div>
          <div class="form-group">
            <label for="data-fine" >Data dell'ultima proiezione:</label>
            <input type="date" name="data-fine" class="form-control" id="data-fine" required>
            <small id="data-fine-help" class="form-text text-muted">Seleziona la data dell'ultima proiezione dello spettacolo. Tieni presente che lo spettacolo sarà proiettato ogni giorni a partire dalla data di inizio a quella di fine.</small>
          </div>
          <div class="form-group">
            <label for="ora-inizio" >Ora Inizio:</label>
            <input type="time" name="ora-inizio" class="form-control" id="ora-inizio" required>
            <small id="ora-inizio-help" class="form-text text-muted">Seleziona l'orario in cui è consentito l'ingresso in sala.</small>
          </div>
          <div class="form-group">
            <label for="ora-fine" >Ora Fine:</label>
            <input type="time" name="ora-fine" class="form-control" id="ora-fine" required>
            <small id="ora-fine-help" class="form-text text-muted">Seleziona l'orario in cui lo spettacolo terminerà.</small>
          </div>
          <div class="form-group">
            <label for="prezzo" >Prezzo:</label>
            <input type="number" name="prezzo" class="form-control" id="prezzo" step="any" required>
          </div>
          <button type="submit" class="btn btn-primary mb-2">Inserisci lo Spettacolo</button>
        </form>
    </div>
</div>
<script src="aggiungi_spettacolo.js"></script>

<jsp:include page="Footer.jsp" />
