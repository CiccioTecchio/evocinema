<%-- 
    Document   : AccountVisualizzazioneRecensioni
    Created on : 30-gen-2018, 14.33.33
    Author     : Michele
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Recensione"%>
<%@page import="java.util.List"%>
<%@page import="model.UtenteBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page= "VisualizzaRecensioniCNT"/>
<%
    HttpSession s = request.getSession();
    UtenteBase utente = (UtenteBase) s.getAttribute("user");
    List<Recensione> recensioni = (List<Recensione>) s.getAttribute("recensioni");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account - Recensioni</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>
        <%
            if (utente == null) {
                response.sendRedirect("Login.jsp");
            } else {
        %> 


        <%
            if (recensioni == null) {

        %>       
        <div >

            <p class="m-0 text-center" > Non hai recensito nessun Film  </p>

        </div>
        <%        } else {
            if (recensioni.size() == 0) {
        %>    
        <div >

            <p class="m-0 text-center" > Non hai recensito nessun Film  </p>

        </div>
        <%
        } else {
        %>
        <div class="col-lg-8">
            <div class="card-header">
                <i class="fa fa-table"></i> Le mie Recensioni</div>



            <%
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
                for(Recensione r:recensioni){

            %>



            <%--
    <table table id="listaRecensioni" style="" class="table table-bordered" cellspacing="0" width="100%">
        <tr>
            <td>
                <p>Data immissione: <%=  sdf.format(r.getDataImmissione().getTime()) %></p>
            </td>
            <td>
                <p>Film: <%= r.getFilm().getTitolo() %></p>
            </td>
            <td>
                <p>Valutazione: <%= r.getValutazione() %></p>
            </td>
            <td>
                <p>Testo: <%= r.getTesto() %></p>
            </td>
        </tr><br>
    </table>
            --%> 
            <div class="card-body"> 
                <div class="container-fluid">
                    <hr class="mt-2"> 
                    <div class="row">
                        <div class="col-md-4">
                            <a href="#">
                                <img class="card-img-top img-fluid thumbnail ml-3" style="max-width: 150px ; max-height: 200px" src="../<%=r.getFilm().getLocandina()%>" >
                            </a>
                            <div class="card-body">
                                <h6 class="card-title mb-1" >
                                    <strong><%=r.getFilm().getTitolo()%></strong>
                                </h6>  
                            </div> 
                        </div>                     
                        <div class="col-md-8">
                            <div class="card-body small bg-faded">
                                <div class="media-body">
                                    <h6 class="mt-0 mb-1 valutazione"> <%= r.getValutazione()%>"
                                        
                                    </h6>
                                    <h7 class="card-body mr-2" >
                                     Recensito il: <%=  sdf.format(r.getDataImmissione().getTime())%></h7> 
                                     <h5 class="card-body" > <strong>RECENSIONE:</strong>
                                     </h5>
                                     <h6 class="card-body" style="font-family: monospace"><%= r.getTesto()%></h6>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <%
                }
            %>
            <%}
                    }
                }%>
        </div>
        <script>

            $('.valutazione').each(function (index,item) {
            $(item).rateYo({
            rating: $(item).text(),
            readOnly: true
            });
            });

        </script>
    </body>
    <jsp:include page="Footer.jsp" />
</html>
