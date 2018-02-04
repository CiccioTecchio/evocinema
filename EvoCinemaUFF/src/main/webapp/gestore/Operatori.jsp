<%-- 
    Document   : VisualizzaOperatori
    Created on : 30-gen-2018, 15.54.07
    Author     : Michele
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.UtenteRegistrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession s = request.getSession();
    UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");
    List<UtenteRegistrato> operatori = (List<UtenteRegistrato>) s.getAttribute("operatori");
%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizza Operatori</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>
        <%
            if (utente == null) {
                response.sendRedirect("Login.jsp");
            } else {
        %> 

        <%
            if (operatori == null) {

        %>       
        <h1>Non sono presenti Operatori registrati.</h1>
        <%        } else {
            if (operatori.size() == 0) {
        %>    
        <h1>Non sono presenti Operatori registrati.</h1>
        <%
        } else {
        %>
        <div class="card-header">
            <i class="fa fa-table"></i> Operatori Registrati</div>
        <div class="card-body">
            <%
                String messaggioDelete = (String) request.getAttribute("messageDelete");
                System.out.println(messaggioDelete);
                  if (messaggioDelete != null) {%>


            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong><%= messaggioDelete%></strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <% } %>

            <div class="table-responsive">
                <table id="listaOperatori" style="border-" class="table table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr>

                            <th>Foto Profilo</th>
                            <th>Nome</th>
                            <th>Cognome</th>
                            <th>Email</th>
                            <th>Ruolo</th>
                            <th>Username</th>
                            <th></th>

                        </tr>
                    </thead>
                    <tbody>
                        <%
                            UtenteRegistrato ut = null;

                            for (int i = 0; i < operatori.size(); i++) {

                                ut = (UtenteRegistrato) operatori.get(i);

                        %>

                        <tr class="selezionato" style="cursor: pointer;">
                            <td><img class="img-fluid" src="../images/photoUser.png" width="100px"  class=" img-circle img-thumbnail" alt="avatar" ></td>
                            <td><%=  ut.getNome()%></td>
                            <td><%= ut.getCognome()%></td>
                            <td>
                                <%= ut.getEmail()%>
                            </td>
                            <td>
                                <%= ut.getRuolo().name()%>
                            </td>
                            <td>
                                <%= ut.getNomeUtente()%>
                            </td>
                            <td class="text-center">
                                <div class="text-center" >
                                    <form action="cancellazioneOperatore" method="GET" >
                                        <button class="btn btn-primary btn-md mb-2" > Elimina </button>
                                        <input type="hidden" name="position" value="<%= i%>" >
                                    </form>
                                </div>
                            </td>

                        </tr>

                        <% }%>

                    </tbody>
                </table>
                <%
                            }
                        }
                    }
                %>

                <script>


                    $(document).ready(function () {

                        $("#listaOperatori").DataTable({
                            "order": [[1, "asc"]],
                            "columns": [
                                {"orderable": false},
                                null,
                                null,
                                {"orderable": false},
                                null,
                                null,
                                {"orderable": false}]




                        });


                    });




                </script>







                </body>
                <jsp:include page="Footer.jsp" />
                </html>
