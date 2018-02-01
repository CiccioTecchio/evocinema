document.querySelector("#select-film").onchange = setTitolo;
var target = document.querySelector("#titolo-spettacolo");

function setTitolo(e){
    var el = e.target;
    var selectedValue = el.options[el.selectedIndex].value;
    target.value = selectedValue;
}