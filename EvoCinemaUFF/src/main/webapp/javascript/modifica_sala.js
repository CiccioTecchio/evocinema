
var caselle = document.querySelectorAll(".ms-casella");
var input = document.getElementById("form-input");

for(i = 0; i < caselle.length; i++){
    caselle[i].onclick = handleClick;
    caselle[i].onmouseover = handleMouseOver;
}

function handleMouseOver(e){
    var el = e.target;
    el.style.cursor = "pointer";
}

function handleClick(e) {
    var el = e.target;
    if(el.getAttribute("src") == "../images/poltrona_non_disponibile_v2.png"){
        el.setAttribute("src", "../images/poltrona_disponibile_v2.png");
        el.setAttribute("data-stato", "selezionata");
    } else if(el.getAttribute("src") == "../images/poltrona_disponibile_v2.png"){
        el.setAttribute("src", "../images/poltrona_occupata_v2.png");
        el.setAttribute("data-stato", "danneggiata");
    } else {
        el.setAttribute("src", "../images/poltrona_non_disponibile_v2.png");
        el.setAttribute("data-stato", "non-selezionata");
    }
}

function popolaForm(){
    var values="";
    //var pre = location.protocol + "//" + location.hostname + location.port + "/EvoCinemaUFF/images/";
    for(i = 0; i < caselle.length; i++){
       var stato = caselle[i].getAttribute("data-stato");
       switch(stato){
           case "non-selezionata" :
               values += '0';
               break;
           case "selezionata" :
               values += '1';
               break;
           case "danneggiata" :
               values += '2';
               break;
           default :
               values += 'e';
               break;
       }
       input.setAttribute("value", values);
    }
}
