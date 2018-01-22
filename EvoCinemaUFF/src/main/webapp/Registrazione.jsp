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
    </head>
    <body>

        <%
            HttpSession s = request.getSession();
        
            UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");

            Boolean registrazioneImpossibile = (Boolean) s.getAttribute("registrazioneImpossibile");
            if (utente == null) {
                if (registrazioneImpossibile != null) {
        %>
        <script>
        $(document).ready(function(){
            var x = document.getElementById("error");
           
                x.style.display = "block";
           });
</script>
        <%
        } else {
        %>
        <script>
        $(document).ready(function(){
            var x = document.getElementById("error");
           
                x.style.display = "none";
           });
           </script>
        <%
            }
        %>
        
        <div class="container">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Registra un account</div>
                <div class="card-body">
                    <form action="RegistrazioneCNT" method="post">

                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Nome</label>
                                    <input class="form-control" type="text"
                                           id="nomeRegistrazione" onchange='controlloCaratteri()' name="nomeRegistrazione">
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Cognome</label>
                                    <input class="form-control" id="exampleInputLastName" name="cognomeRegistrazione" type="text" aria-describedby="nameHelp" >
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="exampleInputEmail1">Email</label>
                            <input class="form-control" id="exampleInputEmail1" name="emailRegistrazione" type="email" aria-describedby="emailHelp" >
                        </div>

                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Sesso</label>
                                    <select name="sessoRegistrazione" class="form-control"
                                            >
                                        <option value="maschio">Maschio</option>
                                        <option value="femmina">Femmina</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Data di nascita</label>
                                    <input class="form-control" name="dataRegistrazione" id="exampleInputLastName" type="date" aria-describedby="nameHelp" >
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputEmail1">Indirizzo</label>
                            <input class="form-control" id="exampleInputEmail1" name="indirizzoRegistrazione" type="text" aria-describedby="emailHelp" >
                        </div>

                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Città</label>
                                    <input class="form-control" type="text"
                                           id="cittaRegistrazione" onchange='controlloCaratteri()' name="cittaRegistrazione" >
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Cellulare</label>
                                    <input class="form-control" name="cellulareRegistrazione" id="exampleInputLastName" type="text" aria-describedby="nameHelp" >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input class="form-control" name="passwordRegistrazione" id="exampleInputPassword1" type="password" >
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleConfirmPassword">Conferma password</label>
                                    <input class="form-control"name="password1Registrazione" id="exampleConfirmPassword" type="password" >
                                </div>
                            </div>
                        </div>
                        <br>    
                        <input type="submit" id="bottoneRegistrazione" name="bottoneRegistrazione" value="Conferma" class="btn btn-primary btn-block" href="index.jsp"></input>
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

        <%
        } else {
        %>
        <h2>Hai già fatto l'accesso con un account!</h2>
        <%
            }
        %>

    </body>
    <%@ include file="Footer.jsp"%>
</html>
