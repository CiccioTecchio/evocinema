
</div>
</div>
<footer class="sticky-footer">
    <div class="container">
        <div class="text-center">
            <small>Copyright &copy; Evo Cinema 2017</small>
        </div>
    </div>
</footer>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fa fa-angle-up"></i>
</a>


<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Sicuro di voler eseguire il Logout?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Seleziona "Logout" se vuoi abbandonare la sessione corrente.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Annulla</button>
                <a class="btn btn-primary" href="../Logout">Logout</a>
            </div>
        </div>
    </div>

    
    <script src="../Template%20Sb%20Admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="../Template%20Sb%20Admin/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="../Template%20Sb%20Admin/js/sb-admin.min.js"></script>
    <script src="../javascript/login.js" type="text/javascript"></script>
    <script src="../javascript/registrazione.js" type="text/javascript"></script>
    <script src="../javascript/EliminaAccount.js" type="text/javascript"></script>
    <script src="../javascript/dettagliUtente.js" type="text/javascript"></script>
   

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
        $(function () {
            var dateFormat = "mm/dd/yy",
                    from = $("#from")
                    .datepicker({
                        defaultDate: "+1w",
                        changeMonth: true,
                        numberOfMonths: 1
                    })
                    .on("change", function () {
                        to.datepicker("option", "minDate", getDate(this));
                    }),
                    to = $("#to").datepicker({
                defaultDate: "+1w",
                changeMonth: true,
                numberOfMonths: 1
            })
                    .on("change", function () {
                        from.datepicker("option", "maxDate", getDate(this));
                    });

            function getDate(element) {
                var date;
                try {
                    date = $.datepicker.parseDate(dateFormat, element.value);
                } catch (error) {
                    date = null;
                }

                return date;
            }
        });
    </script>
    <script src="../Template%20Sb%20Admin/vendor/datatables/jquery.dataTables.js"></script>
    <script src="../Template%20Sb%20Admin/vendor/datatables/dataTables.bootstrap4.js"></script>
    <script src="../Template%20Sb%20Admin/js/sb-admin-datatables.min.js"></script>
</div>

<script>
    var datiOperazioni=[];
    prelevaDatiOperazioni();
    function prelevaDatiOperazioni() {
      
        $.ajax({
            url: "${pageContext.request.contextPath}/AnalyticsOperazioniCNT",
            success: function(result){
             
              result =JSON.stringify(result).replace("{\"", "");
              result= result.replace("\":0}","");
                datiOperazioni=result.split("_");
                console.info(datiOperazioni);
                 
                for (var i=0; i < datiOperazioni.length; i++) {
                    datiOperazioni[i]=parseFloat(datiOperazioni[i]);
                }    
                 
                creaGraficoOperazioni();
            }
            
            });
   
           
   }
    
    function creaGraficoOperazioni(){
    Highcharts.chart('container', {
    yAxis: {
        title: {
	            text: 'Num. operazioni'
	        }
    },
    xAxis: {
        type: 'datetime',
        title: {
	            text: 'Mesi'
	        }
    },
    title: {
        text:'Analitycs acquisti'
            },
    series: [{
            name: 'Score',
        data: [
            //DEVO PASSARE ANCHE IL MESE OLTRE AL VALORE PERCHE' NON SI PARTE SEMPRE DA GENNAIO
            [Date.UTC(datiOperazioni[0], datiOperazioni[1], 0), datiOperazioni[2]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[3], 0), datiOperazioni[4]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[5], 0), datiOperazioni[6]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[7], 0), datiOperazioni[8]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[9], 0), datiOperazioni[10]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[11], 0), datiOperazioni[12]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[13], 0), datiOperazioni[14]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[15], 0), datiOperazioni[16]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[17], 0), datiOperazioni[18]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[19], 0), datiOperazioni[20]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[21], 0), datiOperazioni[22]],
            [Date.UTC(datiOperazioni[0], datiOperazioni[23], 0), datiOperazioni[24]]
        ]
    }]
    

});
    }
    
</script>

</body>

</html>
