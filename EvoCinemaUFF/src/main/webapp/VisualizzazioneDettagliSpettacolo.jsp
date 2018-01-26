<%-- 
    Document   : VisualizzazioneDettagliSpettacolo
    Created on : 11-gen-2018, 11.17.16
    Author     : luca
--%>

<%@page import="control.programmazioneCNT.RappresentazioneSala"%>
<%@page import="control.programmazioneCNT.VisualizzazioneDettagliSpettacoloCNT"%>
<%@page import="model.Spettacolo"%>
<%@page import="model.Sala"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="VisualizzazioneDettagliSpettacoloCNT" />
<jsp:include page="Header.jsp" />

<%/*
     HttpSession s = request.getSession();
     String idSpettacolo = request.getParameter("idSpettacolo");
     if(idSpettacolo!=null)
            s.setAttribute("idSpettacolo", idSpettacolo);*/
    
     
    Spettacolo spettacolo = (Spettacolo) request.getAttribute("spettacolo");
    Sala sala = (Sala) request.getAttribute("sala");
    int offset = (int) request.getAttribute("offset");
    RappresentazioneSala rs = new RappresentazioneSala(spettacolo, sala, offset);
    int beginRiga = 0, endRiga = 0, beginColonna = 0, endColonna = 0;
    beginRiga = rs.getBeginRiga();
    beginColonna = rs.getBeginColonna();
    endRiga = rs.getEndRiga();
    endColonna = rs.getEndColonna();
    char[][] matSala = rs.getMatSala();
    char[] matricePostiSpettacolo = rs.getMatricePostiSpettacolo();
//    char[] matricePostiSala = (sala.getConfigPosti()).toCharArray();
//    char[] matricePostiSpettacolo = (spettacolo.getMatricePosti()).toCharArray();
%>
        <div class="container-fluid">
            <div id ="titolo" class="row">
                <div class ="col">Seleziona i Posti</div>
                <div class ="col">Riepilogo-Ordine</div>
            </div> 
            <div class="row">
                <div class ="col">
                    <div id="seats" class="container">
<%/*
    //Conversione dell'array in una matrice
    char[][] matSala = new char[30][30];
    int z = 0;
    for(int i = 0; i < 30; i++){
        for(int j = 0; i < 30; i++){
            matSala[i][j] = matricePostiSala[z];
            z++;
        }
    }
    */
   // 
    /*
    //Calcolo della prima riga dei posti da cui stampare
    boolean flag = true;
    for(int i = 0; flag && i < 30; i++){
        for(int j = 0; j < 30; j++){
            if(matSala[i][j] != '0'){
                beginRiga = i;
                flag = false;
            }    
        }   
    }
    */
    /*
    //Calcolo dell'ultima riga dei posti da stampare
    flag = true;
    for(int i = 29; flag && i >= 0; i--){
        for(int j = 0; j < 30; j++){
            if(matSala[i][j] != '0'){
                endRiga = i;
                flag = false;
            }
        }   
    }
    */
    /*
    //Calcolo della prima colonna dei posti da stampare
    flag = true;
    for(int j = 0; flag && j < 30; j++){
        for(int i = 0; i < 30; i++){
            if(matSala[i][j] != '0') {
                beginColonna = j;    
                flag = false;
            }
        }   
    }
    */
    /*
    //Calcolo dell'ultima colonna dei posti da stampare
    flag = true;
    for(int j = 29; flag && j >= 0; j--){
        for(int i = 0; i < 30; i++){
            if(matSala[i][j] != '0'){
                endColonna = j;
                flag = false;
            }
        }   
    }
*/
    
    //Stampa della sala e dei posti in base al relativo stato
    String url = null, classe = null;
    int y = 0;

    offset = 0;//offset * sala.getNumeroPosti() - 1;
    for(int i = beginRiga; i <= endRiga; i++){
        out.print("<div class=\"row\">");

        for(int j = beginColonna; j <= endColonna; j++){
            switch(matSala[i][j]){
                case '0' :
                    url = "images/trasparente.png";
                    break;
                case '2' :
                    url = "images/poltrona_non_disponibile_v2.png";
                    classe = "vds-posto-non-disponibile";
                    y++;
                    break;
                case '1' :
                    switch(matricePostiSpettacolo[offset + y++]){
                        case 'd':
                            url = "images/poltrona_disponibile_v2.png";
                            classe = "vds-posto-disponibile";
                            break;
                        case 'p':
                            url = "images/poltrona_prenotata_v2.png";
                            classe = "vds-posto-prenotato";
                            break;
                        case 'o':
                            url = "images/poltrona_occupata_v2.png";
                            classe = "vds-posto-occupato";
                            break;
                        default :
                            url = "xyz";
                    }
                    break;
                default :
                    url = String.valueOf(matSala[i][j]);
            }

            out.print("<img class=\"" + classe + " cell\" src=\"" + url + "\" />");

        }
 
        out.print("</div>");

    }
%>
                    </div>
                </div>
                <div class ="col">RiepilogoOrdine</div>


<jsp:include page="Footer.jsp" />