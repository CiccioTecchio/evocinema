<%-- 
    Document   : ErrorPage
    Created on : 13-gen-2018, 11.06.48
    Author     : Antonio
--%>

<%@ page isErrorPage = "true" %>
<% request.setAttribute("title", "Errore"); %>
<jsp:include page="Header.jsp" />
<div class="card">
    <div class="card-header">
        Errore
    </div>
    <div class="card-body">
            <p>
            <H2>Siamo spiacenti, si è verificato un errore durante l'esecuzione:</H2>
            </p>
            <p>
                Causa dell'errore: <%= exception.getCause() %>
            </p>
            <p>
                Codice dell'errore: <%= response.getStatus() %>
            </p>
            <p>
                <a href="index.jsp">Torna alla Home</a>
            </p>
    </div>
</div>
    
<jsp:include page="Footer.jsp" />
    

