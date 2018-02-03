<%-- 
    Document   : Analytics
    Created on : 2-feb-2018, 15.03.03
    Author     : pietr
--%>

<%  request.setAttribute("title", "Analytics - Admin"); %>

<jsp:include page="Header.jsp"/>


<div class="card">
    <div class="p-5" id="container" style="height: 450px"></div>

    <div class="p-5" id="scatter" style="height: 450px"></div>
    
    <div class="p-5" id="container2" style="height: 450px"></div>

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
    
    aggiornaGraficoUtenti(scelta , passa);
    
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
                            x1: 0,
                            y1: 0,
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
<jsp:include page="Footer.jsp"/>
