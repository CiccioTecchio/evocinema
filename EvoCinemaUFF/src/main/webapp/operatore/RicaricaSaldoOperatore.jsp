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
    s.removeAttribute("ricaricaEffettuata");
    String c="";
    
    if(ricaricaEffettuata.equals("")){ 
        c="";
        System.out.println(c);
    }else{
        if(ricaricaEffettuata.equals("true")){
            c="Ricarica effettuata.";
            System.out.println(c);
        }else{
             if(ricaricaEffettuata.equals("false")){
                c="La ricarica non è andata a buon fine. Impossibile ricaricare l'importo inserito.";
                System.out.println(c);
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
        
       <div class="col-lg-8">
            <div class="card-header">
                <i class="fa fa-table"></i> Ricarica Saldo</div>
            <div class="card-body"> 
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6">
                            
        
                <label>Inserisci email utente da ricaricare:</label>
                    <div class="col-lg-10">
                        <input type="text" id="ricaricaEmail" name="ricaricaEmail" ><br><br>
                    </div>
        
        
        <div class="card-body">
                                <label>Seleziona l'importo da ricaricare</label>
                                <div class="btn-group bootstrap-select base__select select_value light open">
                                    <button class="btn btn-secondary btn-lg dropdown-toggle" style="width: 200px; " id="bottone1" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                         <span class="filter-option pull-left" >
                                                Nessun Importo
                                            </span>
                                    </button>
                                    <ul class="dropdown-menu"id="showtogglemenu" style="max-height: 246px; overflow-y: auto;" aria-labelledby="dropdownMenu">
                                        <li><a class="dropdown-item" style="text-align: center " >
                                            <span class="text-success" >
                                                15
                                            </span>
                                        </a></li>
                                        <li><a class="dropdown-item"  style="text-align: center " >
                                            <span class="text-success" >
                                                25
                                            </span>
                                        </a></li>
                                        <li><a class="dropdown-item"  style="text-align: center ">
                                            <span class="text-success" >
                                                30
                                            </span>
                                        </a></li>
                                        <li><a class="dropdown-item"  style="text-align: center ">
                                            <span class="text-success" >
                                                50
                                            </span>
                                        </a></li>
                                         <li><a class="dropdown-item"  style="text-align: center ">
                                            <span class="text-success" >
                                                100
                                            </span>
                                        </a></li>
                                    </ul>
                                </div>
                            </div>
            
        <div class="card-footer" style="background-color:#ffffff ">
        <button onclick="confermaRicarica()" class="btn btn-primary btn-block" type="sumbit">Ricarica</button><br>
        </div>
        <p><font color="red"><%=c%></font></p><br>
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
       
        
                                
                                <script>
                                    $(function(){

                                    $(".dropdown-menu li a").click(function(){

                                      $("#bottone1").text($(this).text());
                                      $("#bottone1").val($(this).text());

                                      });

                                    });
                                    
                                    function confermaRicarica() {
                                        var txt;
                                        var ricarica= $('#bottone1').val();
                                        if(ricarica==="Nessun Importo"){
                                           alert("Seleziona un importo");
                                           location.reload();
                                        }
                                        else if (confirm("Sei sicuro di voler ricaricare il saldo?")) {
                                            txt = "OK";
                                        } else {
                                            txt = "ANNULLA";
                                        }

                                    if(txt==="OK"){
                                        var ricarica= $('#bottone1').val();
                                        var email = $('#ricaricaEmail').val();
                                         
                                        $.post('../RicaricaSaldoOperatoreCNT', {
                                         "ricaricaSaldo" : ricarica, "ricaricaEmail": email
                                        }, function() {
                                            location.reload();
                                        }).fail(function() {
                                                alert("Impossibile ricaricare. Inserisci un'email valida.");
                                                
                                                location.reload();
                                                
                                                    
                                        });
                                    }
                                    
                                }
                                </script>
        
        
        <% }%>
        
    </body>
    <jsp:include page="Footer.jsp" />
</html>
