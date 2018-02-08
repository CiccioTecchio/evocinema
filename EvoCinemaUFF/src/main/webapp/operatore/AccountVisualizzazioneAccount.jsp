<%-- 
    Document   : VisualizzazioneAccount
    Created on : 23-gen-2018, 16.04.16
    Author     : Michele
--%>

<%@page import="model.UtenteRegistrato.sesso"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.UtenteBase"%>
<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession s = request.getSession();

    UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");

    if (utente == null) {
        response.sendRedirect("Login.jsp");
    } else {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dataN = sdf.format(utente.getDataNascita().getTime());
        String a = "";
        String b = "";
        if (utente.getSesso().equals(sesso.M)) {
            a = "selected";
        } else {
            b = "selected";
        }


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dettagli account</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>




        <div class="container" style="padding-top: 60px;">
            <h1 class="page-header">Modifica profilo</h1>
            <div class="row">
                <!-- left column -->
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="text-center">
                        <img src="../images/photoUser.png" class=" img-circle img-thumbnail" alt="avatar">


                    </div>
                </div>
                <!-- edit form column -->
                <div class="col-md-8 col-sm-6 col-xs-12 personal-info">


                    <form  class="form-horizontal" >
                        
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Email</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" name="modificaEmail" maxlength="50"  value="<%=utente.getEmail()%>" disabled="true" >
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Username</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" name="modificaUsername" maxlength="50"  value="<%=utente.getNomeUtente()%>" disabled="true" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Nome</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" id="nomeRegistrazioneVisualizza" name="nomeRegistrazioneVisualizza" maxlength="50"  value="<%=utente.getNome()%>" disabled="true">
                            </div>
                        </div>
                        <div class="form-input-validation is-error" id ="errorNomeVisualizza">
                            <span>
                                <p class="error-pswVisualizza">Questo campo non può contenere meno di 2 caratteri!</p>
                            </span>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Cognome</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" id="cognomeRegistrazioneVisualizza" name="cognomeRegistrazioneVisualizza" maxlength="50"  value="<%=utente.getCognome()%>" disabled="true">
                            </div>
                        </div>
                        <div class="form-input-validation is-error" id ="errorCognomeVisualizza">
                            <span>
                                <p class="error-pswVisualizza">Questo campo non può contenere meno di 2 caratteri!</p>
                            </span>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Data di nascita</label>
                            <div class="col-lg-10">
                                <input class="form-control" name="dataRegistrazioneVisualizza" type="text" id="datepicker" value="<%=dataN%>" disabled="true">
                            </div>
                        </div>
                        <div class="form-input-validation is-error" id ="errorDataNascitaVisualizza">
                            <span>
                                <p class="error-pswVisualizza">Questo campo non può contenere meno di 2 caratteri!</p>
                            </span>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Indirizzo</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" id="indirizzoRegistrazioneVisualizza" name="indirizzoRegistrazioneVisualizza" maxlength="50"value="<%=utente.getIndirizzo()%>" disabled="true">
                            </div>
                        </div>
                        <div class="form-input-validation is-error" id ="errorIndirizzoVisualizza">
                            <span>
                                <p class="error-pswVisualizza">Questo campo non può contenere meno di 2 caratteri!</p>
                            </span>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Città</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" id="cittaRegistrazioneVisualizza" name="cittaRegistrazioneVisualizza" maxlength="50" value="<%=utente.getCittà()%>" disabled="true">
                            </div>
                        </div>
                        <div class="form-input-validation is-error" id ="errorCittaVisualizza">
                            <span>
                                <p class="error-pswVisualizza">Questo campo non può contenere meno di 2 caratteri!</p>
                            </span>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Cellulare</label>
                            <div class="col-lg-10">
                                <input class="form-control" type="text" id="cellulareRegistrazioneVisualizza" name="cellulareRegistrazioneVisualizza" value="<%=utente.getCellulare()%>" maxlength="10" onkeypress="onlyNumbers(event)" disabled="true">
                            </div>
                        </div>
                        <div class="form-input-validation is-error" id ="errorCellulareVisualizza">

                            <span>
                                <p class="error-pswVisualizza">Questo campo non può contenere meno di 9 caratteri!</p>
                            </span>
                        </div>
                        <!--<div class="form-group">
                            <label class="col-lg-3 control-label">Email</label>
                            <div class="col-lg-10">

                                <input class="form-control" type="text" id="emailRegistrazioneVisualizza" name="emailRegistrazioneVisualizza" value="<%=utente.getEmail()%>" maxlength="50">
                            </div>
                            <div class="form-input-validation is-error" id ="errorEmail2Visualizza">
                                <span>
                                    <p class="error-pswVisualizza">Questo campo non può contenere meno di 5 caratteri!</p>
                                </span>
                            </div>
                            <div class="form-input-validation is-error" id ="errorEmail3Visualizza">
                                <span>
                                    <p class="error-pswVisualizza">Attenzione! Inserisci una mail corretta.</p>
                                </span>
                            </div>
                        </div>-->
                        <div class="form-group">
                            <label class="col-md-3 control-label">Password</label>
                            <div class="col-md-10">
                                <input class="form-control" type="password" id="passwordRegistrazioneVisualizza" name="passwordRegistrazioneVisualizza" maxlength="50" disabled="true" >
                            </div>
                        </div>

                        <div class="form-input-validation is-error" id ="errorPasswordVisualizza">
                            <span>
                                <p class="error-pswVisualizza">Questo campo non può contenere meno di 8 caratteri!</p>
                            </span>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Conferma password</label>
                            <div class="col-md-10">
                                <input class="form-control" type="password" id="passwordRegistrazione2Visualizza" name="passwordRegistrazione2Visualizza" maxlength="50" disabled="true"
                            </div>
                            </div>
                </div>
                        <div class="form-input-validation is-error" id ="errorPassword2Visualizza">
                            <span>
                                <p class="error-pswVisualizza">Questo campo non può contenere meno di 8 caratteri!</p>
                            </span>
                        </div>
                        <div class="form-input-validation is-error" id ="errorPasswordMatchVisualizza">
                            <span>
                                <p class="error-pswVisualizza">Le password non corrispondono!</p>
                            </span>
                        </div>
                        
                        <div class="form-group">
                             <div class="col-md-10">
                            <label class="col-md-3 control-label">Sesso</label>
                                    <select id= "sessoRegistrazioneVisualizza" name="sessoRegistrazioneVisualizza" class="form-control" disabled="true">
                                        <option value="maschio" <%=a%> >Maschio</option>
                                        <option value="femmina" <%=b%> >Femmina</option>
                                    </select>
                        </div>
                        </div>
                        
                        <br> 
                        <div class="col-md-10">
                            <div class="row">
                                <div class="col-lg-6">
 
                                    <div class="form-group">
 
 
                                        <input type="button" id="modifica" value="Abilita/Disabilita Modifche" class="btn btn-primary btn-block"/> 
 
                                    </div>
                                </div>
 
                                <div class="col-lg-6">
 
                                    <div class="form-group">
 
 
                                        <button  id="dettagliUtente" class="btn btn-primary btn-block" type="submit">Salva</button>
 
 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>


                    <% if (utente.getRuolo().equals(UtenteRegistrato.ruolo.UTENTE)) {%>
                    <div class="col-md-10">
                        <button onclick="confermaEliminazione()" class="btn btn-primary btn-block" name="<%=utente.getEmail()%>" id="<%=utente.getEmail()%>" type="sumbit">Cancella Account</button>
                    </div>
                </div>
            </div>
        </div> 
        <br><br><br>

        <script>
            function confermaEliminazione() {
            var txt;
            if (confirm("Sei sicuro di voler eliminare l'account?")) {
            txt = "OK";
            } else {
            txt = "ANNULLA";
            }
            
            if (txt === "OK"){
            $.post('../CancellazioneAccountCNT', {

            }, function() {
            location.reload();
            alert("Account eliminato.");
            }).fail(function() {
            alert("Impossibile eliminare.");
            });
            }
            }
        </script>
        <%}%>
    </div>



</div>
<% }%>
</body>
<jsp:include page="Footer.jsp" />
</html>
<script>
    $(function() {
    $("#datepicker").datepicker(){ minDate: - 20, maxDate: "+1M +10D" };
    });
