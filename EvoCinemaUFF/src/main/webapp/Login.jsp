<%-- 
    Document   : Login
    Created on : 18-gen-2018, 10.41.05
    Author     : Giuseppe
--%>

<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("title", "Login"); %>
<jsp:include page="Header.jsp"/>
<!DOCTYPE html>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    </head>
    <script>
           $(document).ready(function(){
            var x = document.getElementById("error");
           
                x.style.display = "none";
           });
         
        function FormValidation(){
           var valore = document.getElementById('emailLogin').value;
           if(valore===""){
        
            document.getElementById('emailLogin').style.borderColor = "red";
            return false;
                }
                else{
                    document.getElementById('emailLogin').style.borderColor = "#ced4da";
                }
             var valore = document.getElementById('passwordLogin').value;
           if(valore===""){
        
            document.getElementById('passwordLogin').style.borderColor = "red";
            return false;
                }
                else{
                    document.getElementById('passwordLogin').style.borderColor = "#ced4da";
                }
            }
            
            </script>
    <body>
        <%
            HttpSession s = request.getSession();
            UtenteRegistrato utente;
            utente = (UtenteRegistrato) s.getAttribute("user");
            
            Boolean login = (Boolean) s.getAttribute("loginErrato");
            if (utente == null) {

                if (login != null) {
        %>
        <script>
       $(document).ready(function(){
            var x = document.getElementById("error");
                x.style.display = "block";

           });
    
            </script>
        <%
        } else {
        %>
       
        <%
            }
        %>
        

            <div  class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
        <form id="form" method="post" action="Login">
          <div  class="form-group">
            <label for="exampleInputEmail1">Email</label>
            <input onsubmit="return FormValidation();" onchange="return FormValidation();" class="form-control" id="emailLogin"  name="emailLogin" type="text" aria-describedby="emailHelp" >
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input onsubmit="return FormValidation();" onchange="return FormValidation();"  id="passwordLogin" name="passwordLogin" class="form-control" id="passwordLogin" type="password" >
          </div>
          <div class="form-group">
            <div class="form-check">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Ricorda Password</label>
            </div>
          </div>

            <input onclick=" return FormLogin();" class="btn btn-primary btn-block" type="submit" name="bottoneLogin" value="Login">
            <div class="form-input-validation is-error" id ="error">
              <span>
                  <p class="error-psw">Siamo spiacenti, la password non è corretta. Riprova.</p>
              </span>
          </div>
            
            
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="Registrazione.jsp">Registra un account</a>
          
        </div>
      </div>
    </div>
  </div>
        
        <%
        } else {
        %>
        <div  class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
        <form id="form" method="post" action="Login">
          <div  class="form-group">
            <label for="exampleInputEmail1">Email</label>
            <input onsubmit="return FormValidation();" onchange="return FormValidation();" class="form-control" id="emailLogin"  name="emailLogin" type="text" aria-describedby="emailHelp" placeholder="Enter email" disabled="">
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input onsubmit="return FormValidation();" onchange="return FormValidation();"  id="passwordLogin" name="passwordLogin" class="form-control" id="passwordLogin" type="password" placeholder="Password" disabled="">
          </div>
          <div class="form-group">
            <div class="form-check">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox" disabled=""> Ricorda Paasword</label>
            </div>
          </div>

            <input onclick=" return FormLogin();" class="btn btn-primary btn-block" type="submit" name="bottoneLogin" value="Login" disabled="">
            <div class="form-input-validation is-error" id ="error">
              <span>
                  <p class="error-psw">Siamo spiacenti, la password non è corretta. Riprova.</p>
              </span>
          </div>
             <div class="form-input-validation is-error" id ="error">
              <span>
                  <p class="error-psw">Siamo spiacenti, sei già loggato! Effettua il Logout</p>
              </span>
          </div>
            
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="Registrazione.jsp">Registra un account</a>
          
        </div>
      </div>
    </div>
  </div>
        
        <%
            }
        %>

    </body>
    <%@ include file="Footer.jsp"%>

</html>