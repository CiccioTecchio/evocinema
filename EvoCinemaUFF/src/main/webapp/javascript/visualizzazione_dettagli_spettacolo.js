
var posti_disponibili = document.querySelectorAll(".vds-posto-disponibile");

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
    if(el.getAttribute("src").localCompare("images/poltrona_disponibile_v2.png") == 0){
        el.setAttribute("src", "images/poltrona_selezionata_v2.png");    
    } else if(el.getAttribute("src").localCompare("images/poltrona_selezionata_v2.png") == 0){
        el.setAttribute("src", "images/poltrona_disponibile_v2.png");
    }
}

