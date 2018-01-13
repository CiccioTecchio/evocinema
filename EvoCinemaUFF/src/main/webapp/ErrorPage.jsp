<%-- 
    Document   : ErrorPage
    Created on : 13-gen-2018, 11.06.48
    Author     : Antonio
--%>

<jsp:include page="Header.jsp" />

<% Exception errore = errore = (Exception) request.getAttribute("errore"); %>

<div class="container">
    <div class="text-center">
        <div class="Error">
            <h1 class="Error"></br>404</h1>
            <h2>Pagina non trovata!</h2>
            <p class="Error">Stack trace dell' errore: <%//errore.toString();%></p>
            <p><a href="index.jsp">Torna alla Home</a></p>
        </div>
    </div>
</div>
    
<jsp:include page="Footer.jsp" />
    

