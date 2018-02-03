<%-- 
    Document   : ModificaSala.jsp
    Created on : 2-feb-2018, 17.19.34
    Author     : luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Modifica Sala"); %>
<jsp:include page="Header.jsp" />
<jsp:include page="ModificaSala"/>
<% char[][] matSala = (char[][]) request.getAttribute("matSala");
   String url, classe;
%>
<div class="container-fluid">
    <div class="row">
        <div class ="col">
            <div id="seats" class="card">
                <h5 class="card-header">Configurazione Sala</h5>
                <div class="card-body" id="seats">
                    <%
                    for(int i = 0; i < 30; i++){
                        %>
                        <div class="row">
                        <%
                        for(int j = 0; j < 30; j++){
                            switch(matSala[i][j]){
                                case '0' :
                                    url = "../images/poltrona_non_disponibile_v2.png";
                                    classe = "ms-non-selezionata";
                                    break;
                                case '1' :
                                    url = "../images/poltrona_disponibile_v2.png";
                                    classe = "ms-selezionata";
                                    break;                                    
                                case '2' :
                                    url = "../images/poltrona_occupata_v2.png";
                                    classe = "ms-danneggiata";
                                    break;
                                default :
                                    url = String.valueOf(matSala[i][j]);
                                    classe = "non funziona";
                                    break;
                            }
                            %>
                    <img data-row="<%=i%>" data-col="<%=j%>" class="ms-casella <%= classe %> cell" src="<%= url %>" />
                    <%
                        }
                    %> </div>    <%
                    }
                    %>
                </div>
                <div class="card-footer">
                    <button onclick="popolaForm();" form="form" class="btn btn-primary">Applica Modifiche</button>
                </div>
            </div>
        </div>
    </div>
</div>

<form id="form" action="ApplicaModificheSala" method="POST" hidden>
    <input id="form-input" type="text" name="posti" value=""/>
</form>
                
<script src="../javascript/modifica_sala.js"></script>
<jsp:include page="Footer.jsp"/>
