/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

/**
 *
 * @author Giuseppe
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import database.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UtenteRegistratoDAO model = null;
    UtenteRegistrato utente = null;

    public Login() {
        super();
    }

    public static String filter(String input) {
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                case '&':
                    filtered.append("&amp;");
                    break;
                default:
                    filtered.append(c);
                    break;
            }
        }
        return (filtered.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean utenteIsNull = true;
        HttpSession s = request.getSession();
        request.setAttribute("title", "Login");
        String email = filter(request.getParameter("emailLogin"));
        String password = filter(request.getParameter("passwordLogin"));

        try {
            model = new UtenteRegistratoDAO();
            utente = model.controllaLogin(email, password);
            
            if (utente == null) {
                utenteIsNull = false;
                s.setAttribute("user", utente);
            }
        } catch (NamingException | SQLException | ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!utenteIsNull) {
            response.getWriter().write("loginErrato");
            
        } else {
            s.setAttribute("user", utente);
            
            if (null == utente.getRuolo()) {
                response.getWriter().write("index.jsp");
            } else switch (utente.getRuolo()) {
                case GESTORE:
                    response.getWriter().write("gestore/index.jsp");
                    break;
                case OPERATORE:
                    response.getWriter().write("operatore/index.jsp");
                    break;
                case UTENTE:
                    response.getWriter().write("utentee/index.jsp");
                    break;
                default:
                    response.getWriter().write("index.jsp");
                    break;
            }
        }
    }

}
