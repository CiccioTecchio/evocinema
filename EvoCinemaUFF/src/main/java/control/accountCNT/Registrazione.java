/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

import database.UtenteBaseDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UtenteBase;
import model.UtenteRegistrato;

/**
 *
 * @author Giuseppe
 */
public class Registrazione {

    public class ControlloRegistrazione extends HttpServlet {

        private static final long serialVersionUID = 1L;

        UtenteBaseDAO model = null;

        public ControlloRegistrazione() {
            super();
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            try {
                model = new UtenteBaseDAO();
            } catch (NamingException | SQLException ex) {
                Logger.getLogger(Registrazione.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            HttpSession s = request.getSession();

            String username = (String) request.getParameter("userRegistrazione");

            try {

                Boolean flag = model.controllaUtente(username);
                if (flag) {
                    s.setAttribute("registrazioneImpossibile", true);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Registrazione.jsp");
                    dispatcher.forward(request, response);
                } else {
                    s.removeAttribute("registrazioneImpossibile");
                    UtenteBase u = new UtenteBase();

                    u.setNome((String) request.getParameter("nomeRegistrazione"));
                    u.setCognome((String) request.getParameter("cognomeRegistrazione"));

                    Calendar data = request.getParameter("giorno") + request.getParameter("mese") + request.getParameter("anno");

                    u.setDataNascita(data);

                    u.setCellulare((String) request.getParameter("nomeRegistrazione"));
                    u.setCittà((String) request.getParameter("nomeRegistrazione"));
                    u.setEmail((String) request.getParameter("nomeRegistrazione"));
                    u.setIndirizzo((String) request.getParameter("nomeRegistrazione"));
                    u.setSaldo(0);
                    u.setSesso(UtenteRegistrato.sesso.M);
                    
                    u.setNomeUtente((String) request.getParameter("userRegistrazione"));
                    u.setPassword((String) request.getParameter("passwordRegistrazione"));

                    s.setAttribute("utenteRegistrazione", u);
                    try {
                        model.aggiungiUtente(u);

                    } catch (SQLException | ParseException e) {
                        e.printStackTrace();
                    }

                    RequestDispatcher dispatcher = getServletContext()
                            .getRequestDispatcher("/pages/RegistrazioneEffettuata.jsp");
                    dispatcher.forward(request, response);
                }

            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }

}
