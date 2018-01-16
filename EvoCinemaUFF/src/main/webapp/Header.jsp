
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title><%= request.getAttribute("title") %></title>

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
        
        <!-- Custom fonts for this template-->
        <link href="Template%20Sb%20Admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- Page level plugin CSS-->
        <link href="Template%20Sb%20Admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="Template%20Sb%20Admin/css/sb-admin.css" rel="stylesheet">
       

        <link rel="icon" href="images/logoGIF.gif" type="image/gif" sizes="160x160">
                <script src="HomeSlider/vendor/jquery/jquery.min.js"></script>
        <script src="HomeSlider/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="javascript/swiper.js"></script> 
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script src="Template%20Sb%20Admin/vendor/jquery-easing/jquery.easing.min.js"></script>
         <script src="Template%20Sb%20Admin/vendor/datatables/jquery.dataTables.js"></script>
         <script src="Template%20Sb%20Admin/vendor/datatables/dataTables.bootstrap4.js"></script>
         <script src="Template%20Sb%20Admin/js/sb-admin.min.js"></script>
         <script src="Template%20Sb%20Admin/js/sb-admin-datatables.min.js"></script>

        <style>

        </style>

    </head>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
    <div class="container">
        <a class="navbar-brand" href="#">Evo Cinema</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Login
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
    <body>
