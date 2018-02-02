
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
   <script src="../javascript/registraGestore.js" type="text/javascript"></script>

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
    var arrayAffluenza=[]; //Analytics per vendita biglietti per ogni spettacolo
    var datiOperazioni=[],datiAcquisti=[],datiPrenotazioni=[]; //Analytics operazioni
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
                var i=0; 
                for (z=j; z < datiOperazioni.length; z++) { 
                    datiPrenotazioni[i]=datiOperazioni[z]; 
                    i++; 
                }     
                 
                //console.info(datiAcquisti);
                //console.info(datiPrenotazioni);
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
            [Date.UTC(datiAcquisti[0], datiAcquisti[1], 0), datiAcquisti[2]], 
            [Date.UTC(datiAcquisti[3], datiAcquisti[4], 0), datiAcquisti[5]], 
            [Date.UTC(datiAcquisti[6], datiAcquisti[7], 0), datiAcquisti[8]], 
            [Date.UTC(datiAcquisti[9], datiAcquisti[10], 0), datiAcquisti[11]], 
            [Date.UTC(datiAcquisti[12], datiAcquisti[13], 0), datiAcquisti[14]], 
            [Date.UTC(datiAcquisti[15], datiAcquisti[16], 0), datiAcquisti[17]], 
            [Date.UTC(datiAcquisti[18], datiAcquisti[19], 0), datiAcquisti[20]], 
            [Date.UTC(datiAcquisti[21], datiAcquisti[22], 0), datiAcquisti[23]], 
            [Date.UTC(datiAcquisti[24], datiAcquisti[25], 0), datiAcquisti[26]], 
            [Date.UTC(datiAcquisti[27], datiAcquisti[28], 0), datiAcquisti[29]], 
            [Date.UTC(datiAcquisti[30], datiAcquisti[31], 0), datiAcquisti[32]], 
            [Date.UTC(datiAcquisti[33], datiAcquisti[34], 0), datiAcquisti[35]], 
            [Date.UTC(datiAcquisti[36], datiAcquisti[37], 0), datiAcquisti[38]] 
        ] 
    }, 
    { 
            name: 'Prenotazioni', 
            data: [ 
            //DEVO PASSARE ANCHE IL MESE OLTRE AL VALORE PERCHE' NON SI PARTE SEMPRE DA GENNAIO 
            [Date.UTC(datiPrenotazioni[0], datiPrenotazioni[1], 0), datiPrenotazioni[2]], 
            [Date.UTC(datiPrenotazioni[3], datiPrenotazioni[4], 0), datiPrenotazioni[5]], 
            [Date.UTC(datiPrenotazioni[6], datiPrenotazioni[7], 0), datiPrenotazioni[8]], 
            [Date.UTC(datiPrenotazioni[9], datiPrenotazioni[10], 0), datiPrenotazioni[11]], 
            [Date.UTC(datiPrenotazioni[12], datiPrenotazioni[13], 0), datiPrenotazioni[14]], 
            [Date.UTC(datiPrenotazioni[15], datiPrenotazioni[16], 0), datiPrenotazioni[17]], 
            [Date.UTC(datiPrenotazioni[18], datiPrenotazioni[19], 0), datiPrenotazioni[20]], 
            [Date.UTC(datiPrenotazioni[21], datiPrenotazioni[22], 0), datiPrenotazioni[23]], 
            [Date.UTC(datiPrenotazioni[24], datiPrenotazioni[25], 0), datiPrenotazioni[26]], 
            [Date.UTC(datiPrenotazioni[27], datiPrenotazioni[28], 0), datiPrenotazioni[29]], 
            [Date.UTC(datiPrenotazioni[30], datiPrenotazioni[31], 0), datiPrenotazioni[32]], 
            [Date.UTC(datiPrenotazioni[33], datiPrenotazioni[34], 0), datiPrenotazioni[35]], 
            [Date.UTC(datiPrenotazioni[36], datiPrenotazioni[37], 0), datiPrenotazioni[38]]  
            
        ]
    }]
    

});
    }
    
    prelevaDatiAffluenzaFilm();
    function prelevaDatiAffluenzaFilm() {
      
        $.ajax({
            url: "${pageContext.request.contextPath}/AnalyticsFilmCNT",
            success: function(result){
             
              result =JSON.stringify(result).replace("{\"", "");
              result= result.replace("\":0}","");
                arrayAffluenza=result.split("_");
                //console.info(datiOperazioni);
                
                arrayAffluenza[0]=parseFloat(arrayAffluenza[0]);    
                for (var i=1; i < arrayAffluenza.length; i+=2) {
                    arrayAffluenza[i]=parseFloat(arrayAffluenza[i]);
                }    
                //console.info(arrayAffluenza);
                creaGraficoAffluenzaSpettacoli();
            }
            
            });
   }
    
    
    function creaGraficoAffluenzaSpettacoli(){
        Highcharts.chart('scatter', {
	    chart: {
	        type: 'column',
	      //  renderTo: 'scatter',
	    },
	    title: {
	        text: title='Analytics affluenza per film' //'World\'s largest cities per 2014'
	    },
            
	    xAxis: {
	        type: 'category',
	        min: 0,
	        max: arrayAffluenza[1],
                labels: {
	            rotation: -45,
	            style: {
	                fontSize: '10px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    },
	    yAxis: {
	        min: 0,
	        max: arrayAffluenza[0],
	        title: {
	            text: 'Numero biglietti venduti'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    series: [{
	        name: 'Biglietti venduti',
	        data: (function () {
                        // generate an array of random data
                        var data = [],
                        i;
                        for (i = 2; i <= arrayAffluenza.length; i +=2) {
                        data.push([
                            arrayAffluenza[i],arrayAffluenza[i+1]
                            ]);   
                        }
            return data;
        }()),
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            y: 10, // 10 pixels down from the top
	            style: {
	                fontSize: '10px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    }]
	});	
    
    }
    
    
</script>

</body>

</html>
