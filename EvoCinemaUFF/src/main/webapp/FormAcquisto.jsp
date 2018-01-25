<%-- 
    Document   : FormAcquisto
    Created on : 18-gen-2018, 11.18.43
    Author     : pietr
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <% request.setAttribute("title", "Finalizza acquisto"); %>
<jsp:include page="Header.jsp" />
<!DOCTYPE html>

<form id="myform" method="POST" onclick="return OnSubmitFormAcquisto()">  
        <fieldset class="fieldsetCustom">
            <legend class="legendCustom">Vendita biglietti - 3 </legend>

            <div>
                <div class="m-4">
                    <lable>Nome spettacolo in programmazione: </lable>
                    <label class="float-right" value="<%=request.getParameter("idSpettacolo")%>" 
                           nome="idSpettacolo"><%=request.getParameter("nomeSpettacolo")%></label>
                </div>
                
                <% Calendar cal = Calendar.getInstance();
                String orario="";
                int ora = cal.get(Calendar.HOUR_OF_DAY);
                int minuti = cal.get(Calendar.MINUTE);
                        if ((ora >= 0) && (ora <= 9)) {
                            orario = "0" + ora;
                        } else {
                            orario += ora;
                        }
                        if (minuti>=0 && minuti<10) {
                            orario+=":0"+minuti;
                        } else {
                            orario += ":" + cal.get(Calendar.MINUTE);
                        }
                
                
                String data = cal.get(Calendar.YEAR) + "/";
                        int mese = cal.get(Calendar.MONTH);
                        if ((mese >= 0) && (mese < 9)) {
                            data = data + "0" + (mese + 1);
                        } else {
                            data = data + (mese + 1);
                        }
                        data = data + "/" + cal.get(Calendar.DAY_OF_MONTH);

                %>
                <div class="m-4">
                    <lable>Orario prenotazione: </lable>
                    <label class="float-right" nome="orarioPrenotazione" value="<%=orario%>"><%=orario%></label>
                </div>
                
                <div class="m-4">
                    <lable>Data prenotazione: </lable>
                    <label class="float-right" nome="dataPrenotazione" value="<%=data%>"><%=data%></label>
                </div>
                
                <!--STAMARE COL E RIG POSTI SELEZIONATI-->
                
                <div class="m-4">
                    <lable>N° sala: </lable>
                    <label class="float-right" name="idSala" value="<%=request.getParameter("idSala")%>"
                           ><%=request.getParameter("idSala")%></label>
                </div>
                
                <div class="m-4">
                    <lable>Importo da pagare: </lable>
                    <label class="float-right" name="importoTotale" value="<%=request.getParameter("importoTotale")%>"
                           ><%=request.getParameter("importoTotale")%></label>
                </div>
            </div>    
        
            <div class="float-right">
                    <input class="mr-3" type="submit" name="scelta" value="Acquista" onclick="acquistofunction()"/>  <!--I DUE BUTTON RICHIAMERANNO LA STESSA SERVLET
                        CHE IN BASE AL VALORE PASSATO REDIRECT BACK, SE ANNULLA, ALTRIMENTI REDIRECT AVANTI-->
                    <input type="submit" name="scelta" value="Prenota" onclick="prenotazioneFunction()"/>
            </div>
            
        </fieldset>                
</form>

<script>
    
    function acquistofunction() {
    var r = confirm("Sei sicuro di voler acquistare il biglietto?");
    if (r == true) {
        document.getElementById("myform").action="GestioneAcquistiCNT";
    }

}

function prenotazioneFunction() {
    var r = confirm("Sei sicuro di voler prenotare il biglietto?");
    if (r == true) {
        //document.getElementById("myform").action="PrenotazioneCNT";
    }
    
}
    
</script>

<jsp:include page="Footer.jsp" />