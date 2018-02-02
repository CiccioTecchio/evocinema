<%-- 
    Document   : RicaricaSaldoOperatore
    Created on : 2-feb-2018, 14.22.21
    Author     : Michele
--%>

<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();
   UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");
   
   
   
    String ricaricaEffettuata="";
    ricaricaEffettuata+=(String) s.getAttribute("ricaricaEffettuata");
    String c="";
    
    if(ricaricaEffettuata==null){ 
        c="";
    }else{
        if(ricaricaEffettuata.equals("true")){
            c="";
        }else{
             if(ricaricaEffettuata.equals("false")){
            c="La ricarica non è andata a buon fine. Impossibile ricaricare l'importo inserito.";
            }
        }
    }
    
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Operatore - Ricarica saldo</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>
        <% 
            if (utente==null){
            response.sendRedirect("../Login.jsp");}
            else{
        %>
        
        
            
        <h1>Ricarica saldo</h1><br>
        
        Inserisci email utente da ricaricare:<br>
            
        <input type="text" id="ricaricaEmail" name="ricaricaEmail" ><br><br>
        
        
        Inserisci importo da ricaricare:<br>
            
        <input type="text" id="ricaricaSaldo" name="ricaricaSaldo"><br>
        <p></p><br><br>
            
        
        <button onclick="confermaRicarica()" class="btn btn-primary btn-block" type="sumbit">Ricarica</button><br>
        <p><font color="red"><%=c%></font></p><br>
        
                                
                                <script>
                                    function confermaRicarica() {
                                        var txt;
                                        if (confirm("Sei sicuro di voler ricaricare il saldo?")) {
                                            txt = "OK";
                                        } else {
                                            txt = "ANNULLA";
                                        }

                                    if(txt==="OK"){
                                        var ricarica= $('#ricaricaSaldo').val();
                                        var email = $('#ricaricaEmail').val();
                                         
                                        $.post('../RicaricaSaldoOperatoreCNT', {
                                         "ricaricaSaldo" : ricarica, "ricaricaEmail": email
                                        }, function() {
                                               location.reload();
                                        }).fail(function() {
                                                alert("Impossibile ricaricare.");
                                                
                                                location.reload();
                                                
                                                    
                                        });
                                    }
                                    
                                }
                                </script>
        
        
        <% }%>
        
    </body>
    <jsp:include page="Footer.jsp" />
</html>
