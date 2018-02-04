

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recupero password</title>
    </head>
    <jsp:include page="Header.jsp" />
    <body>
        <%  
            HttpSession s= request.getSession();
            Boolean emailNonValida = null;
            emailNonValida=(Boolean) s.getAttribute("emailNonValida");
            s.removeAttribute("emailNonValida");
            String a="";
            String color="";
            if(emailNonValida==null){
                
            }else{
                if(emailNonValida.equals(true)){
                    a="Inserisci un'email valida.";
                }else{
                    if(emailNonValida.equals(false)){
                        a="Controlla la tua email";
                        color="green";
                    }
                }
            }
         %>
        
        <h1>Recupero Password</h1>
        
        <br>Inserisci la tua email per ricevere una nuova password<br><br>
        
        
            <input type="text" name="emailRecupero" id="emailRecupero">
            <button onclick="recuperoPassword()" type="submit">Invia</button>
        
            <p><font color="<%=color%>"><%=a%></font></p>
        
        <script>
            function recuperoPassword() {
            var email= $('#emailRecupero').val();
            
            $.post('RecuperoPasswordCNT', {
                "emailRecupero":email
            }, function() {
            location.reload();
            alert("Controlla la tua email.");
            }).fail(function() {
            alert("Inserisci un'email valida.");
            });
            }
            
        </script>
        
    </body>
    <jsp:include page="Footer.jsp" />
</html>
