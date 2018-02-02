

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
    var datiOperazioni=[],datiAcquisti=[],datiPrenotazioni=[];
    prelevaDatiOperazioni();
    function prelevaDatiOperazioni() {
      
        $.ajax({
            url: "${pageContext.request.contextPath}/AnalyticsOperazioniCNT",
            success: function(result){
             
              result =JSON.stringify(result).replace("{\"", "");
              result= result.replace("\":0}","");
                datiOperazioni=result.split("_");
                //console.info(datiOperazioni);
                 
                for (var i=0; i < datiOperazioni.length; i++) {
                    datiOperazioni[i]=parseFloat(datiOperazioni[i]);
                }    
                
                var j=0; 
                while(datiOperazioni[j]!='10000'){
                datiAcquisti[j]=datiOperazioni[j];
                j++;
                }
                j++;
                var i=1;
                datiPrenotazioni[0]=datiOperazioni[0];
                for (z=j; z < datiOperazioni.length; z++) {
                    datiPrenotazioni[i]=datiOperazioni[z];
                    i++;
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
        text:'Analytics operazioni'
            },
    series: [{
            color: '#00FF00',
            name: 'Acquisti',
        data: [
            //DEVO PASSARE ANCHE IL MESE OLTRE AL VALORE PERCHE' NON SI PARTE SEMPRE DA GENNAIO
            [Date.UTC(datiAcquisti[0], datiAcquisti[1], 0), datiAcquisti[2]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[3], 0), datiAcquisti[4]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[5], 0), datiAcquisti[6]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[7], 0), datiAcquisti[8]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[9], 0), datiAcquisti[10]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[11], 0), datiAcquisti[12]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[13], 0), datiAcquisti[14]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[15], 0), datiAcquisti[16]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[17], 0), datiAcquisti[18]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[19], 0), datiAcquisti[20]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[21], 0), datiAcquisti[22]],
            [Date.UTC(datiAcquisti[0], datiAcquisti[23], 0), datiAcquisti[24]]
        ]
    },
    {
            name: 'Prenotazioni',
        data: [
            //DEVO PASSARE ANCHE IL MESE OLTRE AL VALORE PERCHE' NON SI PARTE SEMPRE DA GENNAIO
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[1], 0), datiPrenotazioni[2]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[3], 0), datiPrenotazioni[4]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[5], 0), datiPrenotazioni[6]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[7], 0), datiPrenotazioni[8]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[9], 0), datiPrenotazioni[10]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[11], 0), datiPrenotazioni[12]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[13], 0), datiPrenotazioni[14]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[15], 0), datiPrenotazioni[16]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[17], 0), datiPrenotazioni[18]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[19], 0), datiPrenotazioni[20]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[21], 0), datiPrenotazioni[22]],
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[23], 0), datiPrenotazioni[24]]
        ]
    }]
    
    });
    }
    
</script>

</body>

</html>
