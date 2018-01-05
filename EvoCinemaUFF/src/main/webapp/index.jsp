<%-- 
    Document   : index
    Created on : 18-dic-2017, 12.39.44
    Author     : Michele
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
 
        <title>Evo Cinema</title>
        
        <!-- Bootstrap core CSS -->
        <link href="HomeSlider/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="HomeSlider/css/swiper.min.css" rel="stylesheet">
      
        <link href="HomeSlider/css/datepicker.css" rel="stylesheet">
        <link href="HomeSlider/css/datepicker.less" rel="stylesheet/less">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">   
          <link href="HomeSlider/css/mycss.css" rel="stylesheet">
        <style>
           
  
           
        </style>
        
    </head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
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

        <header>
            
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
                    <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
                    <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
                    <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
                    <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
                </div>       
            </div>
        </header>
        <div id="date">
            <div align="center">
                <strong><label for="from" >Dal</label></strong>
                <input type="text" id="from" name="from" style="width:100px">
                <strong><label for="to">al</label>
                <input type="text" id="to" name="to"style="width:100px">
               
            </div>
        </div>
        
        

        <!-- Footer -->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
            </div>
            <!-- /.container -->
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="HomeSlider/vendor/jquery/jquery.min.js"></script>
        <script src="HomeSlider/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="swiper.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        
        <script>
      var swiper = new Swiper('.swiper-container', {
          effect: 'coverflow',
          grabCursor: true,
          centeredSlides: true,
          slidesPerView: 'auto',
          coverflowEffect: {
              rotate: 50,
              stretch: 0,
              depth: 100,
              modifier: 1,
              slideShadows: true,
          },
          pagination: {
              el: '.swiper-pagination',
          },
      });
      </script>
   <script>
         $( function() {
    var dateFormat = "mm/dd/yy",
      from = $( "#from" )
        .datepicker({
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 1
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#to" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 1
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
  } );
  </script>
    </body>

</html>

