<%-- 
    Document   : Registrazione
    Created on : 18-gen-2018, 10.46.22
    Author     : Giuseppe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="Header.jsp"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrazione</title>
    </head>
    <body>
<script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
        <%           
            HttpSession s = request.getSession();

            UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");

            Boolean registrazioneImpossibile = (Boolean) s.getAttribute("registrazioneImpossibile");
            if (utente == null) {
                if (registrazioneImpossibile != null) {
        %>

        <%
        } else {
        %>

        <%
            }
        %>

        <div class="container">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Registra un account</div>
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
                                    <label for="exampleInputName">Sesso</label>
                                    <select id= "sessoRegistrazione" name="sessoRegistrazione" class="form-control">
                                        <option value="maschio">Maschio</option>
                                        <option value="femmina">Femmina</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Data di nascita</label>
                                    <input class="form-control" name="dataRegistrazione" type="date" id="dateForm">
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
                        <input class="btn btn-primary btn-block" type="submit" name="bottoneRegistrazione" id="registrazione" value="Registrazione">
                        <div  id ="error">
                            <span>
                                <p class="error-psw2">Siamo spiacenti, esiste già un altro account con questo nome utente</p>
                            </span>
                        </div>
                        
                    </form>
                    <div class="text-center">
                        <a class="d-block small mt-3" href="Login.jsp">Clicca qui per accedere</a>

                    </div>
                </div>
            </div>
        </div>
        <div><br><br><br></div>

        <%
        } else {
        %>
        <div class="container">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Registra un account</div>
                <div class="card-body">
                    <form>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Nome </label>
                                    <input class="form-control" type="text" id="nomeRegistrazione" name="nomeRegistrazione" maxlength="50" disabled="true">
                                    <div class="form-input-validation is-error" id ="errorNome">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 2 caratteri!</p>
                                        </span>
                                    </div>


                                </div>

                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Cognome </label>
                                    <input class="form-control" type="text" id="cognomeRegistrazione" name="cognomeRegistrazione" maxlength="50" disabled="true">
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
                            <input class="form-control" type="text" id="emailRegistrazione" name="emailRegistrazione" maxlength="50" disabled="true">
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
                                    <label for="exampleInputName">Sesso</label>
                                    <select id= "sessoRegistrazione" name="sessoRegistrazione" class="form-control" disabled="true">
                                        <option value="maschio">Maschio</option>
                                        <option value="femmina">Femmina</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Data di nascita</label>
                                    <input class="form-control" name="dataRegistrazione" type="text" id="datepicker" disabled="true">
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
                            <input class="form-control" type="text" id="indirizzoRegistrazione" name="indirizzoRegistrazione" maxlength="50" disabled="true">
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
                                    <input class="form-control" type="text" id="cittaRegistrazione" name="cittaRegistrazione" maxlength="50" disabled="true">
                                    <div class="form-input-validation is-error" id ="errorCitta">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 5 caratteri.</p>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Cellulare</label>
                                    <input class="form-control" type="tel" id="cellulareRegistrazione" name="cellulareRegistrazione" disabled="true" >
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
                            <input class="form-control" type="text" id="userRegistrazione" name="userRegistrazione" maxlength="50" disabled="true">
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
                                    <input class="form-control" type="password" id="passwordRegistrazione" name="passwordRegistrazione" maxlength="32" disabled="true">
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
                                    <input class="form-control" type="password" id="passwordRegistrazione2" name="passwordRegistrazione2" maxlength="32" disabled="true">
                                    <div class="form-input-validation is-error" id ="errorPassword2">
                                        <span>
                                            <p class="error-psw">Questo campo non può contenere meno di 8 caratteri!</p>
                                        </span>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        <br>    
                        <input class="btn btn-primary btn-block" type="submit" name="bottoneRegistrazione" id="registrazione" value="Registrazione" disabled="true">
                        <div  id ="error-AlreadyRegistred">
                            <span>
                                <p class="error-psw3">Devi Prima effettuare il logout!</p>
                            </span>
                        </div>
                        
                    </form>
                    <div class="text-center">
                        <a class="d-block small mt-3" href="Login.jsp">Clicca qui per accedere</a>

                    </div>
                </div>
            </div>
        </div>
        <div><br><br><br></div>
        <%
            }
        %>

    </body>
    <%@ include file="Footer.jsp"%>
</html>
