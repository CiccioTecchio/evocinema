 <!-- Footer -->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; Evo Cinema 2017</p>
            </div>
            <!-- /.container -->
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="HomeSlider/vendor/jquery/jquery.min.js"></script>
        <script src="HomeSlider/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="javascript/swiper.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<<<<<<< HEAD
                        <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

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
=======
      
>>>>>>> b32cedcdec513fc791c781606105a26cdd3aad2c
    </body>

</html>
