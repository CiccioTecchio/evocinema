<%-- 
    Document   : FormAcquisto
    Created on : 18-gen-2018, 11.18.43
    Author     : pietr
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp" />
<!DOCTYPE html>
<body>        
    <form>  
        <fieldset class="fieldsetCustom">
            <legend class="legendCustom">Vendita biglietti - 1 </legend>

            <div>
                <div class="m-4">
                    <lable>Spettacolo in programmazione: </lable>
                    <label class="float-right">Thor</label>
                </div>
                
                <div class="m-4">
                    <lable>Orario prenotazione: </lable>
                    <label class="float-right">20:00</label>
                </div>
                
                <div class="m-4">
                    <lable>Data programmazione: </lable>
                    <label class="float-right">12/10/17</label>
                </div>
                
                <div class="m-4">
                    <lable>N° posti: </lable>
                    <label class="float-right">3</label>
                </div>
                
                <div class="m-4">
                    <lable>N° sala: </lable>
                    <label class="float-right">1</label>
                </div>
                
                <div class="m-4">
                    <lable>Importo da pagare: </lable>
                    <label class="float-right">7,50</label>
                </div>
            </div>    
        
            <div class="mt-5">
                <input class="mr-3" type="submit" name="Conferma" value="Indietro" />
                <div class="float-right">
                    <input class="mr-3" type="submit" name="Conferma" value="Conferma" />  <!--I DUE BUTTON RICHIAMERANNO LA STESSA SERVLET
                        CHE IN BASE AL VALORE PASSATO REDIRECT BACK, SE ANNULLA, ALTRIMENTI REDIRECT AVANTI-->
                    <input type="submit" name="Annulla" value="Annulla" />
                </div>
            </div>
        </fieldset>                
    <form>
</body>
<jsp:include page="Footer.jsp" />