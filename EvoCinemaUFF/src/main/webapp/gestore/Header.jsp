<%@page import="model.*"%>
<%@page contentType="text/html"%>
<!DOCTYPE html>

<html lang="it">
    <head>
        <meta charset="utf-16">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">


        <title><%= request.getAttribute("title")%></title>


        <%
            UtenteRegistrato u = (UtenteRegistrato) (request.getSession()).getAttribute("user");
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
        <link rel="stylesheet" href="../visualizzazione_programmazione.css">
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
        <script src="https://code.highcharts.com/modules/variable-pie.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>

    </head>

    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
        <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>


        <script src="../HomeSlider/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="../javascript/swiper.js"></script>


        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top " id="mainNav">
            <a class="navbar-brand" style="color: white" href="index.jsp" ><span></span><img src="../images/logo.png" class="img-fluid" style="max-height: 40px; max-width: 40px"> Evo Cinema </a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">

                <jsp:include page="NavGestore.jsp" />    
                
                <ul class="navbar-nav ml-auto">

                    <%
                        if (u == null) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="../Login.jsp">
                            <i class="fa fa-fw fa-sign-in" href="../Login.jsp"></i> Login </a>
                    </li>
                    <%
                    } else {
                    %>

                    <li class="nav-item dropdown mr-3" data-placement="right">
                        <div class="dropdown-menu " id="showtogglemenu" aria-labelledby="alertsDropdown">
                            <a class="dropdown-item" href="AccountVisualizzazioneAccount.jsp">
                                <span class="text-success">
                                    <i class="fa fa-user-circle"></i> Profilo
                                </span>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" data-toggle="modal" data-target="#exampleModal">
                                <span class="text-danger">
                                    <i class="fa fa-fw fa-sign-out"></i> Logout
                                </span>
                            </a>
                        </div>
                        <a class="nav-link dropdown-toggle mr-3" id="alertsDropdown"  href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <i class="fa fa-user-circle fa-lg"></i> &nbsp; <%= u.getNomeUtente()%>
                        </a>
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