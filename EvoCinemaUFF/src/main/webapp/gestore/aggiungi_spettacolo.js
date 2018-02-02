document.querySelector("#select-film").onchange = setTitolo;
var tit_spettacolo = document.querySelector("#titolo-spettacolo");
var id_film = document.querySelector("#id-film");

function setTitolo(e){
    var el = e.target;
    var selectedValue = el.options[el.selectedIndex].value;
    var selectedID = el.options[el.selectedIndex].getAttribute("data-id");
    tit_spettacolo.value = selectedValue;
    id_film.value = selectedID;
}