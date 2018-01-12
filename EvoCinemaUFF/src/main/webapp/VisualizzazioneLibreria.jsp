<%@page import="java.util.*,"%>
<%@page import="model.FilmConValutazioneMedia"%>
<% request.setAttribute("title","Libreria"); %>
<% ArrayList<FilmConValutazioneMedia> film=new ArrayList<>();
%>
<jsp:include page="Header.jsp"/>
        <form method="post" action="getAllFilmEval">
            <p>
                <span> Film</span><input class="">
            </p>
           
            <div class="list-group">
                
                     <% for (int i=0;i<film.size();i++){
                         %>
                                
                <div class="list-group-item">
                <div >
                    <img src=<%=film.get(i).getFilm().getLocandina()%> >
                </div>
                <div class="list-group-item-secondary">
                    <h3> <%=film.get(i).getFilm().getTitolo()%> 
                        <%=film.get(i).getValutazioneMedia()%>
                    </h3>
                    </div>
                </div>
                <% }
                %>
                
                
            </table>
            
<jsp:include page="Footer.jsp"/>