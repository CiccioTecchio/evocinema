
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">


        <title><%= request.getAttribute("title")%></title>


        <%
            HttpSession r = request.getSession();
            UtenteRegistrato u = (UtenteRegistrato) r.getAttribute("user");
        %>


        <!-- Bootstrap core CSS -->
         <!-- Bootstrap core CSS -->
        <link href="../HomeSlider/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="../HomeSlider/css/swiper.min.css" rel="stylesheet">
        <link href="../HomeSlider/css/datepicker.css" rel="stylesheet">
        <link href="../HomeSlider/css/datepicker.less" rel="stylesheet/less">
        
        
        <link href="../HomeSlider/css/mycss.css" rel="stylesheet">
        <link href="../HomeSlider/css/errorPage.css" rel="stylesheet">
        <link rel="stylesheet" href="../visualizzazione_dettagli_spettacolo.css">
        <link rel="stylesheet" href="../Gestione_operazione_sala.css">

        <link rel="icon" href="../images/logo.png" type="image/png" />


        <!-- Custom fonts for this template-->
        <link href="../Template%20Sb%20Admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="../Template%20Sb%20Admin/vendor/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
        <!-- Page level plugin CSS-->
        <link href="../Template%20Sb%20Admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
        <!-- Custom styles for this template-->
        
        <link rel="icon" href="../images/logoGIF.gif" type="image/gif" sizes="160x160">
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">

         <link href="../Template%20Sb%20Admin/css/sb-admin.css" rel="stylesheet">
        <script src="../HomeSlider/vendor/jquery/jquery.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="../Template%20Sb%20Admin/js/sb-admin.min.js"></script>

        <!-- Libreria Rating  -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
        <!-- Sconti search film-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>


        <script src="https://code.highcharts.com/highcharts.js"></script>
        

    </head>

    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>


        <script src="../HomeSlider/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="../javascript/swiper.js"></script> 
      



        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top " id="mainNav">
            <a class="navbar-brand" style="color: white" href="index.jsp" ><span></span><img src="../images/logo.png" class="img-fluid" style="max-height: 40px; max-width: 50px">Evo Cinema ADMIN</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
                    <!-- inizio menù laterale -->
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Home">
                        <a class="nav-link" href="index.jsp">
                            <i class="fa fa-fw fa-home"></i>
                            <span class="nav-link-text">Home - Admin</span>
                        </a>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Libreria">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-book"></i>
                            <span class="nav-link-text">Libreria</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents">
                            <li>
                                <a href="inserisciFilmLibreria.jsp">Inserisci un Film in Libreria</a>
                            </li>
                            <li><a href="ModificaLibreria.jsp">Modifica Libreria</a>

                            </li>

                        </ul>
                    </li>
                     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Sconti">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents1" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-book"></i>
                            <span class="nav-link-text">Sconti</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents1">
                            <li>
                                <a href="VisualizzaSconti.jsp">Visualizza Sconti</a>
                            </li>
                            <li><a href="InserisciSconto.jsp">Inserisci Sconto</a>

                            </li>

                        </ul>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="OperatoriEGestori">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents2" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-book"></i>
                            <span class="nav-link-text">Operatori e Gestori</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents2">
                            <li>
                                <a href="../VisualizzaOperatoriCNT">Visualizza Operatori</a>
                            </li>
                            <li><a href="../VisualizzaGestoriCNT">Visualizza Gestori</a>

                            </li>

                        </ul>
                    </li>
                    



                    <!-- fine menù laterale -->       
                </ul>


                <ul class="navbar-nav sidenav-toggler">
                    <li class="nav-item">
                        <a class="nav-link text-center" id="sidenavToggler">
                            <i class="fa fa-fw fa-angle-left"></i>
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">

                    <%
                        if (u == null) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">
                            <i class="fa fa-fw fa-sign-in" href="Login.jsp"></i>Login</a>
                    </li>
                    <%
                    } else {
                    %> 

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle mr-lg-2" id="alertsDropdown"  href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <i class="fa fa-user-circle fa-lg"></i><%= u.getNomeUtente()%>
                        </a>
                        <div class="dropdown-menu " id="showtogglemenu" aria-labelledby="alertsDropdown">
                            <a class="dropdown-item" href="../AccountVisualizzazioneAccount.jsp">
                                <span class="text-success">
                                    <strong>
                                        <i class="fa fa-user-circle"></i>Profilo</strong>
                                </span>

                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" data-toggle="modal" data-target="#exampleModal">
                                <span class="text-danger">
                                    <strong>
                                        <i class="fa fa-fw fa-sign-out"></i>Logout</strong>
                                </span>
                            </a>
                        </div>
                    </li>

                    <%
                        }
                    %>
                </ul>
                <script>
                    $(document).ready(function () {
                        $("#alertsDropdown").click(function () {
                            $("#showtogglemenu").toggle();
                        });
                    });
                </script>

            </div>
        </nav>





        <div class="content-wrapper">
            <div class="container-fluid">




