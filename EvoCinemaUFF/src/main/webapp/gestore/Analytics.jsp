<%-- 
    Document   : Analytics
    Created on : 2-feb-2018, 15.03.03
    Author     : pietr
--%>

<%  request.setAttribute("title", "Analytics - Admin"); %>

<jsp:include page="Header.jsp"/>


<div class="card">
    <div class="p-5" id="container" style="height: 450px"></div>
    <div class="form-group text-center">
                <label> Visualizza </label>
                <select id="selectOperazioni" onchange="prelevaDatiOperazioni()" >
                    <option value="Operazioni"> Operazioni </option>
                    <option value="Acquisti" >Acquisti </option>
                    <option value="Prenotazioni">Prenotazioni</option>
                 </select>
    </div>
</div>

<div class="card">
    <div class="p-5" id="scatter" style="height: 450px"></div>
    <div class="form-group text-center">
                <label> Biglietti venduti maggiori di</label>
                <input type="number" min="0" value="0" id="sceltaAffluenzaFilm" 
                       style=" width: 5%" onchange="prelevaDatiAffluenzaFilm()">
                <label class="ml-2">Tutti</label>
                <input type="checkbox" id="tuttiBiglietti" checked="checked" onchange="  if( $('#tuttiBiglietti').prop('checked')) { $('#sceltaAffluenzaFilm').val('0'); $('#tuttiBiglietti').attr('checked','checked'); $('#sceltaAffluenzaFilm').change(); } " >
    </div>

</div>    

<div class="card">
    <div class="p-5" id="container2" style="height: 450px"></div>

    <div class="form-group text-center">
                <label> Visualizza mesi con incassi maggiori di</label>
                <input type="number" min="0" value="0" id="sceltaMinIncassi" 
                       style=" width: 5%" onchange="prelevaDatiIncassi()">
                <label class="ml-2">Tutti</label>
                <input type="checkbox" id="tuttiIncassi" checked="checked" onchange=" if( $('#tuttiIncassi').prop('checked')) { $('#sceltaMinIncassi').val('0'); $('#tuttiIncassi').attr('checked','checked'); $('#sceltaMinIncassi').change(); } " >
    </div>
</div>    

<div class="card">
    <div class="p-5" id="sconti" style="height: 450px"></div>
    <div class="form-group text-center">
                <label> Visualizza Sconti</label>
                <select id="selectSconti" onchange="prelevaDatiSconti()" >
                    <option value="Tutti">Tutti</option>
                    <option value="Disponibili"> Disponibili </option>
                    <option value="Non disponibili" >Non disponibili </option>
                 </select>
    </div>
</div>

<div class="card" >
    
    
    
    <div class="p-5" id="iscrizioniUtenti" style="height: 450px"></div>
    <div class="form-group text-center">
                <label for="censura"> Seleziona Eta </label>
                <select id="selectUtentiEta" onchange="cambioSelectUtenti()" >
                     <option value="maggiore">Maggiore di </option>
                    <option value="minore">Minore di</option>
                    <option value="tutti" selected> Tutti </option>
                 </select>
                
                
                <input type="number" min="1" disabled="true" onchange="insertEta()" style=" width: 5%" id="utentiEta" >
     </div>
</div>
<script>
    
// inizio script iscrizioni utenti

$( document ).ready(function() {
    
    aggiornaGraficoUtenti("tutti","");
    
});

function insertEta(){
    
    var param = $('#utentiEta').val();
    
    var passa ="&eta="+param;
    
    var scelta = $('#selectUtentiEta').val();
    
    if (! isNotNumber($('#utentiEta'))){ alert('passa'); aggiornaGraficoUtenti(scelta , passa);}
    
};

function cambioSelectUtenti(){
    
  var select = $('#selectUtentiEta').val();
  $('#utentiEta').val("");
    if( select === 'tutti' ){ 
            $('#utentiEta').attr("disabled",true);
            aggiornaGraficoUtenti(select , "");}
    else {
        
        $('#utentiEta').attr("disabled",false);
        
    }
    
};

