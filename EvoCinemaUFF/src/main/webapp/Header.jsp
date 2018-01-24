/* global show */


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
        <link href="HomeSlider/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="HomeSlider/css/swiper.min.css" rel="stylesheet">

        <link href="HomeSlider/css/datepicker.css" rel="stylesheet">
        <link href="HomeSlider/css/datepicker.less" rel="stylesheet/less">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">   
        <link href="HomeSlider/css/mycss.css" rel="stylesheet">
        <link href="HomeSlider/css/errorPage.css" rel="stylesheet">
        <link rel="stylesheet" href="visualizzazione_dettagli_spettacolo.css">
        <link rel="stylesheet" href="Gestione_operazione_sala.css">

        <link rel="icon" href="images/logo.png" type="image/png" />


        <!-- Custom fonts for this template-->
        <link href="Template%20Sb%20Admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="Template%20Sb%20Admin/vendor/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
        <!-- Page level plugin CSS-->
        <link href="Template%20Sb%20Admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
        <!-- Custom styles for this template-->
        <script src="HomeSlider/vendor/jquery/jquery.min.js"></script>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
 
    <!--    <script src="HomeSlider/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
               <script src="HomeSlider/vendor/bootstrap/js/bootstrap.min.js"></script> -->
        <link href="Template%20Sb%20Admin/css/sb-admin.css" rel="stylesheet">
      
        <script src="Template%20Sb%20Admin/js/sb-admin.min.js"></script>


        <link rel="icon" href="images/logoGIF.gif" type="image/gif" sizes="160x160">
      



        <!-- Libreria Rating  -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">



    </head>

    <body class="fixed-nav sticky-footer bg-dark" id="page-top">



        
        <script src="javascript/swiper.js"></script> 
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top " id="mainNav">
            <a class="navbar-brand" style="color: white" href="index.jsp" ><span></span><img src="images/logo.png" class="img-fluid" style="max-height: 40px; max-width: 50px">Evo Cinema</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
                    <!-- inizio menù laterale -->
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Home">
                        <a class="nav-link" href="index.jsp">
                            <i class="fa fa-fw fa-home"></i>
                            <span class="nav-link-text">Home</span>
                        </a>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Libreria">
                        <a class="nav-link" href="VisualizzaLibreria.jsp">
                            <i class="fa fa-fw fa-book"></i>
                            <span class="nav-link-text">Libreria</span>
                        </a>
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
                            <i class="fa fa-user-circle fa-lg"></i><%= u.getNomeUtente() %>
                        </a>
                        <div class="dropdown-menu " id="showtogglemenu" aria-labelledby="alertsDropdown">
                              <a class="dropdown-item" href="VisualizzaProfilo.jsp">
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
                    <%--
                    <li class="nav-item">
                        <a class="nav-link" >
                        <i class=""></i>
                        <%= u.getNomeUtente() %>
                        </a>
                    </li>
                        <%--<a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
                        --%>
                        
                           <%
                                }
                            %>
                </ul>
                  <script>
                   $(document).ready(function(){
                     $("#alertsDropdown").click(function(){
                        $("#showtogglemenu").toggle();
                    });
                    }); 
                  </script>
 
            </div>
        </nav>





        <div class="content-wrapper">
            <div class="container-fluid">




                <!--    Barra Duplicata ?? 
                
                        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top " id="mainNav">
                            <a class="navbar-brand" style="color: white" ><span></span><img src="images/logo.png" class="img-fluid" style="max-height: 40px; max-width: 50px">Evo Cinema</a>
                            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarResponsive">
                                <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
                <!-- inizio menù laterale -->
                <!-- <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Home">
                     <a class="nav-link" href="index.jsp">
                         <i class="fa fa-fw fa-home"></i>
                         <span class="nav-link-text">Home</span>
                     </a>
                 </li>
                 <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Libreria">
                     <a class="nav-link" href="VisualizzaLibreria.jsp">
                         <i class="fa fa-fw fa-book"></i>
                         <span class="nav-link-text">Libreria</span>
                     </a>
                 </li>




                <!-- fine menù laterale -->       
                <!-- </ul>
 
 
                 <ul class="navbar-nav sidenav-toggler">
                     <li class="nav-item">
                         <a class="nav-link text-center" id="sidenavToggler">
                             <i class="fa fa-fw fa-angle-left"></i>
                         </a>
                     </li>
                 </ul>
                 <ul class="navbar-nav ml-auto">
                     <li class="nav-item">
                         <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                <%
                    // if (u == null) {
                %>
                <i class="fa fa-fw fa-sign-in"></i>Login</a>
                <%                                //} else {
                %>
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
                <%                            //}
%>
            </li>
        </ul>
    </div>
</nav>  -->






