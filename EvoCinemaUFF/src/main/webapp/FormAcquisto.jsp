<%-- 
    Document   : FormAcquisto
    Created on : 18-gen-2018, 11.18.43
    Author     : pietr
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Sconto.tipologia"%>
<%@page import="model.Sconto.verificabile"%>
<%@page import="com.sun.istack.internal.logging.Logger"%>
<%@page import="model.UtenteRegistrato.ruolo"%>
<%@page import="model.UtenteRegistrato"%>
<%@page import="model.Sconto.disponibile"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Sconto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Finalizza operazione");%>
<jsp:include page="Header.jsp" />

<!DOCTYPE html>

<%
    UtenteRegistrato utente =(UtenteRegistrato) session.getAttribute("user");
    if ((utente == null)){
        response.sendRedirect("Login.jsp");
    }

%>
<jsp:include page= "GestioneAcquistiCNT"/>
<form id="myform" method="POST">  
    <div class="card">
        
        <%  UtenteRegistrato user=(UtenteRegistrato)session.getAttribute("user");
            if (utente!=null){
                if(user.getRuolo()==UtenteRegistrato.ruolo.OPERATORE){
                %>
                <!--SE E' UN OPERATORE INSERIRA' MANUALMENTE L'EMAIL DEL CLIENTE-->
                <div class="m-4">
                <label class="radio-inline">
                <input type="radio" name="pagamento" value="Contanti" onclick="VisibilitaEmail()" checked> Pagamento in contanti<br>
                </label>
                
                <label class="radio-inline">
                <input type="radio" name="pagamento" value="Virtuale" onclick="VisibilitaEmail()" > Pagamento online<br>
                </label>
                </div>
                <div id="divEmail" class="m-4">
                    <lable>Inserisci l'email del cliente </lable>
                    <input class="form-control" type="text" id="emailUtenteBase" name="emailUtenteBase" onblur = "verificaCorrettezzaEmail()">
                    <input type="hidden" id="correttezzaEmail" value="false">
                    
                </div>
                <%}
                else{
                %>
                <input type="hidden" id="correttezzaEmail" value="true"> 
                <!--SE E' L'UTENTE INTERESSATO CHE STA ACQUISTANDO ONLINE, RECUPERO L'EMAIL DALLA SESSIONE-->
                <input type="hidden" id="emailUtenteBase" value="<%=user.getEmail()%>" >
                <%}
        
           List<Spettacolo> spettacoli = (List<Spettacolo>) request.getAttribute("SPETTACOLI");
           Spettacolo spettacoloSelezionato = null;
           
           for(Spettacolo s: spettacoli)
           {
               if(s.getIdSpettacolo()==Integer.parseInt(request.getParameter("idSpettacolo")))
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
            
            <% Calendar cal = spettacoloSelezionato.getOraInizio();
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

                cal = spettacoloSelezionato.getOraFine();
                String orario2 = "";
                ora = cal.get(Calendar.HOUR_OF_DAY);
                minuti = cal.get(Calendar.MINUTE);
                if ((ora >= 0) && (ora <= 9)) {
                    orario2 = "0" + ora;
                } else {
                    orario2 += ora;
                }
                if (minuti >= 0 && minuti < 10) {
                    orario2 += ":0" + minuti;
                } else {
                    orario2 += ":" + cal.get(Calendar.MINUTE);
                }

                cal = spettacoloSelezionato.getDataInizio();
                String data = cal.get(Calendar.YEAR) + "/";
                int mese = cal.get(Calendar.MONTH);
                if ((mese >= 0) && (mese < 9)) {
                    data = data + "0" + (mese + 1);
                } else {
                    data = data + (mese + 1);
                }
                int giorno=cal.get(Calendar.DAY_OF_MONTH);
                if((giorno>0)&&(giorno<10))
                    data = data + "/" + "0" + cal.get(Calendar.DAY_OF_MONTH);
                else data = data + "/" + cal.get(Calendar.DAY_OF_MONTH);
        
                cal = spettacoloSelezionato.getDataFine();
                String data2 = cal.get(Calendar.YEAR) + "/";
                mese = cal.get(Calendar.MONTH);
                if ((mese >= 0) && (mese < 9)) {
                    data2 = data2 + "0" + (mese + 1);
                } else {
                    data2 = data2 + (mese + 1);
                }
                giorno=cal.get(Calendar.DAY_OF_MONTH);
                if((giorno>0)&&(giorno<10))
                    data2 = data2 + "/" + "0" + cal.get(Calendar.DAY_OF_MONTH);
                else data2 = data2 + "/" + cal.get(Calendar.DAY_OF_MONTH);
        
            %>
            
            <div class="m-4">
                <lable>Data spettacolo in programmazione: </lable>
                <lable class="float-right"><%=data%>-<%=data2%></lable>
            </div>
            
            
            <div class="m-4">
                <lable>Orario spettacolo in programmazione: </lable>
                <lable class="float-right"><%=orario%>-<%=orario2%></lable>
            </div>
            
            

            <input id="spettacoloScelto" name="spettacoloScelto" type="hidden" value="<%=spettacoloSelezionato.getIdSpettacolo()%>">
            
            <%  cal = Calendar.getInstance();
                 orario = "";
                 ora = cal.get(Calendar.HOUR_OF_DAY);
                 minuti = cal.get(Calendar.MINUTE);
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

                 data="";
                mese = cal.get(Calendar.MONTH);
                if ((mese >= 0) && (mese < 9)) {
                    data = data + "0" + (mese + 1);
                } else {
                    data = data + (mese + 1);
                }
                giorno=cal.get(Calendar.DAY_OF_MONTH);
                if((giorno>0)&&(giorno<10))
                    data = data + "/" + "0"+cal.get(Calendar.DAY_OF_MONTH);
                else data = data + "/" +cal.get(Calendar.DAY_OF_MONTH);
                data += "/"+cal.get(Calendar.YEAR);
            %>
            <div class="m-4">
                <lable>Orario corrente: </lable>
                <label class="float-right" nome="orarioPrenotazione" value="<%=orario%>"><%=orario%></label>
            </div>

            <div class="m-4">
                <lable>Data corrente: </lable>
                <label class="float-right" nome="dataOperazione" value="<%=data%>"><%=data%></label>
            </div>

            <!--STAMARE COL E RIG POSTI SELEZIONATI-->

            <div class="m-4">
                <lable>N° sala: </lable>
                <label class="float-right"> <%=spettacoloSelezionato.getIdSala()%> </label>
                <input type="hidden" class="float-right" name="idSala" value="<%=spettacoloSelezionato.getIdSala()%>">
            </div>
            
            
            <div class="m-4">    
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
                String posti=request.getParameter("posti");//posti=request.getParameter("posti");
                int numeroBiglietti=0;
                while(!posti.equals(""))
                {
                    numeroBiglietti++;
                    
                    String posto=posti.substring(0,posti.indexOf('-'));
                    posti=posti.substring(posti.indexOf('-')+1);
            
            %>
                <tr>
                    <td class="classePosti"><%=posto%></td>
                    <td class="classeSconti">
                        <select onchange="calcolaPrezzoTotale()">
                            <option value="41" selected >Nessuno</option>
                            <%   Calendar dataOggi = Calendar.getInstance();
                                    
                                 String giornoOggi="";
                                 switch(dataOggi.get(Calendar.DAY_OF_WEEK))
                                 {
                                    case 1:
                                        giornoOggi="sunday";
                                         break;
                                    case 2:
                                        giornoOggi="monday";
                                         break;
                                    case 3:
                                        giornoOggi="TUESDAY";
                                         break;
                                    case 4:
                                        giornoOggi="wednesday";
                                         break;
                                    case 5:
                                        giornoOggi="thursday";
                                         break;
                                    case 6:
                                        giornoOggi="friday";
                                         break;
                                    case 7:
                                        giornoOggi="SATURDAY";
                                         break;    
                                 }
                                 
                                for(Sconto s: (List<Sconto>) request.getAttribute("SCONTI"))
                                {
                                    if( (s.getDisponibile()==disponibile.TRUE) && (s.getIdSconto()!=41))
                                    {   //SE E' UN OPERATORE
                                        if((((s.getVerificabile()==verificabile.FALSE)||
                                                (s.getVerificabile()==verificabile.TRUE))
                                                &&(user.getRuolo()==ruolo.OPERATORE))
                                        //SE E' UN UTENTE BASE
                                            ||(((s.getVerificabile()==verificabile.TRUE)&&
                                                (user.getRuolo()==ruolo.UTENTE))
                                                &&(//CONDIZIONI TIPOLOGIA
                                                ((s.getTipologia()==tipologia.GIORNO_SETTIMANA)&&
                                                (giornoOggi.equalsIgnoreCase(s.getParametroTipologia())))||
                                                ((s.getTipologia()==tipologia.DATA)&&(data.equals(s.getParametroTipologia())))||
                                                ((s.getTipologia()==tipologia.FILM)&&( Integer.parseInt(s.getParametroTipologia()) == spettacoloSelezionato.getIdFilm() ))||
                                                ((s.getTipologia()==tipologia.SPETTACOLO)&&(spettacoloSelezionato.getIdSpettacolo()==Integer.parseInt(s.getParametroTipologia())))
                                                )// FINE CONDIZIONI TIPOLOGIA
                                                )) //FINE SE E' UN UTENTE BASE
                                                
                                                
                                        {
                                            
                                     //SCONTI VISUALIZZABILI DALL'UTENTE GIORNO DELLA SETTIMANA,
                                            //FILM, SPETTACOLO?
                                %>
                                <option value="<%=s.getIdSconto()%>">
                                    <%=s.getNome()%>
                                </option>
                               <%} }}%>
                        </select>
                        
                    </td> 
                    
                </tr>

            <%}%>   
            <input type="hidden" name="numeroBiglietti" value=" <%= numeroBiglietti%> " >    
            </tbody>
        </table>
    </div>
    </div>        
            <div class="m-4">
                
                <%
                    if(utente.getRuolo().equals(ruolo.UTENTE)){
                %>
                <label class="radio-inline">
                <input type="radio" name="operazione" value="Acquista" onclick="calcolaPrezzoTotale()" checked> Acquista<br>
                </label>
                
                <label class="radio-inline">
                <input type="radio" name="operazione" value="Prenota" onclick="calcolaPrezzoTotale()">Prenota<br>  
                </label>
                <%
                    }
                    if(utente.getRuolo().equals(ruolo.OPERATORE)){
                %>
                <label class="radio-inline">
                <input type="radio" name="operazione" value="Acquista" onclick="calcolaPrezzoTotale()" checked> Vendi biglietto<br>
                </label> 
                <%
                    }
                %>
            </div>
         
            
            <div class="m-4">
                <lable>Importo da pagare: </lable>
                <label id="prezzoTotale" class="float-right"></label>
                <input type="hidden" id="prezzoTotaleHidden" name="prezzoTotaleHidden" value="">
            </div>
        </div>    
            
            
       <div class="m-4">
           
           <input id="idProcedi" class="mr-3" type="button" value="Procedi" name="Procedi" onclick="return disponibilitaSaldo()" />  <!--I DUE BUTTON RICHIAMERANNO LA STESSA SERVLET
                CHE IN BASE AL VALORE PASSATO REDIRECT BACK, SE ANNULLA, ALTRIMENTI REDIRECT AVANTI-->
        </div>
    </div>             
   
            <input type="hidden" id="stringaPostiESconti" name="stringaPostiESconti" value="">        
            
</form>
<% } %>
<script>
    //richiamare la funzione la prima volta
    calcolaPrezzoTotale();   
    function calcolaPrezzoTotale(){
        var selects = document.getElementsByTagName('select');
        var stringaIdSconti="";
        for(var i=0;i<selects.length;i++)
        {
            stringaIdSconti=stringaIdSconti+selects[i].value+'-';
        }
        var xmht = new XMLHttpRequest();
        var spettacoloScelto=document.getElementById('spettacoloScelto').value;
        var operazione=document.querySelector('input[name="operazione"]:checked').value;
        
        xmht.onreadystatechange = function () {
            console.log("readyState: " + this.readyState);
            if (this.readyState == 4 && this.status == 200) {

                var result = this.responseText;
                
                //document.getElementById('prezzoTotale').innerHTML=result.substring(2,6);
                document.getElementById('prezzoTotale').innerHTML=result.substring(
                        2,result.lastIndexOf('"'))+' €';    
                document.getElementById('prezzoTotaleHidden').value=result.substring(
                        2,result.lastIndexOf('"'));    
            }
        };
        xmht.open("GET", "JSONCalcoloPrezzoTotale?stringaIdSconti="+stringaIdSconti+
                "&spettacoloScelto="+spettacoloScelto+"&operazione="+operazione, true);
        xmht.send();
    }
        
        
    

    function disponibilitaSaldo() {
        //alert("disponibilita saldo");
        var emailAcquirente=document.getElementById('emailUtenteBase').value;
        var importoTotale=document.getElementById("prezzoTotaleHidden").value;
        if(document.getElementById("correttezzaEmail").value==='false')
        {
            alert('Email non corretta');
            return;
        }
        var xmht = new XMLHttpRequest();
        xmht.onreadystatechange = function () {
            console.log("readyState: " + this.readyState);
            if (this.readyState == 4 && this.status == 200) {
                 var result = this.responseText;
                if (result.substring(2,result.lastIndexOf('"')) === 'Saldo insufficiente')
                    alert(result.substring(2,result.lastIndexOf('"')));
                if (result.substring(2,result.lastIndexOf('"')) === 'Ok')
                    {
                        //generaStringaPostiSconti();
    
                        var operazione=document.querySelector('input[name="operazione"]:checked').value;
                        if(operazione==="Prenota")
                            prenotazioneFunction();
                        if(operazione==="Acquista")    
                            acquistoFunction();
                    }
            }
        };
        xmht.open("GET", "JSONDisponibilitaSaldo?importoTotale="+importoTotale+"&emailAcquirente="+
                emailAcquirente, true);
        xmht.send();
    }

    function acquistoFunction() {
        //alert("Acquisto function");
        var r = confirm("Sei di voler procedere?");
        if (r == true) {
            generaStringaPostiSconti();
            document.getElementById("myform").action = "AcquistoBigliettoCNT";
            document.getElementById("myform").submit();
        }

    }

    function prenotazioneFunction() {
        var r = confirm("Sei sicuro di voler prenotare il biglietto?");
        if (r == true) {
            generaStringaPostiSconti();
            document.getElementById("myform").action = "PrenotazioneBigliettoCNT";
            document.getElementById("myform").submit();
        }

    }
    
    VisibilitaEmail();
    function VisibilitaEmail(){
        var pagamento=document.querySelector('input[name="pagamento"]:checked').value;
        if(pagamento=="Contanti"){
            document.getElementById("divEmail").style.visibility = 'hidden';
            document.getElementById("idProcedi").onclick = acquistoFunction;
        }else{
            document.getElementById("divEmail").style.visibility = 'visible';
            document.getElementById("idProcedi").onclick = disponibilitaSaldo;
        }
            
    }
    
    function verificaCorrettezzaEmail() {
        var emailAcquirente=document.getElementById('emailUtenteBase').value;
        var xmht = new XMLHttpRequest();
        xmht.onreadystatechange = function () {
            console.log("readyState: " + this.readyState);
            if (this.readyState == 4 && this.status == 200) {
                var result = this.responseText;
                
                if (result.substring(2,result.lastIndexOf('"')) === 'Email corretta')
                {
                    document.getElementById("correttezzaEmail").value="true";
                    alert(result.substring(2,result.lastIndexOf('"'))+"  "+document.getElementById("correttezzaEmail").value);
                }
                else
                {
                    document.getElementById("correttezzaEmail").value="false";
                    alert(result.substring(2,result.lastIndexOf('"'))+"  "+document.getElementById("correttezzaEmail").value);
                }
            }
        };
        xmht.open("GET", "JSONcorrettezzaEmail?emailAcquirente="+emailAcquirente, true);
        xmht.send();
    }
    
    
    function generaStringaPostiSconti(){
        var sconti=[];
        var posti =[];
        $(".classeSconti option:selected").each(function () {
            var x = $(this).val();
            sconti.push(x);
        });
        
        $(".classePosti").each(function () {
            var x = $(this).text();
            posti.push(x);
        });
        
        var stringaDaPassare = '';
        
        for (var i = 0; i < posti.length; i++) {
            stringaDaPassare = stringaDaPassare + posti[i]+"-"+sconti[i]+"-";
           
        }
        // alert(stringaDaPassare);
        
        document.getElementById("stringaPostiESconti").value=stringaDaPassare;
        //alert(document.getElementById("stringaPostiESconti").value);
    }

</script>

<jsp:include page="Footer.jsp" />