function aggiornaGraficoUtenti(scelta , parametro){
   

$.getJSON(
    "${pageContext.request.contextPath}/gestore/IscrizioniUtenti?scelta="+scelta+parametro,
    function (data) {

        Highcharts.chart('iscrizioniUtenti', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'Iscrizioni Utenti'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                        'Clicca e trascina per ingrandire i contenuti' : ''
            },
            xAxis: {
                type: 'datetime'
            },    
            yAxis: {
                title: {
                    text: 'Numero di Iscrizioni'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            y1: 0,
                            x1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[6]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[7]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 4
                    },
                    lineWidth: 3,
                    states: {
                        hover: {
                            lineWidth: 3
                        }
                },
                    threshold: null
                }
            },

            series: [{
                type: 'area',
                name: 'Iscrizioni',
                data: data
            }]
        });
    }
);};
// fine script iscrizione utenti
</script>


<script>
    var arraySconti=[];//Analytics per freqeunza utilizzo sconti
    var arrayAffluenza=[]; //Analytics per vendita biglietti per ogni spettacolo
    var datiOperazioni=[],datiAcquisti=[],datiPrenotazioni=[]; //Analytics operazioni
    var datiIncassi=[];//Analytics incassi
    prelevaDatiOperazioni();
    function prelevaDatiOperazioni() {
        $.ajax({
            url: "${pageContext.request.contextPath}/AnalyticsOperazioniCNT?scelta="+
                    document.getElementById("selectOperazioni").value,
            success: function(result){
             
              result =JSON.stringify(result).replace("{\"", "");
              result= result.replace("\":0}","");
                datiOperazioni=result.split("_");
                for (var i=0; i < datiOperazioni.length; i++) {
                    datiOperazioni[i]=parseFloat(datiOperazioni[i]);
                }    
                
                datiAcquisti=[];    
                datiPrenotazioni=[];       
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
    chart: {
                zoomType: 'x'
            },        
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
    subtitle: {
        text: document.ontouchstart === undefined ?
                        'Clicca e trascina per ingrandire i contenuti' : ''
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
        if( ! isNotNumber($('#sceltaAffluenzaFilm'))){
            if($('#sceltaAffluenzaFilm').val() !== '0') { $('#tuttiBiglietti').removeAttr('checked');  $('#tuttiBiglietti').prop("checked", false); }
        $.ajax({
            url: "${pageContext.request.contextPath}/AnalyticsFilmCNT?numMinAffluenza="+
                    document.getElementById("sceltaAffluenzaFilm").value,
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
   }}
    
    
    function creaGraficoAffluenzaSpettacoli(){
        Highcharts.chart('scatter', {
            chart: {
                zoomType: 'x',
                type: 'column'
	    },
	    title: {
	        text: title='Analytics affluenza per film' 
	    },
            subtitle: {
                    text: document.ontouchstart === undefined ?
                                    'Clicca e trascina per ingrandire i contenuti' : ''
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
	        },
                title: {
	            text: 'Film'
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
    
    
    prelevaDatiIncassi();
    function prelevaDatiIncassi() {
      
        if( ! isNotNumber($('#sceltaMinIncassi'))){
        if($('#sceltaMinIncassi').val() !== '0') { $('#tuttiIncassi').removeAttr('checked');  $('#tuttiIncassi').prop("checked", false); };
            $.ajax({
           
            
            url: "${pageContext.request.contextPath}/AnalyticsIncasiCNT?sceltaMinIncassi="+
                    document.getElementById("sceltaMinIncassi").value,
            success: function(result){
             
              result =JSON.stringify(result).replace("{\"", "");
              result= result.replace("\":0}","");
                datiIncassi=result.split("_");
                //console.info(datiOperazioni);
                 
                for (var i=0; i < datiIncassi.length; i++) {
                    datiIncassi[i]=parseFloat(datiIncassi[i]);
                }    
                //console.info(datiIncassi);
                creaGraficoIncassi();
            }
            
            });
   }};
    
    function creaGraficoIncassi(){
    Highcharts.chart('container2', {
    chart: {
                zoomType: 'x'
            },
    yAxis: {
        title: {
	            text: 'Guadagno'
	        }
    },
    xAxis: {
        type: 'datetime',
        title: {
	            text: 'Mesi'
	        }
    },
    title: {
        text:'Analytics incassi' 
            },
    subtitle: {
        text: document.ontouchstart === undefined ?
                        'Clicca e trascina per ingrandire i contenuti' : ''
    },
    series: [{ 
            color: '#FF0000',
            name: 'Incassi', 
            data: [ 
            [Date.UTC(datiIncassi[0], datiIncassi[1], 0), datiIncassi[2]], 
            [Date.UTC(datiIncassi[3], datiIncassi[4], 0), datiIncassi[5]], 
            [Date.UTC(datiIncassi[6], datiIncassi[7], 0), datiIncassi[8]], 
            [Date.UTC(datiIncassi[9], datiIncassi[10], 0), datiIncassi[11]], 
            [Date.UTC(datiIncassi[12], datiIncassi[13], 0), datiIncassi[14]], 
            [Date.UTC(datiIncassi[15], datiIncassi[16], 0), datiIncassi[17]], 
            [Date.UTC(datiIncassi[18], datiIncassi[19], 0), datiIncassi[20]], 
            [Date.UTC(datiIncassi[21], datiIncassi[22], 0), datiIncassi[23]], 
            [Date.UTC(datiIncassi[24], datiIncassi[25], 0), datiIncassi[26]], 
            [Date.UTC(datiIncassi[27], datiIncassi[28], 0), datiIncassi[29]], 
            [Date.UTC(datiIncassi[30], datiIncassi[31], 0), datiIncassi[32]], 
            [Date.UTC(datiIncassi[33], datiIncassi[34], 0), datiIncassi[35]], 
            [Date.UTC(datiIncassi[36], datiIncassi[37], 0), datiIncassi[38]]  
        ]
    }]
    });
    }
    
prelevaDatiSconti();
function prelevaDatiSconti(){

        $.ajax({
            url: "${pageContext.request.contextPath}/AnalyticsScontiCNT?selectSconti="+
                    document.getElementById("selectSconti").value,
            success: function(result){
             
              result =JSON.stringify(result).replace("{\"", "");
              result= result.replace("\":0}","");
               arraySconti=result.split("*");
                var i=0;
                while( i < arraySconti.length) {
                    var ii=i+4;
                    arraySconti[i]=parseFloat(arraySconti[i]);
                    arraySconti[ii]=parseFloat(arraySconti[ii]);
                    i+=5;
                }
                //console.info(arraySconti);
                creaGraficoSconti();
            }
            
            });

}



creaGraficoSconti();
function creaGraficoSconti(){ 
   Highcharts.chart('sconti', {
        chart: {
            type: 'variablepie'
        },
        title: {
            text: 'Frequenza di utilizzo degli sconti'
        },
        tooltip: {
            headerFormat: '',
            pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' +
                    'IdSconto : <b>{point.idSconto}</b><br/>' +
                        'Disponibilità : <b>{point.disponibilita}</b><br/>' +
                'Tipologia : <b>{point.tipologia}</b><br/>'+
        'Frequenza utilizzo : <b>{point.z}</b><br/>' 
        },
        series: [{
            minPointSize: 10,
            innerSize: '14%',
            zMin: 0,
            name: 'sconti',
            data: (function () {
                        var data = [];
                        for(var i=0;i<arraySconti.length;i+=5){
                            var j=i;
                            //console.info(arraySconti[i]);
                            if(isNaN(arraySconti[i]))break;
                            data.push({
                            idSconto: arraySconti[j] ,
                            name:arraySconti[j+1],
                            disponibilita: arraySconti[j+2],     
                            tipologia:arraySconti[j+3],
                            y: 1,
                            z: arraySconti[j+4]
                            });
                        }
                    return data;
                }())
                    
        }] //fine serie
    });

}; 


// inizio utility 
 function isNotNumber( x ){
	
        var regExp =  /^[0-9]+$/;
	var value = x.val(); 
	
	
	if(  value === '' || ! value.match(regExp)  ){ if (! isNotFloatNumber(x)) return false; 
                                                                    else{
                                                                    x.css("background-color","#ff4d4d");   return true;}} 
	
	else{ 
            
           
           x.css("background-color","white");
            
            return false;} 
	
};

function isNotFloatNumber( x ){
	
        var regExp = '[-+]?([0-9]*.[0-9]+|[0-9]+)';
	var value = x.val(); 
	
	
	if(  value === '' || ! value.match(regExp)  ){ x.css("background-color","#ff4d4d");   return true;} 
	
	else{ 
            
           
           x.css("background-color","white");
            
            return false;} 
	
};
// fine utility

</script>


<jsp:include page="Footer.jsp"/>
