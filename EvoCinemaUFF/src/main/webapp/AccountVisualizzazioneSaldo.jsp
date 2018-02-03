<%-- 
    Document   : VisualizzazioneSaldo
    Created on : 23-gen-2018, 19.52.58
    Author     : Michele
--%>

<%@page import="model.UtenteBase"%>
<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();
    UtenteBase utente = (UtenteBase) s.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account - Saldo</title>

    </head>
    <jsp:include page="Header.jsp" />

    <body>
        <%
            if (utente == null) {
                response.sendRedirect("Login.jsp");
            } else {
        %>  
        <div class="col-lg-8">
            <div class="card-header">
                <i class="fa fa-table"></i> Ricarica Saldo</div>
            <div class="card-body"> 
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card-title">
                                <h3 class="card-title"> Il tuo saldo attuale è: <%=utente.getSaldo()%> </h3>
                            </div>
                            <div class="card-body">
                                <label>Seleziona l'importo da ricaricare</label>
                                <div class="btn-group bootstrap-select base__select select_value light open">
                                    <button class="btn btn-secondary btn-lg dropdown-toggle" style="width: 200px" id="bottone1" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                         <span class="filter-option pull-left" >
                                                Nessun Importo
                                            </span>
                                    </button>
                                    <ul class="dropdown-menu"id="showtogglemenu" style=" width: 200px; max-height: 246px; overflow-y: auto;" aria-labelledby="dropdownMenu">
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
                                <button onclick="confermaRicarica()" class="btn btn-primary btn-block" type="sumbit">Ricarica</button>
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
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
        if (confirm("Sei sicuro di voler ricaricare il saldo?")) {
            txt = "OK";
        } else {
            txt = "ANNULLA";
        }

        if (txt === "OK") {
            var ricarica = $('#bottone1').val();

            $.post('GestioneSaldoCNT', {
                "ricaricaSaldo": ricarica
            }, function () {

                alert("Ricarica effettuata.");
                location.reload();
            }).fail(function () {
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
