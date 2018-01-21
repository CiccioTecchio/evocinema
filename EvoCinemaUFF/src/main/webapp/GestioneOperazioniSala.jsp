<%-- 
    Document   : gestioneOperazioniSala
    Created on : 11-gen-2018, 11.53.26
    Author     : pietr
--%>

<%@page import="model.Sconto"%>
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
<script>
    function OnSubmitFormGestioneOperazioniSala()
    {
        if(document.pressed == 'Annulla')
        {
            alert("annulla");
            document.myform.action ="GestioneOperazioniSala.jsp";
        }
        else
        if(document.pressed == 'Conferma')
        {
            alert("conferma");
            document.myform.action ="GestioneAcquistiCNT";
        }
        return true;
    }

    function aggiornaOra(idSpettacolo){
    var select = document.getElementById("orarioSelezionato");
    
    var xmht = new XMLHttpRequest();
	xmht.onreadystatechange =function(){
                console.log("readyState: " + this.readyState);
		if(this.readyState==4&&this.status==200){
                    /*SVUOTIAMO L'ELEMENTO SELECT*/
                    select.options.length = 0;    
                    
                    /*PER OGNI OGGETTO JSON AGGIUNGERE L'OPTION RISPETTO AGLI ORARI DELLO SPETTACOLO*/        
		    var result=this.responseText;    
                    while(result.length>2){
                    var orario=result.substring(2, 7);
                    result=result.substring(8);
                    var option = document.createElement("option");
                    option.text = orario;
                    option.value = orario;
                    
                    select.add(option);
                    }
		}
	};
	xmht.open("GET","JSONOrariSpettacolo?idSpettacolo="+idSpettacolo,true);
	xmht.send();
    
    
    }
    
    function aggiornaData(orarioSpettacoloSelezionato){
        var idSpettacoloSelezionato = document.getElementById("spettacoloSelezionato").value;
    
        var xmht = new XMLHttpRequest();
            xmht.onreadystatechange =function(){
		console.log("readyState: " + this.readyState);
		if(this.readyState==4&&this.status==200){
                   
                   //MODIFICARE ELEMENTO CON ID "TO" NELLA PAGINA
                    var result=this.responseText;  
                    alert("data "+result.substring(2,10));
                    document.getElementById("to").value=result.substring(2,10);
                    
		}
	};
	xmht.open("GET","JSONDataSpettacolo?idSpettacolo="+
                idSpettacoloSelezionato+"&oraSpettacolo="+orarioSpettacoloSelezionato,true);
	xmht.send();
    
    }
</script>



<form name="" method="POST" onclick="return OnSubmitFormGestioneOperazioniSala()">
    <fieldset class="fieldsetCustom">


        <legend class="legendCustom">Vendita biglietti - 1 </legend>

        <div class="m-4">
            <lable>Spettacolo in programmazione</lable>
            <select id="spettacoloSelezionato" class="float-right" onchange="aggiornaOra(this.value)">
                <% for (Spettacolo s : (ArrayList<Spettacolo>)request.getAttribute("SPETTACOLI")) {
                        String titolo = s.getTitolo();
                        int id = s.getIdFilm();%>
                <option name="idSpettacolo" value="<%=id%>"><%=titolo%></option>  <!--COME VALORE DELL'OPTION SETTO L'ID DEL FILM COSI 
                                                           CE L'HO GIA' DISPONIBILE-->
                <%}%>
            </select>		
        </div>

        <div class="m-4">
            <lable>Orario programmazione</lable>
            <select id="orarioSelezionato" class="float-right" onchange="aggiornaData(this.value)">
               
            </select>
        </div>
        
        <div class="m-4">
            <lable>Data programmazione</lable>
            <!-- datapicker bootstrap-->
            <input type="text" class="float-right" id="to" name="data">
        </div> 
    
        
   

        <!-- NUMERO DI BIGLIETTI IN BASE AD I POSTI CHE VERRANNO OCCUPATI
        <div>
        <lable>N°biglietti</lable>
        <input class="float-right" type="number" value="1" name="quantity" min="1" max="5"> 
        </div>
        -->    

        <div class="m-4">
            <lable>Sconto</lable>
            <select class="float-right">
            <% for (Sconto sc : (ArrayList<Sconto>)request.getAttribute("SCONTI")) {
                        String titolo = sc.getNome();
                        int idSconto = sc.getIdSconto();
            %>    
                <option value="<%=idSconto%>"><%=titolo%></option>
            <%}%>
            </select>
        </div>
        

        <div class="float-right">
            <!--LA SERVLET PREMENDO SU CONFERMA AGGIUNGE NELLA SESSIONE UN OGGETTO CON QUESTI PARAMETRI-->
            <input class="mr-3" type="submit" name="Conferma" value="Conferma" 
                   onclick="document.pressed=this.value"/>  
            
            <input type="submit" name="Annulla" value="Annulla"
                   onclick="document.pressed=this.value"/>
        </div>
             
    </fieldset>
</form>
<jsp:include page="Footer.jsp" />