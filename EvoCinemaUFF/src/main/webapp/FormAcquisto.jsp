<%-- 
    Document   : FormAcquisto
    Created on : 18-gen-2018, 11.18.43
    Author     : pietr
--%>
<%@page import="model.Sconto.disponibile"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Sconto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Finalizza operazione");%>
<jsp:include page="Header.jsp" />
<jsp:include page= "GestioneAcquistiCNT"/>
<!DOCTYPE html>

<form id="myform" method="POST">  
    <fieldset class="fieldsetCustom">
        <legend class="legendCustom">Vendita biglietti - 3 </legend>
        <% 
           List<Spettacolo> spettacoli = (List<Spettacolo>) request.getAttribute("SPETTACOLI");
           Spettacolo spettacoloSelezionato = null;
           for(Spettacolo s: spettacoli)
           {
               if(s.getIdSpettacolo()==19)
                   //s.getIdSpettacolo()==(Integer.parseInt(request.getParameter("idSpetacolo")))
               {
                   spettacoloSelezionato = s;
                   break;
               }
           }
                %>
        <div>
            <div class="m-4">
                <lable>Nome spettacolo in programmazione: </lable>
                <lable class="float-right"><%=spettacoloSelezionato.getTitolo()%></lable>
            </div>

            <input id="spettacoloScelto" type="hidden" value="<%=spettacoloSelezionato.getIdSpettacolo()%>" id="spettacoloScelto">
            
            <% Calendar cal = Calendar.getInstance();
                String orario = "";
                int ora = cal.get(Calendar.HOUR_OF_DAY);
                int minuti = cal.get(Calendar.MINUTE);
                if ((ora >= 0) && (ora <= 9)) {
                    orario = "0" + ora;
                } else {
                    orario += ora;
                }
                if (minuti >= 0 && minuti < 10) {
                    orario += ":0" + minuti;
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
                <label class="float-right" name="idSala" value="<%=spettacoloSelezionato.getIdSala()%>"
                       ><%=spettacoloSelezionato.getIdSala()%></label>
            </div>
            
            
            
            <div class="table-responsive">
        <table id="listaFilm" style="border-" class="table table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    
                    <th>Id posto</th>
                    <th>Sconto</th>
                    
                    
                </tr>
            </thead>
            
            <tbody>                
                <%
                String posti="1-12-13-14-";//posti=request.getParameter("posti");
                int numeroBiglietti=0;
                while(!posti.equals(""))
                {
                    numeroBiglietti++;
                    
                    String posto=posti.substring(0,posti.indexOf('-'));
                    posti=posti.substring(posti.indexOf('-')+1);
            
            %>
                <tr>
                    <td><%=posto%></td>
                    <td>
                        <select>
                            <option value="0" >Nessuno</option>
                            <%for(Sconto s: (List<Sconto>) request.getAttribute("SCONTI"))
                                {
                                    if(s.getDisponibile()==disponibile.TRUE)
                                    {
                                %>
                                <option value="<%=s.getIdSconto()%>">
                                    <%=s.getNome()%>
                                </option>
                               <%} }%>
                        </select>
                        
                    </td> 
                    
                </tr>

            <%}%>   
                
            </tbody>
        </table>
    </div>
            <div class="m-4">
                <label class="radio-inline">
                <input type="radio" name="operazione" value="Acquista" onclick="calcolaPrezzoTotale()" checked> Acquista<br>
                </label>
                <label class="radio-inline">
                <input type="radio" name="operazione" value="Prenota" onclick="calcolaPrezzoTotale()">Prenota<br>  
                </label>
            </div>
         
            
            <div class="m-4">
                <lable>Importo da pagare: </lable>
                <label id="prezzoTotale" class="float-right" name="importoTotale" value="<%=(numeroBiglietti*spettacoloSelezionato.getPrezzo())%>"
                       ><%=(numeroBiglietti*spettacoloSelezionato.getPrezzo())%></label>
            </div>
        </div>    
            
            
       <div class="float-right">
           
            <input class="mr-3" type="submit" value="Procedi" name="Procedi" onclick="return disponibilitaSaldo()"/>  <!--I DUE BUTTON RICHIAMERANNO LA STESSA SERVLET
                CHE IN BASE AL VALORE PASSATO REDIRECT BACK, SE ANNULLA, ALTRIMENTI REDIRECT AVANTI-->
        </div>
                 

    </fieldset>                
</form>

<script>

    function calcolaPrezzoTotale(){
        var selects = document.getElementsByTagName('select');
        var stringaIdSconti="";
        alert('prima');
        for(var i=0;i<selects.length;i++)
        {
            stringaIdSconti=stringaIdSconti+selects[i].value+'-';
        }
        alert('idSconti '+stringaIdSconti);
        var xmht = new XMLHttpRequest();
        var spettacoloScelto=document.getElementById('spettacoloScelto').value;
        xmht.onreadystatechange = function () {
            console.log("readyState: " + this.readyState);
            if (this.readyState == 4 && this.status == 200) {

                var result = this.responseText;
                
                

            }
        };
        xmht.open("GET", "JSONCalcoloPrezzoTotale?stringaIdSconti="+stringaIdSconti+
                "&spettacoloScelto="+spettacoloScelto, true);
        xmht.send();
    }
        
        
    

    function disponibilitaSaldo() {

        var xmht = new XMLHttpRequest();
        xmht.onreadystatechange = function () {
            console.log("readyState: " + this.readyState);
            if (this.readyState == 4 && this.status == 200) {

                var result = this.responseText;
                if (result === 'Saldo insufficiente')
                    alert(result);
                if (result === 'Ok')
                    acquistoFunction();

            }
        };
        xmht.open("GET", "JSONDisponibilitaSaldo", true);
        xmht.send();
    }

    function acquistoFunction() {
        var r = confirm("Sei di voler procedere?");
        if (r == true) {
            document.getElementById("myform").action = "AcquistoBigliettoCNT";
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