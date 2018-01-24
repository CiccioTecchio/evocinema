<%-- 
    Document   : InserisciFilm
    Created on : 23-gen-2018, 11.48.16
    Author     : francescodefeo
jsp inserisci film alt
--%>
<%--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>

<div class="container-fluid">
            <div class="card card-register mx-auto mt-3">
                <div class="card-header">Inserisci un nuovo Film</div>
                <div class="card-body">
                    <form action="InserisciFilmCNT" method="post">
                         <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Tipo </label>
                                    <select name="tipo" class="form-control"
                                            >
                                        <option value="FILM">Commedia</option>
                                        <option value="ALTRO">Spettacolo Teatrale</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Titolo</label>
                                    <input class="form-control" name="titolo" id="exampleInputLastName" type="text" aria-describedby="nameHelp" >
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Durata </label>
                                    <input class="form-control" type="text"
                                           id="nomeRegistrazione" onchange='controlloCaratteri()' name="nomeRegistrazione">
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Trama </label>
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
                                    <label for="exampleInputName">Genere</label>
                                    <select name="genere" class="form-control"
                                            >
                                        <option value="Commedia">Commedia</option>
                                        <option value="Fantascienza">Fantascienza</option>
                                        <option value="Thriller">Thriller</option>
                                        <option value="Drammatico">Drammatico</option>
                                        <option value="Horror">Horror</option>
                                        <option value="Storico">Storico</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Regia</label>
                                    <input class="form-control" name="regia" id="exampleInputLastName" type="text" aria-describedby="nameHelp" >
                                </div>
                            </div>
                        </div>
                         <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Censura</label>
                                    <select name="genere" class="form-control"
                                            >
                                        <option value="T">Tutti</option>
                                        <option value="VM16">Minori di 16 anni</option>
                                        <option value="VM14">Minori di 14 anni</option>
                  
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Data di uscita</label>
                                    <input class="form-control" name="dataUscita" id="exampleInputLastName" type="date" aria-describedby="nameHelp" >
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputEmail1">Cast</label>
                            <input class="form-control" id="exampleInputEmail1" name="cast" type="text" aria-describedby="emailHelp" >
                        </div>

                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                                    <label for="exampleInputName">Produzione</label>
                                    <input class="form-control" type="text"
                                           id="produzione" onchange='controlloCaratteri()' name="produzione" >
                                </div>
                                <div class="col-md-6">
                                    <label for="exampleInputLastName">Distribuzione</label>
                                    <input class="form-control" name="distribuzione" id="exampleInputLastName" type="text" aria-describedby="nameHelp" >
                                </div>
                            </div>
                        </div>
                          <div class="form-group">
                            <label for="exampleInputEmail1">Trama</label>
                            <input class="form-control" id="exampleInputEmail1" name="trama" type="text" aria-describedby="emailHelp" >
                        </div>
                        <br>    
                        <input type="submit" id="bottoneRegistrazione" name="bottoneRegistrazione" value="Conferma" class="btn btn-primary btn-block" href="index.jsp"></input>
                        <div  id ="error">
                            <span>
                                <p class="error-psw2">Siamo spiacenti, esiste già un altro film con queste specifiche</p>
                            </span>
                        </div>
                    </form>
                        
                    </div>
                </div>
            </div>
        </div>

          
          
          
<jsp:include page= "/Footer.jsp"/>

--%>