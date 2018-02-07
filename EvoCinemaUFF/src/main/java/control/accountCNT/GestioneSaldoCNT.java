
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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

/**
 * Servlet che gestisce la visione e la ricarica del saldo da parte di un
 * UtenteBase.
 *
 * @author Michele
 */
public class GestioneSaldoCNT extends HttpServlet {

    /**
     * Processa le richieste dei metodi HTTP <code>GET</code> e
     * <code>POST</code>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    /**
     * Gestisce il metodo HTTP <code>GET</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Gestisce il metodo HTTP <code>POST</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession s = request.getSession();
        UtenteBase utente = (UtenteBase) s.getAttribute("user");
        float ricarica = 0.0f;
        ricarica = Float.parseFloat(request.getParameter("ricaricaSaldo"));

        try {
            UtenteRegistratoDAO model = new UtenteRegistratoDAO();

            if (ricarica > 0) {
                float saldo = utente.getSaldo();
                System.out.println("saldo: " + saldo);
                System.out.println("importo da ricaricare: " + ricarica);
                saldo = saldo + ricarica;
                System.out.println("saldo dopo ricarica: " + saldo);
                utente.setSaldo(saldo);
                System.out.println("saldo utente: " + utente.getSaldo());
                model.updateUtenteRegistrato(utente);
            }

        } catch (NamingException | SQLException | ParseException ex) {
            Logger.getLogger(GestioneSaldoCNT.class.getName()).log(Level.SEVERE, null, ex);
        }

        String page = "/AccountVisualizzazioneSaldo.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

}
