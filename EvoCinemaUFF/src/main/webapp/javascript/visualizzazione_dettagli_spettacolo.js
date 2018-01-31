
var posti_disponibili = document.querySelectorAll(".vds-posto-disponibile");
var seats = document.getElementById("seats");
var prezzo = seats.getAttribute("data-prezzo");
var ro = document.querySelector("#riepilogo-ordine .container");

setIdPosto(seats.getAttribute("data-num-posti"));

for(i = 0; i < posti_disponibili.length; i++){
    posti_disponibili[i].onclick = handleClick;
    posti_disponibili[i].onmouseover = handleMouseOver;
}

function handleMouseOver(e){
    var el = e.target;
    el.style.cursor = "pointer";
}

function handleClick(e) {
    var el = e.target;
    if(el.getAttribute("src") == "images/poltrona_disponibile_v2.png"){
        el.setAttribute("src", "images/poltrona_selezionata_v2.png");
        addToRiepilogo(e);
        calcolaTotale();
        setPosti();
    } else if(el.getAttribute("src") == "images/poltrona_selezionata_v2.png"){
        el.setAttribute("src", "images/poltrona_disponibile_v2.png");
        removeFromRiepilogo(e);
        calcolaTotale();
        setPosti();
    }
}

function setIdPosto(id){
    var posti = document.querySelectorAll("#seats img[class*='vds-posto-']");
    for(i = 0; i < id; i++){
        posti[i].setAttribute("data-pos", i);
    }
}

function addToRiepilogo(e){
    var el = e.target;
    var str = "<div class='row'><div class='col'><p data-pos='" + el.getAttribute("data-pos") + "'>Posto: " + el.getAttribute("data-pos") + 
                "</p></div><div class='col'><p class='prezzo'>Prezzo: " + prezzo + "&euro;</p></div></div>";
    str = str + ro.innerHTML;
    ro.innerHTML = str;
}

function removeFromRiepilogo(e){
    var el = e.target;
    var pos = el.getAttribute("data-pos");
    $("p[data-pos='"+pos+"'").parent().parent().remove();
}

function calcolaTotale(){
    $("#totale").html("Totale = " + $(".prezzo").length * prezzo + "&euro;");
}

function setPosti(){
    var posti = "";
    var ps = ro.querySelectorAll("p[data-pos]");
    for(i = 0; i < ps.length; i++){
        posti += (ps[i].getAttribute("data-pos") + "-");
    }
    $("#p").attr("value", posti);
}