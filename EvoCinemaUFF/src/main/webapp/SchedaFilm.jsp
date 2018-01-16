<%-- 
    Document   : VisualizzaLibreria
    Created on : 13-gen-2018, 18.42.05
    Author     : GiuseppeDelGaudio
--%>
<%@page import="model.Film"%>
<%@page import="java.util.ArrayList"%>
<% request.setAttribute("title", "Scheda Film"); %>
<jsp:include page="Header.jsp" />
<%@ page import="model.FilmConValutazioneMedia"%>


<div class="content-wrapper">
    <div class="container-fluid">

    <%         
   
    String idFilmAr =  request.getParameter("film");
    ArrayList<FilmConValutazioneMedia> array = (ArrayList<FilmConValutazioneMedia>) request.getSession().getAttribute("listaFilmValutazione");
    
    if( array == null || idFilmAr == null || idFilmAr == " "){ %>
    

     <div class="content-wrapper">
    <div class="container-fluid">
      
        <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
        </div>
      
      
    
    <%
    
    }else{
    
    %>
     
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">My Dashboard</li>
      </ol>
    

    <%} %> 
    </div>
     </div>
<jsp:include page="Footer.jsp" />