<%@page import="control.salaCNT.GeneraMenu"%>
<%@page import="model.Sala"%>
<%@page import="java.util.List"%>
<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Home">
                        <a class="nav-link" href="../index.jsp">
                            <i class="fa fa-fw fa-home"></i>
                            <span class="nav-link-text">Home</span>
                        </a>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Analytics">
                        <a class="nav-link" href="Analytics.jsp">
                            <i class="fa fa-fw fa-area-chart"></i>
                            <span class="nav-link-text">Analytics</span>
                        </a>
                    </li>                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Gestione programmazione">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-calendar"></i>
                            <span class="nav-link-text">Gestione programmazione</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents" >
 
                            <li>
                                <a href="../VisualizzazioneProgrammazione.jsp">Visualizzazione programmazione</a>
                            </li>
                            <li>
                                <a href="AggiungiSpettacolo.jsp">Inserisci spettacolo in programmazione</a>
                            </li>

                        </ul>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Gestione libreria">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents1" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-film"></i>
                            <span class="nav-link-text">Gestione libreria film</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents1" >
                            <li>
                                <a href="VisualizzaLibreria.jsp">Visualizza libreria</a>
                            </li>
                            <li>
                                <a href="inserisciFilmLibreria.jsp">Inserisci un film in libreria</a>
                            </li>
                            <li>
                                <a href="ModificaLibreria.jsp">Modifica libreria</a>
                            </li>

                        </ul>
                    </li>
                     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Gestione sconti">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents2" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-bars"></i>
                            <span class="nav-link-text">Sconti</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents2">
                            <li>
                                <a href="VisualizzaSconti.jsp">Visualizza sconti</a>
                            </li>
                            <li>
                                <a href="InserisciSconto.jsp">Inserisci sconto</a>
                            </li>

                        </ul>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="OperatoriEGestori">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents3" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-child"></i>
                            <span class="nav-link-text">Operatori e Gestori</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents3">
                            <li>
                                <a href="AggiungiOperatore.jsp">Registra nuovo Operatore o Gestore</a>
                            </li>
                            <li>
                                <a href="../VisualizzaOperatoriCNT">Operatori</a>
                            </li>
                            <li>
                                <a href="../VisualizzaGestoriCNT">Gestori</a>
                            </li>

                        </ul>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="GestioneSala">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents4" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-building"></i>
                            <span class="nav-link-text">Gestione Sale</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents4">
                            <%  //Boolean flag = (Boolean) request.getServletContext().getAttribute("salaFlag");
                                List<Sala> sale;// = (List<Sala>) request.getServletContext().getAttribute("salaList");
                                //if(flag == null || flag == true){
                                    sale = GeneraMenu.getSale();
                                /*    request.getServletContext().setAttribute("salaList", sale);
                                    request.getServletContext().setAttribute("salaFlag", new Boolean(false));
                                }*/
                                for(Sala s : sale){ %>
                            <li>
                                <a href="ModificaSala.jsp?id=<%=s.getIdSala()%>">Sala <%=s.getIdSala()%></a>
                            </li>
                            <% } %>
                            <li>
                                <a href="AggiuntaSala.jsp">Aggiungi nuova sala</a>
                            </li>
                        </ul>
                    </li>

                </ul>


                <ul class="navbar-nav sidenav-toggler">
                    <li class="nav-item">
                        <a class="nav-link text-center" id="sidenavToggler">
                            <i class="fa fa-fw fa-angle-left"></i>
                        </a>
                    </li>
                </ul>