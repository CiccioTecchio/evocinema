<%-- 
    Document   : AggiungiOperatore
    Created on : 1-feb-2018, 18.38.30
    Author     : Michele
--%>

<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registra nuovo gestore</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>
    <script>
    $( function() {
        $( "#datepicker" ).datepicker();
        } );
    </script>
        <%            
            HttpSession s = request.getSession();
            
            Boolean aggiunto=null;
            aggiunto=(Boolean) s.getAttribute("accountRegistrato");
            s.removeAttribute("accountRegistrato");
            String a="";
            String b="";
            if(aggiunto==null){
                a="";b="";
            }else{
                if(aggiunto.equals(true)){
                    a+="Account registrato.";
                    b+="green";
                }else{
                    if(aggiunto.equals(false)){
                        a+="Impossibile registrare.";
                        b+="red";
                    }
                }} 
            
            UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");
            if (utente==null){
                response.sendRedirect("../Login.jsp");
            }else{    
        %>    
        
        <h1><font color="<%=b%>"><%=a%></font></h1>
        <div class="container">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Registra un nuovo account</div>
                <div class="card-body">
                    <form>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Nome </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="nomeRegistrazione" maxlength="50">
                                    <div class="form-input-validation is-error" id ="errorNome">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 2 caratteri!</p>
                                        </span>
                                    </div>


                                </div>

                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Cognome </label>
                                    <input class="form-control" type="text" id="cognomeRegistrazione" name="cognomeRegistrazione" maxlength="50">
                                    <div class="form-input-validation is-error" id ="errorCognome">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 2 caratteri!</p>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="exampleInputEmail1">Email</label>
                            <input class="form-control" type="text" id="emailRegistrazione" name="emailRegistrazione" maxlength="50">
                            <div class="form-input-validation is-error" id ="errorEmail2">
                                <span>
                                    <p class="error-psw">Questo campo non può contenere meno di 5 caratteri!</p>
                                </span>
                            </div>
                            <div class="form-input-validation is-error" id ="errorEmail3">
                                <span>
                                    <p class="error-psw">Attenzione! Inserisci una mail corretta.</p>
                                </span>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Ruolo</label>
                                    <select id= "ruoloRegistrazione" name="ruoloRegistrazione" class="form-control">
                                        <option value="gestore">Gestore</option>
                                        <option value="operatore">Operatore</option>
                                    </select>
                                </div>
                            </div>  
                        </div>
                        
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Sesso</label>
                                    <select id= "sessoRegistrazione" name="sessoRegistrazione" class="form-control">
                                        <option value="maschio">Maschio</option>
                                        <option value="femmina">Femmina</option>
                                    </select>
                                </div>
                                
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Data di nascita</label>
                                    <input class="form-control" name="dataRegistrazione" type="text" id="datepicker">
                                   <!-- <input  id="dataNascitaRegistrazione" type="date"  >-->
                                    <div class="form-input-validation is-error" id ="errorDataNascita">
                                        <span>
                                            <p class="error-psw">Devi inserire la data di nascita.</p>
                                        </span>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputEmail1">Indirizzo</label>
                            <input class="form-control" type="text" id="indirizzoRegistrazione" name="indirizzoRegistrazione" maxlength="50">
                            <div class="form-input-validation is-error" id ="errorIndirizzo">
                                <span>
                                    <p class="error-psw">Questo campo non può contenere meno di 2 caratteri.</p>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Città</label>
                                    <input class="form-control" type="text" id="cittaRegistrazione" name="cittaRegistrazione" maxlength="50">
                                    <div class="form-input-validation is-error" id ="errorCitta">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 2 caratteri.</p>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Cellulare</label>
                                    <input class="form-control" type="tel" id="cellulareRegistrazione" name="cellulareRegistrazione" maxlength="10"onkeypress="onlyNumbers(event)" >
                                    <div class="form-input-validation is-error" id ="errorCellulare">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 9 caratteri.</p>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputName">Username</Label>
                            <input class="form-control" type="text" id="userRegistrazione" name="userRegistrazione" maxlength="50">
                            <div class="form-input-validation is-error" id ="errorUsername">
                                <span>
                                    <p class="error-psw">Questo campo non può contenere meno di 5 caratteri.</p>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input class="form-control" type="password" id="passwordRegistrazione" name="passwordRegistrazione" maxlength="32" >
                                    <div class="form-input-validation is-error" id ="errorPassword">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 8 caratteri!</p>
                                        </span>
                                    </div>
                                    <div class="form-input-validation is-error" id ="errorMatch">
                                        <span>
                                            <p class="error-psw">Attenzione! Le password che hai inserito non coincidono!</p>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleConfirmPassword">Conferma password</label>
                                    <input class="form-control" type="password" id="passwordRegistrazione2" name="passwordRegistrazione2" maxlength="32" >
                                    <div class="form-input-validation is-error" id ="errorPassword2">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 8 caratteri!</p>
                                        </span>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        <br>    
                        <input class="btn btn-primary btn-block" type="submit" name="bottoneRegistrazioneGestore" id="registrazioneGestore" value="Registra">
                        <div  id ="error">
                            <span>
                                <p class="error-psw2">Siamo spiacenti, esiste già un altro account con questo nome utente</p>
                            </span>
                        </div>
                        <center><h1><font color="<%=b%>"><%=a%></font></h1></center>
                        
                    </form>
                    
                    </div>
                </div>
            </div>
        </div>
        <div><br><br><br></div>
                    
                <% } %>    
    </body>
    <jsp:include page="Footer.jsp" />
</html>
