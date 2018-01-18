<%-- 
    Document   : gestioneOperazioniSala
    Created on : 11-gen-2018, 11.53.26
    Author     : pietr
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Film"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="GestioneAcquistiCNT"/>
<jsp:include page="Header.jsp" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


        <%
            //DATI TEMPORANEI
            ArrayList<Film> film = new ArrayList<>();
            ArrayList<Spettacolo> spettacoli = new ArrayList<>();

            film.add(new Film());
            film.get(0).setIdFilm(1);
            film.get(0).setTitolo("Thor");
            film.add(new Film());
            film.get(1).setIdFilm(2);
            film.get(1).setTitolo("Justice League");

            Calendar orario = Calendar.getInstance();
            spettacoli.add(new Spettacolo());
            spettacoli.get(0).setIdFilm(1);
            orario.set(Calendar.HOUR_OF_DAY, 11);
            spettacoli.get(0).setOraInizio(orario);
            spettacoli.get(0).setDataInizio(orario);   
            spettacoli.add(new Spettacolo());
            spettacoli.get(1).setIdFilm(1);
            orario.set(Calendar.HOUR_OF_DAY, 14);
            spettacoli.get(1).setOraInizio(orario);
            spettacoli.get(1).setDataInizio(orario);   
            
            spettacoli.add(new Spettacolo());
            spettacoli.get(2).setIdFilm(2);
            orario.set(Calendar.HOUR_OF_DAY, 13);
            spettacoli.get(2).setOraInizio(orario);    
            spettacoli.get(2).setDataInizio(orario);   
        %>


<script>
    function aggiornaOraEData(){
    var idFilmSelezionato = document.getElementById("spettacoloSelezionato").value;
    var select = document.getElementById("orarioProgrammazione");
    alert("prima del for");
    
    <%String ora1= ""+spettacoli.get(0).getOraInizio().toString();
    
    
    %>
    var spettacolo=new Object();
    spettacolo.idFilm="<%=spettacoli.get(0).getIdFilm()%>"
    
    alert("idFilm "+spettacolo.idFilm);
      /*
       for(Spettacolo s:spettacoli) 
        {
            alert("dentro il for");
            if (s.getIdFilm() == idFilmSelezionato)  
            {
                alert("dentro if");
                var option = document.createElement("option");
                option.text = "prova";
                option.value = "prova";
                select.options.length = 0;
                select.add(option, select[0]);
                
            } 
        }*/
    }
</script>



<form>
    <fieldset class="fieldsetCustom">


        <legend class="legendCustom">Vendita biglietti - 1 </legend>

        <div class="ml-4">
            <lable>Spettacolo in programmazione</lable>
            <select id="spettacoloSelezionato" class="float-right" onchange="aggiornaOraEData()">
                <%for (Film f : film) {
                        String titolo = f.getTitolo();
                        int id = f.getIdFilm();%>
                <option value="<%=id%>"><%=titolo%></option>  <!--COME VALORE DELL'OPTION SETTO L'ID DEL FILM COSI 
                                                           CE L'HO GIA' DISPONIBILE-->
                <%}%>
            </select>		
        </div>
        <br><br>

        <div class="ml-4">
            <lable>Orario programmazione</lable>
            <select id="orarioProgrammazione" class="float-right">
                <%for (Spettacolo s : spettacoli) {
                        String ora = ("" + s.getOraInizio().getTime());%>

                <option value="<%=ora%>"><%=ora%></option>
                <%}%>
            </select>
        </div>
        <br><br>

        <div class="ml-4">
            <lable>Data programmazione</lable>
            <!-- datapicker bootstrap-->
            <input type="text" class="float-right" id="to" name="data">
        </div>
        <br><br>
        </div>

        <!-- NUMERO DI BIGLIETTI IN BASE AD I POSTI CHE VERRANNO OCCUPATI
        <div>
        <lable>N°biglietti</lable>
        <input class="float-right" type="number" value="1" name="quantity" min="1" max="5"> 
        </div>
        </br> </br>
        -->    

        <div class="ml-4">
            <lable>Sconto</lable>
            <select class="float-right">
                <option value="Nessuno">Nessuno</option>
                <option value="Promo1">Promo1</option>
            </select>
        </div>
        
        </br></br>

        <div class="float-right">
            <input class="mr-3" type="submit" name="Conferma" value="Conferma" />  <!--I DUE BUTTON RICHIAMERANNO LA STESSA SERVLET
                    CHE IN BASE AL VALORE PASSATO REDIRECT BACK, SE ANNULLA, ALTRIMENTI REDIRECT AVANTI-->
            <input type="submit" name="Annulla" value="Annulla" />
        </div>

    </fieldset>
</form>
<jsp:include page="Footer.jsp" />