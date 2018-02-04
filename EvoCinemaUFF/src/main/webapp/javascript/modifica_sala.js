
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
    } else if(el.getAttribute("src") == "../images/poltrona_disponibile_v2.png"){
        el.setAttribute("src", "../images/poltrona_occupata_v2.png");
    } else {
        el.setAttribute("src", "../images/poltrona_non_disponibile_v2.png");
    }
}

function popolaForm(){
    var values="";
    var pre = "http://" + location.hostname + ":8080/EvoCinemaUFF/images/";
    for(i = 0; i < caselle.length; i++){
       var src = caselle[i].src; //getAttribute("src");
       switch(src){
           case (pre + "poltrona_non_disponibile_v2.png") :
               values += '0';
               break;
           case (pre + "poltrona_disponibile_v2.png") :
               values += '1';
               break;
           case (pre + "poltrona_occupata_v2.png") :
               values += '2';
               break;
           default :
               values += 'e';
               break;
       }
       input.setAttribute("value", values);
    }
}