</script>
<script>
    $("#modifica").click(function() {
        $("#nomeRegistrazioneVisualizza").attr('disabled', !$("#nomeRegistrazioneVisualizza").attr('disabled'));
        $("#cognomeRegistrazioneVisualizza").attr('disabled', !$("#cognomeRegistrazioneVisualizza").attr('disabled'));
        $("#datepicker").attr('disabled', !$("#datepicker").attr('disabled'));
        $("#indirizzoRegistrazioneVisualizza").attr('disabled', !$("#indirizzoRegistrazioneVisualizza").attr('disabled'));
        $("#cittaRegistrazioneVisualizza").attr('disabled', !$("#cittaRegistrazioneVisualizza").attr('disabled'));
        $("#cellulareRegistrazioneVisualizza").attr('disabled', !$("#cellulareRegistrazioneVisualizza").attr('disabled'));
        $("#passwordRegistrazioneVisualizza").attr('disabled', !$("#passwordRegistrazioneVisualizza").attr('disabled'));
        $("#passwordRegistrazione2Visualizza").attr('disabled', !$("#passwordRegistrazione2Visualizza").attr('disabled'));
        $("#sessoRegistrazioneVisualizza").attr('disabled', !$("#sessoRegistrazioneVisualizza").attr('disabled'));
    });
    </script>
