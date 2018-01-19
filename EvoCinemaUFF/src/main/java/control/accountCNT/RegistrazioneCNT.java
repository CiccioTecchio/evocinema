/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

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
import model.*;
import database.*;
import java.text.ParseException;

/**
 *
 * @author Giuseppe
 */
public class RegistrazioneCNT {

    public class ControlloRegistrazione extends HttpServlet {

        private static final long serialVersionUID = 1L;

        UtenteRegistratoDAO model = null;

        public ControlloRegistrazione() {
            super();
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            try {
                model = new UtenteRegistratoDAO();
            } catch (NamingException | SQLException ex) {
                Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
            }

            HttpSession s = request.getSession();
            String username = (String) request.getParameter("userRegistrazione");
            
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
}
