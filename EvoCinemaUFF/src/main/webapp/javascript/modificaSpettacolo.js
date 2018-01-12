function controlloCaratteriSpettacolo() {
	var c = document.getElementById("costo").value;
	var campoC = false;

	if (c == "") {
		document.getElementById("spanCosto").innerHTML = "Il campo non pu&ograve; essere vuoto.";
		campoC = false;
	} else {
		document.getElementById("spanCosto").innerHTML = "";
		campoC = true;
	}

	if (campoC == true) {
		document.getElementById("bottoneSalva").removeAttribute("disabled");
	} else {
		document.getElementById("bottoneSalva").setAttribute("disabled", true);

	}

}
